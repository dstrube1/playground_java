/*
This class represents one media file.
*/

package com.dstrube.mediaplayer;

import java.nio.file.Path;

public class MediaFile {

    public enum MediaType {
        AUDIO,
        VIDEO
    }

    private final Path path;
    private final MediaType type;

    public MediaFile(Path path, MediaType type) {
        this.path = path;
        this.type = type;
    }

    public Path getPath() {
        return path;
    }

    public MediaType getType() {
        return type;
    }

    public String getFileName() {
        return path.getFileName().toString();
    }

    @Override
    public String toString() {
        return getFileName();
    }
}