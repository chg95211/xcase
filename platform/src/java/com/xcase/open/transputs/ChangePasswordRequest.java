/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.open.transputs;

/**
 *
 * @author martin
 */
public interface ChangePasswordRequest {

    String getUserId();

    void setUserId(String userId);

    String getPassword();

    void setPassword(String password);
}
