/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.box.transputs;

import java.util.List;

/**
 *
 * @author martin
 */
public interface UploadResponse extends BoxResponse {

    /**
     * the UploadResult list.
     *
     * @return the uploadResultList
     */
    public List getUploadResultList();

    /**
     * @param uploadResultList the uploadResultList to set
     */
    public void setUploadResultList(List uploadResultList);
}
