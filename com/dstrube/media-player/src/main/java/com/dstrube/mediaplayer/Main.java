/*
To compile / run (from java/com/dstrube/media-player):
mvn clean compile
mvn javafx:run
mvn javafx:run -Djavafx.args="/Path/to/music"
e.g.: 
mvn javafx:run -Djavafx.args="/Users/dstrube/Projects/AudioParser/inputs/rock"
mvn javafx:run -Djavafx.args="/Users/dstrube/Projects/AudioParser/inputs/chill"
*/

package com.dstrube.mediaplayer;

import javafx.application.Application;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;

public class Main extends Application {

    private MediaPlayerController controller;

    @Override
    public void start(Stage stage) {
		List<String> args = getParameters().getRaw();
		final String defaultPath = "/Users/dstrube/Projects/AudioParser/inputs/rock";
		Path musicDirectory;
		if (args.isEmpty()) {
			musicDirectory = Path.of(defaultPath);
 		}else{
 			musicDirectory = Path.of(args.get(0));
	 		 if (!Files.isDirectory(musicDirectory)) {
			    /*
			    This might be the more professional way of bailing out, 
			    but it's too ugly on the Terminal for my simple needs:
			    throw new IllegalArgumentException("Not a directory: " + musicDirectory);
	        	*/
	 		 	System.err.println("ERROR: Not a directory: " + musicDirectory +"\n");
	 		 	// we're not really error free at this point (vvv), but again, ugly output = bad
	 		 	System.exit(0); 
	 		 	// Redundant but clear:
	 		 	return;
			} 
 		}

        MediaLibrary library = new MediaLibrary(musicDirectory);

        controller = new MediaPlayerController();

        ConsoleUI consoleUI = new ConsoleUI(library, controller);

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
    	// launch() comes from JavaFX's Application class
        launch(args);
        // => "Start the JavaFX application framework, and eventually create my Main application."
		// JavaFX then calls:
		//start(Stage stage)
    }
}