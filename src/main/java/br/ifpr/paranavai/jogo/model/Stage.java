package br.ifpr.paranavai.jogo.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
// IMPORTAÇÕES PARA O AUDIO
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import br.ifpr.paranavai.jogo.model.Character.Player;
import br.ifpr.paranavai.jogo.model.Character.Bullets.Shoot;
import br.ifpr.paranavai.jogo.model.Character.Bullets.SpecialShoot;
import br.ifpr.paranavai.jogo.model.Enemies.Asteroide;
import br.ifpr.paranavai.jogo.model.Enemies.Meteorito;
import br.ifpr.paranavai.jogo.model.Enemies.Naves;
import br.ifpr.paranavai.jogo.model.Screens.Controles;
import br.ifpr.paranavai.jogo.model.Screens.FimDeJogo;
import br.ifpr.paranavai.jogo.model.Screens.Historico;
import br.ifpr.paranavai.jogo.model.Screens.MenuInicial;
import br.ifpr.paranavai.jogo.model.Screens.Pausar;
import br.ifpr.paranavai.jogo.Services.StageServiceImpl;
import br.ifpr.paranavai.jogo.Util.TamanhoTela;

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
    private TamanhoTela screenResolution;
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

        // IMAGEM DE FUNDO
        ImageIcon carregando = new ImageIcon("src/main/resources/Sprites/Fundos/FundoFase.jpg");
        this.background = carregando.getImage();
        // IMAGEM SEGUNDO FUNDO
        ImageIcon carregandoDois = new ImageIcon("src/main/resources/Sprites/Fundos/FundoFase2.jpg");
        this.backgroundTwo = carregandoDois.getImage();

        // PERSONAGEM
        stageModel.setPlayer(new Player());
        stageModel.getPlayer().load();

        // INICIA TELA 'MENU'
        screenMenu = new MenuInicial();
        screenMenu.load();
        // INICIA TELA 'HISTORICO' COM AS PARTIDAS ANTERIORES
        screenHistory = new Historico();
        screenHistory.load();
        // INICIA TELA 'CONTROLES'
        screenControls = new Controles();
        screenControls.load();
        // INICIA TELA 'FIM DE JOGO'
        screenEndGame = new FimDeJogo();
        screenEndGame.load();
        // INICIA A CLASSE TAMANHO TELA;
        screenResolution = new TamanhoTela();
        screenResolution.carregar();
        // INICIA A CLASSE TAMANHO TELA;
        screenPaused = new Pausar();
        screenPaused.load();

        // RESCALONAMENTO DAS IMAGENS DE FUNDO
        this.background = this.background.getScaledInstance(
                screenResolution.getWIDTH_SCREEN(), screenResolution.getHEIGHT_SCREEN(), Image.SCALE_FAST);
        // FUNDO 2
        this.backgroundTwo = this.backgroundTwo.getScaledInstance(
                screenResolution.getWIDTH_SCREEN(), screenResolution.getHEIGHT_SCREEN(), Image.SCALE_FAST);

        // INICIALIZANDO CLASSE DE LEITURA DO TECLADO
        addKeyListener(new TecladoAdapter());
        timerFlashing = new Timer(GENERAL_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flashing = !flashing; // ALTERNA A VISIBILIDADE DO PERSONAGEM
            }
        });

        // TIMER PARA O EFEITO DE PISCANDO
        timerFlashing.setRepeats(true);
        flashing = false;
        // ATUALIZA A CLASSE E REDENSENHA A TELA EM INTERVALOS REGULARES.
        timerGlobal = new Timer(5, this);
        timerGlobal.start(); // START

        stageServiceImpl.spawnAsteroids();
        stageServiceImpl.spawnEnemieMeteor();
        stageServiceImpl.spawnEnemieShip();
    }

    // CAMINHO PARA A PASTA QUE POSSUIR OS AUDIOS
    private HashMap<String, Clip> clipSounds = new HashMap<>();

    public void loadSound(String soundName) {
        try {
            if (!clipSounds.containsKey(soundName)) {
                URL urlPath = this.getClass()
                        .getResource("/Audios/" + soundName);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(urlPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clipSounds.put(soundName, clip);
            }

            Clip playSound = clipSounds.get(soundName);
            if (playSound.isRunning()) {
                playSound.stop(); // PARA O CLIP PRA ENTÃO REINICIAR
            }
            playSound.setFramePosition(0); // REINICIA O AUDIO
            playSound.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
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

            // CARREGANDO OS COMPONENTES DA CLASSE PERSONAGEM(VIDA E PONTUAÇÃO)
            stageModel.getPlayer().pontuacao(grapichs);
            stageModel.getPlayer().desenhaVida(grapichs);
            stageModel.getPlayer().desenhaEliminacoes(grapichs);
            stageModel.getPlayer().restauraVida(grapichs);
            // CARREGANDO A IMAGEM DO PERSONAGEM SOMENTE SE NÃO ESTIVER PISCANDO
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

    // MÉTODO PARA ATUALIZAÇÃO DAS IMAGENS
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!screenPaused.isPaused()) {
            repaint();
            // VEREFICA SE O INIMIGO ESTA VISIVEL E ATUALIZA A SUA POSICAÇÃO ATRAVES
            // DO METODO 'ATUALIZAR', AO FICAR INVISIVEL O INIMIGO E EXCLUIDO
            // A CLASSE ITERATOR E COMO UM PONTEIRO EM C QUE PERMITE INTERAJIR COM UM CERTO
            // OBJETO(INIMIGO) DE UMA LISTA DE FORMA ESPECIFICA E EM SEQUENCIA
            Iterator<Naves> iteratorNaves = stageModel.getEnemieShip().iterator();
            while (iteratorNaves.hasNext()) {
                Naves inimigoNave = iteratorNaves.next();
                if (inimigoNave.isVisibility()) {
                    inimigoNave.update();
                } else {
                    iteratorNaves.remove();
                }
            } // FIM NAVES

            // ATUALIZA A POSIÇÃO DO METEORO
            Iterator<Meteorito> iteratorMeteorito = stageModel.getEnemieMeteor().iterator();
            while (iteratorMeteorito.hasNext()) {
                Meteorito inimigoMeteorito = iteratorMeteorito.next();
                if (inimigoMeteorito.isVisibility()) {
                    inimigoMeteorito.update();
                } else {
                    iteratorMeteorito.remove();
                }
            } // FIM

            // ASTEROIDES ATUALIZA
            Iterator<Asteroide> iteratorAsteroide = stageModel.getAsteroids().iterator();
            while (iteratorAsteroide.hasNext()) {
                Asteroide asteroide = iteratorAsteroide.next();
                if (asteroide.isVisibility()) {
                    asteroide.update();
                } else {
                    iteratorAsteroide.remove();
                }
            }

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
            gerenciaFase();
            checarColisoes();
            stageModel.getPlayer().update();
        }
        // PARA O SPAWN DOS INIMIGOS PRA QUE ELES NÃO ACUMULEM EM UMA SÓ POSIÇÃO
        // TAMBÉM CARREGA O MENU E A IMAGEM DA TELA PAUSE
        if (screenPaused.isPaused()) {
            stageServiceImpl.getTimerEnemyShip().stop();
            stageServiceImpl.getTimerAsteroids().stop();
            stageServiceImpl.getTimerMeteor().stop();
            screenPaused.menu(getGraphics());
        }
    }

    public void gerenciaFase() {
        // ALTERA A VELOCIDADE DA NAVE INIMIGA COM BASE NOS PONTOS GANHOS DO PERSONAGEM
        int[] pontosPersonagem = { 300, 600, 800, 1000 };
        double[] ajusteVelocidades = { 5, 5.5, 6.5, 7 };
        for (int i = 0; i < pontosPersonagem.length; i++) {
            if (stageModel.getPlayer().getScore() > pontosPersonagem[i]) {
                double aumentoVelocidade = ajusteVelocidades[i];
                stageModel.getEnemieShip().forEach(naveTemporaria -> naveTemporaria.setSpeed(aumentoVelocidade));
            }
        }

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

    public void checarColisoes() {
        // VALORES PARA CADA INIMIGO DESTRUIDO
        int VALOR_INIMIGOS = 10;
        // FORMAS
        Rectangle personagemForma = stageModel.getPlayer().getBounds();
        Rectangle naveInimigaForma;
        Rectangle meteoritoForma;

        // VERIFICA COLISÃO COM A BORDA EM 'X'
        if (stageModel.getPlayer().getPositionInX() < 0) {
            stageModel.getPlayer().setPositionInX(0); // POSIÇÃO MÍNIMA X
        } else if (stageModel.getPlayer().getPositionInX() + stageModel.getPlayer().getWidthImage() > screenResolution
                .getWIDTH_SCREEN()) {
            int maximoEmX = screenResolution.getWIDTH_SCREEN() - stageModel.getPlayer().getWidthImage(); // CALCULA A
                                                                                                        // POSIÇÃO
                                                                                                        // MÁXIMA
            stageModel.getPlayer().setPositionInX(maximoEmX);
        }
        // VERIFICA COLISÃO COM A BORDA EM 'Y'
        if (stageModel.getPlayer().getPositionInY() < 0) {
            stageModel.getPlayer().setPositionInY(0); // POSIÇÃO MÍNIMA Y
        } else if (stageModel.getPlayer().getPositionInY() + stageModel.getPlayer().getHeightImage() > screenResolution
                .getHEIGHT_SCREEN()) {
            int maximoEmY = screenResolution.getHEIGHT_SCREEN() - stageModel.getPlayer().getHeightImage();
            stageModel.getPlayer().setPositionInY(maximoEmY);
        }

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
                        int pontuacaoAtual = stageModel.getPlayer().getScore() + VALOR_INIMIGOS;
                        if (pontuacaoAtual % 50 == 0) {
                            stageModel.getPlayer().setSuperQuantity(2);
                        }
                        inimigoNave.setVisibility(false);
                        stageModel.getPlayer().setScore(pontuacaoAtual);
                        stageModel.getPlayer().setScoreDeadEnemys(stageModel.getPlayer().getScoreDeadEnemys() + 1);
                        loadSound("explosao.wav");
                        this.enemyKilled = true;
                    } else {
                        inimigoNave.setLife(inimigoNave.getLife() - 1);
                        loadSound("colisao.wav");
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
                        stageModel.getPlayer().setScore(stageModel.getPlayer().getScore() + VALOR_INIMIGOS);
                        loadSound("explosao.wav");
                        this.enemyKilled = true;
                    } else {
                        inimigoNave.setLife(inimigoNave.getLife() - 1);
                        loadSound("colisao.wav");
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
                    stageModel.getPlayer().setScore(stageModel.getPlayer().getScore() + VALOR_INIMIGOS);
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
                    stageModel.getPlayer().setScore(stageModel.getPlayer().getScore() + VALOR_INIMIGOS);
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
                loadSound("botao.wav");
            } else if (screenControls.isVisibility() == true) {
                controleTelas(e);
            } else if (screenHistory.isVisibility() == true) {
                controleTelas(e);
            } else if (stageModel.getPlayer().isPlaying() == true) {
                int tecla = e.getKeyCode();
                stageModel.getPlayer().mover(e);
                stageModel.getPlayer().atirar(e);
                if (tecla == KeyEvent.VK_SPACE && stageModel.getPlayer().isCanShoot()) {
                    loadSound("tiro.wav");
                }
                pausar(e);
            } else if (screenEndGame.isVisibility() == true) {
                controleFimDeJogo(e);
                screenEndGame.controleMenu(e);
                loadSound("botao.wav");
            }

            if (screenPaused.isPaused()) {
                int tecla = e.getKeyCode();
                screenPaused.menuPausado(e);
                loadSound("botao.wav");
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