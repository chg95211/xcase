/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.sharepoint.impl.simple.transputs;

import com.xcase.sharepoint.objects.SharepointUser;
import com.xcase.sharepoint.transputs.RefreshAccessTokenResponse;

/**
 *
 * @author martin
 */
public class RefreshAccessTokenResponseImpl extends SharepointResponseImpl implements RefreshAccessTokenResponse {

    /**
     * the authentication token.
     */
    private String accessToken;
    private int expiresIn;
    private String refreshToken;
    private String tokenType;

    /**
     * the box user.
     */
    private SharepointUser user;

    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * @param accessToken the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return the expiresIn
     */
    public int getExpiresIn() {
        return expiresIn;
    }

    /**
     * @param expiresIn the expiresIn to set
     */
    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * @return the refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @param refreshToken the authToken to set
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * @return the tokenType
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * @param tokenType the tokenType to set
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * @return the user
     */
    public SharepointUser getUser() {
        return this.user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(SharepointUser user) {
        this.user = user;
    }
}
