package scripts.display.utils;

import scripts.display.resources.Resources;

import javax.sound.midi.*;
import javax.sound.sampled.*;
import java.io.IOException;

public class MusicPlayer {

    public static Sequencer sequencer;
    public static Sequence sequence;

    /**
     * Fonction qui joue une musique en boucle.
     */
    public static void playMusic() {
        try {
            sequencer = MidiSystem.getSequencer(); // Get the default Sequencer
            if (sequencer == null) {
                System.err.println("Sequencer device not supported");
            }
            sequencer.open(); // Open device
            // Create sequence, the File must contain MIDI file data.
            sequence = MidiSystem.getSequence(Resources.MUSICS.get("music"));
            sequencer.setSequence(sequence); // load it into sequencer
            sequencer.start();  // start the playback
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        } catch (MidiUnavailableException | InvalidMidiDataException | IOException ex) {
            ex.printStackTrace();
        }


    }

    public static void stopMusic() {
        sequencer.stop();
    }

}
