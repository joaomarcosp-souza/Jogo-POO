package br.ifpr.paranavai.jogo.Util;

import java.net.URL;
import java.util.HashMap;
import java.io.IOException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds {

    private HashMap<String, Clip> clipSounds = new HashMap<>();
    public void loadSound(String soundName) {
        try {
            if (!clipSounds.containsKey(soundName)) {
                URL urlPath = this.getClass()
                        .getResource("/Sounds/" + soundName);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(urlPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clipSounds.put(soundName, clip);
            }

            Clip playSound = clipSounds.get(soundName);
            if (playSound.isRunning()) {
                playSound.stop();
            }
            playSound.setFramePosition(0);
            playSound.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
