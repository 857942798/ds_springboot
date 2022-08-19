package com.ds.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//服务器配置

@Configuration
@PropertySource({"classpath:my.properties"})
@ConfigurationProperties(prefix="test")
@Data
public class ServerSettings {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * domain不能为空
	 */
	private String domain="default-domain";
}
