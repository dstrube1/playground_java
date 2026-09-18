/*
To run:
1: rename this to Main
2:
mvn clean compile
mvn javafx:run

What's going on:
main()
  │
  ▼
launch()
  │
  ▼
JavaFX initializes
  │
  ▼
start()
  │
  ▼
MediaPlayerController.play()
  │
  ▼
JavaFX keeps application alive
  │
  ▼
MP3 plays to completion

Note, this doesn't cleanly shutdown after completion

*/
package com.dstrube.mediaplayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.nio.file.Path;

public class Main2 extends Application {

    private MediaPlayerController controller;

    @Override
    public void start(Stage stage) {

        Path audioFile = Path.of(
                "/Users/dstrube/Projects/AudioParser/inputs/chill/Max Richter - Autumn Music 1.mp3"
        );

        MediaFile mediaFile = new MediaFile(
                audioFile
        );

        controller = new MediaPlayerController();

        controller.play(mediaFile);
    }

    @Override
    public void stop() {

        if (controller != null) {
            controller.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}