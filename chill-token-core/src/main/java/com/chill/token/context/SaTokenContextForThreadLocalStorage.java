
package com.chill.token.context;

import com.chill.token.context.model.SaRequest;
import com.chill.token.context.model.SaResponse;
import com.chill.token.context.model.SaStorage;
import com.chill.token.error.SaErrorCode;
import com.chill.token.exception.InvalidContextException;

/**
 * Sa-Token 上下文处理器 [ThreadLocal 版本] ---- 对象存储器
 *
 * <p> 一般情况下你不需要直接操作此类，因为框架的 starter 集成包里已经封装了完整的上下文操作 </p>
 *
 * @author chill
 * @since 1.16.0
 */
public class SaTokenContextForThreadLocalStorage {

    /**
     * 基于 ThreadLocal 的 [ Box 存储器 ]
     */
    public static ThreadLocal<Box> boxThreadLocal = new InheritableThreadLocal<>();

    /**
     * 初始化当前线程的 [ Box 存储器 ]
     *
     * @param request  {@link SaRequest}
     * @param response {@link SaResponse}
     * @param storage  {@link SaStorage}
     */
    public static void setBox(SaRequest request, SaResponse response, SaStorage storage) {
        Box bok = new Box(request, response, storage);
        boxThreadLocal.set(bok);
    }

    /**
     * 清除当前线程的 [ Box 存储器 ]
     */
    public static void clearBox() {
        boxThreadLocal.remove();
    }

    /**
     * 获取当前线程的 [ Box 存储器 ]
     *
     * @return /
     */
    public static Box getBox() {
        return boxThreadLocal.get();
    }

    /**
     * 获取当前线程的 [ Box 存储器 ], 如果为空则抛出异常
     *
     * @return /
     */
    public static Box getBoxNotNull() {
        Box box = boxThreadLocal.get();
        if (box == null) {
            throw new InvalidContextException("未能获取有效的上下文").setCode(SaErrorCode.CODE_10002);
        }
        return box;
    }

    /**
     * 在当前线程的 SaRequest 包装对象
     *
     * @return /
     */
    public static SaRequest getRequest() {
        return getBoxNotNull().getRequest();
    }

    /**
     * 在当前线程的 SaResponse 包装对象
     *
     * @return /
     */
    public static SaResponse getResponse() {
        return getBoxNotNull().getResponse();
    }

    /**
     * 在当前线程的 SaStorage 存储器包装对象
     *
     * @return /
     */
    public static SaStorage getStorage() {
        return getBoxNotNull().getStorage();
    }


    /**
     * Box 临时内部类，用于存储 [ SaRequest、SaResponse、SaStorage ] 三个包装对象
     *
     * @author chill
     * @since 1.16.0
     */
    public static class Box {

        public SaRequest request;

        public SaResponse response;

        public SaStorage storage;

        public Box(SaRequest request, SaResponse response, SaStorage storage) {
            this.request = request;
            this.response = response;
            this.storage = storage;
        }

        public SaRequest getRequest() {
            return request;
        }

        public void setRequest(SaRequest request) {
            this.request = request;
        }

        public SaResponse getResponse() {
            return response;
        }

        public void setResponse(SaResponse response) {
            this.response = response;
        }

        public SaStorage getStorage() {
            return storage;
        }

        public void setStorage(SaStorage storage) {
            this.storage = storage;
        }

        @Override
        public String toString() {
            return "Box [request=" + request + ", response=" + response + ", storage=" + storage + "]";
        }

    }

}
