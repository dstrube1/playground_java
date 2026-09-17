package com.dstrube.mediaplayer;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private final MediaLibrary library;
    private final MediaPlayerController controller;
    private Playlist playlist;
    private final MediaMetadataCache metadataCache;
    private final Scanner scanner;

    public ConsoleUI(
            MediaLibrary library,
            MediaPlayerController controller) {

        this.library = library;
        this.controller = controller;
        metadataCache = new MediaMetadataCache();
        scanner = new Scanner(System.in);
    }

    public void run() {

        try {
		    List<MediaFile> mediaFiles = library.scan();
		    playlist = new Playlist(mediaFiles);
	        controller.setOnTrackFinished(this::playNextAutomatically);
		} catch (Exception e) {
            System.err.println(
                    "Could not scan media directory:"
            );
            System.err.println(e.getMessage());
            return;
        }

        printWelcome();


            boolean running = true;

        while (running) {

            printMenu();

            System.out.print("> ");

            String command = scanner.nextLine()
                    .trim()
                    .toLowerCase();

            switch (command) {

                case "1":
                    listMedia();
                    break;

                case "2":
                    playMedia();
                    break;

                case "3":
                    controller.pause();
                    System.out.println("Paused.");
                    break;

                case "4":
                    controller.resume();
                    System.out.println("Resumed.");
                    break;

                case "5":
                    controller.stop();
                    System.out.println("Stopped.");
                    break;

                case "6":
				    playNext();
					break;

				case "7":
				    playPrevious();
				    break;

				case "8":
					sortMedia();//scanner);
					break;

				case "9":
                    controller.stop();
				    running = false;
				    break;

                default:
                    System.out.println(
                            "Unknown command."
                    );
            }
        }

        controller.stop();

        System.out.println("Goodbye.");
        System.exit(0);
    }

    private void printWelcome() {

        System.out.println();
        System.out.println("==========================");
        System.out.println("     Java Media Player");
        System.out.println("==========================");
        System.out.println();
    }

    private void printMenu() {

        System.out.println();
        System.out.println("1. List media");
        System.out.println("2. Play");
        System.out.println("3. Pause");
        System.out.println("4. Resume");
        System.out.println("5. Stop");
        System.out.println("6. Next");
		System.out.println("7. Previous");
		System.out.println("8. Sort");
		System.out.println("9. Quit");
    }

	private void listMedia() {
	    if (playlist.isEmpty()) {
        	System.out.println("No media files found.");
        	return;
    	}

	    System.out.println();
    	System.out.println("Media:");

	    for (int i = 0; i < playlist.size(); i++) {
	        MediaFile file = playlist.get(i);
	        String marker =
                i == playlist.getCurrentIndex()
                        ? "*"
                        : " ";

	        String duration = metadataCache.getFormattedDuration(file);

			System.out.printf(
			        "%s %d. %s [%s]%n",
			        marker,
			        i + 1,
			        file.getFileName(),
			        duration
			);
    	}
	}

    private void playMedia() {

        if (playlist.isEmpty()) {
            System.out.println("No media files available.");
            return;
        }

        listMedia();

        System.out.print("Select a media file: ");

        String input = scanner.nextLine().trim();

        try {
            int selection = Integer.parseInt(input);

            if (selection < 1 || selection > playlist.size()) {
                System.out.println("Invalid selection.");
                return;
            }

            MediaFile selected = playlist.get(selection - 1);

			playlist.setCurrentIndex(selection - 1);

            controller.play(selected);

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a number."
            );
        }
    }
    
    private void playNext() {
	    MediaFile next = playlist.next();
	    if (next == null) {
	        System.out.println("Already at the end of the playlist.");
	        return;
	    }
	    controller.play(next);
	}
	
	private void playPrevious() {
	    MediaFile previous = playlist.previous();
    	if (previous == null) {
        	System.out.println("Already at the beginning of the playlist.");
    	    return;
	    }
    	controller.play(previous);
	}
	
	private void playNextAutomatically() {
	    MediaFile next = playlist.next();

    	if (next == null) {
	        System.out.println("Reached the end of the playlist.");
        	return;
    	}

	    System.out.println("Automatically playing: " + next.getFileName());
    	controller.play(next);
	}

	private void sortMedia(){ //Scanner scanner) {

    	if (playlist.isEmpty()) {
        	System.out.println("The playlist is empty.");
    	    return;
	    }

	    System.out.println();
    	System.out.println("Sort media by:");
	    System.out.println("1. Name - ascending");
    	System.out.println("2. Name - descending");
	    System.out.println("3. Duration - ascending");
    	System.out.println("4. Duration - descending");
	    System.out.print("Choose an option: ");

    	String input = scanner.nextLine();

	    Playlist.SortOrder sortOrder;

	    switch (input) {

    	    case "1":
        	    sortOrder = Playlist.SortOrder.NAME_ASCENDING;
            	break;

	        case "2":
    	        sortOrder = Playlist.SortOrder.NAME_DESCENDING;
        	    break;

	        case "3":
    	        sortOrder = Playlist.SortOrder.DURATION_ASCENDING;
        	    break;

	        case "4":
    	        sortOrder = Playlist.SortOrder.DURATION_DESCENDING;
        	    break;

    	    default:
        	    System.out.println("Invalid sort option.");
            	return;
	    }

	    playlist.sort(sortOrder, metadataCache);

	    System.out.println("Media sorted.");

	    listMedia();
	}
}



















