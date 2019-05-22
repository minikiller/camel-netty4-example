package com.kalix.testing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelMatcher;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@ChannelHandler.Sharable
public class BroadCastChannelHandler extends ChannelInboundHandlerAdapter {

    private static final Gson GSON = new GsonBuilder().create();

    private final Charset charset=Charset.defaultCharset();

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final AtomicInteger response = new AtomicInteger();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel ch = ctx.channel();
        if (ChannelGroups.size() > 0) {
            Message msg = new Message(ch.remoteAddress().toString().substring(1), SDF.format(new Date()));
            ChannelGroups.broadcast(GSON.toJson(msg), new ChannelMatchers());
        }
        ChannelGroups.add(ch);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel ch = ctx.channel();
        ChannelGroups.discard(ch);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel ch = ctx.channel();
        String value=((ByteBuf)msg).toString(this.charset);
        if (ChannelGroups.contains(ch) && value.equals("welcome")) {
            System.out.println(String.format("receive [%s] from [%s] at [%s]", String.valueOf(msg) ,
                    ch.remoteAddress().toString().substring(1), SDF.format(new Date())));
            response.incrementAndGet();
        }
        synchronized (response) {
            System.out.println(response.get() + "\t" + ChannelGroups.size());
            if (response.get() == ChannelGroups.size() - 1) {
                System.out.println("server close all connected channel");
                ChannelGroups.disconnect();
                response.set(0);
            }
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ChannelGroups.discard(ctx.channel());
        response.decrementAndGet();
    }

    public static class ChannelMatchers implements ChannelMatcher {

        @Override
        public boolean matches(Channel channel) {
            return true;
        }

    }

}