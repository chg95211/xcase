/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.open.impl.simple.transputs;

import com.xcase.open.transputs.CreatePartyNoteResponse;

public class CreatePartyNoteResponseImpl implements CreatePartyNoteResponse {

    private int id;

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

}
