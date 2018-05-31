package com.mtit.minhtien.api_youtubeapi_playlist;

/**
 * Created by MT IT on 12/3/2017.
 */

public class VideoYoutube {
    private String title;
    private String thumbnail;
    private String idVideo;

    public VideoYoutube(String title, String thumbnail, String idVideo) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.idVideo = idVideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }
}
