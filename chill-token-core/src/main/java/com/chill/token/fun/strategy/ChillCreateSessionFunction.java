
package com.chill.token.fun.strategy;

import com.chill.token.session.ChillSession;

import java.util.function.Function;

/**
 * 函数式接口：创建 ChillSession 的策略
 *
 * <p>  参数：SessionId  </p>
 * <p>  返回：SaSession对象  </p>
 *
 * @author chill
 * @since 1.0
 */
@FunctionalInterface
public interface ChillCreateSessionFunction extends Function<String, ChillSession> {

}
