
package com.chill.token.spring;

import com.chill.token.context.ChillTokenContext;
import com.chill.token.context.model.ChillRequest;
import com.chill.token.context.model.ChillResponse;
import com.chill.token.context.model.ChillStorage;

/**
 * Chill-Token 上下文处理器 [ SpringMVC版本实现 ]。在 SpringMVC、SpringBoot 中使用 Chill-Token 时，必须注入此实现类，否则会出现上下文无效异常
 *
 * @author chill
 * @since 1.0
 */
public class ChillTokenContextForSpring implements ChillTokenContext {

    /**
     * 获取当前请求的 Request 包装对象
     */
    @Override
    public ChillRequest getRequest() {
        return new ChillRequestDefaultImpl(SpringMVCUtil.getRequest());
    }

    /**
     * 获取当前请求的 Response 包装对象
     */
    @Override
    public ChillResponse getResponse() {
        return new ChillResponseDefaultImpl(SpringMVCUtil.getResponse());
    }

    /**
     * 获取当前请求的 Storage 包装对象
     */
    @Override
    public ChillStorage getStorage() {
        return new ChillStorageDefaultImpl(SpringMVCUtil.getRequest());
    }

    /**
     * 判断：指定路由匹配符是否可以匹配成功指定路径
     */
    @Override
    public boolean matchPath(String pattern, String path) {
        return ChillPathMatcherHolder.getPathMatcher().match(pattern, path);
    }

    /**
     * 判断：在本次请求中，此上下文是否可用。
     */
    @Override
    public boolean isValid() {
        return SpringMVCUtil.isWeb();
    }

}
