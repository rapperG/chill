
package com.chill.token.spring;

import com.chill.token.context.ChillTokenContext;
import org.springframework.context.annotation.Bean;

/**
 * 注册 Chill-Token 框架所需要的 Bean
 *
 * @author chill
 * @since 1.0
 */
public class ChillTokenContextRegister {

    /**
     * 获取上下文处理器组件 (Spring版)
     *
     * @return /
     */
    @Bean
    public ChillTokenContext chillTokenContext() {
        return new ChillTokenContextForSpring();
    }
//
//    void register() {
//        ChillManager.setChillTokenContext(chillTokenContext());
//    }
}
