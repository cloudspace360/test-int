package com.se.wp.library.models;

/**
 * @author ravraj.lakum File This is a POJO class for File bean
 */
/**
 * @author Vijay.Tiwari01
 *
 */
public class DocumentFile {
    private String extension;
    private String filename;
    private String id;
    private String size;
    private String downloadFileURL;
    private boolean disableDownload;
    private String checkSum;
    private boolean downloaded;

    public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}

	public boolean isDownloaded() {
		return downloaded;
	}

	public void setDownloaded(boolean downloaded) {
		this.downloaded = downloaded;
	}

	/**
     * @return
     */
    public String getDownloadFileURL() {
	return downloadFileURL;
    }

    /**
     * @param downloadFileURL
     */
    public void setDownloadFileURL(String downloadFileURL) {
	this.downloadFileURL = downloadFileURL;
    }

    /**
     * @return
     */
    public String getExtension() {
	return extension;
    }

    /**
     * @param extension
     */
    public void setExtension(String extension) {
	this.extension = extension;
    }

    /**
     * @return
     */
    public String getFilename() {
	return filename;
    }

    /**
     * @param filename
     */
    public void setFilename(String filename) {
	this.filename = filename;
    }

    /**
     * @return
     */
    public String getId() {
	return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
	this.id = id;
    }

    /**
     * @return
     */
    public String getSize() {
	return size;
    }

    /**
     * @param size
     */
    public void setSize(String size) {
	this.size = size;
    }
    
    public boolean isDisableDownload() {
		return disableDownload;
	}

	public void setDisableDownload(boolean disableDownload) {
		this.disableDownload = disableDownload;
	}
}
