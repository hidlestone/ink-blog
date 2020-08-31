package com.payn.ink.config;

import com.payn.ink.interceptor.BaseInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

/**
 * SpringMVC 配置拦截器等
 *
 * @author: payn
 * @date: 2020/8/20 16:28
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private PathConfig pathConfig;
	@Autowired
	private BaseInterceptor baseInterceptor;

	/**
	 * 映射静态资源文件夹中的文件。注意配置的域名。
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**").addResourceLocations("file:///" + pathConfig.getFilePath());
	}

	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//多个拦截器组成一个拦截器链
		//addPathPatterns 用于添加拦截规则
		//excludePathPatterns 用户排除拦截  
		//在Spring添加拦截器之前先自己创建一下这个Spring Bean，这样就能在Spring映射这个拦截器前，把拦截器中的依赖注入给完成了。
		registry.addInterceptor(baseInterceptor).addPathPatterns("/**")
				.excludePathPatterns("/admin/dologin")
				.excludePathPatterns(Arrays.asList("/*editor.html", "/themes/css/**", "/themes/js/**", "/themes/img/**",
						"/admin/css/**", "/admin/fonts/**", "/admin/js/**", "/admin/layui/**", "/favicon.ico"));
		super.addInterceptors(registry);
	}

}
