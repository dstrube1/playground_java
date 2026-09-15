package com.dstrube.mediaplayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class MediaLibrary {

    private static final List<String> AUDIO_EXTENSIONS = List.of(
            ".mp3"
    );

    private static final List<String> VIDEO_EXTENSIONS = List.of(
            ".mp4"
    );

    private final Path rootDirectory;

    public MediaLibrary(Path rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public List<MediaFile> scan() throws IOException {

        if (!Files.isDirectory(rootDirectory)) {
            throw new IllegalArgumentException(
                    "Not a directory: " + rootDirectory
            );
        }

        List<MediaFile> mediaFiles = new ArrayList<>();

		// Recursively walk the directory tree
        try (var paths = Files.walk(rootDirectory)) {

            paths
            		// Eliminate directories
                    .filter(Files::isRegularFile)
                    // Convert a Path into our MediaFile abstraction
                    .map(this::createMediaFile)
                    // Unknown file types are ignored
                    .filter(file -> file != null)
                    .forEach(mediaFiles::add);
        }

        mediaFiles.sort(
                Comparator.comparing(
                        MediaFile::getFileName,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        return mediaFiles;
    }

    private MediaFile createMediaFile(Path path) {

        String fileName = path.getFileName()
                .toString()
                .toLowerCase(Locale.ROOT);

        for (String extension : AUDIO_EXTENSIONS) {
            if (fileName.endsWith(extension)) {
                return new MediaFile(
                        path,
                        MediaFile.MediaType.AUDIO
                );
            }
        }

        for (String extension : VIDEO_EXTENSIONS) {
            if (fileName.endsWith(extension)) {
                return new MediaFile(
                        path,
                        MediaFile.MediaType.VIDEO
                );
            }
        }

        return null;
    }
}