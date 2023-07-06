
package com.chill.token.fun;

import com.chill.token.context.model.SaRequest;
import com.chill.token.context.model.SaResponse;

/**
 * 路由拦截器验证方法的函数式接口，方便开发者进行 lambda 表达式风格调用
 *
 * @author chill
 * @since 1.34.0
 */
@FunctionalInterface
public interface SaRouteFunction {

    /**
     * 执行验证的方法
     *
     * @param request  Request 包装对象
     * @param response Response 包装对象
     * @param handler  处理对象
     */
    void run(SaRequest request, SaResponse response, Object handler);

}
