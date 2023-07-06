
package com.chill.token.context;

import com.chill.token.SaManager;
import com.chill.token.application.SaApplication;
import com.chill.token.context.model.SaRequest;
import com.chill.token.context.model.SaResponse;
import com.chill.token.context.model.SaStorage;

/**
 * Sa-Token 上下文持有类，你可以通过此类快速获取当前环境下的 SaRequest、SaResponse、SaStorage、SaApplication 对象。
 *
 * @author chill
 * @since 1.18.0
 */
public class SaHolder {

    /**
     * 获取当前请求的 SaTokenContext 上下文对象
     *
     * @return /
     * @see SaTokenContext
     */
    public static SaTokenContext getContext() {
        return SaManager.getSaTokenContextOrSecond();
    }

    /**
     * 获取当前请求的 Request 包装对象
     *
     * @return /
     * @see SaRequest
     */
    public static SaRequest getRequest() {
        return SaManager.getSaTokenContextOrSecond().getRequest();
    }

    /**
     * 获取当前请求的 Response 包装对象
     *
     * @return /
     * @see SaResponse
     */
    public static SaResponse getResponse() {
        return SaManager.getSaTokenContextOrSecond().getResponse();
    }

    /**
     * 获取当前请求的 Storage 包装对象
     *
     * @return /
     * @see SaStorage
     */
    public static SaStorage getStorage() {
        return SaManager.getSaTokenContextOrSecond().getStorage();
    }

    /**
     * 获取全局 SaApplication 对象
     *
     * @return /
     * @see SaApplication
     */
    public static SaApplication getApplication() {
        return SaApplication.defaultInstance;
    }

}
