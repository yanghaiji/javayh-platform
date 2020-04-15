package com.javayh.common.util;

import lombok.extern.slf4j.Slf4j;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * <p>
 * 获取Ip
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-03-01 22:59
 */
@Slf4j
public class IPUtils {

	public static String getHostIp() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface
					.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = allNetInterfaces
						.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress ip = addresses.nextElement();
					if (ip instanceof Inet4Address hostIp && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
						return hostIp.getHostAddress();
					}
				}
			}
		}
		catch (Exception e) {
			log.error(e.getStackTrace()[0].getLineNumber()
					+ e.getStackTrace()[0].getClassName());
		}
		return null;
	}

}
