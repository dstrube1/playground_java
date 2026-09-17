package com.dstrube.mediaplayer;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class MediaMetadataCache {

    private final Map<Path, Double> durationCache = new HashMap<>();

    public double getDurationSeconds(MediaFile mediaFile) {

        Path path = mediaFile.getPath();

        Double cachedDuration = durationCache.get(path);

        if (cachedDuration != null) {
            return cachedDuration;
        }

        double duration =
                MediaMetadata.getDurationSeconds(mediaFile);

        durationCache.put(path, duration);

        return duration;
    }

    public String getFormattedDuration(MediaFile mediaFile) {

        double duration = getDurationSeconds(mediaFile);

        return MediaMetadata.formatDuration(duration);
    }

    public void clear() {
        durationCache.clear();
    }
}