package br.ifpr.paranavai.jogo.model.Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;

public class FimDeJogo extends TelasBase {
    // OPÇÕES MENU
    private static final String OPTION_YES = "SIM";
    private static final String OPTION_NO = "NAO";
    private static final String MESSAGE = "JOGAR NOVAMENTE?";
    // TITULO
    private static final String OVER = "OVER";
    private static final String GAME = "GAME";
    // FONTES
    private final Font FONT_MENU = super.getPixel().deriveFont(Font.BOLD, super.getTamanhoFonte());
    private final Font FONT_TITLE = super.getPixel().deriveFont(Font.BOLD, super.getTituloTamanho());
    // CAMINHO IMAGEM DE FUNDO
    private static final String PATH_IMAGE_GAME_OVER = "src/main/resources/Sprites/Fundos/TelaFimDeJogo.gif";

    public FimDeJogo() {
        super.setVisibility(false);
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(PATH_IMAGE_GAME_OVER);
        super.setImage(loading.getImage());
        super.setWidthImage(super.getImage().getWidth(null));
        super.setHeightImage(super.getImage().getHeight(null));
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        super.setImage(super.getImage().getScaledInstance(getScreenResolution().WIDTH_SCREEN, getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST));
    };

    public void titulo(Graphics g) {
        g.drawImage(super.getImage(), 0, 0, null);
        g.setFont(FONT_TITLE);

        FontMetrics fm = g.getFontMetrics();
        int tituloPosX = (getScreenResolution().WIDTH_SCREEN - fm.stringWidth(GAME)) / 2;
        int subTituloLargura = (fm.stringWidth(OVER));
        // EFEITO PARA O TITULO
        g.setColor(Color.WHITE);
        g.drawString(GAME, tituloPosX + 5, super.getPOSICAO_TITULO_Y());
        g.setColor(super.getCorAmarela());
        g.drawString(GAME, tituloPosX, (super.getPOSICAO_TITULO_Y()));
        // DESENHA O SUBTITULO
        g.setColor(Color.WHITE);
        g.drawString(OVER, tituloPosX + (fm.stringWidth(GAME) - subTituloLargura) / 2,
                (super.getPOSICAO_TITULO_Y() + 80));
    }

    public void menu(Graphics g) {
        int OFFSETY = 150;
        // COMEÇO DA FRASE
        g.setFont(FONT_MENU);
        FontMetrics fm = g.getFontMetrics();
        int posicaoEmX = (getScreenResolution().WIDTH_SCREEN - fm.stringWidth(MESSAGE)) / 2;
        int posicaoEmY = getScreenResolution().HEIGHT_SCREEN - OFFSETY;
        g.drawString(MESSAGE, posicaoEmX, posicaoEmY);
        int mensagemPosX = posicaoEmX + fm.stringWidth(MESSAGE) / 2;

        // CENTRALIZA AS OPCÇÕES
        int widthYes = fm.stringWidth(OPTION_YES);
        int widthNo = fm.stringWidth(OPTION_NO);
        int widthTotal = widthYes + widthNo;

        // 'SIM'
        int positionYesInX = (mensagemPosX - widthTotal / 2) - 10;
        int positionYesInY = posicaoEmY + 40;
        if (super.getCursor() == 0) {
            g.setColor(getCorAmarela());
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", positionYesInX - cursorWidth, positionYesInY);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(OPTION_YES, positionYesInX, positionYesInY);

        // 'NÃO'
        int positionNoInX = (positionYesInX + widthYes) + 30;
        int positionNoInY = positionYesInY;
        if (super.getCursor() == 1) {
            g.setColor(getCorAmarela());
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", positionNoInX - cursorWidth, positionNoInY);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(OPTION_NO, positionNoInX, positionNoInY);
    }

    public void controleMenu(KeyEvent keyboard) {
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
}