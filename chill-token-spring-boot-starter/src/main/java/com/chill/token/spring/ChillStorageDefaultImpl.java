package com.chill.token.spring;

import com.chill.token.context.model.ChillStorage;

import javax.servlet.http.HttpServletRequest;

/**
 * 对 ChillStorage 包装类的实现（Servlet 版）
 *
 * @author chill
 * @since 1.0
 */
public class ChillStorageDefaultImpl implements ChillStorage {

    /**
     * 底层Request对象
     */
    protected HttpServletRequest request;

    /**
     * 实例化
     *
     * @param request request对象
     */
    public ChillStorageDefaultImpl(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 获取底层源对象
     */
    @Override
    public Object getSource() {
        return request;
    }

    /**
     * 在 [Request作用域] 里写入一个值
     */
    @Override
    public ChillStorageDefaultImpl set(String key, Object value) {
        request.setAttribute(key, value);
        return this;
    }

    /**
     * 在 [Request作用域] 里获取一个值
     */
    @Override
    public Object get(String key) {
        return request.getAttribute(key);
    }

    /**
     * 在 [Request作用域] 里删除一个值
     */
    @Override
    public ChillStorageDefaultImpl delete(String key) {
        request.removeAttribute(key);
        return this;
    }

}
