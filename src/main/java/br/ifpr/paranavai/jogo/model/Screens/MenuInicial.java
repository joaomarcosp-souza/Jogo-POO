package br.ifpr.paranavai.jogo.model.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class MenuInicial extends TelasBase {
    
    private Image imageTitle;
    private static final int ESPACAMENTO_OPCOES_Y = 50;
    private static final String NEW_GAME = "NOVO JOGO";
    private static final String LOAD = "CARREGAR JOGO";
    private static final String SCREEN_HISTORY = "HISTORICO";
    private static final String SCREEN_CONTROLS = "CONTROLES";
    private static final String GIF_BACKGROUND = "src/main/resources/Sprites/Fundos/FundoMenu.gif";
    private static final String PATH_IMAGE_TITLE = "src/main/resources/Sprites/Fundos/MenuPrincipal.png";
    private final Font NEW_FONT = super.getPixel().deriveFont(Font.BOLD, super.getMenuTamanho());

    public MenuInicial() {
        setVisibility(true);
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(GIF_BACKGROUND);
        setImage(loading.getImage());
        setWidthImage(getImage().getWidth(null));
        setHeightImage(getImage().getHeight(null));
        setImage(getImage().getScaledInstance(getScreenResolution().WIDTH_SCREEN, getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST));

        // IMAGEM DO TITULO
        ImageIcon loadingPathTitle = new ImageIcon(PATH_IMAGE_TITLE);
        this.imageTitle = loadingPathTitle.getImage();
        this.imageTitle = this.imageTitle.getScaledInstance(getScreenResolution().WIDTH_SCREEN,
                getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST);
    }

    public void conteudo(Graphics g) {
        g.drawImage(super.getImage(), 0, 0, null);
        g.drawImage(this.imageTitle, 0, 0, null);

        g.setFont(NEW_FONT);
        FontMetrics fm = g.getFontMetrics();
        // OPÇÃO PARA A TELA 'NOVO JOGO'
        int positionX = (getScreenResolution().WIDTH_SCREEN - fm.stringWidth(NEW_GAME)) / 2;
        int positionY = getScreenResolution().HEIGHT_SCREEN - 200;
        if (super.getCursor() == 0) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(NEW_GAME, positionX, positionY);
        if (super.getCursor() == 0) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", positionX - cursorWidth, positionY);
        } // FIM MODO FASE

        // OPÇÃO PARA A TELA 'CARREGAR JOGO'
        int infinitoPosX = (getScreenResolution().WIDTH_SCREEN - fm.stringWidth(LOAD)) / 2;
        int infinitoPosY = positionY + ESPACAMENTO_OPCOES_Y;
        if (super.getCursor() == 1) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(LOAD, infinitoPosX, infinitoPosY);
        if (super.getCursor() == 1) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", infinitoPosX - cursorWidth, infinitoPosY);
        }

        // OPÇÃO PARA A TELA 'CONTROLES'
        int controlePosX = (getScreenResolution().WIDTH_SCREEN - fm.stringWidth(SCREEN_CONTROLS)) / 2;
        int controlePosY = infinitoPosY + ESPACAMENTO_OPCOES_Y;
        if (super.getCursor() == 2) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(SCREEN_CONTROLS, controlePosX, controlePosY);
        if (super.getCursor() == 2) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", controlePosX - cursorWidth, controlePosY);
        }

        // OPÇÃO PARA A TELA 'HISTORICO'
        int historicoPosX = (getScreenResolution().WIDTH_SCREEN - fm.stringWidth(SCREEN_HISTORY)) / 2;
        int historicoPosY = controlePosY + ESPACAMENTO_OPCOES_Y;
        if (super.getCursor() == 3) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(SCREEN_HISTORY, historicoPosX, historicoPosY);
        if (super.getCursor() == 3) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", historicoPosX - cursorWidth, historicoPosY);
        }
    }

    public void controleMenu(KeyEvent keyboard) {
        int key = keyboard.getKeyCode();
        // CONTROLA A POSIÇÃO DO CURSOR
        if (key == KeyEvent.VK_UP) {
            super.setCursor(getCursor() - 1);
            if (super.getCursor() < 0) {
                super.setCursor(3);
            }
        }
        if (key == KeyEvent.VK_DOWN) {
            super.setCursor(getCursor() + 1);
            if (super.getCursor() > 3) {
                super.setCursor(0);
            }
        }
    }
}