package ch.kalajdzic.web.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nu.pattern.OpenCV;

public class CameraPictureData {


    private String photoBase64;

    @JsonCreator
    CameraPictureData(@JsonProperty("photoBase64") String photoBase64) {
        OpenCV.loadLocally();
        this.photoBase64 = photoBase64;
    }

    public String getPhotoBase64() {
        String cleanBase64 = cleanBase64();
        return cleanBase64;
    }

    private String cleanBase64() {
        String cleanBase64 = photoBase64.replaceAll("\r", "").replaceAll("\n", "");
        return cleanBase64;
    }
}
