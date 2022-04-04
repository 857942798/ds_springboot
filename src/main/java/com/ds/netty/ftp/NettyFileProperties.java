package com.ds.netty.ftp;

import io.netty.util.internal.SystemPropertyUtil;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author: dongsheng
 * @CreateTime: 2022/2/18
 * @Description:
 */
@ConfigurationProperties(prefix="netty.file")
@Data
@Validated
public class NettyFileProperties {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Integer port=50000;

    private String path;

    private String bindIp;

    //必须大于1 ,老板线程，即认为是分配工作的线程
    private Integer bossThreads = Math.max(1, SystemPropertyUtil.getInt(
            "io.netty.eventLoopThreads", Runtime.getRuntime().availableProcessors() * 2));

    //必须大于1，实际工作线程数量，这个数量最好根据JVM的系统信息进行配置，这里直接动态获取
    private Integer workThreads = Math.max(1, SystemPropertyUtil.getInt(
            "io.netty.eventLoopThreads", Runtime.getRuntime().availableProcessors() * 2));

}

