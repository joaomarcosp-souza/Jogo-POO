package br.ifpr.paranavai.jogo.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import br.ifpr.paranavai.jogo.services.Screens.ScreenServiceImpl;
import br.ifpr.paranavai.jogo.services.stage.StageServiceImpl;
import br.ifpr.paranavai.jogo.model.Character.Player;


public class Stage extends JPanel implements ActionListener {

    private StageModel stageModel;

    private StageServiceImpl stageService;
    private ScreenServiceImpl screenService;

    private Image background;
    private Image backgroundTwo;

    public boolean flashing;
    public long lastCollision;
    public Timer timerFlashing;
    public int countFlashing = 0;
    private final Timer timerGlobal;
    public final int GENERAL_DELAY = 1000;

    public Stage() {

        stageModel = new StageModel();
        stageService = new StageServiceImpl(this, this.stageModel);

        screenService = new ScreenServiceImpl(stageModel, stageService);

        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon carregando = new ImageIcon("src/main/resources/Sprites/Fundos/FundoFase.jpg");
        this.background = carregando.getImage();

        ImageIcon carregandoDois = new ImageIcon("src/main/resources/Sprites/Fundos/FundoFase2.jpg");
        this.backgroundTwo = carregandoDois.getImage();

        stageModel.setPlayer(new Player());
        stageModel.getPlayer().load();

        // RESCALONAMENTO DAS IMAGENS DE FUNDO
        this.background = this.background.getScaledInstance(
                stageModel.getScreenSize().getWIDTH_SCREEN(), stageModel.getScreenSize().getHEIGHT_SCREEN(),
                Image.SCALE_FAST);

        this.backgroundTwo = this.backgroundTwo.getScaledInstance(
                stageModel.getScreenSize().getWIDTH_SCREEN(), stageModel.getScreenSize().getHEIGHT_SCREEN(),
                Image.SCALE_FAST);

        addKeyListener(new TecladoAdapter());
        timerFlashing = new Timer(GENERAL_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flashing = !flashing;
            }
        });
        timerFlashing.setRepeats(true);
        flashing = false;

        timerGlobal = new Timer(5, this);
        timerGlobal.start();

        stageService.spawnAsteroids();
        stageService.spawnEnemieMeteor();
        stageService.spawnEnemieShip();
    }

    public void paintComponent(Graphics g) {
        Graphics2D grapichs = (Graphics2D) g;
        super.paintComponent(g);

        if (stageModel.getScreenMenu().isVisibility() == true) {
            stageModel.getScreenMenu().conteudo(grapichs);
        } else if (stageModel.getScreenControls().isVisibility() == true) {
            stageModel.getScreenControls().conteudo(grapichs);
        } else if (stageModel.getScreenHistory().isVisibility() == true) {
            stageModel.getScreenHistory().conteudo(grapichs);
        }

        if (stageModel.getPlayer().isPlaying() == true) {
            grapichs.drawImage(this.background, 0, 0, null);
            if (stageModel.getPlayer().getScore() >= 200) {
                grapichs.drawImage(this.backgroundTwo, 0, 0, null);
            }

            stageService.drawnBullets(g);
            stageService.dranwEnemies(g);
            stageService.dranwEnemiesScore(g);

            stageModel.getPlayer().pontuacao(grapichs);
            stageModel.getPlayer().desenhaVida(grapichs);
            stageModel.getPlayer().desenhaEliminacoes(grapichs);
            stageModel.getPlayer().restauraVida(grapichs);

            if (!flashing) {
                grapichs.drawImage(stageModel.getPlayer().getImage(), stageModel.getPlayer().getPositionInX(),
                        stageModel.getPlayer().getPositionInY(),
                        null);
            }
        } else if (stageModel.getScreenEndGame().isVisibility() == true) {
            stageModel.getScreenEndGame().titulo(grapichs);
            stageModel.getScreenEndGame().menu(grapichs);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!stageModel.getScreenPaused().isPaused()) {
            repaint();
            stageService.controlEnemies();
            stageService.controlBullets();
            stageService.speedEnemieControl();

            stageService.screenCollisions();
            stageService.EnemieMeteorCollisions();
            stageService.EnemieShipCollisions();

            resetStage();
            playerControl();
            stageModel.getPlayer().update();
        }
        // PARA O SPAWN DOS INIMIGOS
        if (stageModel.getScreenPaused().isPaused()) {
            stageService.getTimerEnemyShip().stop();
            stageService.getTimerAsteroids().stop();
            stageService.getTimerMeteor().stop();
            stageModel.getScreenPaused().menu(getGraphics());
        }
    }

    public void resetStage() {
        if (stageModel.getPlayer().isPlaying() == false) {
            // EFEITO DO PERSONAGEM
            timerFlashing.stop();
            stageModel.getPlayer().getBullets().clear();
            // PARA O SPAWN DE INIMIGOS
            stageService.getTimerEnemyShip().stop();
            stageService.getTimerAsteroids().stop();
            stageService.getTimerMeteor().stop();
            // LIMPA A LISTA DE INMIGOS
            stageModel.getEnemieShip().clear();
            stageModel.getEnemieMeteor().clear();
            // RESETA A POSIÇÃO DO JOGADOR
            if (stageModel.getPlayer().getPositionInX() != stageModel.getPlayer().getINITIAL_POSITION_X()
                    || stageModel.getPlayer().getPositionInY() != stageModel.getPlayer().getINITIAL_POSITION_Y()) {
                stageModel.getPlayer().setPositionInX(stageModel.getPlayer().getINITIAL_POSITION_X());
                stageModel.getPlayer().setPositionInY(stageModel.getPlayer().getINITIAL_POSITION_Y());
            }
            // RESETA A HUD
            stageModel.getPlayer().setScore(0);
            stageModel.getPlayer().setScoreDeadEnemys(0);
            stageModel.getPlayer().setLife(stageModel.getPlayer().getInitialLife());
        } else {
            // INICIAR O SPAWN DOS INIMIGOS
            stageService.getTimerEnemyShip().start();
            stageService.getTimerAsteroids().start();
            if (stageModel.getPlayer().getScore() >= 100) {
                stageService.getTimerMeteor().start();
            }
        }
    }

    public void playerControl() {
        // FUNÇÃO PARA FAZER O PERSONAGEM 'PISCAR' CASO COLIDA
        countFlashing++;
        if (countFlashing % 2 == 1) {
            flashing = false; // SE FOR IMPAR O PERSONAGEM TA VISIVEL
        } else {
            flashing = true; // SE FOR PAR O PERSONAGEM ESTA PISCANDO
        }
        if (countFlashing == 10) {
            timerFlashing.stop();
            flashing = false;
        }

        // FUNÇÃO PARA VERIFICAR A VIDA DO PERSONAGEM E ATIVAR O SPAWN DE VIDA
        if (stageModel.getPlayer().getScore() > 0 && stageModel.getPlayer().getScore() % 300 == 0
                && stageModel.getPlayer().isHealthRestored() == false) {
            stageModel.getPlayer().setHealthRestoreCheck(1);
            stageModel.getPlayer().setHealthRestored(true);
        }
    }

    // CHAMANDO A LEITURA DOS TECLADOS
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (stageModel.getScreenMenu().isVisibility() == true) {
                screenService.visibilityControlMenu(e);
                stageModel.getScreenMenu().controleMenu(e);
                stageModel.getSounds().loadSound("botao.wav");
            } else if (stageModel.getScreenControls().isVisibility() == true) {
                screenService.visibilityControlScreens(e);
            } else if (stageModel.getScreenHistory().isVisibility() == true) {
                screenService.visibilityControlScreens(e);
            } else if (stageModel.getPlayer().isPlaying() == true) {
                int tecla = e.getKeyCode();
                stageModel.getPlayer().mover(e);
                stageModel.getPlayer().atirar(e);
                if (tecla == KeyEvent.VK_SPACE && stageModel.getPlayer().isCanShoot()) {
                    stageModel.getSounds().loadSound("tiro.wav");
                }
                // SALVAMENTO RAPIDO(TESTE)
                if (tecla == KeyEvent.VK_F5) {
                    stageService.saveGame();
                }
                screenService.visibilityScreenPause(e);
            } else if (stageModel.getScreenEndGame().isVisibility() == true) {
                screenService.visibilityControlEndGame(e);
                stageModel.getScreenEndGame().controleMenu(e);
                stageModel.getSounds().loadSound("botao.wav");
            }

            if (stageModel.getScreenPaused().isPaused()) {
                int tecla = e.getKeyCode();
                stageModel.getScreenPaused().menuPausado(e);
                stageModel.getSounds().loadSound("botao.wav");
                if (tecla == KeyEvent.VK_ENTER) {
                    if (stageModel.getScreenPaused().getCursor() == 0) {
                        stageModel.getScreenPaused().setPaused(false);
                        stageModel.getPlayer().setPlaying(true);
                        stageModel.getScreenPaused().setVisibility(false);
                    }
                    if (stageModel.getScreenPaused().getCursor() == 1) {
                        stageModel.getScreenPaused().setPaused(false);
                        stageModel.getPlayer().setPlaying(false);
                        stageModel.getScreenPaused().setVisibility(false);
                        stageModel.getScreenMenu().setVisibility(true);
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            stageModel.getPlayer().parar(e);
        }
    }
}