/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xcase.msgraph.impl.simple.transputs;

import com.xcase.msgraph.transputs.RemoveGroupMemberRequest;

/**
 *
 * @author martin
 */
public class RemoveGroupMemberRequestImpl extends MSGraphRequestImpl implements RemoveGroupMemberRequest {
    private String groupId;
    private String memberId;

    public String getGroupId() {
        return this.groupId;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }    
}
