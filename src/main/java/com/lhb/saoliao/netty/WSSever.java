package com.lhb.saoliao.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WSSever {
    private static class SingletonServer {
        static final WSSever instance = new WSSever();
    }

    public static WSSever getInstance() {
        return SingletonServer.instance;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;

    public WSSever() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSSeverInitializer());
    }

    /**
     * netty服务的启动方法,端口号为8088
     * @throws Exception
     */
    public void start() throws Exception {
        this.channelFuture = serverBootstrap.bind(8088);
        System.out.println("netty服务已经启动");
        log.info("netty服务已经启动");
    }


//    public static void main(String[] args) throws Exception {
//        EventLoopGroup mainGroup = new NioEventLoopGroup();
//        EventLoopGroup subGroup = new NioEventLoopGroup();
//        try {
//            ServerBootstrap serverBootstrap = new ServerBootstrap();
//            serverBootstrap.group(mainGroup, subGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(null);
//            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
//            channelFuture.channel().closeFuture().sync();
//        } finally {
//            mainGroup.shutdownGracefully();
//            subGroup.shutdownGracefully();
//        }
//    }
}
