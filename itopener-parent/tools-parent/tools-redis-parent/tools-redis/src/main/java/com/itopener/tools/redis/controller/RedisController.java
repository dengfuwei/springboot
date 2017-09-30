package com.itopener.tools.redis.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.itopener.framework.ResultMap;
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
	private RedisTemplate<String, Object> redisTemplate;
	
	@GetMapping("index")
	public ResultMap index(){
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
		Set<String> keys = redisTemplate.keys("*");
		return ResultMap.buildSuccess().put("keys", keys);
	}
	
	@GetMapping("keys")
	public ResultMap keys(String pattern){
		Set<String> keys = redisTemplate.keys(pattern);
		return ResultMap.buildSuccess().put("keys", keys);
	}
	
	@GetMapping("info")
	public ResultMap info(){
		Properties info = redisTemplate.getConnectionFactory().getClusterConnection().info();
		return ResultMap.buildSuccess().put("info", info);
	}
	
	@GetMapping("cluster")
	public ResultMap cluster(){
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
		List<RedisClientInfo> clients = redisTemplate.getClientList();
		return ResultMap.buildSuccess().put("clients", clients);
	}
	
	@PostMapping("save")
	public ResultMap save(String key, String value, long timeout){
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
		return ResultMap.buildSuccess();
	}
	
	@DeleteMapping("keys/{key}")
	public ResultMap delete(@PathVariable String key){
		redisTemplate.delete(key);
		return ResultMap.buildSuccess();
	}
	
	@GetMapping("keys/{key}")
	public ResultMap query(@PathVariable String key){
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
}
