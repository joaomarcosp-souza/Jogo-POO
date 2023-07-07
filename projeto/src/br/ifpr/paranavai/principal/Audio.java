package br.ifpr.paranavai.principal;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Audio {

    private Clip clip;
    private URL musicaURL;

    public Audio() {
        musicaURL = getClass().getResource("recursos\\Audios\\teste.wav");
    }

     public void carregar() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicaURL);
            clip = AudioSystem.getClip();
            clip.open(ais);
            System.out.println("Áudio carregado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar o áudio");
        }
    }

    public void tocar() {
        if (clip != null) {
            clip.start();
        } else {
            System.out.println("Sem arquivo carregado 'TOCAR'");
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            System.out.println("Sem arquivo carregado 'LOOP'");
        }
    }

    public void parar() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
