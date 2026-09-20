package com.dstrube.mediaplayer;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MediaPlayerController {

	// The currently active player
    private MediaPlayer player;
    
    // Callback to automatically play next track
    private Runnable onTrackFinished;
    
    private Runnable onTrackReady;

    public void play(MediaFile mediaFile) {
		// First, ensure the previous player is cleaned up before creating the new one.
        stop();

        Media media = new Media(
                mediaFile.getPath().toUri().toString()
        );

        player = new MediaPlayer(media);

		player.setOnReady(() -> {
		    player.play();
		});

        player.setOnEndOfMedia(() -> {
            System.out.println("Playback finished.");

            player.dispose();
            player = null;
            if (onTrackFinished != null) {
	        	onTrackFinished.run();
    		}
        });

        player.setOnError(() -> {
            System.err.println("MediaPlayer error:");
            System.err.println(player.getError());
        });

        media.setOnError(() -> {
            System.err.println("Media error:");
            System.err.println(media.getError());
        });
    }

    public void pause() {

        if (player != null) {
            player.pause();
        }
    }

    public void resume() {

        if (player != null) {
            player.play();
        }
    }

    public void stop() {

        if (player != null) {
            player.stop();
            player.dispose();
            player = null;
        }
    }

    public boolean isPlaying() {
        return player != null
                && player.getStatus() == MediaPlayer.Status.PLAYING;
    }
    
    public void setOnTrackFinished(Runnable callback) {
    	this.onTrackFinished = callback;
	}
	
	public void setOnTrackReady(Runnable callback) {
	    this.onTrackReady = callback;
	}
}