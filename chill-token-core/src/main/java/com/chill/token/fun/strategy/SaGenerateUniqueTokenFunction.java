
package com.chill.token.fun.strategy;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 生成唯一式 token 的函数式接口，方便开发者进行 lambda 表达式风格调用
 *
 * <p>  参数：元素名称, 最大尝试次数, 创建 token 函数, 检查 token 函数 </p>
 * <p>  返回：生成的token  </p>
 *
 * @author chill
 * @since 1.35.0
 */
@FunctionalInterface
public interface SaGenerateUniqueTokenFunction {

    /**
     * 封装 token 生成、校验的代码，生成唯一式 token
     *
     * @param elementName         要生成的元素名称，方便抛出异常时组织提示信息
     * @param maxTryTimes         最大尝试次数
     * @param createTokenFunction 创建 token 的函数
     * @param checkTokenFunction  校验 token 是否唯一的函数（返回 true 表示唯一，可用）
     * @return 最终生成的唯一式 token
     */
    String execute(
            String elementName,
            int maxTryTimes,
            Supplier<String> createTokenFunction,
            Function<String, Boolean> checkTokenFunction
    );

}
