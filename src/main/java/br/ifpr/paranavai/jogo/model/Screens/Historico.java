package br.ifpr.paranavai.jogo.model.Screens;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Historico extends TelasBase {
    
    private Image imageHistory;
    private static final String PATH_IMAGE = "/Sprites/Fundos/FundoTelas.jpg";
    private static final String PATH_IMAGE_TITLE = "/Sprites/Fundos/TelaHistorico.png";

    public Historico() {
        super.setVisibility(false);
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(getClass().getResource(PATH_IMAGE));
        super.setImage(loading.getImage());
        super.setWidthImage(super.getImage().getWidth(null));
        super.setHeightImage(super.getImage().getHeight(null));
        super.setImage(super.getImage().getScaledInstance(super.getScreenResolution().WIDTH_SCREEN,
                super.getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST));

        // IMAGEM
        ImageIcon loadingImageHistory = new ImageIcon(getClass().getResource(PATH_IMAGE_TITLE));
        this.imageHistory = loadingImageHistory.getImage();
        this.imageHistory = this.imageHistory.getScaledInstance(getScreenResolution().WIDTH_SCREEN,
                getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST);
    }

    public void conteudo(Graphics g) {
        g.drawImage(super.getImage(), 0, 0, null);
        g.drawImage(imageHistory, 0, 0, null);
    }
}
