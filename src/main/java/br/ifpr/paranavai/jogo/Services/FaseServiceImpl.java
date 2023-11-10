package br.ifpr.paranavai.jogo.Services;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.ifpr.paranavai.jogo.model.Fase;
import br.ifpr.paranavai.jogo.model.FaseModel;
import br.ifpr.paranavai.jogo.model.Character.Bullets.Shoot;
import br.ifpr.paranavai.jogo.model.Character.Bullets.SpecialShoot;
import br.ifpr.paranavai.jogo.model.Enemies.Asteroide;
import br.ifpr.paranavai.jogo.model.Enemies.Meteorito;
import br.ifpr.paranavai.jogo.model.Enemies.Naves;
import javax.swing.Timer;

public class FaseServiceImpl implements FaseService {
    private Fase fase;
    private FaseModel faseModel;

    private Timer timerMeteor;
    private Timer timerAsteroids;
    private Timer timerEnemyShip;

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
            tiro.load();
            graphics.drawImage(tiro.getImage(), tiro.getPositionInX(), tiro.getPositionInY(), null);
        }

        // TIRO ESPECIAL
        ArrayList<SpecialShoot> tiroSuper = faseModel.getPlayer().getSpecialBullet();
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
        for (Meteorito meteorito : faseModel.getEnemieMeteor()) {
            meteorito.load();
            graphics.drawImage(meteorito.getImage(), meteorito.getPositionInX(), meteorito.getPositionInY(),
                    null);
        }

        for (Naves inimigoNave : faseModel.getEnemieShip()) {
            inimigoNave.load();
            graphics.drawImage(inimigoNave.getImage(), inimigoNave.getPositionInX(), inimigoNave.getPositionInY(),
                    null);
            inimigoNave.vidas(graphics);
            inimigoNave.inimigoDados(graphics);
        }

        for (Asteroide asteroide : faseModel.getAsteroids()) {
            asteroide.load();
            graphics.drawImage(asteroide.getImage(),
                    asteroide.getPositionInX(), asteroide.getPositionInY(), null);
        }
    }

    @Override
    public void spawnEnemieShip() {
        faseModel.setEnemieShip(new ArrayList<Naves>());
        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        timerEnemyShip = new Timer(1500, new ActionListener() {
            int alturaInimigo = 80;
            int vidaInimigos = 1;
            int multiploNave = 100;
            boolean vidaAumentada;

            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = faseModel.getScreenSize().getWIDTH_SCREEN();
                int posicaoEmY = (int) (Math.random() * ((faseModel.getScreenSize().getHEIGHT_SCREEN()) - alturaInimigo));
                // AUMENTA A VIDA DO INIMIGO COM BASE NOS PONTOS DO JOGADOR
                if (faseModel.getPlayer().isPlaying() && faseModel.getPlayer().getScore() > 0
                        && faseModel.getPlayer().getScore() % multiploNave == 0
                        && vidaInimigos < 4 && !vidaAumentada) {
                    vidaAumentada = true;
                    vidaInimigos = vidaInimigos + 1;
                }
                // VERIFICANDO SE A PONTUAÇÃO NÃO E MAIS VALIDA E REDEFININDO A VARIAVEL PARA
                if (faseModel.getPlayer().getScore() % multiploNave != 0) {
                    vidaAumentada = false;
                }
                faseModel.getEnemieShip().add(new Naves(posicaoEmX, posicaoEmY, vidaInimigos));
            }
        });
        timerEnemyShip.setRepeats(true);
    }

    @Override
    public void spawnEnemieMeteor() {
        faseModel.setEnemieMeteor(new ArrayList<Meteorito>());
        int alturaInimigo = 100;
        // TIMER PARA SPAWNAR O METEORITO
        timerMeteor = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmY = -alturaInimigo;
                int posicaoEmX = (int) (Math.random() * (faseModel.getScreenSize().getWIDTH_SCREEN() - alturaInimigo));
                faseModel.getEnemieMeteor().add(new Meteorito(posicaoEmX, posicaoEmY));
            }
        });
        timerMeteor.setRepeats(true);
    }

    @Override
    public void spawnAsteroids() {
        faseModel.setAsteroids(new ArrayList<Asteroide>());
        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        timerAsteroids = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = faseModel.getScreenSize().getWIDTH_SCREEN();
                int posicaoEmY = (int) (Math.random() * (faseModel.getScreenSize().getHEIGHT_SCREEN()));
                faseModel.getAsteroids().add(new Asteroide(posicaoEmX, posicaoEmY));
            }
        });
        timerAsteroids.setRepeats(true);
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
