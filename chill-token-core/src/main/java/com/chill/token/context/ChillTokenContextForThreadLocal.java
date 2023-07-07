
package com.chill.token.context;

import com.chill.token.context.model.ChillRequest;
import com.chill.token.context.model.ChillResponse;
import com.chill.token.context.model.ChillStorage;

/**
 * Chill-Token 上下文处理器 [ ThreadLocal 版本 ]
 *
 * <p>
 * 使用 [ ThreadLocal 版本 ] 上下文处理器需要在全局过滤器或者拦截器内率先调用
 * ChillTokenContextForThreadLocalStorage.setBox(req, res, sto) 初始化上下文
 * </p>
 *
 * <p> 一般情况下你不需要直接操作此类，因为框架的 starter 集成包里已经封装了完整的上下文操作 </p>
 *
 * @author chill
 * @since 1.0
 */
public class ChillTokenContextForThreadLocal implements ChillTokenContext {

    @Override
    public ChillRequest getRequest() {
        return ChillTokenContextForThreadLocalStorage.getRequest();
    }

    @Override
    public ChillResponse getResponse() {
        return ChillTokenContextForThreadLocalStorage.getResponse();
    }

    @Override
    public ChillStorage getStorage() {
        return ChillTokenContextForThreadLocalStorage.getStorage();
    }

    @Override
    public boolean matchPath(String pattern, String path) {
        return false;
    }

    @Override
    public boolean isValid() {
        return ChillTokenContextForThreadLocalStorage.getBox() != null;
    }

}
