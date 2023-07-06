
package com.chill.token.fun.strategy;

import com.chill.token.session.SaSession;

import java.util.function.Function;

/**
 * 函数式接口：创建 SaSession 的策略
 *
 * <p>  参数：SessionId  </p>
 * <p>  返回：SaSession对象  </p>
 *
 * @author chill
 * @since 1.35.0
 */
@FunctionalInterface
public interface SaCreateSessionFunction extends Function<String, SaSession> {

}
