Structure:

media-player/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── mediaplayer/
                        ├── Main.java
                        ├── MediaPlayerApp.java
                        ├── MediaLibrary.java
                        ├── MediaFile.java
                        └── ConsoleUI.java

Main.java:
Application entry point

MediaLibrary.java:
Scans the folder
Finds MP3/MP4 files
Maintains the media list

MediaFile.java:
Represents an individual media file
Filename, path, type, etc.

MediaPlayerApp.java:
Controls actual playback
Play/pause/stop/next/previous/volume

ConsoleUI.java:
Reads commands from System.in
Displays menus/status information

Although the interface can be entirely console-based, JavaFX's media subsystem still uses native media components underneath. In particular, MP4 playback depends on the codecs available/supported by the JavaFX runtime.

Note:
If JavaFX doesn't work, try VLCJ next; and if not that, FFmpeg + custom playback.

Because pom.xml must be in the root folder for this project with the code somewhere below, first did this:
mkdir -p src/main/java/com/dstrube/mediaplayer
from here:
java/com/dstrube/media-player
Redundant? Yes.
Also necessary? Apparently.

