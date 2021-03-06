/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.box.impl.simple.transputs;

import com.xcase.box.constant.BoxConstant;
import com.xcase.box.transputs.GetFolderItemsRequest;

/**
 *
 * @author martin
 */
public class GetFolderItemsRequestImpl extends BoxRequestImpl implements GetFolderItemsRequest {

    private String folderId;

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
     * @return action name
     */
    public String getActionName() {
        return BoxConstant.ACTION_NAME_GET_FOLDER_ITEMS;
    }
}
