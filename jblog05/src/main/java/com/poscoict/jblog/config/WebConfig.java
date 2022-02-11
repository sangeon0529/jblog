package com.poscoict.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.poscoict.config.web.FileuploadConfig;
import com.poscoict.config.web.MvcConfig;
import com.poscoict.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.poscoict.jblog.controller","com.poscoict.jblog.exception"})
@Import({FileuploadConfig.class,MvcConfig.class,SecurityConfig.class})
public class WebConfig {

}
