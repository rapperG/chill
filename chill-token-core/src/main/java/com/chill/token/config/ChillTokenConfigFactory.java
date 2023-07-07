
package com.chill.token.config;

import com.chill.token.error.ChillErrorCode;
import com.chill.token.exception.basic.TokenException;
import com.chill.token.util.ChillFoxUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Chill-Token配置文件的构建工厂类
 *
 * <p> 用于手动读取配置文件初始化 {@link ChillTokenConfig}对象，只有在非IOC环境下你才会用到此类 </p>
 *
 * @author chill
 * @since 1.0
 */
public class ChillTokenConfigFactory {

    private ChillTokenConfigFactory() {
    }

    /**
     * 配置文件地址
     */
    public static String configPath = "chill-token.properties";

    /**
     * 根据 configPath 路径获取配置信息
     *
     * @return 一个ChillTokenConfig对象
     */
    public static ChillTokenConfig createConfig() {
        return createConfig(configPath);
    }

    /**
     * 根据指定路径路径获取配置信息
     *
     * @param path 配置文件路径
     * @return 一个 ChillTokenConfig 对象
     */
    public static ChillTokenConfig createConfig(String path) {
        Map<String, String> map = readPropToMap(path);
        // if (map == null) {
        // throw new RuntimeException("找不到配置文件：" + configPath, null);
        // }
        return (ChillTokenConfig) initPropByMap(map, new ChillTokenConfig());
    }

    /**
     * 工具方法: 将指定路径的properties配置文件读取到Map中
     *
     * @param propertiesPath 配置文件地址
     * @return 一个Map
     */
    private static Map<String, String> readPropToMap(String propertiesPath) {
        Map<String, String> map = new HashMap<>(16);
        try {
            InputStream is = ChillTokenConfigFactory.class.getClassLoader().getResourceAsStream(propertiesPath);
            if (is == null) {
                return null;
            }
            Properties prop = new Properties();
            prop.load(is);
            for (String key : prop.stringPropertyNames()) {
                map.put(key, prop.getProperty(key));
            }
        } catch (IOException e) {
            throw new TokenException("配置文件(" + propertiesPath + ")加载失败", e).setCode(ChillErrorCode.CODE_10021);
        }
        return map;
    }

    /**
     * 工具方法: 将 Map 的值映射到一个 Model 上
     *
     * @param map 属性集合
     * @param obj 对象, 或类型
     * @return 返回实例化后的对象
     */
    private static Object initPropByMap(Map<String, String> map, Object obj) {

        if (map == null) {
            map = new HashMap<>(16);
        }

        // 1、取出类型
        Class<?> cs;
        if (obj instanceof Class) {
            // 如果是一个类型，则将obj=null，以便完成静态属性反射赋值
            cs = (Class<?>) obj;
            obj = null;
        } else {
            // 如果是一个对象，则取出其类型
            cs = obj.getClass();
        }

        // 2、遍历类型属性，反射赋值
        for (Field field : cs.getDeclaredFields()) {
            String value = map.get(field.getName());
            if (value == null) {
                // 如果为空代表没有配置此项
                continue;
            }
            try {
                Object valueConvert = ChillFoxUtil.getValueByType(value, field.getType());
                field.setAccessible(true);
                field.set(obj, valueConvert);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new TokenException("属性赋值出错：" + field.getName(), e).setCode(ChillErrorCode.CODE_10022);
            }
        }
        return obj;
    }

}
