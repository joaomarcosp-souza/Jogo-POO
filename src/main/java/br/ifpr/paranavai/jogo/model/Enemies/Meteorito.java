package br.ifpr.paranavai.jogo.model.Enemies;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import br.ifpr.paranavai.jogo.model.GraphicsElements;

@Entity
@Table(name = "tb_meteoritos")
public class Meteorito extends GraphicsElements {

    private static final String PATH_IMAGE = "src/main/resources/Sprites/Inimigos/meteorito.png";

    public Meteorito(int posicaoEmX, int posicaoEmY) {
        super.setPositionInX(posicaoEmX);
        super.setPositionInY(posicaoEmY);
        super.setInitialSpeed(4);
        super.setSpeed(super.getInitialSpeed());
        super.setVisibility(true);
    }

    @Override
    public void load() {
        ImageIcon loading = new ImageIcon(PATH_IMAGE);
        super.setImage(loading.getImage());
        super.setWidthImage(super.getImage().getWidth(null));
        super.setHeightImage(super.getImage().getHeight(null));
    }

    @Override
    public void update() {
        super.setPositionInY((int) (super.getPositionInY() + super.getInitialSpeed()));
        if (super.getPositionInY() > super.getScreenResolution().HEIGHT_SCREEN) {
            super.setVisibility(false);
        }

        // VERIFICANDO SE O METEORITO INIMIGO ESTA NASCENDO DENTRO DOS LIMETES DA TELA
        if (super.getPositionInX() < 0) {
            super.setPositionInX(0);
        } else if (super.getPositionInX() > super.getScreenResolution().WIDTH_SCREEN - super.getWidthImage()) {
            super.setPositionInX(super.getScreenResolution().WIDTH_SCREEN - super.getWidthImage());
        }
    }
}
