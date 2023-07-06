
package com.chill.token.basic.value;

import com.chill.token.fun.SaRetFunction;

/**
 * 对写值的一组方法封装
 * <p> 封装 SaStorage、SaSession、SaApplication 等存取值的一些固定方法，减少重复编码 </p>
 *
 * @author chill
 * @since 1.31.0
 */
public interface SaSetValueInterface extends SaGetValueInterface {

    // --------- 需要子类实现的方法

    /**
     * 写值
     *
     * @param key   名称
     * @param value 值
     * @return 对象自身
     */
    SaSetValueInterface set(String key, Object value);

    /**
     * 删值
     *
     * @param key 要删除的key
     * @return 对象自身
     */
    SaSetValueInterface delete(String key);


    // --------- 接口提供封装的方法

    /**
     * 取值 (如果值为 null，则执行 fun 函数获取值，并把函数返回值写入缓存)
     *
     * @param <T> 返回值的类型
     * @param key key
     * @param fun 值为null时执行的函数
     * @return 值
     */
    @SuppressWarnings("unchecked")
    default <T> T get(String key, SaRetFunction fun) {
        Object value = get(key);
        if (value == null) {
            value = fun.run();
            set(key, value);
        }
        return (T) value;
    }

    /**
     * 写值 (只有在此 key 原本无值的情况下才会写入)
     *
     * @param key   名称
     * @param value 值
     * @return 对象自身
     */
    default SaSetValueInterface setByNull(String key, Object value) {
        if (!has(key)) {
            set(key, value);
        }
        return this;
    }

}
