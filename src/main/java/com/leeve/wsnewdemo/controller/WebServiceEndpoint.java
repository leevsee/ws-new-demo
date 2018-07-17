package com.leeve.wsnewdemo.controller;

import com.leeve.wsnewdemo.ws.leeves.LeevesServiceRequest;
import com.leeve.wsnewdemo.ws.leeves.LeevesServiceResponse;
import com.leeve.wsnewdemo.ws.test.TestService;
import com.leeve.wsnewdemo.ws.test.TestServiceResponse;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Description: web service endpoint
 * Package: com.leeve.wsnewdemo.controller
 *
 * @author Leeves
 * @version 1.0.0  2018-07-06
 */
@Endpoint
public class WebServiceEndpoint {

    private static final String NAMESPACE_URI = "http://webservice.leeves.com/ws";

    /**
     * 处理web service请求
     * 其中localPart是和
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "leevesServiceRequest")
    @ResponsePayload
    public LeevesServiceResponse getReqMsg(@RequestPayload LeevesServiceRequest leevesServiceRequest) {
        String reqMsg = leevesServiceRequest.getReqMsg();
        System.out.println("----LeevesService服务端接收到----：" + reqMsg);
        LeevesServiceResponse leevesServiceResponse = new LeevesServiceResponse();
        leevesServiceResponse.setResMsg("LeevesService服务端收到：" + reqMsg + "！向客户端发送数据：我是LeevesService");
        return leevesServiceResponse;
    }

    /**
     * 处理web service请求
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "testService")
    @ResponsePayload
    public TestServiceResponse getTestReqMsg(@RequestPayload TestService testService) {
        String testReqMsg = testService.getReqMsg();
        System.out.println("----TestService服务端接收到----：" + testReqMsg);
        TestServiceResponse testServiceResponse = new TestServiceResponse();
        testServiceResponse.setResMsg("TestService服务端收到：" + testReqMsg + "！向客户端发送数据：我是TestService");
        return testServiceResponse;
    }


}