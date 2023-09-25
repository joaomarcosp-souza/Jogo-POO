package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Pausar extends TelasBase {
    
    private boolean paused;
    // OPÇÕES MENU
    private static final String OPTION_YES = "SIM";
    private static final String OPTION_NO = "NAO";
    private static final String MENSSAGE_MENU = "VOLTAR AO JOGO?";
    // FONTES
    private static final String PATH_IMAGE = "/Sprites/Fundos/TelaPause.png";
    private Font NEW_FONT = super.getPixel().deriveFont(Font.BOLD, super.getTamanhoFonte());

    public Pausar() {
        super.setVisibility(false);
        this.paused = false;
    }

    @Override
    public void carregar() {
        ImageIcon loading = new ImageIcon(getClass().getResource(PATH_IMAGE));
        super.setImage(loading.getImage());
        // RESIMENSIONA A IMG
        super.setImage(super.getImage().getScaledInstance(getScreenResolution().WIDTH_SCREEN, getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST));
    }

    public void menu(Graphics g) {
        int OFFSETY = 150;
        g.drawImage(super.getImage(), 0, 0, null);
        // COMEÇO DA FRASE
        g.setColor(Color.WHITE);
        g.setFont(NEW_FONT);
        FontMetrics fm = g.getFontMetrics();
        int positionInX = (getScreenResolution().WIDTH_SCREEN - fm.stringWidth(MENSSAGE_MENU)) / 2;
        int positionInY = getScreenResolution().HEIGHT_SCREEN - OFFSETY;
        g.drawString(MENSSAGE_MENU, positionInX, positionInY);
        int positionMenssage = positionInX + fm.stringWidth(MENSSAGE_MENU) / 2;

        // CENTRALIZA AS OPCÇÕES
        int widthYes = fm.stringWidth(OPTION_YES);
        int widthNo = fm.stringWidth(OPTION_NO);
        int widthTotal = widthYes + widthNo;

        // 'SIM'
        int positionYesInX = (positionMenssage - widthTotal / 2) - 10;
        int positionYesInY = positionInY + 40;
        if (super.getCursor() == 0) {
            g.setColor(getCorAmarela());
            int cursorWidth = fm.stringWidth(" ");
            g.drawString(" ", positionYesInX - cursorWidth, positionYesInY);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(OPTION_YES, positionYesInX, positionYesInY);

        // 'NÃO'
        int positionNoInX = (positionYesInX + widthYes) + 30;
        int positionNoInY = positionYesInY;
        if (super.getCursor() == 1) {
            g.setColor(getCorAmarela());
            int cursorWidth = fm.stringWidth(" ");
            g.drawString(" ", positionNoInX - cursorWidth, positionNoInY);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(OPTION_NO, positionNoInX, positionNoInY);
    }

    public void menuPausado(KeyEvent keyboard) {
        int key = keyboard.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            super.setCursor(super.getCursor() - 1);
            if (super.getCursor() < 0) {
                super.setCursor(1);
            }
        }
        if (key == KeyEvent.VK_RIGHT) {
            super.setCursor(super.getCursor() + 1);
            if (super.getCursor() > 1) {
                super.setCursor(0);
            }
        }
    }

    // GETTERS E SETTERS
    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
