package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Controles extends TelasBase {
    
    private Image imageControl;
    // CAMINHO IMAGEM DE FUNDO
    private static final String PATH_IMAGE = "recursos\\Sprites\\Fundos\\FundoTelas.jpg";
    private static final String PATH_IMAGE_TITLE = "recursos\\Sprites\\Fundos\\TelaControle.png";
    
    public Controles() {
        super.setVisibility(false);
    }

    @Override
    public void carregar() {
        ImageIcon loading = new ImageIcon(PATH_IMAGE);
        super.setImage(loading.getImage());
        super.setImage(super.getImage().getScaledInstance(getScreenResolution().WIDTH_SCREEN, getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST));

        // IMAGEM SOBREPOSIÇÃO
        ImageIcon loadingImageControl = new ImageIcon(PATH_IMAGE_TITLE);
        this.imageControl = loadingImageControl.getImage();
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        this.imageControl = this.imageControl.getScaledInstance(getScreenResolution().WIDTH_SCREEN,
                getScreenResolution().HEIGHT_SCREEN,
                Image.SCALE_FAST);
    };

    // CARREGANDO A IMAGEM DE FUNDO E A IMAGEM COM OS COMANDOS
    public void conteudo(Graphics g) {
        g.drawImage(super.getImage(), 0, 0, null);
        g.drawImage(imageControl, 0, 0, null);
    }
}
