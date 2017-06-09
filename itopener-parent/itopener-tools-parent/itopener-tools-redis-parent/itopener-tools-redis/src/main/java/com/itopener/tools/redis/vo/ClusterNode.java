/**  
 * Project Name:msxf-tools-redis 
 * File Name:ClusterNode.java  
 * Package Name:com.msxf.tools.redis.vo  
 * Date:2017年5月12日上午10:23:47 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.tools.redis.vo;

import java.util.List;
import java.util.Set;

import org.springframework.data.redis.connection.RedisClusterNode.Flag;
import org.springframework.data.redis.connection.RedisClusterNode.LinkState;
import org.springframework.data.redis.connection.RedisNode.NodeType;

/**
 * Created by fuwei.deng on 2017年5月12日.
 */
public class ClusterNode {

	private String id;
	
	private String hostPort;
	
	private String name;
	
	private NodeType type;
	
	private String masterId;
	
	private LinkState linkState;
	
	private Set<Flag> flags;
	
	private String slotRange;
	
	private List<ClusterNode> clusterNodes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHostPort() {
		return hostPort;
	}

	public void setHostPort(String hostPort) {
		this.hostPort = hostPort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public LinkState getLinkState() {
		return linkState;
	}

	public void setLinkState(LinkState linkState) {
		this.linkState = linkState;
	}

	public Set<Flag> getFlags() {
		return flags;
	}

	public void setFlags(Set<Flag> flags) {
		this.flags = flags;
	}

	public String getSlotRange() {
		return slotRange;
	}

	public void setSlotRange(String slotRange) {
		this.slotRange = slotRange;
	}

	public List<ClusterNode> getClusterNodes() {
		return clusterNodes;
	}

	public void setClusterNodes(List<ClusterNode> clusterNodes) {
		this.clusterNodes = clusterNodes;
	}

}
