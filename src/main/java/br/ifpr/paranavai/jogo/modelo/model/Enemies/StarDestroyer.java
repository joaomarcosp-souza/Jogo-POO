package br.ifpr.paranavai.jogo.modelo.model.Enemies;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;
import br.ifpr.paranavai.jogo.modelo.model.Base;

@Entity
@Table(name= "tb_starDestroyer")
public class StarDestroyer extends Base {

    private static final String PATH_IMAGE = "src/main/resources/Sprites/Inimigos/starDestroyer.png";

    public StarDestroyer(int posicaoEmX, int posicaoEmY) {
        super.setInitialSpeed(3);
        super.setSpeed(super.getInitialSpeed());
        super.setPositionInX(posicaoEmX);
        super.setPositionInY(posicaoEmY);
        super.setVisibility(true);
    }

    @Override
    public void carregar() {
        ImageIcon loading = new ImageIcon(PATH_IMAGE);
        super.setImage(loading.getImage());
        super.setWidthImage(super.getImage().getWidth(null));
        super.setHeightImage(super.getImage().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPositionInX((int) (super.getPositionInX() - super.getSpeed()));
        if (super.getPositionInX() < -1000) {
            super.setVisibility(false);
        }

        if (super.getPositionInY() < 0) {
            super.setPositionInY(0);
        } else if (super.getPositionInY() > super.getScreenResolution().HEIGHT_SCREEN - super.getHeightImage()) {
            super.setPositionInY(super.getScreenResolution().HEIGHT_SCREEN - super.getHeightImage());
        }
    }
}
