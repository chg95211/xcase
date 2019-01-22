/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.sharepoint.impl.simple.transputs;

import com.xcase.sharepoint.constant.SharepointConstant;
import com.xcase.sharepoint.transputs.SetDescriptionRequest;

/**
 * @author martin
 *
 */
public class SetDescriptionRequestImpl extends SharepointRequestImpl implements SetDescriptionRequest {

    /**
     * auth token.
     */
    private String authToken;

    /**
     * target, could be 'file' or 'folder'.
     */
    private String target;

    /**
     * target id, file id or folder id.
     */
    private String targetId;

    /**
     * description.
     */
    private String description;

    /**
     * @return the authToken
     */
    public String getAuthToken() {
        return this.authToken;
    }

    /**
     * @param authToken the authToken to set
     */
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return this.target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return the targetId
     */
    public String getTargetId() {
        return this.targetId;
    }

    /**
     * @param targetId the targetId to set
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return action name
     */
    public String getActionName() {
        return SharepointConstant.ACTION_NAME_SET_DESCRIPTION;
    }
}
