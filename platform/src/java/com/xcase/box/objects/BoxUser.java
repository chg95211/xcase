/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.box.objects;

/**
 *
 * @author martin
 */
public interface BoxUser {

    /**
     * @return the login
     */
    public String getLogin();

    /**
     * @param login the login to set
     */
    public void setLogin(String login);

    /**
     * @return the name
     */
    public String getName();

    /**
     * @param name the name to set
     */
    public void setName(String name);

    /**
     * @return the email
     */
    public String getEmail();

    /**
     * @param email the email to set
     */
    public void setEmail(String email);

    /**
     * @return the accessId
     */
    public String getAccessId();

    /**
     * @param accessId the accessId to set
     */
    public void setAccessId(String accessId);

    /**
     * @return the userId
     */
    public String getUserId();

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId);

    /**
     * @return the spaceAmount
     */
    public long getSpaceAmount();

    /**
     * @param spaceAmount the spaceAmount to set
     */
    public void setSpaceAmount(long spaceAmount);

    /**
     * @return the spaceUsed
     */
    public long getSpaceUsed();

    /**
     * @param spaceUsed the spaceUsed to set
     */
    public void setSpaceUsed(long spaceUsed);
}
