package br.ifpr.paranavai.jogo.modelo.Modos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
// IMPORTAÇÕES PARA O AUDIO
import java.io.File;
import java.io.FileInputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.InputStream;

import br.ifpr.paranavai.jogo.modelo.Invasores.Meteorito;
import br.ifpr.paranavai.jogo.modelo.Invasores.Naves;
import br.ifpr.paranavai.jogo.modelo.Invasores.Asteroide;
import br.ifpr.paranavai.jogo.modelo.Invasores.StarDestroyer;
import br.ifpr.paranavai.jogo.modelo.Jogador.Personagem;
import br.ifpr.paranavai.jogo.modelo.Jogador.Tiro;
import br.ifpr.paranavai.jogo.modelo.Jogador.SuperTiro;
import br.ifpr.paranavai.jogo.modelo.Telas.Controles;
import br.ifpr.paranavai.jogo.modelo.Telas.Historico;
import br.ifpr.paranavai.jogo.modelo.Telas.MenuInicial;
import br.ifpr.paranavai.jogo.modelo.Telas.Pausar;
import br.ifpr.paranavai.principal.TamanhoTela;
import br.ifpr.paranavai.jogo.modelo.Telas.FimDeJogo;

public class Infinito extends JPanel implements ActionListener {

    private boolean inimigoMorto;
    private int posicaoPontuacaoX, posicaoPontuacaoY;
    // ATRIBUTOS DA CLASSE
    private Image fundo;
    private Image segundoFundo;
    // LISTA PARA INIMIGOS
    private List<Naves> naveInimiga;
    private List<Asteroide> asteroides;
    private List<StarDestroyer> deathstar;
    private List<Meteorito> meteoritoInimigo;
    // INSTANCIAS
    private Pausar telaPausar;
    private FimDeJogo fimDeJogo;
    private MenuInicial telaMenu;
    private Personagem personagem;
    private Historico telaHistorico;
    private Controles telaControles;
    private TamanhoTela telaTamanho;
    // TIMERS
    private Timer timerMeteorito;
    private Timer timerDeathStar;
    private Timer timerAsteroides;
    private Timer timerNaveInimiga;
    private final Timer timerGlobal;
    // DELAYS INIMIHOS
    private final static int DELAY_GERAL = 1000;
    // 'TIMER' PARA A COLISAO
    private long ultimaColisao;
    // VARIAVEIS DO EFEITO 'PISCANDO' PARA O PERSONAGEM
    private boolean piscando;
    private Timer timerPiscar;
    private int contadorPiscar = 0;

    // AUDIO
    private File bulletSound = new File("recursos/Audios/tiro.wav");
    private AudioStream bulletSoundAudio;
    private InputStream bulletSoundInput;

