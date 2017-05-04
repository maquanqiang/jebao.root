package com.jebao.erp.web.utils.contract;

/**
 * Created by Administrator on 2016/12/19.
 */
public class PdfInfo {

    private Long iiId;
    private String url;
    private String fileName;

    public Long getIiId() {
        return iiId;
    }

    public void setIiId(Long iiId) {
        this.iiId = iiId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        PdfInfo other = (PdfInfo)obj;
        if (url == null) {
            if (other.url != null) return false;
        } else if (!url.equals(other.url)) return false;
        if (fileName == null) {
            if (other.fileName != null) return false;
        } else if (!fileName.equals(other.fileName)) return false;
        return true;
    }
}

