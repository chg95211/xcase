/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.box.impl.simple.transputs;

import com.xcase.box.constant.BoxConstant;
import com.xcase.box.transputs.RefreshAccessTokenRequest;

/**
 *
 * @author martin
 */
public class RefreshAccessTokenRequestImpl extends BoxRequestImpl implements RefreshAccessTokenRequest {

    /**
     * authorizationCode.
     */
    private String authorizationCode;

    /**
     * ticket.
     */
    private String clientSecret;

    /**
     * isEnterprise
     */
    private boolean isEnterprise = false;

    /**
     * refreshToken.
     */
    private String refreshToken;

    /**
     * ticket.
     */
    private String ticket;

    /**
     * @return the authorizationCode
     */
    public String getAuthorizationCode() {
        return authorizationCode;
    }

    /**
     * @param authorizationCode the authorizationCode to set
     */
    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    /**
     * @return the clientSecret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * @param clientSecret the clientSecret to set
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * @return the isEnterprise
     */
    public boolean getIsEnterprise() {
        return isEnterprise;
    }

    /**
     * @param isEnterprise the isEnterprise to set
     */
    public void setIsEnterprise(boolean isEnterprise) {
        this.isEnterprise = isEnterprise;
    }

    /**
     * @return the refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @param refreshToken the refreshToken to set
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * @return the ticket
     */
    /**
     * @return the ticket
     */
    public String getTicket() {
        return this.ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * @return action name
     */
    public String getActionName() {
        return BoxConstant.ACTION_NAME_GET_ACCESS_TOKEN;
    }
}
