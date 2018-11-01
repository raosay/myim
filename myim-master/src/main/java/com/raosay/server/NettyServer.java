package com.raosay.server;

import com.raosay.server.handler.IMIdleStateHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

/**
 *
 * 长连接服务启动类
 *
 * @author ron
 * @version $Id: NettyServer, v 0.1 2018/10/11 17:29 Administrator Exp $
 */
public class NettyServer {




    public static void main(String[] args){


        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap  = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workGroup)
                .childAttr(AttributeKey.valueOf("server"), "true")
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childOption(ChannelOption.TCP_NODELAY,true)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IMIdleStateHandler())
                                .addLast();
                    }
                });

        serverBootstrap.bind(9099);
    }


}
