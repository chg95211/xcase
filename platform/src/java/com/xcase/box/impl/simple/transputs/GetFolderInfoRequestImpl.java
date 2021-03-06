/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.box.impl.simple.transputs;

import com.xcase.box.constant.BoxConstant;
import com.xcase.box.transputs.GetFolderInfoRequest;

/**
 *
 * @author martin
 */
public class GetFolderInfoRequestImpl extends BoxRequestImpl implements GetFolderInfoRequest {

    /**
     * auth token.
     */
    private String authToken;

    /**
     * parent folder id.
     */
    private String folderId;

    /**
     * folder name.
     */
    private String folderName;

    /**
     * share this folder or not.
     */
    private boolean share;

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
     * @return the folderId
     */
    public String getFolderId() {
        return this.folderId;
    }

    /**
     * @param folderId the folderId to set
     */
    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    /**
     * @return the folderName
     */
    public String getFolderName() {
        return this.folderName;
    }

    /**
     * @param folderName the folderName to set
     */
    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    /**
     * @return the share
     */
    public boolean isShare() {
        return this.share;
    }

    /**
     * @param share the share to set
     */
    public void setShare(boolean share) {
        this.share = share;
    }

    /**
     * @return action name
     */
    public String getActionName() {
        return BoxConstant.ACTION_NAME_CREATE_FOLDER;
    }
}
