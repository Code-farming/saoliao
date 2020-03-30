package com.lhb.saoliao.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * 处理消息的Handler
 * TextWebSocketFrame是在netty中用于为websocket专门处理文本的对象,frame是消息载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用于记录和管理所有客户端的channel
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String content = msg.text();
        System.out.println("服务器接收到的数据:"+content);

//        for (Channel channel:clients){
//            channel.writeAndFlush(new TextWebSocketFrame("[服务器在]"+ LocalDateTime.now()+
//                    "接收到消息,消息为:"+content));
//        }

//        下面这个方法与上面使for循环是一致的
        clients.writeAndFlush(new TextWebSocketFrame("[服务器在]"+ LocalDateTime.now()+
                "接收到消息,消息为:"+content));
    }

    /**
     * 当客户端连接到服务器时(或者打开链接)
     * 获取客户端的Channel,并且将Channel添加到ChannelGroup进行管理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    /**
     * 当客户端断开链接时
     * 将客户端的Channel从ChannelGroup中移除
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handlerRemoved时,ChannelGroup会自动将对应的Channel移除
        //clients.remove(ctx.channel());
        System.out.println("客户端断开,Channel对应得长ID:"+ctx.channel().id().asLongText());
        System.out.println("客户端断开,Channel对应得短ID:"+ctx.channel().id().asShortText());


    }
}
