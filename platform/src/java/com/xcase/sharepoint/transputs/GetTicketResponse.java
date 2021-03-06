/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.sharepoint.transputs;

/**
 *
 * @author martin
 */
public interface GetTicketResponse extends SharepointResponse {

    /**
     * @return the ticket
     */
    public String getTicket();

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(String ticket);
}
