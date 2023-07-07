
package com.chill.token.fun;

import com.chill.token.context.model.ChillRequest;
import com.chill.token.context.model.ChillResponse;

/**
 * 路由拦截器验证方法的函数式接口，方便开发者进行 lambda 表达式风格调用
 *
 * @author chill
 * @since 1.0
 */
@FunctionalInterface
public interface ChillRouteFunction {

    /**
     * 执行验证的方法
     *
     * @param request  Request 包装对象
     * @param response Response 包装对象
     * @param handler  处理对象
     */
    void run(ChillRequest request, ChillResponse response, Object handler);

}
