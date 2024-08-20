package com.loja.projetolojaweb2.VO;

import java.io.Serializable;

public class UploadFileResponseVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponseVO() {
    }

    public UploadFileResponseVO(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
}
