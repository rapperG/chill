package com.chill.token.spring;

import com.chill.token.context.model.ChillResponse;
import com.chill.token.error.ChillSpringBootErrorCode;
import com.chill.token.exception.basic.TokenException;

import javax.servlet.http.HttpServletResponse;

/**
 * 对 ChillResponse 包装类的实现（Servlet 版）
 *
 * @author chill
 * @since 1.0
 */
public class ChillResponseDefaultImpl implements ChillResponse {

    /**
     * 底层Request对象
     */
    protected HttpServletResponse response;

    /**
     * 实例化
     *
     * @param response response对象
     */
    public ChillResponseDefaultImpl(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * 获取底层源对象
     */
    @Override
    public Object getSource() {
        return response;
    }

    /**
     * 设置响应状态码
     */
    @Override
    public ChillResponse setStatus(int sc) {
        response.setStatus(sc);
        return this;
    }

    /**
     * 在响应头里写入一个值
     */
    @Override
    public ChillResponse setHeader(String name, String value) {
        response.setHeader(name, value);
        return this;
    }

    /**
     * 在响应头里添加一个值
     *
     * @param name  名字
     * @param value 值
     * @return 对象自身
     */
    public ChillResponse addHeader(String name, String value) {
        response.addHeader(name, value);
        return this;
    }

    /**
     * 重定向
     */
    @Override
    public Object redirect(String url) {
        try {
            response.sendRedirect(url);
        } catch (Exception e) {
            throw new TokenException(e).setCode(ChillSpringBootErrorCode.CODE_20002);
        }
        return null;
    }


}
