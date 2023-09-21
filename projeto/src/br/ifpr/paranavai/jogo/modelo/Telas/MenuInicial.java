package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class MenuInicial extends TelasBase {
    
    private Image imageTitle;
    // VARIAVEIS DE AJUSTE
    private static final int ESPACAMENTO_OPCOES_Y = 50;
    // OPCÕES DO MENU
    private static final String WAVE_GAME_MODE = "MODO ONDAS";
    private static final String SCREEN_CONTROLS = "CONTROLES";
    private static final String SCREEN_HISTORY = "HISTORICO";
    private static final String INIFINITY_GAME_MODE = "MODO INFINITO";
    // CAMINHO IMAGEM DE FUNDO
    private static final String GIF_BACKGROUND = "recursos\\Sprites\\Fundos\\FundoMenu.gif";
    private static final String PATH_IMAGE_TITLE = "recursos\\Sprites\\Fundos\\MenuPrincipal.png";
    // FONTES
    private final Font NEW_FONT = super.getPixel().deriveFont(Font.BOLD, super.getMenuTamanho());

    public MenuInicial() {
        setVisibility(true);
    }

    @Override
    public void carregar() {
        ImageIcon loading = new ImageIcon(GIF_BACKGROUND);
        setImage(loading.getImage());
        setWidthImage(getImage().getWidth(null));
        setHeightImage(getImage().getHeight(null));
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        setImage(getImage().getScaledInstance(getScreenResolution().WIDTH_SCREEN, getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST));

        // IMAGEM DO TITULO
        ImageIcon loadingPathTitle = new ImageIcon(PATH_IMAGE_TITLE);
        this.imageTitle = loadingPathTitle.getImage();
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        this.imageTitle = this.imageTitle.getScaledInstance(getScreenResolution().WIDTH_SCREEN,
                getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST);
    }

    public void conteudo(Graphics g) {
        g.drawImage(super.getImage(), 0, 0, null);
        g.drawImage(this.imageTitle, 0, 0, null);

        g.setFont(NEW_FONT);
        FontMetrics fm = g.getFontMetrics();
        // OPÇÃO PARA A TELA 'MODO FASES'
        int positionX = (getScreenResolution().WIDTH_SCREEN - fm.stringWidth(WAVE_GAME_MODE)) / 2;
        int positionY = getScreenResolution().HEIGHT_SCREEN - 200;
        if (super.getCursor() == 0) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(WAVE_GAME_MODE, positionX, positionY);
        if (super.getCursor() == 0) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", positionX - cursorWidth, positionY);
        } // FIM MODO FASE

        // OPÇÃO PARA A TELA 'MODO INFINITO'
        int infinitoPosX = (getScreenResolution().WIDTH_SCREEN - fm.stringWidth(INIFINITY_GAME_MODE)) / 2;
        int infinitoPosY = positionY + ESPACAMENTO_OPCOES_Y;
        if (super.getCursor() == 1) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(INIFINITY_GAME_MODE, infinitoPosX, infinitoPosY);
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
