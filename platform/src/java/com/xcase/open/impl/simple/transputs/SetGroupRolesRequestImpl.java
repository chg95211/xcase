/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.open.impl.simple.transputs;

import com.xcase.open.transputs.SetGroupRolesRequest;

public class SetGroupRolesRequestImpl extends OpenRequestImpl implements SetGroupRolesRequest {

    private String groupId;
    private String[] roleArray;

    @Override
    public String getGroupId() {
        return this.groupId;
    }

    @Override
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public String[] getRoleArray() {
        return this.roleArray;
    }

    @Override
    public void setRoleArray(String[] roleArray) {
        this.roleArray = roleArray;
    }

}
