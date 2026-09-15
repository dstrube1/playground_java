/*
To run:
mvn clean compile
mvn javafx:run
*/

package com.dstrube.mediaplayer;

import javafx.application.Application;
import javafx.stage.Stage;

import java.nio.file.Path;

public class Main extends Application {

    private MediaPlayerController controller;

    @Override
    public void start(Stage stage) {

        Path musicDirectory = Path.of(
                "/Users/dstrube/Projects/AudioParser/inputs/chill"
        );

        MediaLibrary library =
                new MediaLibrary(musicDirectory);

        controller =
                new MediaPlayerController();

        ConsoleUI consoleUI =
                new ConsoleUI(library, controller);

		// Don't want this on the main thread
        //consoleUI.run();
        Thread consoleThread = new Thread(consoleUI::run);

		consoleThread.setDaemon(false);
		consoleThread.start();
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