package com.javayh.common.hash;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>
 * 一致性hash
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-27 11:23
 */
public class ConsistentHashing {

	/**
	 * 真实缓存地址 ip:port {"192.168.0.100:8080","192.168.0.101:8082"}
	 */
	private String[] cacheServers;

	/** 保存虚拟节点 */
	private final SortedMap<Long, String> nodes = new TreeMap<Long, String>();

	/** 每个虚拟节点的数量 */
	private final int VIRTUAL_NODE_NUM = 3;

	public ConsistentHashing(final String[] cacheServers) {
		// 初始化
		for (String eachServer : cacheServers) {
			this.addNode(eachServer);
		}
	}

	/**
	 * <p>
	 * 创建虚拟节点
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/27
	 * @param nodeKey
	 * @return void
	 */
	public void addNode(String nodeKey) {
		// 为每一个实体节点创建3个虚拟节点
		for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
			long eachHashCode = this.hash(nodeKey + ":" + i);
			nodes.put(eachHashCode, nodeKey);
		}
	}

	/**
	 * <p>
	 * hash
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/27
	 * @param key
	 * @return long
	 */
	private long hash(String key) {
		String md5key = DigestUtils.md5Hex(key);
		return Long.parseLong(md5key.substring(0, 15), 16) % ((long) Math.pow(2, 32));
	}

	/**
	 * <p>
	 * 按照同一个方向寻找
	 * </p>
	 * @version 1.0.0
	 * @author Dylan-haiji
	 * @since 2020/3/27
	 * @param key
	 * @return java.lang.String
	 */
	public String getRealServer(String key) {
		long hashCode = this.hash(key);
		SortedMap<Long, String> tailMap = nodes.tailMap(hashCode);
		long serverKey = tailMap.isEmpty() ? nodes.firstKey() : tailMap.firstKey();
		return nodes.get(serverKey);
	}

	// public static void main(String[] args) {
	// ConsistentHashing t = new ConsistentHashing(new String[]{"192.168.0.100:8080",
	// "192.168.0.101:8082"});
	// System.out.println(t.getRealServer("javayh"));
	// }

}
