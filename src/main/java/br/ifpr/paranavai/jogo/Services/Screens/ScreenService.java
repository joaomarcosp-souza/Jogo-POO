package br.ifpr.paranavai.jogo.Services.Screens;

import java.awt.event.KeyEvent;

public interface ScreenService {
    public abstract void visibilityControlMenu(KeyEvent e);
    public abstract void visibilityControlScreens(KeyEvent e);
    public abstract void visibilityScreenPause(KeyEvent e);
    public abstract void visibilityControlEndGame(KeyEvent e);
}
