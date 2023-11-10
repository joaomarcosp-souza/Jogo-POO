package br.ifpr.paranavai.jogo.model.Screens;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Controles extends TelasBase {
    
    private Image imageControl;
    private static final String PATH_IMAGE = "src/main/resources/Sprites/Fundos/FundoTelas.jpg";
    private static final String PATH_IMAGE_TITLE = "src/main/resources/Sprites/Fundos/TelaControle.png";
    
    public Controles() {
        super.setVisibility(false);
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(PATH_IMAGE);
        super.setImage(loading.getImage());
        super.setImage(super.getImage().getScaledInstance(getScreenResolution().WIDTH_SCREEN, getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST));

        // IMAGEM SOBREPOSIÇÃO
        ImageIcon loadingImageControl = new ImageIcon(PATH_IMAGE_TITLE);
        this.imageControl = loadingImageControl.getImage();
        this.imageControl = this.imageControl.getScaledInstance(getScreenResolution().WIDTH_SCREEN,
                getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST);
    };

    public void conteudo(Graphics g) {
        g.drawImage(super.getImage(), 0, 0, null);
        g.drawImage(imageControl, 0, 0, null);
    }
}
