package br.ifpr.paranavai.jogo.Services;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.ifpr.paranavai.jogo.model.Stage;
import br.ifpr.paranavai.jogo.model.StageModel;
import br.ifpr.paranavai.jogo.model.Character.Bullets.Shoot;
import br.ifpr.paranavai.jogo.model.Character.Bullets.SpecialShoot;
import br.ifpr.paranavai.jogo.model.Enemies.Asteroide;
import br.ifpr.paranavai.jogo.model.Enemies.Meteorito;
import br.ifpr.paranavai.jogo.model.Enemies.Naves;
import javax.swing.Timer;

public class StageServiceImpl implements StageService {
    private Stage stage;
    private StageModel stageModel;

    private Timer timerMeteor;
    private Timer timerAsteroids;
    private Timer timerEnemyShip;

    public StageServiceImpl(Stage fase, StageModel faseModel) {
        this.stage = fase;
        this.stageModel = faseModel;

    }

    @Override
    public void drawnBullets(Graphics graphics) {
        // TIRO NORMAL
        ArrayList<Shoot> tiros = stageModel.getPlayer().getBullets();
        for (int i = 0; i < tiros.size(); i++) {
            Shoot tiro = tiros.get(i);
            tiro.load();
            graphics.drawImage(tiro.getImage(), tiro.getPositionInX(), tiro.getPositionInY(), null);
        }

        // TIRO ESPECIAL
        ArrayList<SpecialShoot> tiroSuper = stageModel.getPlayer().getSpecialBullet();
        for (int i = 0; i < tiroSuper.size(); i++) {
            SpecialShoot tiroEspecial = tiroSuper.get(i);
            tiroEspecial.load();
            graphics.drawImage(tiroEspecial.getImage(),
                    tiroEspecial.getPositionInX(), tiroEspecial.getPositionInY(), null);
        }
    }

    @Override
    public void dranwEnemies(Graphics graphics) {
        // COMEÇO METEORO E NAVES INIMIGAS
        for (Meteorito meteorito : stageModel.getEnemieMeteor()) {
            meteorito.load();
            graphics.drawImage(meteorito.getImage(), meteorito.getPositionInX(), meteorito.getPositionInY(),
                    null);
        }

        for (Naves inimigoNave : stageModel.getEnemieShip()) {
            inimigoNave.load();
            graphics.drawImage(inimigoNave.getImage(), inimigoNave.getPositionInX(), inimigoNave.getPositionInY(),
                    null);
            inimigoNave.vidas(graphics);
            inimigoNave.inimigoDados(graphics);
        }

        for (Asteroide asteroide : stageModel.getAsteroids()) {
            asteroide.load();
            graphics.drawImage(asteroide.getImage(),
                    asteroide.getPositionInX(), asteroide.getPositionInY(), null);
        }
    }

