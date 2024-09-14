package com.project.youtubedownloader.YoutubeDownloader.model;

public class DownloadRequest {
    private String url;
    private String format;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
