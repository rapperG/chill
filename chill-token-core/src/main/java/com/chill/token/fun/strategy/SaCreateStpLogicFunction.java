
package com.chill.token.fun.strategy;

import com.chill.token.stp.StpLogic;

import java.util.function.Function;

/**
 * 函数式接口：创建 StpLogic 的算法
 *
 * <p>  参数：账号体系标识  </p>
 * <p>  返回：创建好的 StpLogic 对象  </p>
 *
 * @author chill
 * @since 1.35.0
 */
@FunctionalInterface
public interface SaCreateStpLogicFunction extends Function<String, StpLogic> {

}
