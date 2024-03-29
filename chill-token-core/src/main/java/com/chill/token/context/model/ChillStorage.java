
package com.chill.token.context.model;

import com.chill.token.basic.value.ChillSetValueInterface;

/**
 * Storage Model，请求作用域的读取值对象。
 *
 * <p> 在一次请求范围内: 存值、取值。数据在请求结束后失效。
 *
 * @author chill
 * @since 1.0
 */
public interface ChillStorage extends ChillSetValueInterface {

    /**
     * 获取底层被包装的源对象
     *
     * @return /
     */
    Object getSource();

    // ---- 实现接口存取值方法

    /**
     * 取值
     */
    @Override
    Object get(String key);

    /**
     * 写值
     */
    @Override
    ChillStorage set(String key, Object value);

    /**
     * 删值
     */
    @Override
    ChillStorage delete(String key);

}
