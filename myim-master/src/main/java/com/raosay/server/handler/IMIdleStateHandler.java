package com.raosay.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author ron
 * @version $Id: IMIdleStateHandler, v 0.1 2018/10/31 16:49 Administrator Exp $
 */
public class IMIdleStateHandler extends IdleStateHandler {


    private static final int readIdleTimeSeconds = 15 ;



    public IMIdleStateHandler() {
        super(readIdleTimeSeconds, 0, 0, TimeUnit.SECONDS);
    }


    /**
     * Calls {@link ChannelHandlerContext#fireUserEventTriggered(Object)} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param evt
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent e = (IdleStateEvent)evt;
            if(e.state() == IdleState.READER_IDLE){
                System.out.println("该发送心跳了");
            }else if(e.state() == IdleState.WRITER_IDLE){
                System.out.println("该发送心跳了");
            }

        }
        super.userEventTriggered(ctx, evt);
    }
}
