package com.leeve.wsnewdemo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Description: web service 配置
 * Package: com.leeves.wsspringdemo.config
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    /**
     * web service 访问地址分发
     */
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> wsDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    /**
     * 把XSD schemas转换成wsdl
     */
    @Bean(name = "leevesService")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema leevesServiceSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("LeevesService");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://webservice.leeves.com/ws");
        wsdl11Definition.setSchema(leevesServiceSchema);
        return wsdl11Definition;
    }

    /**
     * 把XSD schemas转换成wsdl
     */
    @Bean(name = "testService")
    public MyWsdl11Definition mMyWsdl11Definition(XsdSchema testServiceSchema) {
        MyWsdl11Definition wsdl11Definition = new MyWsdl11Definition();
        wsdl11Definition.setPortTypeName("TestService");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://webservice.leeves.com/ws");
        wsdl11Definition.setSchema(testServiceSchema);
        return wsdl11Definition;
    }

    /**
     * 创建leeves的XsdSchema实例
     */
    @Bean
    public XsdSchema leevesServiceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/leevesService.xsd"));
    }

    /**
     * 创建test的XsdSchema实例
     */
    @Bean
    public XsdSchema testServiceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/testService.xsd"));
    }

}