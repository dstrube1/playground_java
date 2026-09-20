package com.dstrube.mediaplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Playlist {

    private final List<MediaFile> mediaFiles;

    private int currentIndex = -1;
    
    private SortOrder sortOrder = SortOrder.NAME_ASCENDING;

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
	
	public SortOrder getSortOrder() {
    	return sortOrder;
	}
	
	public void sort(
        SortOrder sortOrder,
        MediaMetadataCache metadataCache) {
        
        this.sortOrder = sortOrder;

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
    	        comparator = createDurationComparator(
                    metadataCache, 
                    false
            	);
        	    break;

	        case DURATION_DESCENDING:
    	        comparator = createDurationComparator(
                    metadataCache,
                    true
        	    );
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
	
	private Comparator<MediaFile> createDurationComparator(
    	    MediaMetadataCache metadataCache,
        	boolean descending) {

	    return (file1, file2) -> {

    	    double duration1 =
                metadataCache.getDurationSeconds(file1);

	        double duration2 =
                metadataCache.getDurationSeconds(file2);

    	    boolean unknown1 = duration1 < 0;
        	boolean unknown2 = duration2 < 0;

	        // Unknown durations always go to the end.
    	    if (unknown1 && unknown2) {
            	return 0;
        	}

	        if (unknown1) {
        	    return 1;
    	    }

	        if (unknown2) {
        	    return -1;
    	    }

	        if (descending) {
        	    return Double.compare(duration2, duration1);
    	    }

        	return Double.compare(duration1, duration2);
    	};
	}
	
	public void shuffle() {
	    MediaFile currentFile = current();
	    Collections.shuffle(mediaFiles);
	    if (currentFile != null) {
    	    currentIndex = mediaFiles.indexOf(currentFile);
    	}
	}
}








