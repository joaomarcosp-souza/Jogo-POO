package br.ifpr.paranavai.jogo.util;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenSize {
    public int WIDTH_SCREEN;
    public int HEIGHT_SCREEN;
    public Dimension SCREEN;

    public void carregar() {
        SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH_SCREEN = (int) SCREEN.getWidth();
        HEIGHT_SCREEN = (int) SCREEN.getHeight();
    }

    // GETTERS E SETTERS
    public int getWIDTH_SCREEN() {
        return WIDTH_SCREEN;
    }

    public void setWIDTH_SCREEN(int lARGURA_TELA) {
        WIDTH_SCREEN = lARGURA_TELA;
    }

    public int getHEIGHT_SCREEN() {
        return HEIGHT_SCREEN;
    }

    public void setHEIGHT_SCREEN(int aLTURA_TELA) {
        HEIGHT_SCREEN = aLTURA_TELA;
    }
}
