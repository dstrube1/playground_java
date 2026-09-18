/*
This class represents one media file.
*/

package com.dstrube.mediaplayer;

import java.nio.file.Path;

public class MediaFile {

    private final Path path;

    public MediaFile(Path path){ 
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public String getFileName() {
        return path.getFileName().toString();
    }

    @Override
    public String toString() {
        return getFileName();
    }
}