    public Infinito() {
        setFocusable(true);
        setDoubleBuffered(true);

        // IMAGEM DE FUNDO
        ImageIcon carregando = new ImageIcon("recursos\\Sprites\\Fundos\\FundoFase.jpg");
        this.fundo = carregando.getImage();
        // IMAGEM SEGUNDO FUNDO
        ImageIcon carregandoDois = new ImageIcon("recursos\\Sprites\\Fundos\\FundoFase2.jpg");
        this.segundoFundo = carregandoDois.getImage();

        // INICIA A CLASSE PERSONAGEM
        personagem = new Personagem();
        personagem.carregar();
        // INICIA TELA 'MENU'
        telaMenu = new MenuInicial();
        telaMenu.carregar();
        // INICIA TELA 'HISTORICO' COM AS PARTIDAS ANTERIORES
        telaHistorico = new Historico();
        telaHistorico.carregar();
        // INICIA TELA 'CONTROLES'
        telaControles = new Controles();
        telaControles.carregar();
        // INICIA TELA 'FIM DE JOGO'
        fimDeJogo = new FimDeJogo();
        fimDeJogo.carregar();
        // INICIA A CLASSE TAMANHO TELA;
        telaTamanho = new TamanhoTela();
        telaTamanho.carregar();
        // INICIA A CLASSE TAMANHO TELA;
        telaPausar = new Pausar();
        telaPausar.carregar();

        // RESCALONAMENTO DAS IMAGENS DE FUNDO
        this.fundo = this.fundo.getScaledInstance(
                telaTamanho.getLARGURA_TELA(), telaTamanho.getALTURA_TELA(), Image.SCALE_FAST);
        // FUNDO 2
        this.segundoFundo = this.segundoFundo.getScaledInstance(
                telaTamanho.getLARGURA_TELA(), telaTamanho.getALTURA_TELA(), Image.SCALE_FAST);

        // INICIALIZANDO CLASSE DE LEITURA DO TECLADO
        addKeyListener(new TecladoAdapter());
        timerPiscar = new Timer(DELAY_GERAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piscando = !piscando; // ALTERNA A VISIBILIDADE DO PERSONAGEM
            }
        });

        // TIMER PARA O EFEITO DE PISCANDO
        timerPiscar.setRepeats(true);
        piscando = false;
        // ATUALIZA A CLASSE E REDENSENHA A TELA EM INTERVALOS REGULARES.
        timerGlobal = new Timer(5, this);
        timerGlobal.start(); // START
        // INICIALIZANDO MÉTODOS INIMIGOS
        inicializaNaveInimiga(); // NAVES
        inicializaMeteorito(); // METEORITOS
        inicializaDeathStar();// NUVEMS
        inicializaAsteroides();
    }

    // INICIANDO POSIÇÃO DAS NAVES INIMIGAS ALEATORIAMENTE
    public void inicializaNaveInimiga() {
        naveInimiga = new ArrayList<Naves>();
        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        timerNaveInimiga = new Timer(DELAY_GERAL, new ActionListener() {
            int alturaInimigo = 80;
            int vidaInimigos = 1;
            int multiploNave = 100;
            boolean vidaAumentada;

            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = telaTamanho.getLARGURA_TELA(); // POSICÃO INICIAL DO PERSONAGEM
                int posicaoEmY = (int) (Math.random() * ((telaTamanho.getALTURA_TELA()) - alturaInimigo));
                // AUMENTA A VIDA DO INIMIGO COM BASE NOS PONTOS DO JOGADOR
                if (personagem.isJogando() && personagem.getPontos() > 0 && personagem.getPontos() % multiploNave == 0
                        && vidaInimigos < 4 && !vidaAumentada) {
                    vidaAumentada = true;
                    vidaInimigos = vidaInimigos + 1;
                }
                // VERIFICANDO SE A PONTUAÇÃO NÃO E MAIS VALIDA E REDEFININDO A VARIAVEL PARA
                if (personagem.getPontos() % multiploNave != 0) {
                    vidaAumentada = false;
                }
                naveInimiga.add(new Naves(posicaoEmX, posicaoEmY, vidaInimigos));
            }
        });
        timerNaveInimiga.setRepeats(true);
    }

    // INICIANDO POSIÇÃO DO METEORITO ALEATORIAMENTE
    public void inicializaMeteorito() {
        meteoritoInimigo = new ArrayList<Meteorito>();
        int alturaInimigo = 100;
        // TIMER PARA SPAWNAR O METEORITO
        timerMeteorito = new Timer(DELAY_GERAL * 3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmY = -alturaInimigo;
                int posicaoEmX = (int) (Math.random() * (telaTamanho.getLARGURA_TELA() - alturaInimigo));
                meteoritoInimigo.add(new Meteorito(posicaoEmX, posicaoEmY));
            }
        });
        timerMeteorito.setRepeats(true);
    }// FIM MÉTODO METEORITO

    // INICIALIZA NUVEM SEM COLISÃO(TEMPORARIO)
    public void inicializaDeathStar() {
        deathstar = new ArrayList<StarDestroyer>();
        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        timerDeathStar = new Timer(DELAY_GERAL * 19, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = telaTamanho.getLARGURA_TELA();
                int posicaoEmY = (int) (Math.random() * (telaTamanho.getALTURA_TELA() - 100));
                deathstar.add(new StarDestroyer(posicaoEmX, posicaoEmY));
            }
        });
        timerDeathStar.setRepeats(true);
    }

    public void inicializaAsteroides() {
        asteroides = new ArrayList<Asteroide>();
        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        timerAsteroides = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = telaTamanho.getLARGURA_TELA();
                int posicaoEmY = (int) (Math.random() * (telaTamanho.getALTURA_TELA()));
                asteroides.add(new Asteroide(posicaoEmX, posicaoEmY));
            }
        });
        timerAsteroides.setRepeats(true);
    }

    public void paintComponent(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        super.paintComponent(g);

        if (telaMenu.isVisibilidade() == true) {
            telaMenu.conteudo(graficos);
        } else if (telaControles.isVisibilidade() == true) {
            telaControles.conteudo(graficos);
        } else if (telaHistorico.isVisibilidade() == true) {
            telaHistorico.conteudo(graficos);
        }

        if (personagem.isJogando() == true) {
            graficos.drawImage(this.fundo, 0, 0, null);
            if (personagem.getPontos() >= 200) {
                graficos.drawImage(this.segundoFundo, 0, 0, null);
            }
            // CARREGANDO TIRO
            List<Tiro> tiros = personagem.getTiros();
            for (int i = 0; i < tiros.size(); i++) {
                Tiro tiro = tiros.get(i);
                tiro.carregar();
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);

            }
            // CARREGANDO TIRO ESPECIAL
            List<SuperTiro> tiroSuper = personagem.getSupertiro();
            for (int i = 0; i < tiroSuper.size(); i++) {
                SuperTiro tiroEspecial = tiroSuper.get(i);
                tiroEspecial.carregar();
                graficos.drawImage(tiroEspecial.getImagem(),
                        tiroEspecial.getPosicaoEmX(), tiroEspecial.getPosicaoEmY(), null);
            }
            // CARREGA DEATH STAR
            for (int i = 0; i < asteroides.size(); i++) {
                Asteroide asteroide = asteroides.get(i);
                asteroide.carregar();
                graficos.drawImage(asteroide.getImagem(),
                        asteroide.getPosicaoEmX(), asteroide.getPosicaoEmY(), null);
            }
            // CARREGA DEATH STAR
            for (int i = 0; i < deathstar.size(); i++) {
                StarDestroyer chefao = deathstar.get(i);
                chefao.carregar();
                graficos.drawImage(chefao.getImagem(),
                        chefao.getPosicaoEmX(), chefao.getPosicaoEmY(), null);
            }

            // DESENHA A PONTUAÇÃO ACIMA DO INIMIGO MORTO
            if (inimigoMorto) {
                graficos.setColor(Color.WHITE);
                graficos.drawString("+ 10", posicaoPontuacaoX + 5, posicaoPontuacaoY -= 1);
            } else {
                graficos.setColor(Color.WHITE);
                g.drawString("- 1", posicaoPontuacaoX + 75, posicaoPontuacaoY += 1);
            }

            // COMEÇO METEORO E NAVES INIMIGAS
            for (int i = 0; i < meteoritoInimigo.size(); i++) {
                Meteorito meteorito = meteoritoInimigo.get(i);
                meteorito.carregar();
                graficos.drawImage(meteorito.getImagem(), meteorito.getPosicaoEmX(), meteorito.getPosicaoEmY(),
                        null);
            }

            for (int i = 0; i < naveInimiga.size(); i++) {
                Naves inimigoNave = naveInimiga.get(i);
                inimigoNave.carregar();
                graficos.drawImage(inimigoNave.getImagem(), inimigoNave.getPosicaoEmX(), inimigoNave.getPosicaoEmY(),
                        null);
                inimigoNave.vidas(graficos);
                inimigoNave.inimigoDados(graficos);
            } // FIM METEORO E NAVES INIMIGAS

            // CARREGANDO OS COMPONENTES DA CLASSE PERSONAGEM(VIDA E PONTUAÇÃO)
            personagem.pontuacao(graficos);
            personagem.desenhaVida(graficos);
            personagem.desenhaEliminacoes(graficos);
            personagem.restauraVida(graficos);
            // CARREGANDO A IMAGEM DO PERSONAGEM SOMENTE SE NÃO ESTIVER PISCANDO
            if (!piscando) {
                graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(),
                        null);
            }
        } else if (fimDeJogo.isVisibilidade() == true) {
            fimDeJogo.titulo(graficos);
            fimDeJogo.menu(graficos);
        }

        g.dispose();
    }

    // MÉTODO PARA ATUALIZAÇÃO DAS IMAGENS
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!telaPausar.isPausado()) {
            repaint();
            // VEREFICA SE O INIMIGO ESTA VISIVEL E ATUALIZA A SUA POSICAÇÃO ATRAVES
            // DO METODO 'ATUALIZAR', AO FICAR INVISIVEL O INIMIGO E EXCLUIDO
            // A CLASSE ITERATOR E COMO UM PONTEIRO EM C QUE PERMITE INTERAJIR COM UM CERTO
            // OBJETO(INIMIGO) DE UMA LISTA DE FORMA ESPECIFICA E EM SEQUENCIA
            Iterator<Naves> iteratorNaves = naveInimiga.iterator();
            while (iteratorNaves.hasNext()) {
                Naves inimigoNave = iteratorNaves.next();
                if (inimigoNave.isVisibilidade()) {
                    inimigoNave.atualizar();
                } else {
                    iteratorNaves.remove();
                }
            } // FIM NAVES

            // ATUALIZA A POSIÇÃO DO METEORO
            Iterator<Meteorito> iteratorMeteorito = meteoritoInimigo.iterator();
            while (iteratorMeteorito.hasNext()) {
                Meteorito inimigoMeteorito = iteratorMeteorito.next();
                if (inimigoMeteorito.isVisibilidade()) {
                    inimigoMeteorito.atualizar();
                } else {
                    iteratorMeteorito.remove();
                }
            } // FIM

            // DEATHSTAR ATUALIZA
            Iterator<StarDestroyer> iteratorChefao = deathstar.iterator();
            while (iteratorChefao.hasNext()) {
                StarDestroyer chefao = iteratorChefao.next();
                if (chefao.isVisibilidade()) {
                    chefao.atualizar();
                } else {
                    iteratorChefao.remove();
                }
            }

            // ASTEROIDES ATUALIZA
            Iterator<Asteroide> iteratorAsteroide = asteroides.iterator();
            while (iteratorAsteroide.hasNext()) {
                Asteroide asteroide = iteratorAsteroide.next();
                if (asteroide.isVisibilidade()) {
                    asteroide.atualizar();
                } else {
                    iteratorAsteroide.remove();
                }
            }

            // ATUALIZA POSICAÇÃO DO TIRO
            List<Tiro> tiros = personagem.getTiros();
            Iterator<Tiro> iteratorTiro = tiros.iterator();
            while (iteratorTiro.hasNext()) {
                Tiro tiro = iteratorTiro.next();
                if (tiro.isVisibilidade()) {
                    tiro.atualizar();
                } else {
                    iteratorTiro.remove();
                }
            } // FIM

            // ATUALIZA POSICAÇÃO DO TIRO ESPECIAL
            List<SuperTiro> tirosEspecial = personagem.getSupertiro();
            Iterator<SuperTiro> iteratorTiroEspecial = tirosEspecial.iterator();
            while (iteratorTiroEspecial.hasNext()) {
                SuperTiro tiroSuper = iteratorTiroEspecial.next();
                if (tiroSuper.isVisibilidade()) {
                    tiroSuper.atualizar();
                } else {
                    iteratorTiroEspecial.remove();
                }
            }
            // FIM
            gerenciaFase();
            checarColisoes();
            personagem.atualizar();
        }
        // PARA O SPAWN DOS INIMIGOS PRA QUE ELES NÃO ACUMULEM EM UMA SÓ POSIÇÃO
        // TAMBÉM CARREGA O MENU E A IMAGEM DA TELA PAUSE
        if (telaPausar.isPausado()) {
            timerNaveInimiga.stop();
            timerMeteorito.stop();
            timerAsteroides.stop();
            timerDeathStar.stop();
            telaPausar.menu(getGraphics());
        }
    }

    public void gerenciaFase() {
        // ALTERA A VELOCIDADE DA NAVE INIMIGA COM BASE NOS PONTOS GANHOS DO PERSONAGEM
        int[] pontosPersonagem = { 300, 600, 800, 1000 };
        double[] ajusteVelocidades = { 5, 5.5, 6.5, 7 };
        for (int i = 0; i < pontosPersonagem.length; i++) {
            if (personagem.getPontos() > pontosPersonagem[i]) {
                double aumentoVelocidade = ajusteVelocidades[i];
                naveInimiga.forEach(naveTemporaria -> naveTemporaria.setVelocidade(aumentoVelocidade));
            }
        }

        // CONTROLE DA FASE
        if (personagem.isJogando() == false) {
            // PERSONAGEM
            timerPiscar.stop();
            personagem.getTiros().clear();
            // PARA O SPAWN DE INIMIGOS
            timerNaveInimiga.stop();
            timerMeteorito.stop();
            timerDeathStar.stop();
            timerAsteroides.stop();
            // LIMPA A LISTA DE INMIGOS
            naveInimiga.clear();
            meteoritoInimigo.clear();
            deathstar.clear();
            // RESETA A POSIÇÃO DO JOGADOR
            if (personagem.getPosicaoEmX() != personagem.getPOSICAO_INICIAL_X()
                    || personagem.getPosicaoEmY() != personagem.getPOSICAO_INICIAL_Y()) {
                personagem.setPosicaoEmX(personagem.getPOSICAO_INICIAL_X());
                personagem.setPosicaoEmY(personagem.getPOSICAO_INICIAL_Y());
            }
            // RESETA A HUD
            personagem.setPontos(0);
            personagem.setInimigosMortos(0);
            personagem.setVida(personagem.getVidaInicial());
        } else {
            timerNaveInimiga.start();
            timerDeathStar.start();
            timerAsteroides.start();

            if (personagem.getPontos() >= 300) {
                timerMeteorito.start();
            }
        }

        // FUNÇÃO PARA FAZER O PERSONAGEM 'PISCAR' CASO COLIDA
        contadorPiscar++; // CONTADOR
        if (contadorPiscar % 2 == 1) {
            piscando = false; // SE FOR IMPAR O PERSONAGEM TA VISIVEL
        } else {
            piscando = true; // SE FOR PAR O PERSONAGEM ESTA PISCANDO
        }
        if (contadorPiscar == 10) { // VEZES PARA 'PISCAR'
            timerPiscar.stop();
            piscando = false;
        }

        // FUNÇÃO PARA VERIFICAR A VIDA DO PERSONAGEM E ATIVAR O SPAWN DE VIDA
        if (personagem.getPontos() > 0 && personagem.getPontos() % 300 == 0 && personagem.isVidaRestaurada() == false) {
            personagem.setQtdeRestauraVida(1);
            personagem.setVidaRestaurada(true);
        }

        try {
            bulletSoundInput = new FileInputStream(bulletSound);
            bulletSoundAudio = new AudioStream(bulletSoundInput);
        } catch (Exception e) {

        }

    }

    public void checarColisoes() {
        // VALORES PARA CADA INIMIGO DESTRUIDO
        int VALOR_INIMIGOS = 10;
        // FORMAS
        Rectangle personagemForma = personagem.getBounds();
        Rectangle naveInimigaForma;
        Rectangle meteoritoForma;

        // VERIFICA COLISÃO COM A BORDA EM 'X'
        if (personagem.getPosicaoEmX() < 0) {
            personagem.setPosicaoEmX(0); // POSIÇÃO MÍNIMA X
        } else if (personagem.getPosicaoEmX() + personagem.getLarguraImagem() > telaTamanho.getLARGURA_TELA()) {
            int maximoEmX = telaTamanho.getLARGURA_TELA() - personagem.getLarguraImagem(); // CALCULA A POSIÇÃO MÁXIMA
            personagem.setPosicaoEmX(maximoEmX);
        }
        // VERIFICA COLISÃO COM A BORDA EM 'Y'
        if (personagem.getPosicaoEmY() < 0) {
            personagem.setPosicaoEmY(0); // POSIÇÃO MÍNIMA Y
        } else if (personagem.getPosicaoEmY() + personagem.getAlturaImagem() > telaTamanho.getALTURA_TELA()) {
            int maximoEmY = telaTamanho.getALTURA_TELA() - personagem.getAlturaImagem();
            personagem.setPosicaoEmY(maximoEmY);
        }

        // VERIFICA A COLISÃO DO PERSONAGEM COM A NAVE INIMIGA
        for (int i = 0; i < naveInimiga.size(); i++) {
            Naves naveTemporaria = naveInimiga.get(i);
            naveInimigaForma = naveTemporaria.getBounds();
            long tempoAtual = System.currentTimeMillis();
            if (tempoAtual - ultimaColisao < DELAY_GERAL) {
                return;
            } else {
                contadorPiscar = 0; // REINICIA O CONTADOR
                timerPiscar.start(); // INICIA O TIMER
                if (personagemForma.getBounds().intersects(naveInimigaForma.getBounds())) {
                    // VERIFICA SE A VIDA DO PERSONAGME PODE SER RESTAURADA
                    if (personagem.getVida() == 1) {
                        personagem.setJogando(false);
                        fimDeJogo.setVisibilidade(true);
                        personagem.setVisibilidade(false);
                    } else {
                        personagem.setVida(personagem.getVida() - 1);
                    }
                    // VERIFICA A VIDA DA NAVE INIMIGA
                    if (naveTemporaria.getVida() == 1) {
                        naveTemporaria.setVisibilidade(false);
                    } else {
                        naveTemporaria.setVida(naveTemporaria.getVida() - 1);
                    }
                    ultimaColisao = tempoAtual;
                }
            }
            timerPiscar.stop();
            piscando = false;
        }
        for (Naves inimigoNave : naveInimiga) {
            Rectangle formaInimigoNave = inimigoNave.getBounds();
            // TIRO NORMAL
            for (Tiro tiro : personagem.getTiros()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoNave)) {
                    if (inimigoNave.getVida() == 1) {
                        int pontuacaoAtual = personagem.getPontos() + VALOR_INIMIGOS;
                        if (pontuacaoAtual % 50 == 0) {
                            personagem.setQtdeSuper(2);
                        }
                        inimigoNave.setVisibilidade(false);
                        personagem.setPontos(pontuacaoAtual);
                        personagem.setInimigosMortos(personagem.getInimigosMortos() + 1);
                        this.inimigoMorto = true;
                    } else {
                        inimigoNave.setVida(inimigoNave.getVida() - 1);
                        this.inimigoMorto = false;
                    }
                    posicaoPontuacaoX = inimigoNave.getPosicaoEmX();
                    posicaoPontuacaoY = inimigoNave.getPosicaoEmY();
                    tiro.setVisibilidade(false);
                }
            }
            // SUPER TIRO
            for (SuperTiro superTiro : personagem.getSupertiro()) {
                Rectangle formaSuper = superTiro.getBounds();
                if (formaSuper.intersects(formaInimigoNave)) {
                    if (inimigoNave.getVida() == 1) {
                        inimigoNave.setVisibilidade(false);
                        personagem.setPontos(personagem.getPontos() + VALOR_INIMIGOS);
                        this.inimigoMorto = true;
                    } else {
                        inimigoNave.setVida(inimigoNave.getVida() - 1);
                        this.inimigoMorto = false;
                    }
                    superTiro.setVisibilidade(false);
                    posicaoPontuacaoX = inimigoNave.getPosicaoEmX();
                    posicaoPontuacaoY = inimigoNave.getPosicaoEmY();
                }
            }
        }

        // VEREFICA A COLISÃO DO PERSONAGEM COM O METEORITO
        for (int k = 0; k < meteoritoInimigo.size(); k++) {
            Meteorito meteritoTemporario = meteoritoInimigo.get(k);
            meteoritoForma = meteritoTemporario.getBounds();
            if (personagemForma.getBounds().intersects(meteoritoForma.getBounds())) {
                // VERIFICA SE O PERSONAGEM ESTA PERTO DE MORRER
                if (personagem.getVida() == 1) {
                    personagem.setJogando(false);
                    fimDeJogo.setVisibilidade(true);

                } else {
                    personagem.setVida(personagem.getVida() - 1);
                }
                meteritoTemporario.setVisibilidade(false);
                personagem.setVisibilidade(false);
            }
        }
        // VERIFICA A COLISÃO DO TIRO NORMAL E SUPER COM O METEORIOTO
        for (Meteorito inimigoMeteorito : meteoritoInimigo) {
            Rectangle formaInimigoMeteorito = inimigoMeteorito.getBounds();
            // TIRO NORMAL
            for (Tiro tiro : personagem.getTiros()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoMeteorito)) {
                    inimigoMeteorito.setVisibilidade(false);
                    tiro.setVisibilidade(false);
                    personagem.setPontos(personagem.getPontos() + VALOR_INIMIGOS);
                    inimigoMorto = true;
                    posicaoPontuacaoX = inimigoMeteorito.getPosicaoEmX();
                    posicaoPontuacaoY = inimigoMeteorito.getPosicaoEmY();
                }
            }
            // SUPER TIRO
            for (SuperTiro superTiro : personagem.getSupertiro()) {
                Rectangle formaSuper = superTiro.getBounds();
                if (formaSuper.intersects(formaInimigoMeteorito)) {
                    inimigoMeteorito.setVisibilidade(false);
                    superTiro.setVisibilidade(false);
                    personagem.setPontos(personagem.getPontos() + VALOR_INIMIGOS);
                    inimigoMorto = true;
                    posicaoPontuacaoX = inimigoMeteorito.getPosicaoEmX();
                    posicaoPontuacaoY = inimigoMeteorito.getPosicaoEmY();
                }
            }
        }
    }

    public void controleMenuInicial(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ENTER) {
            if (telaMenu.getCursor() == 0) { // TELA JOGO FASE - IMPLEMENTAR
                System.out.println("Tela Fase - Implementando");
            } else if (telaMenu.getCursor() == 1) { // TELA JOGO
                personagem.setJogando(true);
                telaMenu.setVisibilidade(false);
            } else if (telaMenu.getCursor() == 2) { // TELA CONTROLES
                telaMenu.setVisibilidade(false);
                telaControles.setVisibilidade(true);
            } else if (telaMenu.getCursor() == 3) { // TELA HISTORICO - IMPLEMENTAR
                telaMenu.setVisibilidade(false);
                telaHistorico.setVisibilidade(true);
            }
        }
    }

    public void controleTelas(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (telaControles.isVisibilidade() == true) {
            if (tecla == KeyEvent.VK_ESCAPE) {
                telaControles.setVisibilidade(false);
                telaMenu.setVisibilidade(true);
            }
        }
        if (telaHistorico.isVisibilidade() == true) {
            if (tecla == KeyEvent.VK_ESCAPE) {
                telaHistorico.setVisibilidade(false);
                telaMenu.setVisibilidade(true);
            }
        }
    }

    public void pausar(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ESCAPE) {
            if (personagem.isJogando()) {
                personagem.setJogando(false);
                telaPausar.setPausado(true);
            }
        }
    }

    public void controleFimDeJogo(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (fimDeJogo.isVisibilidade() == true) {
            if (tecla == KeyEvent.VK_ENTER) {
                if (fimDeJogo.getCursor() == 0) {
                    personagem.setJogando(true);
                    fimDeJogo.setVisibilidade(false);
                }
                if (fimDeJogo.getCursor() == 1) {
                    fimDeJogo.setVisibilidade(false);
                    telaMenu.setVisibilidade(true);
                }
            }
        }
    }

    // CHAMANDO A LEITURA DOS TECLADOS
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (telaMenu.isVisibilidade() == true) {
                controleMenuInicial(e);
                telaMenu.controleMenu(e);
            } else if (telaControles.isVisibilidade() == true) {
                controleTelas(e);
            } else if (telaHistorico.isVisibilidade() == true) {
                controleTelas(e);
            } else if (personagem.isJogando() == true) {
                int tecla = e.getKeyCode();
                personagem.mover(e);
                personagem.atirar(e);
                if (tecla == KeyEvent.VK_SPACE && personagem.isPodeAtirar()) {
                    AudioPlayer.player.start(bulletSoundAudio);
                }
                pausar(e);
            } else if (fimDeJogo.isVisibilidade() == true) {
                controleFimDeJogo(e);
                fimDeJogo.controleMenu(e);
            }

            if (telaPausar.isPausado()) {
                int tecla = e.getKeyCode();
                telaPausar.menuPausado(e);
                if (tecla == KeyEvent.VK_ENTER) {
                    if (telaPausar.getCursor() == 0) {
                        telaPausar.setPausado(false);
                        personagem.setJogando(true);
                        telaPausar.setVisibilidade(false);
                    }
                    if (telaPausar.getCursor() == 1) {
                        telaPausar.setPausado(false);
                        personagem.setJogando(false);
                        telaPausar.setVisibilidade(false);
                        telaMenu.setVisibilidade(true);
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.parar(e);
        }
    }
}