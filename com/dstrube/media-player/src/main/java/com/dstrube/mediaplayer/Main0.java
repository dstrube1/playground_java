/*
To run with just one song as input:
1: rename this to Main
2:
mvn javafx:run -Djavafx.args="/Users/dstrube/Projects/AudioParser/inputs/chill/Max Richter - Autumn Music 1.mp3"

*/

package com.dstrube.mediaplayer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main0 extends Application {

    private MediaPlayer player;

    @Override
    public void start(Stage stage) {

        Parameters parameters = getParameters();

        if (parameters.getRaw().isEmpty()) {
            System.err.println("No media file specified.");
            Platform.exit();
            return;
        }

        Path audioFile = Path.of(
                String.join(" ", parameters.getRaw())
        );

        if (!Files.exists(audioFile)) {
            System.err.println("File does not exist: " + audioFile);
            Platform.exit();
            return;
        }

        System.out.println("Loading: " + audioFile);

        Media media = new Media(audioFile.toUri().toString());

        player = new MediaPlayer(media);

        player.setOnReady(() -> {
            System.out.println("Media is ready.");
            System.out.println("Duration: " + media.getDuration());
            System.out.println("Playing...");

            player.play();
        });

        player.setOnEndOfMedia(() -> {
            System.out.println("Playback finished.");

            player.dispose();
            Platform.exit();
        });

        player.setOnError(() -> {
            System.err.println("MediaPlayer error:");
            System.err.println(player.getError());

            player.dispose();
            Platform.exit();
        });

        media.setOnError(() -> {
            System.err.println("Media error:");
            System.err.println(media.getError());

            Platform.exit();
        });
    }

    @Override
    public void stop() {
        if (player != null) {
            player.dispose();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

