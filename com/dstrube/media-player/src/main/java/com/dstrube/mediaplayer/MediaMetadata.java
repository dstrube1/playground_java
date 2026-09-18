package com.dstrube.mediaplayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.concurrent.CountDownLatch;

public class MediaMetadata {

	public static double getDurationSeconds(MediaFile mediaFile) {

        Media media = new Media(
                mediaFile.getPath().toUri().toString()
        );

        MediaPlayer player = new MediaPlayer(media);

        CountDownLatch latch = new CountDownLatch(1);

        final double[] duration = { -1 };

        player.setOnReady(() -> {

            duration[0] = media.getDuration().toSeconds();

            latch.countDown();
        });

        player.setOnError(latch::countDown);

        try {
            latch.await();
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            player.dispose();

            return -1;
        }

        player.dispose();

        return duration[0];
    }

    public static String formatDuration(double totalSeconds) {
		
		if (totalSeconds < 0) {
            return "Unknown";
        }
        
        int totalSecondsInt = (int) totalSeconds;

        int minutes = totalSecondsInt / 60;
        int seconds = totalSecondsInt % 60;

        return String.format(
                "%d:%02d",
                minutes,
                seconds
        );
    }
}
/*
JavaFX loads the media metadata asynchronously, while the console wants to 
display it synchronously.
*/
