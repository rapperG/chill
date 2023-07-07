
package com.chill.token.context;

import com.chill.token.context.model.ChillRequest;
import com.chill.token.context.model.ChillResponse;
import com.chill.token.context.model.ChillStorage;
import com.chill.token.error.ChillErrorCode;
import com.chill.token.exception.InvalidContextException;

/**
 * Chill-Token 上下文处理器 [ 默认实现类 ]
 *
 * <p>
 * 一般情况下框架会为你自动注入合适的上下文处理器，如果代码断点走到了此默认实现类，
 * 说明你引入的依赖有问题或者错误的调用了 Chill-Token 的API
 * </p>
 *
 * @author chill
 * @since 1.0
 */
public class ChillTokenContextDefaultImpl implements ChillTokenContext {

    /**
     * 默认的上下文处理器对象
     */
    public static ChillTokenContextDefaultImpl defaultContext = new ChillTokenContextDefaultImpl();

    /**
     * 错误提示语
     */
    public static final String ERROR_MESSAGE = "未能获取有效的上下文处理器";

    @Override
    public ChillRequest getRequest() {
        throw new InvalidContextException(ERROR_MESSAGE).setCode(ChillErrorCode.CODE_10001);
    }

    @Override
    public ChillResponse getResponse() {
        throw new InvalidContextException(ERROR_MESSAGE).setCode(ChillErrorCode.CODE_10001);
    }

    @Override
    public ChillStorage getStorage() {
        throw new InvalidContextException(ERROR_MESSAGE).setCode(ChillErrorCode.CODE_10001);
    }

    @Override
    public boolean matchPath(String pattern, String path) {
        throw new InvalidContextException(ERROR_MESSAGE).setCode(ChillErrorCode.CODE_10001);
    }

}
