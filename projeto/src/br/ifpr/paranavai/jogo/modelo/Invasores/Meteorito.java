package br.ifpr.paranavai.jogo.modelo.Invasores;

import javax.swing.ImageIcon;
import br.ifpr.paranavai.jogo.modelo.Base;

public class Meteorito extends Base {

    private static final String PATH_IMAGE = "recursos\\Sprites\\Inimigos\\meteorito.png";

    public Meteorito(int posicaoEmX, int posicaoEmY) {
        super.setPositionInX(posicaoEmX);
        super.setPositionInY(posicaoEmY);
        super.setInitialSpeed(4);
        super.setSpeed(super.getInitialSpeed());
        super.setVisibility(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(PATH_IMAGE);
        super.setImage(carregador.getImage());
        super.setWidthImage(super.getImage().getWidth(null));
        super.setHeightImage(super.getImage().getHeight(null));
    }

    @Override
    public void atualizar() {
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
