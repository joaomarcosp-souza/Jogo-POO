package br.ifpr.paranavai.jogo.Services;

import java.awt.Graphics;
import java.util.ArrayList;

import br.ifpr.paranavai.jogo.model.Fase;
import br.ifpr.paranavai.jogo.model.FaseModel;
import br.ifpr.paranavai.jogo.model.Character.Bullets.Shoot;
import br.ifpr.paranavai.jogo.model.Character.Bullets.SpecialShoot;

public class FaseServiceImpl implements FaseService {

    private Fase fase;
    private FaseModel faseModel;

    public FaseServiceImpl(Fase fase, FaseModel faseModel) {
        this.fase = fase;
        this.faseModel = faseModel;
    }

    @Override
    public void drawnBullets(Graphics graphics) {
        // TIRO NORMAL
        ArrayList<Shoot> tiros = faseModel.getPlayer().getBullets();
        for (int i = 0; i < tiros.size(); i++) {
            Shoot tiro = tiros.get(i);
            tiro.carregar();
            graphics.drawImage(tiro.getImage(), tiro.getPositionInX(), tiro.getPositionInY(), null);
        }

        // TIRO ESPECIAL
        ArrayList<SpecialShoot> tiroSuper = faseModel.getPlayer().getSpecialBullet();
        for (int i = 0; i < tiroSuper.size(); i++) {
            SpecialShoot tiroEspecial = tiroSuper.get(i);
            tiroEspecial.carregar();
            graphics.drawImage(tiroEspecial.getImage(),
                    tiroEspecial.getPositionInX(), tiroEspecial.getPositionInY(), null);
        }
    }
}
