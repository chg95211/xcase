/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.open.impl.simple.transputs;

import com.xcase.open.transputs.CreateMatterAttachmentResponse;

public class CreateMatterAttachmentResponseImpl implements CreateMatterAttachmentResponse {

    private int id;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int attachmentID) {
        this.id = id;
    }

}
