/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.salesforce.transputs;

/**
 *
 * @author martin
 */
public interface GetAccountRequest extends SalesforceRequest {

    public String getAccountId();

    public void setAccountId(String accountId);
}
