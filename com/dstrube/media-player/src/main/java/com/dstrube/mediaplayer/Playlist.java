package com.dstrube.mediaplayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Playlist {

    private final List<MediaFile> mediaFiles;

    private int currentIndex = -1;

    public Playlist(List<MediaFile> mediaFiles) {

        this.mediaFiles = new ArrayList<>(mediaFiles);

        if (!this.mediaFiles.isEmpty()) {
            currentIndex = 0;
        }
    }

    public int size() {
        return mediaFiles.size();
    }

    public boolean isEmpty() {
        return mediaFiles.isEmpty();
    }

    public MediaFile get(int index) {

        if (index < 0 || index >= mediaFiles.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid playlist index: " + index
            );
        }

        return mediaFiles.get(index);
    }

    public MediaFile current() {

        if (currentIndex < 0) {
            return null;
        }

        return mediaFiles.get(currentIndex);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int index) {

        if (index < 0 || index >= mediaFiles.size()) {
            throw new IndexOutOfBoundsException(
                    "Invalid playlist index: " + index
            );
        }

        currentIndex = index;
    }

    public boolean hasNext() {

        return currentIndex >= 0
                && currentIndex < mediaFiles.size() - 1;
    }

    public boolean hasPrevious() {

        return currentIndex > 0;
    }

    public MediaFile next() {

        if (!hasNext()) {
            return null;
        }

        currentIndex++;

        return current();
    }

    public MediaFile previous() {

        if (!hasPrevious()) {
            return null;
        }

        currentIndex--;

        return current();
    }

    public List<MediaFile> getMediaFiles() {

        return List.copyOf(mediaFiles);
    }
    
    public enum SortOrder {
 	   NAME_ASCENDING,
	    NAME_DESCENDING,
    	DURATION_ASCENDING,
    	DURATION_DESCENDING
	}
	
	public void sort(
        SortOrder sortOrder,
        MediaMetadataCache metadataCache) {

	    MediaFile currentFile = current();

    	Comparator<MediaFile> comparator;

	    switch (sortOrder) {

    	    case NAME_ASCENDING:
        	    comparator = Comparator.comparing(
                    MediaFile::getFileName,
                    String.CASE_INSENSITIVE_ORDER
            	);
            	break;

	        case NAME_DESCENDING:
    	        comparator = Comparator.comparing(
                    MediaFile::getFileName,
                    String.CASE_INSENSITIVE_ORDER
        	    ).reversed();
            	break;

	        case DURATION_ASCENDING:
    	        comparator = Comparator.comparingDouble(
                    metadataCache::getDurationSeconds
            	);
        	    break;

	        case DURATION_DESCENDING:
    	        comparator = Comparator.comparingDouble(
                    metadataCache::getDurationSeconds
        	    ).reversed();
            	break;

	        default:
    	        throw new IllegalArgumentException(
                    "Unknown sort order: " + sortOrder
        	    );
	    }

    	mediaFiles.sort(comparator);

	    if (currentFile != null) {
        	currentIndex = mediaFiles.indexOf(currentFile);
    	}
	}
}