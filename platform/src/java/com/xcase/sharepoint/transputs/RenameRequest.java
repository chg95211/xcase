/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.sharepoint.transputs;

/**
 *
 * @author martin
 */
public interface RenameRequest extends SharepointRequest {

    /**
     * @return the authToken
     */
    public String getAuthToken();

    /**
     * @param authToken the authToken to set
     */
    public void setAuthToken(String authToken);

    /**
     * @return the target
     */
    public String getTarget();

    /**
     * @param target the target to set
     */
    public void setTarget(String target);

    /**
     * @return the targetId
     */
    public String getTargetId();

    /**
     * @param targetId the targetId to set
     */
    public void setTargetId(String targetId);

    /**
     * @return the newName
     */
    public String getNewName();

    /**
     * @param newName the newName to set
     */
    public void setNewName(String newName);
}
