package com.hx.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UrlPathHelper;

public class RequestUtils {

	// 从正则表达式里面提取字符
	private static String getIP(String s) {
		String rexp = "([\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3})";
		Pattern p = Pattern.compile(rexp);
		Matcher m = p.matcher(s);
		boolean result = m.find();
		String ip = "0.0.0.0";
		while (result) {
			ip = m.group(1);
			result = m.find();
		}
		return ip;
	}

	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return getIP(ip);
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return getIP(ip.substring(0, index));
			} else {
				return getIP(ip);
			}
		} else {
			return getIP(request.getRemoteAddr());
		}
	}

	/**
	 * 获得当前的访问路径
	 * 
	 * HttpServletRequest.getRequestURL+"?"+HttpServletRequest.getQueryString
	 * 
	 * @param request
	 * @return
	 */
	public static String getLocation(HttpServletRequest request) {
		UrlPathHelper helper = new UrlPathHelper();
		StringBuffer buff = request.getRequestURL();
		String uri = request.getRequestURI();
		String origUri = helper.getOriginatingRequestUri(request);
		buff.replace(buff.length() - uri.length(), buff.length(), origUri);
		String queryString = helper.getOriginatingQueryString(request);
		if (queryString != null) {
			buff.append("?").append(queryString);
		}
		return buff.toString();
	}




	// 获取登录设备的类型
	public static Integer getDevice(HttpServletRequest request) {
		if (request.getHeader("User-Agent").indexOf("iOS") >= 0) {
			return 10;
		} else if (request.getHeader("User-Agent").indexOf("android") >= 0) {
			return 5;
		} else if (request.getHeader("User-Agent").indexOf("pos") >= 0) {
			return 15;
		}
		return 0;
	}
}
