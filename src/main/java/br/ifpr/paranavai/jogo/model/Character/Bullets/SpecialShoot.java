package br.ifpr.paranavai.jogo.model.Character.Bullets;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;
import br.ifpr.paranavai.jogo.model.Base;

@Entity
@Table(name = "tb_superTiro")
public class SpecialShoot extends Base {

    private int angle;
    private static final int SPEED_SUPER = 8;
    private static final String IMAGE_PATH_SUPER = "/Sprites/Tiros/superTiro.gif";

    public SpecialShoot(int posicaoEmX, int posicaoEmY, int angulo) {
        super.setPositionInX(posicaoEmX);
        super.setPositionInY(posicaoEmY);
        super.setVisibility(true);
        this.angle = angulo;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(getClass().getResource(IMAGE_PATH_SUPER));
        super.setImage(carregador.getImage());
        super.setWidthImage(super.getImage().getWidth(null));
        super.setHeightImage(super.getImage().getHeight(null));
    }

    @Override
    public void atualizar() {
        // CONVERTE A VARIAVEL 'ANGULO' PARA RADIANOS
        double radianos = Math.toRadians(angle);
        // CALCULANDO AS VELOCIDADES DE 'X' E 'Y' COM BASE NO ANGULO
        int velocidadeEmX = (int) (SPEED_SUPER * Math.cos(radianos));
        int velocidadeEmY = (int) (SPEED_SUPER * Math.sin(radianos));

        super.setPositionInX(super.getPositionInX() + velocidadeEmX);
        super.setPositionInY(super.getPositionInY() + velocidadeEmY);

        // VERIFICA O TAMANHO DA BORDA, PRA REMOVER OS TIROS POSTERIORMENTE
        if (super.getPositionInX() > super.getScreenResolution().WIDTH_SCREEN
                || super.getPositionInY() > getScreenResolution().HEIGHT_SCREEN) {
            super.setVisibility(false);
        }
    }
}
