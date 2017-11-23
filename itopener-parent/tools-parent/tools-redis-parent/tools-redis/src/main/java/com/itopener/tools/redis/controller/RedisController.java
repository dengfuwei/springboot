package com.itopener.tools.redis.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itopener.framework.ResultMap;
import com.itopener.tools.redis.config.RedisSerializerEnum;
import com.itopener.tools.redis.config.RedisTemplateMap;
import com.itopener.tools.redis.config.ToolsRedisConstants;
import com.itopener.tools.redis.vo.ClusterNode;

/**
 * @author fuwei.deng
 * @date 2017年9月30日 下午3:04:28
 * @version 1.0.0
 */
@RestController
@RequestMapping("redis")
public class RedisController {
	
	private final Logger logger = LoggerFactory.getLogger(RedisController.class);
	
	@Resource
	private RedisTemplateMap redisTemplateMap;
	
	@Resource
	private HttpServletRequest request;
	
	@SuppressWarnings("unchecked")
	public RedisTemplate<Object, Object> getCurrRedisTemplate() {
		Integer currSerializer = (Integer) request.getSession().getAttribute(ToolsRedisConstants.SESSION_CURR_SERIALIZER_KEY);
		currSerializer = currSerializer == null ? RedisSerializerEnum.DEFAULT.getCode() : currSerializer;
		return (RedisTemplate<Object, Object>) redisTemplateMap.getRedisTemplate(currSerializer);
	}
	
	@GetMapping("index")
	public ResultMap index() {
		JSONArray serializers = new JSONArray();
		for(RedisSerializerEnum item : RedisSerializerEnum.values()) {
			JSONObject serializer = new JSONObject();
			serializer.put("code", item.getCode());
			serializer.put("name", item.getName());
			serializers.add(serializer);
		}
		Integer currSerializer = (Integer) request.getSession().getAttribute(ToolsRedisConstants.SESSION_CURR_SERIALIZER_KEY);
		currSerializer = currSerializer == null ? RedisSerializerEnum.DEFAULT.getCode() : currSerializer;
		request.getSession().setAttribute(ToolsRedisConstants.SESSION_CURR_SERIALIZER_KEY, currSerializer);
		return ResultMap.buildSuccess().put("serializers", serializers).put("currSerializer", currSerializer);
	}
	
	@PutMapping("serializer/{code}")
	public ResultMap switchSerializer(@PathVariable int code) {
		if(!RedisSerializerEnum.exists(code)) {
			return ResultMap.buildFailed("不存在此序列化：" + code);
		}
		request.getSession().setAttribute(ToolsRedisConstants.SESSION_CURR_SERIALIZER_KEY, code);
		return ResultMap.buildSuccess();
	}
	
	@GetMapping("home")
	public ResultMap home(){
		RedisTemplate<Object, Object> redisTemplate = getCurrRedisTemplate();
		int keySize = redisTemplate.keys("*").size();
		int clientSize = redisTemplate.getClientList().size();
		
		Map<RedisClusterNode, Collection<RedisClusterNode>> masterSlaveMap = redisTemplate.getConnectionFactory().getClusterConnection().clusterGetMasterSlaveMap();
		int masterSize = masterSlaveMap.keySet().size();
		int slaveSize = 0;
		for(RedisClusterNode master : masterSlaveMap.keySet()){
			Collection<RedisClusterNode> slaves = masterSlaveMap.get(master);
			slaveSize += slaves.size();
		}
		
		return ResultMap.buildSuccess().put("keySize", keySize)
				.put("clientSize", clientSize)
				.put("masterSize", masterSize)
				.put("slaveSize", slaveSize);
	}
	
	@GetMapping("allkeys")
	public ResultMap allKeys(){
		RedisTemplate<Object, Object> redisTemplate = getCurrRedisTemplate();
		Set<Object> keys = redisTemplate.keys("*");
		return ResultMap.buildSuccess().put("keys", keys);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("keys")
	public ResultMap keys(String pattern){
		RedisTemplate<Object, Object> redisTemplate = getCurrRedisTemplate();
		
		RedisSerializer<Object> redisSerializer = (RedisSerializer<Object>) redisTemplate.getKeySerializer();
		byte[] rawKey = redisSerializer.serialize(pattern);
		Set<byte[]> rawKeys = redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
			public Set<byte[]> doInRedis(RedisConnection connection) {
				return connection.keys(rawKey);
			}
		}, true);
		
