/**
 * Copyright 2016 Xcase All rights reserved.
 */
package com.xcase.sharepoint.objects;

/**
 *
 * @author martin
 */
public interface SharepointFile {

    /**
     * @return the eTag
     */
    public String getETag();

    /**
     * @param eTag the eTag to set
     */
    public void setETag(String eTag);

    /**
     * @return the fileId
     */
    public String getFileId();

    /**
     * @param fileId the fileId to set
     */
    public void setFileId(String fileId);

    /**
     * @return the fileName
     */
    public String getFileName();

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName);

    /**
     * @return the folderId
     */
    public String getFolderId();

    /**
     * @param folderId the folderId to set
     */
    public void setFolderId(String folderId);

    /**
     * @return the shared
     */
    public boolean isShared();

    /**
     * @param shared the shared to set
     */
    public void setShared(boolean shared);

    /**
     * @return the sharedName
     */
    public String getSharedName();

    /**
     * @param sharedName the sharedName to set
     */
    public void setSharedName(String sharedName);

    /**
     * @return the size
     */
    public long getSize();

    /**
     * @param size the size to set
     */
    public void setSize(long size);

    /**
     * @return the description
     */
    public String getDescription();

    /**
     * @param description the description to set
     */
    public void setDescription(String description);

    /**
     * @return the sha1
     */
    public String getSha1();

    /**
     * @param sha1 the sha1 to set
     */
    public void setSha1(String sha1);

    /**
     * @return the created
     */
    public long getCreated();

    /**
     * @param created the created to set
     */
    public void setCreated(long created);

    /**
     * @return the updated
     */
    public long getUpdated();

    /**
     * @param updated the updated to set
     */
    public void setUpdated(long updated);

    /**
     * @return the valueUrl
     */
    public String getValueUrl();

    /**
     * @param valueUrl the valueUrl to set
     */
    public void setValueUrl(String valueUrl);

    public void populateFromXmlNode(org.dom4j.Node fileNode);
}
