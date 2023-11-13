package br.ifpr.paranavai.jogo.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;

import br.ifpr.paranavai.jogo.model.Character.Player;
import br.ifpr.paranavai.jogo.model.Character.Bullets.Shoot;
import br.ifpr.paranavai.jogo.model.Character.Bullets.SpecialShoot;
import br.ifpr.paranavai.jogo.model.Enemies.Meteorito;
import br.ifpr.paranavai.jogo.model.Enemies.Naves;
import br.ifpr.paranavai.jogo.model.Screens.Controles;
import br.ifpr.paranavai.jogo.model.Screens.FimDeJogo;
import br.ifpr.paranavai.jogo.model.Screens.Historico;
import br.ifpr.paranavai.jogo.model.Screens.MenuInicial;
import br.ifpr.paranavai.jogo.model.Screens.Pausar;
import br.ifpr.paranavai.jogo.Services.StageServiceImpl;
import br.ifpr.paranavai.jogo.Util.ScreenSize;

public class Stage extends JPanel implements ActionListener {

    private StageModel stageModel;
    private StageServiceImpl stageServiceImpl;
    private boolean enemyKilled;
    private int scorePositionX, scorePositionY;
    // ATRIBUTOS DA CLASSE
    private Image background;
    private Image backgroundTwo;
    // INSTANCIAS
    private Pausar screenPaused;
    private FimDeJogo screenEndGame;
    private MenuInicial screenMenu;
    private Historico screenHistory;
    private Controles screenControls;
    private ScreenSize screenResolution;
    // TIMERS
    private final Timer timerGlobal;
    // DELAYS INIMIHOS
    private final static int GENERAL_DELAY = 1000;
    // 'TIMER' PARA A COLISAO
    private long lastCollision;
    // VARIAVEIS DO EFEITO 'PISCANDO' PARA O PERSONAGEM
    private boolean flashing;
    private Timer timerFlashing;
    private int countFlashing = 0;

