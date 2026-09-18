/*
Display directory listing

To run:
1: rename this to Main
2:
mvn clean compile
java -cp target/classes com.dstrube.mediaplayer.Main
*/
package com.dstrube.mediaplayer;

import java.nio.file.Path;
import java.util.List;

public class Main1 {

    public static void main(String[] args) throws Exception {

        Path musicDirectory = Path.of(
                "/Users/dstrube/Projects/AudioParser/inputs/chill"
        );

        MediaLibrary library = new MediaLibrary(musicDirectory);

        List<MediaFile> mediaFiles = library.scan();

        System.out.println("Media files found: " + mediaFiles.size());

        for (int i = 0; i < mediaFiles.size(); i++) {

            MediaFile file = mediaFiles.get(i);

            System.out.printf(
                    "%d. %s [%s]%n",
                    i + 1,
                    file.getFileName()
            );
        }
    }
}