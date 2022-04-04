package com.ds.netty.ftp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


/**
 * @author: dongsheng
 * @CreateTime: 2022/2/18
 * @Description:
 */
@Component
@ConditionalOnProperty(
        value = {"netty.file.enabled"},
        matchIfMissing = false
)
public class FilePipeline extends ChannelInitializer<SocketChannel> {

    @Autowired
    FileServerHandler fleServerHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast("http-decoder", new HttpRequestDecoder());
        p.addLast("http-aggregator", new HttpObjectAggregator(65536));
        p.addLast("http-encoder", new HttpResponseEncoder());
        p.addLast("http-chunked", new ChunkedWriteHandler());
        //引入文件处理的handler
        p.addLast("fileServerHandler",fleServerHandler);
    }

}

