package br.ifpr.paranavai.jogo.model.Character.Bullets;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;

import br.ifpr.paranavai.jogo.model.GraphicsElements;

@Entity
@Table(name= "tb_tiro")
public class Shoot extends GraphicsElements {
    
    private static final int SPEED_BULLET = 8;
    private static final String IMAGE_PATH_BULLET = "/Sprites/Tiros/tiroNormal.gif";

    public Shoot(){
    }

    public Shoot(int posicaoEmX, int posicaoEmY) {
        super.setPositionInX(posicaoEmX);
        super.setPositionInY(posicaoEmY);
        super.setVisibility(true);
    }

    @Override
    public void load() {
        ImageIcon carregador = new ImageIcon(getClass().getResource(IMAGE_PATH_BULLET));
        super.setImage(carregador.getImage());
        super.setWidthImage(super.getImage().getWidth(null));
        super.setHeightImage(super.getImage().getHeight(null));
    }

    @Override
    public void update() {
        super.setPositionInX(super.getPositionInX() + SPEED_BULLET);
        if (super.getPositionInX() > getScreenResolution().WIDTH_SCREEN) {
            super.setVisibility(false);
        }
    }
}
