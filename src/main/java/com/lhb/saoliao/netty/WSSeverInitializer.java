package com.lhb.saoliao.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WSSeverInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

    //============================ 以下Handler是为支持Http协议=======================
        // websocket基于Http协议,需要有Http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // 对HttpMessage进行聚合,聚合成FullHttpRequest或者FullHttpResponse
        // 几乎在netty编程中,都会使用到此Handler
        pipeline.addLast(new HttpObjectAggregator(1024*64));

     //============================ 以下Handler是为支持WebSocket协议=======================
        /**
         * WebSocket服务器处理的协议,用于指定客户端连接访问的路由: /ws
         * 本handler会帮你处理一些繁重的事情
         * 会帮你处理握手动作,handshaking(Close, Ping, Pong) ping+pong=心跳
         * 对于websocket来说,都是以frame进行传输的,不同的数据类型对应的frames不同
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
     //============================ 自定义Handler=======================
        pipeline.addLast(new ChatHandler());

    }
}