		Set<Object> keys = new HashSet<Object>();
		for(byte[] item : rawKeys) {
			String key = null;
			try {
				key = (String) redisTemplateMap.getRedisTemplate(RedisSerializerEnum.DEFAULT.getCode()).getKeySerializer().deserialize(item);
			} catch (SerializationException e) {
				key = (String) redisSerializer.deserialize(item);
			}
			keys.add(key);
		}
//		Set<Object> keys = (Set<Object>) SerializationUtils.deserialize(rawKeys, redisTemplateMap.getRedisTemplate(RedisSerializerEnum.DEFAULT.getCode()).getKeySerializer());
//		Set<Object> keys = redisTemplate.keys(pattern);
		return ResultMap.buildSuccess().put("keys", keys);
	}
	
	@GetMapping("info")
	public ResultMap info(){
		RedisTemplate<Object, Object> redisTemplate = getCurrRedisTemplate();
		Properties info = redisTemplate.getConnectionFactory().getClusterConnection().info();
		return ResultMap.buildSuccess().put("info", info);
	}
	
	@GetMapping("cluster")
	public ResultMap cluster(){
		RedisTemplate<Object, Object> redisTemplate = getCurrRedisTemplate();
		Map<RedisClusterNode, Collection<RedisClusterNode>> masterSlaveMap = redisTemplate.getConnectionFactory().getClusterConnection().clusterGetMasterSlaveMap();
		List<ClusterNode> cluster = new ArrayList<ClusterNode>();
		for(RedisClusterNode master : masterSlaveMap.keySet()){
			cluster.add(handle(master));
			Collection<RedisClusterNode> slaves = masterSlaveMap.get(master);
			for(RedisClusterNode slave : slaves){
				cluster.add(handle(slave));
			}
		}
		return ResultMap.buildSuccess().put("cluster", cluster);
	}
	
	private ClusterNode handle(RedisClusterNode redisClusterNode){
		ClusterNode clusterNode = new ClusterNode();
		clusterNode.setId(redisClusterNode.getId());
		clusterNode.setHostPort(redisClusterNode.getHost() + ":" + redisClusterNode.getPort());
		clusterNode.setFlags(redisClusterNode.getFlags());
		clusterNode.setLinkState(redisClusterNode.getLinkState());
		String masterId = redisClusterNode.getMasterId();
		clusterNode.setMasterId(StringUtils.isEmpty(masterId) ? "" : masterId);
		clusterNode.setName(redisClusterNode.getName());
		clusterNode.setType(redisClusterNode.getType());
		Set<Integer> slots = redisClusterNode.getSlotRange().getSlots();
		if(!CollectionUtils.isEmpty(slots)){
			int min = Collections.min(slots);
			int max = Collections.max(slots);
			clusterNode.setSlotRange(min + "-" + max);
		} else{
			clusterNode.setSlotRange("");
		}
		return clusterNode;
	}
	
	@GetMapping("clients")
	public ResultMap clients(){
		RedisTemplate<Object, Object> redisTemplate = getCurrRedisTemplate();
		List<RedisClientInfo> clients = redisTemplate.getClientList();
		return ResultMap.buildSuccess().put("clients", clients);
	}
	
	@PostMapping("save")
	public ResultMap save(String key, String hashKey, String value, long timeout){
		RedisTemplate<Object, Object> redisTemplate = getCurrRedisTemplate();
		if(!StringUtils.isEmpty(hashKey)) {
			redisTemplate.opsForHash().put(key, hashKey, value);
			redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		} else {
			redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
		}
		return ResultMap.buildSuccess();
	}
	
	@DeleteMapping("keys/{key}")
	public ResultMap delete(@PathVariable String key){
		RedisTemplate<Object, Object> redisTemplate = getCurrRedisTemplate();
		redisTemplate.delete(key);
		return ResultMap.buildSuccess();
	}
	
	@GetMapping("keys/{key}")
	public ResultMap query(@PathVariable String key){
		RedisTemplate<Object, Object> redisTemplate = getCurrRedisTemplate();
		long expire = redisTemplate.getExpire(key);
		DataType dataType = redisTemplate.type(key);
		Object value = null;
		try {
			if(dataType == DataType.LIST){
				value = redisTemplate.opsForList().range(key, 0, redisTemplate.opsForList().size(key));
			} else if(dataType == DataType.STRING){
				value = redisTemplate.boundValueOps(key).get(0, -1);
			} else if(dataType == DataType.ZSET){
				value = redisTemplate.opsForZSet().range(key, 1, redisTemplate.opsForZSet().size(key));
			}
		} catch (Exception e) {
			logger.error("获取value失败，" + key, e);
			value = "获取value失败，" + e.getMessage();
		}
		
		return ResultMap.buildSuccess().put("dataType",dataType).put("value", JSON.toJSONString(value)).put("expire", expire);
	}
	
	@GetMapping("hash")
	public ResultMap hash(String key, String hashKey){
		RedisTemplate<Object, Object> redisTemplate = getCurrRedisTemplate();
		long expire = redisTemplate.getExpire(key);
		DataType dataType = redisTemplate.type(key);
		Object value = null;
		try {
			if(!StringUtils.isEmpty(hashKey)) {
				value = redisTemplate.opsForHash().get(key, hashKey);
			}
		} catch (Exception e) {
			logger.error("获取value失败，" + key, e);
			value = "获取value失败，" + e.getMessage();
		}
		Set<Object> hashKeys = redisTemplate.boundHashOps(key).keys();
		return ResultMap.buildSuccess().put("dataType",dataType)
				.put("content", JSON.toJSONString(value))
				.put("expire", expire)
				.put("hashKeys", hashKeys);
	}
}
