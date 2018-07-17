package com.leeve.wsnewdemo.config;

import org.springframework.util.Assert;
import org.springframework.ws.wsdl.wsdl11.provider.DefaultMessagesProvider;
import org.w3c.dom.Element;

/**
 * Description: 自定义MessagesProvider
 * Package: com.leeve.wsnewdemo.config
 *
 * @author Leeves
 * @version 1.0.0  2018-07-11
 */
public class MySuffixBasedMessagesProvider extends DefaultMessagesProvider {

    /** The default suffix used to detect request elements in the schema. */
    public static final String DEFAULT_REQUEST_SUFFIX = "Request";

    /** The default suffix used to detect response elements in the schema. */
    public static final String DEFAULT_RESPONSE_SUFFIX = "Response";

    /** The default suffix used to detect fault elements in the schema. */
    public static final String DEFAULT_FAULT_SUFFIX = "Fault";

    private String requestSuffix = DEFAULT_REQUEST_SUFFIX;

    private String responseSuffix = DEFAULT_RESPONSE_SUFFIX;

    private String faultSuffix = DEFAULT_FAULT_SUFFIX;

    /** 自定义后缀 */
    private String customSuffixStr = "Service";


    /**
     * Returns the suffix used to detect request elements in the schema.
     *
     * @see #DEFAULT_REQUEST_SUFFIX
     */
    public String getRequestSuffix() {
        return requestSuffix;
    }

    /**
     * Sets the suffix used to detect request elements in the schema.
     *
     * @see #DEFAULT_REQUEST_SUFFIX
     */
    public void setRequestSuffix(String requestSuffix) {
        Assert.hasText(requestSuffix, "'requestSuffix' must not be empty");
        this.requestSuffix = requestSuffix;
    }

    /**
     * Returns the suffix used to detect response elements in the schema.
     *
     * @see #DEFAULT_RESPONSE_SUFFIX
     */
    public String getResponseSuffix() {
        return responseSuffix;
    }

    /**
     * Sets the suffix used to detect response elements in the schema.
     *
     * @see #DEFAULT_RESPONSE_SUFFIX
     */
    public void setResponseSuffix(String responseSuffix) {
        Assert.hasText(responseSuffix, "'responseSuffix' must not be empty");
        this.responseSuffix = responseSuffix;
    }

    /**
     * Returns the suffix used to detect fault elements in the schema.
     *
     * @see #DEFAULT_FAULT_SUFFIX
     */
    public String getFaultSuffix() {
        return faultSuffix;
    }

    /**
     * Sets the suffix used to detect fault elements in the schema.
     *
     * @see #DEFAULT_FAULT_SUFFIX
     */
    public void setFaultSuffix(String faultSuffix) {
        Assert.hasText(faultSuffix, "'faultSuffix' must not be empty");
        this.faultSuffix = faultSuffix;
    }

    public String getCustomSuffixStr() {
        return customSuffixStr;
    }

    public void setCustomSuffixStr(String customSuffixStr) {
        this.customSuffixStr = customSuffixStr;
    }

    /**
     * 判断自定义后缀是element元素
     */
    @Override
    protected boolean isMessageElement(Element element) {
        if (super.isMessageElement(element)) {
            String elementName = getElementName(element);
            Assert.hasText(elementName, "Element has no name");
            return elementName.endsWith(getRequestSuffix()) || elementName.endsWith(getResponseSuffix()) ||
                    elementName.endsWith(getFaultSuffix()) || elementName.endsWith(getCustomSuffixStr());
        }
        else {
            return false;
        }
    }
}