package br.ifpr.paranavai.jogo.Services.Stage;

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
import java.awt.Rectangle;
import java.awt.Color;

public class StageServiceImpl implements StageService {
    private Stage stage;
    private StageModel stageModel;

    private Timer timerMeteor;
    private Timer timerAsteroids;
    private Timer timerEnemyShip;

    private int scorePositionX, scorePositionY;

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
    public void dranwEnemiesScore(Graphics graphics) {
        if (stageModel.isEnemyKilled()) {
            graphics.setColor(Color.WHITE);
            graphics.drawString("+ 10", scorePositionX + 5, scorePositionY -= 1);
        } else {
            graphics.setColor(Color.WHITE);
            graphics.drawString("- 1", scorePositionX + 75, scorePositionY += 1);
        }
    }

    @Override
    public void spawnEnemieShip() {
        stageModel.setEnemieShip(new ArrayList<Naves>());
        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        timerEnemyShip = new Timer(1000, new ActionListener() {
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
        // VALORES PARA CADA INIMIGO DESTRUIDO
        int ENEMIE_VALUE = 10;
        // FORMAS
        Rectangle personagemForma = stageModel.getPlayer().getBounds();
        Rectangle naveInimigaForma;

        // VERIFICA A COLISÃO DO PERSONAGEM COM A NAVE INIMIGA
        for (int i = 0; i < stageModel.getEnemieShip().size(); i++) {
            Naves naveTemporaria = stageModel.getEnemieShip().get(i);
            naveInimigaForma = naveTemporaria.getBounds();
            long tempoAtual = System.currentTimeMillis();
            if (tempoAtual - stage.lastCollision < stage.GENERAL_DELAY) {
                return;
            } else {
                stage.countFlashing = 0; // REINICIA O CONTADOR
                stage.timerFlashing.start(); // INICIA O TIMER
                if (personagemForma.getBounds().intersects(naveInimigaForma.getBounds())) {
                    // VERIFICA SE A VIDA DO PERSONAGME PODE SER RESTAURADA
                    if (stageModel.getPlayer().getLife() == 1) {
                        stageModel.getPlayer().setPlaying(false);
                        stageModel.getScreenEndGame().setVisibility(true);
                        stageModel.getPlayer().setVisibility(false);
                    } else {
                        stageModel.getPlayer().setLife(stageModel.getPlayer().getLife() - 1);
                    }
                    // VERIFICA A VIDA DA NAVE INIMIGA
                    if (naveTemporaria.getLife() == 1) {
                        naveTemporaria.setVisibility(false);
                    } else {
                        naveTemporaria.setLife(naveTemporaria.getLife() - 1);
                    }
                    stage.lastCollision = tempoAtual;
                }
            }
            stage.timerFlashing.stop();
            stage.flashing = false;
        }
        for (Naves inimigoNave : stageModel.getEnemieShip()) {
            Rectangle formaInimigoNave = inimigoNave.getBounds();

            // TIRO NORMAL
            for (Shoot tiro : stageModel.getPlayer().getBullets()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoNave)) {
                    if (inimigoNave.getLife() == 1) {
                        int pontuacaoAtual = stageModel.getPlayer().getScore() + ENEMIE_VALUE;
                        if (pontuacaoAtual % 50 == 0) {
                            stageModel.getPlayer().setSuperQuantity(2);
                        }
                        inimigoNave.setVisibility(false);
                        stageModel.getPlayer().setScore(pontuacaoAtual);
                        stageModel.getPlayer().setScoreDeadEnemys(stageModel.getPlayer().getScoreDeadEnemys() + 1);
                        stageModel.getSounds().loadSound("explosao.wav");
                        stageModel.setEnemyKilled(true);
                    } else {
                        inimigoNave.setLife(inimigoNave.getLife() - 1);
                        stageModel.getSounds().loadSound("colisao.wav");
                        stageModel.setEnemyKilled(false);
                    }
                    scorePositionX = inimigoNave.getPositionInX();
                    scorePositionY = inimigoNave.getPositionInY();
                    tiro.setVisibility(false);
                }
            }
            // SUPER TIRO
            for (SpecialShoot superTiro : stageModel.getPlayer().getSpecialBullet()) {
                Rectangle formaSuper = superTiro.getBounds();
                if (formaSuper.intersects(formaInimigoNave)) {
                    if (inimigoNave.getLife() == 1) {
                        inimigoNave.setVisibility(false);
                        stageModel.getPlayer().setScore(stageModel.getPlayer().getScore() + ENEMIE_VALUE);
                        stageModel.getSounds().loadSound("explosao.wav");
                        stageModel.setEnemyKilled(true);
                    } else {
                        inimigoNave.setLife(inimigoNave.getLife() - 1);
                        stageModel.getSounds().loadSound("colisao.wav");
                        stageModel.setEnemyKilled(false);
                    }
                    scorePositionX = inimigoNave.getPositionInX();
                    scorePositionY = inimigoNave.getPositionInY();
                }
            }
        }
    }

    @Override
    public void EnemieMeteorCollisions() {
        int ENEMIE_VALUE = 10;
        Rectangle personagemForma = stageModel.getPlayer().getBounds();
        Rectangle meteoritoForma;

        // VEREFICA A COLISÃO DO PERSONAGEM COM O METEORITO
        for (int k = 0; k < stageModel.getEnemieMeteor().size(); k++) {
            Meteorito meteritoTemporario = stageModel.getEnemieMeteor().get(k);
            meteoritoForma = meteritoTemporario.getBounds();
            if (personagemForma.getBounds().intersects(meteoritoForma.getBounds())) {
                // VERIFICA SE O PERSONAGEM ESTA PERTO DE MORRER
                if (stageModel.getPlayer().getLife() == 1) {
                    stageModel.getPlayer().setPlaying(false);
                    stageModel.getScreenEndGame().setVisibility(true);

                } else {
                    stageModel.getPlayer().setLife(stageModel.getPlayer().getLife() - 1);
                }
                meteritoTemporario.setVisibility(false);
                stageModel.getPlayer().setVisibility(false);
            }
        }
        // VERIFICA A COLISÃO DO TIRO NORMAL E SUPER COM O METEORIOTO
        for (Meteorito inimigoMeteorito : stageModel.getEnemieMeteor()) {
            Rectangle formaInimigoMeteorito = inimigoMeteorito.getBounds();
            // TIRO NORMAL
            for (Shoot tiro : stageModel.getPlayer().getBullets()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoMeteorito)) {
                    inimigoMeteorito.setVisibility(false);
                    tiro.setVisibility(false);
                    stageModel.getPlayer().setScore(stageModel.getPlayer().getScore() + ENEMIE_VALUE);
                    stageModel.setEnemyKilled(true);
                    scorePositionX = inimigoMeteorito.getPositionInX();
                    scorePositionY = inimigoMeteorito.getPositionInY();
                }
            }
            // SUPER TIRO
            for (SpecialShoot superTiro : stageModel.getPlayer().getSpecialBullet()) {
                Rectangle formaSuper = superTiro.getBounds();
                if (formaSuper.intersects(formaInimigoMeteorito)) {
                    inimigoMeteorito.setVisibility(false);
                    superTiro.setVisibility(false);
                    stageModel.getPlayer().setScore(stageModel.getPlayer().getScore() + ENEMIE_VALUE);
                    stageModel.setEnemyKilled(true);
                    scorePositionX = inimigoMeteorito.getPositionInX();
                    scorePositionY = inimigoMeteorito.getPositionInY();
                }
            }
        }
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
