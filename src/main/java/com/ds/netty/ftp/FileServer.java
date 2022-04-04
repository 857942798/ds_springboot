package com.ds.netty.ftp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author: dongsheng
 * @CreateTime: 2022/2/18
 * @Description: 文件服务器启动配置类
 */
@Configuration
@EnableConfigurationProperties({NettyFileProperties.class})
@ConditionalOnProperty(
        value = {"netty.file.enabled"},
        matchIfMissing = false
)
public class FileServer {

    private Logger logger = LoggerFactory.getLogger(FileServer.class);

    @Autowired
    FilePipeline filePipeline;

    @Autowired
    NettyFileProperties nettyFileProperties;

    @Bean("starFileServer")
    public String start() {
        Thread thread =  new Thread(() -> {
            NioEventLoopGroup bossGroup = new NioEventLoopGroup(nettyFileProperties.getBossThreads());
            NioEventLoopGroup workerGroup = new NioEventLoopGroup(nettyFileProperties.getWorkThreads());
            try {
                logger.info("start netty [FileServer] server ,port: " + nettyFileProperties.getPort());
                ServerBootstrap boot = new ServerBootstrap();
                options(boot).group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(filePipeline);
                Channel ch = null;
                //是否绑定IP
                if(!StringUtils.isEmpty(nettyFileProperties.getBindIp())){
                    ch = boot.bind(nettyFileProperties.getBindIp(),nettyFileProperties.getPort()).sync().channel();
                }else{
                    ch = boot.bind(nettyFileProperties.getPort()).sync().channel();
                }
                ch.closeFuture().sync();
            } catch (InterruptedException e) {
                logger.error("启动NettyServer错误", e);
            } finally {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        });
        thread.setName("File_Server");
        thread.start();
        return "file start";
    }

    private ServerBootstrap options(ServerBootstrap boot) {
        return boot;
    }

}

