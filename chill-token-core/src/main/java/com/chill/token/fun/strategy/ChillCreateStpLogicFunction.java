
package com.chill.token.fun.strategy;

import com.chill.token.stp.AuthLoginLogic;

import java.util.function.Function;

/**
 * 函数式接口：创建 AuthLoginLogic 的算法
 *
 * <p>  参数：账号体系标识  </p>
 * <p>  返回：创建好的 AuthLoginLogic 对象  </p>
 *
 * @author chill
 * @since 1.0
 */
@FunctionalInterface
public interface ChillCreateStpLogicFunction extends Function<String, AuthLoginLogic> {

}
