package com.dstrube.mediaplayer;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class MediaMetadataCache {

    private final Map<Path, String> durationCache = new HashMap<>();

    public String getDuration(MediaFile mediaFile) {

        Path path = mediaFile.getPath();

        String cachedDuration = durationCache.get(path);

        if (cachedDuration != null) {
            return cachedDuration;
        }

        String duration = MediaMetadata.getDuration(mediaFile);

        durationCache.put(path, duration);

        return duration;
    }

    public void clear() {
        durationCache.clear();
    }
}