    public Stage() {
        stageModel = new StageModel();
        stageServiceImpl = new StageServiceImpl(this, this.stageModel);

        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon carregando = new ImageIcon("src/main/resources/Sprites/Fundos/FundoFase.jpg");
        this.background = carregando.getImage();

        ImageIcon carregandoDois = new ImageIcon("src/main/resources/Sprites/Fundos/FundoFase2.jpg");
        this.backgroundTwo = carregandoDois.getImage();

        stageModel.setPlayer(new Player());
        stageModel.getPlayer().load();

        screenMenu = new MenuInicial();
        screenMenu.load();

        screenHistory = new Historico();
        screenHistory.load();

        screenControls = new Controles();
        screenControls.load();

        screenEndGame = new FimDeJogo();
        screenEndGame.load();

        screenResolution = new ScreenSize();
        screenResolution.carregar();

        screenPaused = new Pausar();
        screenPaused.load();

        // RESCALONAMENTO DAS IMAGENS DE FUNDO
        this.background = this.background.getScaledInstance(
                screenResolution.getWIDTH_SCREEN(), screenResolution.getHEIGHT_SCREEN(), Image.SCALE_FAST);

        this.backgroundTwo = this.backgroundTwo.getScaledInstance(
                screenResolution.getWIDTH_SCREEN(), screenResolution.getHEIGHT_SCREEN(), Image.SCALE_FAST);

        addKeyListener(new TecladoAdapter());
        timerFlashing = new Timer(GENERAL_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flashing = !flashing;
            }
        });

        // TIMER PARA O EFEITO DE PISCANDO
        timerFlashing.setRepeats(true);
        flashing = false;

        timerGlobal = new Timer(5, this);
        timerGlobal.start();

        stageServiceImpl.spawnAsteroids();
        stageServiceImpl.spawnEnemieMeteor();
        stageServiceImpl.spawnEnemieShip();
    }

    // INICIANDO POSIÇÃO DAS NAVES INIMIGAS ALEATORIAMENTE

    public void paintComponent(Graphics g) {
        Graphics2D grapichs = (Graphics2D) g;
        super.paintComponent(g);

        if (screenMenu.isVisibility() == true) {
            screenMenu.conteudo(grapichs);
        } else if (screenControls.isVisibility() == true) {
            screenControls.conteudo(grapichs);
        } else if (screenHistory.isVisibility() == true) {
            screenHistory.conteudo(grapichs);
        }

        if (stageModel.getPlayer().isPlaying() == true) {
            grapichs.drawImage(this.background, 0, 0, null);
            if (stageModel.getPlayer().getScore() >= 200) {
                grapichs.drawImage(this.backgroundTwo, 0, 0, null);
            }

            stageServiceImpl.drawnBullets(g);
            stageServiceImpl.dranwEnemies(g);

            // DESENHA A PONTUAÇÃO ACIMA DO INIMIGO MORTO
            if (enemyKilled) {
                grapichs.setColor(Color.WHITE);
                grapichs.drawString("+ 10", scorePositionX + 5, scorePositionY -= 1);
            } else {
                grapichs.setColor(Color.WHITE);
                g.drawString("- 1", scorePositionX + 75, scorePositionY += 1);
            }

            stageModel.getPlayer().pontuacao(grapichs);
            stageModel.getPlayer().desenhaVida(grapichs);
            stageModel.getPlayer().desenhaEliminacoes(grapichs);
            stageModel.getPlayer().restauraVida(grapichs);

            if (!flashing) {
                grapichs.drawImage(stageModel.getPlayer().getImage(), stageModel.getPlayer().getPositionInX(),
                        stageModel.getPlayer().getPositionInY(),
                        null);
            }
        } else if (screenEndGame.isVisibility() == true) {
            screenEndGame.titulo(grapichs);
            screenEndGame.menu(grapichs);
        }

        g.dispose();
    }

    // MÉTODO DE CONTROLE DA FASE
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!screenPaused.isPaused()) {
            repaint();

            stageServiceImpl.controlEnemies();
            stageServiceImpl.controlBullets();
            stageServiceImpl.speedEnemieControl();

            stageServiceImpl.screenCollisions();

            resetStage();
            detectCollisions();
            stageModel.getPlayer().update();
        }
        // PARA O SPAWN DOS INIMIGOS
        if (screenPaused.isPaused()) {
            stageServiceImpl.getTimerEnemyShip().stop();
            stageServiceImpl.getTimerAsteroids().stop();
            stageServiceImpl.getTimerMeteor().stop();
            screenPaused.menu(getGraphics());
        }
    }

    public void resetStage() {

        // CONTROLE DA FASE
        if (stageModel.getPlayer().isPlaying() == false) {
            // PERSONAGEM
            timerFlashing.stop();
            stageModel.getPlayer().getBullets().clear();
            // PARA O SPAWN DE INIMIGOS
            stageServiceImpl.getTimerEnemyShip().stop();
            stageServiceImpl.getTimerAsteroids().stop();
            stageServiceImpl.getTimerMeteor().stop();
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
            stageServiceImpl.getTimerEnemyShip().start();
            stageServiceImpl.getTimerAsteroids().start();

            if (stageModel.getPlayer().getScore() >= 300) {
                stageServiceImpl.getTimerMeteor().start();
            }
        }

        // FUNÇÃO PARA FAZER O PERSONAGEM 'PISCAR' CASO COLIDA
        countFlashing++; // CONTADOR
        if (countFlashing % 2 == 1) {
            flashing = false; // SE FOR IMPAR O PERSONAGEM TA VISIVEL
        } else {
            flashing = true; // SE FOR PAR O PERSONAGEM ESTA PISCANDO
        }
        if (countFlashing == 10) { // VEZES PARA 'PISCAR'
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

    public void detectCollisions() {
        // VALORES PARA CADA INIMIGO DESTRUIDO
        int ENEMIE_VALUE = 10;
        // FORMAS
        Rectangle personagemForma = stageModel.getPlayer().getBounds();
        Rectangle naveInimigaForma;
        Rectangle meteoritoForma;

        // VERIFICA A COLISÃO DO PERSONAGEM COM A NAVE INIMIGA
        for (int i = 0; i < stageModel.getEnemieShip().size(); i++) {
            Naves naveTemporaria = stageModel.getEnemieShip().get(i);
            naveInimigaForma = naveTemporaria.getBounds();
            long tempoAtual = System.currentTimeMillis();
            if (tempoAtual - lastCollision < GENERAL_DELAY) {
                return;
            } else {
                countFlashing = 0; // REINICIA O CONTADOR
                timerFlashing.start(); // INICIA O TIMER
                if (personagemForma.getBounds().intersects(naveInimigaForma.getBounds())) {
                    // VERIFICA SE A VIDA DO PERSONAGME PODE SER RESTAURADA
                    if (stageModel.getPlayer().getLife() == 1) {
                        stageModel.getPlayer().setPlaying(false);
                        screenEndGame.setVisibility(true);
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
                    lastCollision = tempoAtual;
                }
            }
            timerFlashing.stop();
            flashing = false;
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
                        this.enemyKilled = true;
                    } else {
                        inimigoNave.setLife(inimigoNave.getLife() - 1);
                        stageModel.getSounds().loadSound("colisao.wav");
                        this.enemyKilled = false;
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
                        this.enemyKilled = true;
                    } else {
                        inimigoNave.setLife(inimigoNave.getLife() - 1);
                        stageModel.getSounds().loadSound("colisao.wav");
                        this.enemyKilled = false;
                    }
                    scorePositionX = inimigoNave.getPositionInX();
                    scorePositionY = inimigoNave.getPositionInY();
                }
            }
        }

        // VEREFICA A COLISÃO DO PERSONAGEM COM O METEORITO
        for (int k = 0; k < stageModel.getEnemieMeteor().size(); k++) {
            Meteorito meteritoTemporario = stageModel.getEnemieMeteor().get(k);
            meteoritoForma = meteritoTemporario.getBounds();
            if (personagemForma.getBounds().intersects(meteoritoForma.getBounds())) {
                // VERIFICA SE O PERSONAGEM ESTA PERTO DE MORRER
                if (stageModel.getPlayer().getLife() == 1) {
                    stageModel.getPlayer().setPlaying(false);
                    screenEndGame.setVisibility(true);

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
                    enemyKilled = true;
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
                    enemyKilled = true;
                    scorePositionX = inimigoMeteorito.getPositionInX();
                    scorePositionY = inimigoMeteorito.getPositionInY();
                }
            }
        }
    }

    public void controleMenuInicial(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ENTER) {
            if (screenMenu.getCursor() == 0) { // NOVO JOGO
                stageModel.getPlayer().setPlaying(true);
                screenMenu.setVisibility(false);
            } else if (screenMenu.getCursor() == 1) { // CARREGAR JOGO
                // A SER IMPLEMENTADO AINDA
            } else if (screenMenu.getCursor() == 2) { // TELA CONTROLES
                screenMenu.setVisibility(false);
                screenControls.setVisibility(true);
            } else if (screenMenu.getCursor() == 3) { // TELA HISTORICO - IMPLEMENTAR
                screenMenu.setVisibility(false);
                screenHistory.setVisibility(true);
            }
        }
    }

    public void controleTelas(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (screenControls.isVisibility() == true) {
            if (tecla == KeyEvent.VK_ESCAPE) {
                screenControls.setVisibility(false);
                screenMenu.setVisibility(true);
            }
        }
        if (screenHistory.isVisibility() == true) {
            if (tecla == KeyEvent.VK_ESCAPE) {
                screenHistory.setVisibility(false);
                screenMenu.setVisibility(true);
            }
        }
    }

    public void pausar(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ESCAPE) {
            if (stageModel.getPlayer().isPlaying()) {
                stageModel.getPlayer().setPlaying(false);
                screenPaused.setPaused(true);
            }
        }
    }

    public void controleFimDeJogo(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (screenEndGame.isVisibility() == true) {
            if (tecla == KeyEvent.VK_ENTER) {
                if (screenEndGame.getCursor() == 0) {
                    stageModel.getPlayer().setPlaying(true);
                    screenEndGame.setVisibility(false);
                }
                if (screenEndGame.getCursor() == 1) {
                    screenEndGame.setVisibility(false);
                    screenMenu.setVisibility(true);
                }
            }
        }
    }

    // CHAMANDO A LEITURA DOS TECLADOS
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (screenMenu.isVisibility() == true) {
                controleMenuInicial(e);
                screenMenu.controleMenu(e);
                stageModel.getSounds().loadSound("botao.wav");
            } else if (screenControls.isVisibility() == true) {
                controleTelas(e);
            } else if (screenHistory.isVisibility() == true) {
                controleTelas(e);
            } else if (stageModel.getPlayer().isPlaying() == true) {
                int tecla = e.getKeyCode();
                stageModel.getPlayer().mover(e);
                stageModel.getPlayer().atirar(e);
                if (tecla == KeyEvent.VK_SPACE && stageModel.getPlayer().isCanShoot()) {
                    stageModel.getSounds().loadSound("tiro.wav");
                }
                pausar(e);
            } else if (screenEndGame.isVisibility() == true) {
                controleFimDeJogo(e);
                screenEndGame.controleMenu(e);
                stageModel.getSounds().loadSound("botao.wav");
            }

            if (screenPaused.isPaused()) {
                int tecla = e.getKeyCode();
                screenPaused.menuPausado(e);
                stageModel.getSounds().loadSound("botao.wav");
                if (tecla == KeyEvent.VK_ENTER) {
                    if (screenPaused.getCursor() == 0) {
                        screenPaused.setPaused(false);
                        stageModel.getPlayer().setPlaying(true);
                        screenPaused.setVisibility(false);
                    }
                    if (screenPaused.getCursor() == 1) {
                        screenPaused.setPaused(false);
                        stageModel.getPlayer().setPlaying(false);
                        screenPaused.setVisibility(false);
                        screenMenu.setVisibility(true);
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