/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.salesforce.transputs;

/**
 *
 * @author martin
 */
public interface CreateAccountRequest extends SalesforceRequest {

    public String getAccountName();

    public void setAccountName(String accountName);
}