    @Override
    public void spawnEnemieShip() {
        stageModel.setEnemieShip(new ArrayList<Naves>());
        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        timerEnemyShip = new Timer(1500, new ActionListener() {
            int alturaInimigo = 80;
            int vidaInimigos = 1;
            int multiploNave = 100;
            boolean vidaAumentada;

            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = stageModel.getScreenSize().getWIDTH_SCREEN();
                int posicaoEmY = (int) (Math.random()
                        * ((stageModel.getScreenSize().getHEIGHT_SCREEN()) - alturaInimigo));
                // AUMENTA A VIDA DO INIMIGO COM BASE NOS PONTOS DO JOGADOR
                if (stageModel.getPlayer().isPlaying() && stageModel.getPlayer().getScore() > 0
                        && stageModel.getPlayer().getScore() % multiploNave == 0
                        && vidaInimigos < 4 && !vidaAumentada) {
                    vidaAumentada = true;
                    vidaInimigos = vidaInimigos + 1;
                }
                // VERIFICANDO SE A PONTUAÇÃO NÃO E MAIS VALIDA E REDEFININDO A VARIAVEL PARA
                if (stageModel.getPlayer().getScore() % multiploNave != 0) {
                    vidaAumentada = false;
                }
                stageModel.getEnemieShip().add(new Naves(posicaoEmX, posicaoEmY, vidaInimigos));
            }
        });
        timerEnemyShip.setRepeats(true);
    }

    @Override
    public void spawnEnemieMeteor() {
        stageModel.setEnemieMeteor(new ArrayList<Meteorito>());
        int alturaInimigo = 100;
        // TIMER PARA SPAWNAR O METEORITO
        timerMeteor = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmY = -alturaInimigo;
                int posicaoEmX = (int) (Math.random() * (stageModel.getScreenSize().getWIDTH_SCREEN() - alturaInimigo));
                stageModel.getEnemieMeteor().add(new Meteorito(posicaoEmX, posicaoEmY));
            }
        });
        timerMeteor.setRepeats(true);
    }

    @Override
    public void spawnAsteroids() {
        stageModel.setAsteroids(new ArrayList<Asteroide>());
        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        timerAsteroids = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = stageModel.getScreenSize().getWIDTH_SCREEN();
                int posicaoEmY = (int) (Math.random() * (stageModel.getScreenSize().getHEIGHT_SCREEN()));
                stageModel.getAsteroids().add(new Asteroide(posicaoEmX, posicaoEmY));
            }
        });
        timerAsteroids.setRepeats(true);
    }

    @Override
    public void controlEnemies() {
        // VEREFICA SE O INIMIGO ESTA VISIVEL E ATUALIZA A SUA POSICAÇÃO ATRAVES
        // DO METODO 'ATUALIZAR', AO FICAR INVISIVEL O INIMIGO E EXCLUIDO
        // A CLASSE ITERATOR E COMO UM PONTEIRO EM C QUE PERMITE INTERAJIR COM UM CERTO
        // OBJETO(INIMIGO) DE UMA LISTA DE FORMA ESPECIFICA E EM SEQUENCIA

        // NAVES
        Iterator<Naves> iteratorNaves = stageModel.getEnemieShip().iterator();
        while (iteratorNaves.hasNext()) {
            Naves inimigoNave = iteratorNaves.next();
            if (inimigoNave.isVisibility()) {
                inimigoNave.update();
            } else {
                iteratorNaves.remove();
            }
        }

        // METEORITO
        Iterator<Meteorito> iteratorMeteorito = stageModel.getEnemieMeteor().iterator();
        while (iteratorMeteorito.hasNext()) {
            Meteorito inimigoMeteorito = iteratorMeteorito.next();
            if (inimigoMeteorito.isVisibility()) {
                inimigoMeteorito.update();
            } else {
                iteratorMeteorito.remove();
            }
        } // FIM

        // ASTEROIDES
        Iterator<Asteroide> iteratorAsteroide = stageModel.getAsteroids().iterator();
        while (iteratorAsteroide.hasNext()) {
            Asteroide asteroide = iteratorAsteroide.next();
            if (asteroide.isVisibility()) {
                asteroide.update();
            } else {
                iteratorAsteroide.remove();
            }
        }
    }

    @Override
    public void speedEnemieControl() {
        int[] pontosPersonagem = { 300, 600, 900, 1200, 2000, 3000 };
        double[] ajusteVelocidades = { 5, 5.5, 6.5, 7, 8, 10 };
        for (int i = 0; i < pontosPersonagem.length; i++) {
            if (stageModel.getPlayer().getScore() > pontosPersonagem[i]) {
                double aumentoVelocidade = ajusteVelocidades[i];
                stageModel.getEnemieShip().forEach(naveTemporaria -> naveTemporaria.setSpeed(aumentoVelocidade));
            }
        }
    }

    @Override
    public void controlBullets() {
        // ATUALIZA POSICAÇÃO DO TIRO
        List<Shoot> tiros = stageModel.getPlayer().getBullets();
        Iterator<Shoot> iteratorTiro = tiros.iterator();
        while (iteratorTiro.hasNext()) {
            Shoot tiro = iteratorTiro.next();
            if (tiro.isVisibility()) {
                tiro.update();
            } else {
                iteratorTiro.remove();
            }
        } // FIM

        // ATUALIZA POSICAÇÃO DO TIRO ESPECIAL
        List<SpecialShoot> tirosEspecial = stageModel.getPlayer().getSpecialBullet();
        Iterator<SpecialShoot> iteratorTiroEspecial = tirosEspecial.iterator();
        while (iteratorTiroEspecial.hasNext()) {
            SpecialShoot tiroSuper = iteratorTiroEspecial.next();
            if (tiroSuper.isVisibility()) {
                tiroSuper.update();
            } else {
                iteratorTiroEspecial.remove();
            }
        }
    }

    @Override
    public void screenCollisions() {
        // VERIFICA COLISÃO COM A BORDA EM 'X'
        if (stageModel.getPlayer().getPositionInX() < 0) {
            stageModel.getPlayer().setPositionInX(0); // POSIÇÃO MÍNIMA X
        } else if (stageModel.getPlayer().getPositionInX() + stageModel.getPlayer().getWidthImage() > stageModel.getScreenSize()
                .getWIDTH_SCREEN()) {
            int maximoEmX = stageModel.getScreenSize().getWIDTH_SCREEN() - stageModel.getPlayer().getWidthImage(); // CALCULA A                                                                                    // MÁXIMA
            stageModel.getPlayer().setPositionInX(maximoEmX);
        }
        // VERIFICA COLISÃO COM A BORDA EM 'Y'
        if (stageModel.getPlayer().getPositionInY() < 0) {
            stageModel.getPlayer().setPositionInY(0); // POSIÇÃO MÍNIMA Y
        } else if (stageModel.getPlayer().getPositionInY() + stageModel.getPlayer().getHeightImage() > stageModel.getScreenSize()
                .getHEIGHT_SCREEN()) {
            int maximoEmY = stageModel.getScreenSize().getHEIGHT_SCREEN() - stageModel.getPlayer().getHeightImage();
            stageModel.getPlayer().setPositionInY(maximoEmY);
        }
    }

    @Override
    public void EnemieShipCollisions() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void EnemieMeteorCollisions() {
        // TODO Auto-generated method stub
        
    }

    // GETTERS E SETTRS
    public Timer getTimerMeteor() {
        return timerMeteor;
    }

    public void setTimerMeteor(Timer timerMeteor) {
        this.timerMeteor = timerMeteor;
    }

    public Timer getTimerAsteroids() {
        return timerAsteroids;
    }

    public void setTimerAsteroids(Timer timerAsteroids) {
        this.timerAsteroids = timerAsteroids;
    }

    public Timer getTimerEnemyShip() {
        return timerEnemyShip;
    }

    public void setTimerEnemyShip(Timer timerEnemyShip) {
        this.timerEnemyShip = timerEnemyShip;
    }

}
