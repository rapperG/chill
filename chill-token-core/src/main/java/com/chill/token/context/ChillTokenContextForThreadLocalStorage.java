
package com.chill.token.context;

import com.chill.token.context.model.ChillRequest;
import com.chill.token.context.model.ChillResponse;
import com.chill.token.context.model.ChillStorage;
import com.chill.token.error.ChillErrorCode;
import com.chill.token.exception.InvalidContextException;

/**
 * Chill-Token 上下文处理器 [ThreadLocal 版本] ---- 对象存储器
 *
 * <p> 一般情况下你不需要直接操作此类，因为框架的 starter 集成包里已经封装了完整的上下文操作 </p>
 *
 * @author chill
 * @since 1.0
 */
public class ChillTokenContextForThreadLocalStorage {

    /**
     * 基于 ThreadLocal 的 [ Box 存储器 ]
     */
    public static ThreadLocal<Box> boxThreadLocal = new InheritableThreadLocal<>();

    /**
     * 初始化当前线程的 [ Box 存储器 ]
     *
     * @param request  {@link ChillRequest}
     * @param response {@link ChillResponse}
     * @param storage  {@link ChillStorage}
     */
    public static void setBox(ChillRequest request, ChillResponse response, ChillStorage storage) {
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
            throw new InvalidContextException("未能获取有效的上下文").setCode(ChillErrorCode.CODE_10002);
        }
        return box;
    }

    /**
     * 在当前线程的 ChillRequest 包装对象
     *
     * @return /
     */
    public static ChillRequest getRequest() {
        return getBoxNotNull().getRequest();
    }

    /**
     * 在当前线程的 ChillResponse 包装对象
     *
     * @return /
     */
    public static ChillResponse getResponse() {
        return getBoxNotNull().getResponse();
    }

    /**
     * 在当前线程的 ChillStorage 存储器包装对象
     *
     * @return /
     */
    public static ChillStorage getStorage() {
        return getBoxNotNull().getStorage();
    }


    /**
     * Box 临时内部类，用于存储 [ ChillRequest、ChillResponse、ChillStorage ] 三个包装对象
     *
     * @author chill
     * @since 1.0
     */
    public static class Box {

        public ChillRequest request;

        public ChillResponse response;

        public ChillStorage storage;

        public Box(ChillRequest request, ChillResponse response, ChillStorage storage) {
            this.request = request;
            this.response = response;
            this.storage = storage;
        }

        public ChillRequest getRequest() {
            return request;
        }

        public void setRequest(ChillRequest request) {
            this.request = request;
        }

        public ChillResponse getResponse() {
            return response;
        }

        public void setResponse(ChillResponse response) {
            this.response = response;
        }

        public ChillStorage getStorage() {
            return storage;
        }

        public void setStorage(ChillStorage storage) {
            this.storage = storage;
        }

        @Override
        public String toString() {
            return "Box [request=" + request + ", response=" + response + ", storage=" + storage + "]";
        }

    }

}
