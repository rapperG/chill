
package com.chill.token;

import com.chill.token.config.ChillTokenConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 注册Chill-Token所需要的Bean
 * <p> Bean 的注册与注入应该分开在两个文件中，否则在某些场景下会造成循环依赖
 *
 * @author chill
 */
public class ChillBeanRegister {

    /**
     * 获取配置Bean
     *
     * @return 配置对象
     */
    @Bean
    @ConfigurationProperties(prefix = "chill-token")
    public ChillTokenConfig getChillTokenConfig() {
        return new ChillTokenConfig();
    }

//	/**
//	 * 获取 json 转换器 Bean (Jackson版)
//	 *
//	 * @return json 转换器 Bean (Jackson版)
//	 */
//	@Bean
//	public ChillJsonTemplate getChillJsonTemplateForJackson() {
//		return new ChillJsonTemplateForJackson();
//	}

}
