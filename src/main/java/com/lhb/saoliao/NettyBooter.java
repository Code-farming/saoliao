package com.lhb.saoliao;

import com.lhb.saoliao.netty.WSSever;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * netty服务启动监听
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            try {
                WSSever.getInstance().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
