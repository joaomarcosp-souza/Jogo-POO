package br.ifpr.paranavai.jogo.modelo.Jogador;

import javax.swing.ImageIcon;
import br.ifpr.paranavai.jogo.modelo.Base;

public class Tiro extends Base {

    private static final int SPEED_BULLET = 8;
    private static final String IMAGE_PATH_BULLET = "recursos\\Sprites\\Tiros\\tiroNormal.gif";

    public Tiro(int posicaoEmX, int posicaoEmY) {
        super.setPositionInX(posicaoEmX);
        super.setPositionInY(posicaoEmY);
        super.setVisibility(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(IMAGE_PATH_BULLET);
        super.setImage(carregador.getImage());
        super.setWidthImage(super.getImage().getWidth(null));
        super.setHeightImage(super.getImage().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPositionInX(super.getPositionInX() + SPEED_BULLET);
        if (super.getPositionInX() > getScreenResolution().WIDTH_SCREEN) {
            super.setVisibility(false);
        }
    }
}
