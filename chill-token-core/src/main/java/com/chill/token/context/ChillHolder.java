
package com.chill.token.context;

import com.chill.token.ChillManager;
import com.chill.token.application.ChillTokenApplication;
import com.chill.token.context.model.ChillRequest;
import com.chill.token.context.model.ChillResponse;
import com.chill.token.context.model.ChillStorage;

/**
 * Chill-Token 上下文持有类，你可以通过此类快速获取当前环境下的 ChillRequest、ChillResponse、ChillStorage、ChillTokenApplication 对象。
 *
 * @author chill
 * @since 1.0
 */
public class ChillHolder {

    /**
     * 获取当前请求的 ChillTokenContext 上下文对象
     *
     * @return /
     * @see ChillTokenContext
     */
    public static ChillTokenContext getContext() {
        return ChillManager.getChillTokenContextOrSecond();
    }

    /**
     * 获取当前请求的 Request 包装对象
     *
     * @return /
     * @see ChillRequest
     */
    public static ChillRequest getRequest() {
        return ChillManager.getChillTokenContextOrSecond().getRequest();
    }

    /**
     * 获取当前请求的 Response 包装对象
     *
     * @return /
     * @see ChillResponse
     */
    public static ChillResponse getResponse() {
        return ChillManager.getChillTokenContextOrSecond().getResponse();
    }

    /**
     * 获取当前请求的 Storage 包装对象
     *
     * @return /
     * @see ChillStorage
     */
    public static ChillStorage getStorage() {
        return ChillManager.getChillTokenContextOrSecond().getStorage();
    }

    /**
     * 获取全局 ChillTokenApplication 对象
     *
     * @return /
     * @see ChillTokenApplication
     */
    public static ChillTokenApplication getApplication() {
        return ChillTokenApplication.defaultInstance;
    }

}
