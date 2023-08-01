package br.ifpr.paranavai.jogo.modelo.Modos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
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

import br.ifpr.paranavai.jogo.modelo.Invasores.Meteorito;
import br.ifpr.paranavai.jogo.modelo.Invasores.Naves;
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
    // ATRIBUTOS DA CLASSE
    private Image fundo;
    private Image segundoFundo;

    // LISTA PARA INIMIGOS
    private List<Naves> NaveInimiga;
    private List<Meteorito> MeteoritoInimigo;
    // INSTANCIAS
    private MenuInicial telaMenu;
    private Personagem personagem;
    private Historico telaHistorico;
    private Controles telaControles;
    private FimDeJogo fimDeJogo;
    private TamanhoTela telaTamanho;
    private Pausar telaPausar;
    // TIMERS INIMIGOS
    private Timer TimerNaveInimiga;
    private Timer TimerMeteorito;
    private int delayInimigoNave = 1000;
    private int delayInimigoMeteorito = 3000;
    private Timer timerGlobal;
    // 'TIMER' PARA A COLISAO
    private long ultimaColisao;
    private int delayColisao;
    // ATRIBUTOS PARA O EFEITO 'PISCANDO'
    private boolean piscando;
    private Timer timerPiscar;
    private int delayPiscando = 1000;
    private int contadorPiscar = 0;

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
        timerPiscar = new Timer(delayPiscando, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piscando = !piscando; // ALTERNA A VISIBILIDADE DO PERSONAGEM
            }
        });
        // TIMER PARA O EFEITO DE PISCANDO
        piscando = false;
        timerPiscar.setRepeats(true);
        // TIMER
        this.delayColisao = 1500;
        // ATUALIZA A CLASSE E REDENSENHA A TELA EM INTERVALOS REGULARES.
        timerGlobal = new Timer(1, this);
        timerGlobal.start(); // START
        // INICIALIZANDO MÉTODOS INIMIGOS
        InicializaNaveInimiga(); // NAVES
        InicializaMeteorito(); // METEORITOS
    }

    // INICIANDO POSIÇÃO DAS NAVES INIMIGAS ALEATORIAMENTE
    public void InicializaNaveInimiga() {
        NaveInimiga = new ArrayList<Naves>();
        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        TimerNaveInimiga = new Timer(delayInimigoNave, new ActionListener() {
            int alturaInimigo = 80;
            int maximoEmY = telaTamanho.getLARGURA_TELA() - alturaInimigo;

            @Override
            public void actionPerformed(ActionEvent e) {
                int vidaInimigos = 1;
                int posicaoEmX = telaTamanho.getLARGURA_TELA(); // POSICÃO INICIAL DO PERSONAGEM
                int posicaoEmY = (int) (Math.random() * ((telaTamanho.getALTURA_TELA()) - alturaInimigo));
                if (posicaoEmY <= 0 || posicaoEmY > maximoEmY) {
                    posicaoEmY = (int) (Math.random() * ((telaTamanho.getALTURA_TELA()) - alturaInimigo));
                }
                // AUMENTA A VIDA DO INIMIGO
                if (personagem.getPontos() > 300) {
                    vidaInimigos = 2;
                }
                if (personagem.getPontos() > 900) {
                    vidaInimigos = 3;
                }
                NaveInimiga.add(new Naves(posicaoEmX, posicaoEmY, vidaInimigos));
            }
        });
        TimerNaveInimiga.setRepeats(true);
    }

    // INICIANDO POSIÇÃO DO METEORITO ALEATORIAMENTE
    public void InicializaMeteorito() {
        MeteoritoInimigo = new ArrayList<Meteorito>();
        int alturaInimigo = 100;
        // TIMER PARA SPAWNAR O METEORITO
        TimerMeteorito = new Timer(delayInimigoMeteorito, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmY = -alturaInimigo;
                int posicaoEmX = (int) (Math.random() * (telaTamanho.getLARGURA_TELA() - alturaInimigo));
                if (posicaoEmX < 0 || posicaoEmX > telaTamanho.getLARGURA_TELA()) {
                    posicaoEmX = (int) (Math.random() * (telaTamanho.getLARGURA_TELA() - alturaInimigo));
                }
                MeteoritoInimigo.add(new Meteorito(posicaoEmX, posicaoEmY));
            }
        });
        TimerMeteorito.setRepeats(true);
    }// FIM MÉTODO METEORITO

    public void paintComponent(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        super.paintComponent(g);

        if (telaMenu.isVisibilidade() == true) {
            // CARRREGANDO OS COMPONENTES DA CLASSE 'MENU'
            telaMenu.titulo(graficos);
            telaMenu.menu(graficos);
        } else if (telaControles.isVisibilidade() == true) {
            telaControles.conteudo(graficos);
        } else if (telaHistorico.isVisibilidade() == true) {
            telaHistorico.conteudo(graficos);
        }

        if (telaPausar.isPausado()) {
            telaPausar.conteudo(graficos);
            telaPausar.menu(graficos);
        }

        if (personagem.isJogando() == true) {
            // FUNDO DA FASE INFINITA(PRINCIPAL)
            graficos.drawImage(this.fundo, 0, 0, null);
            // SEGUNDO FUNDO DA FASE CASO A CONDIÇÃO SEJA VERDADEIRA
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

            // CARREGANDO A IMAGEM DO PERSONAGEM SOMENTE SE NÃO ESTIVER PISCANDO
            if (!piscando) {
                graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(),
                        null);
            }

            // COMEÇO METEORO E NAVES INIMIGAS
            for (int i = 0; i < MeteoritoInimigo.size(); i++) {
                Meteorito mete = MeteoritoInimigo.get(i);
                mete.carregar();
                graficos.drawImage(mete.getImagem(), mete.getPosicaoEmX(), mete.getPosicaoEmY(),
                        null);
            }
            for (int i = 0; i < NaveInimiga.size(); i++) {
                Naves ini = NaveInimiga.get(i);
                ini.carregar();
                graficos.drawImage(ini.getImagem(), ini.getPosicaoEmX(), ini.getPosicaoEmY(), null);
                ini.vidas(graficos);
            }
            // FIM METEORO E NAVES INIMIGAS

            // HITBOX
            graficos.setColor(Color.RED);
            Rectangle hitboxPersonagem = personagem.getBounds();
            graficos.drawRect(hitboxPersonagem.x, hitboxPersonagem.y,
                    hitboxPersonagem.width, hitboxPersonagem.height);
            graficos.setColor(Color.BLUE);

            for (Naves ini : NaveInimiga) {
                Rectangle hitboxInimigo = ini.getBounds();
                graficos.drawRect(hitboxInimigo.x, hitboxInimigo.y, hitboxInimigo.width,
                        hitboxInimigo.height);
            }
            for (Meteorito mete : MeteoritoInimigo) {
                Rectangle hitboxInimigo = mete.getBounds();
                graficos.drawRect(hitboxInimigo.x, hitboxInimigo.y, hitboxInimigo.width,
                        hitboxInimigo.height);
            }
            for (Tiro mete : tiros) {
                Rectangle hitboxInimigo = mete.getBounds();
                graficos.drawRect(hitboxInimigo.x, hitboxInimigo.y, hitboxInimigo.width,
                        hitboxInimigo.height);
            }

            // CARREGANDO OS COMPONENTES DA CLASSE PERSONAGEM(VIDA E PONTUAÇÃO)
            personagem.desenhaPontos(graficos);
            personagem.desenhaVida(graficos);
        }
        // CARREGANDO A TELA DE MORTE CASO O JOGADOR MORRA(OBVIO)
        if (fimDeJogo.isVisibilidade() == true) {
            fimDeJogo.titulo(graficos);
            fimDeJogo.menu(graficos);
            personagem.desenhaPontos(graficos);
        }
        g.dispose();
    }

    // MÉTODO PARA ATUALIZAÇÃO DAS IMAGENS
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!telaPausar.isPausado()) {
            // VEREFICA SE O INIMIGO ESTA VISIVEL E ATUALIZA A SUA POSICAÇÃO ATRAVES
            // DO METODO 'ATUALIZAR', AO FICAR INVISIVEL O INIMIGO E EXCLUIDO
            // A CLASSE ITERATOR E COMO UM PONTEIRO EM C QUE PERMITE INTERAJIR COM UM CERTO
            // OBJETO(INIMIGO) DE UMA LISTA DE FORMA ESPECIFICA E EM SEQUENCIA
            Iterator<Naves> iteratorNaves = NaveInimiga.iterator();
            while (iteratorNaves.hasNext()) {
                Naves ini = iteratorNaves.next();
                if (ini.isVisibilidade()) {
                    ini.atualizar();
                } else {
                    iteratorNaves.remove();
                }
            } // FIM NAVES

            // ATUALIZA A POSIÇÃO DO METEORO
            Iterator<Meteorito> iteratorMeteorito = MeteoritoInimigo.iterator();
            while (iteratorMeteorito.hasNext()) {
                Meteorito mete = iteratorMeteorito.next();
                if (mete.isVisibilidade()) {
                    mete.atualizar();
                } else {
                    iteratorMeteorito.remove();
                }
            } // FIM

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
            } // FIM
              // INICIANDO MÉTODOS
            repaint();
            gerenciaFase();
            checarColisoes();
            // REPINTANDO OS OBJETOS
            personagem.atualizar();
        }
        // PARA O SPAWN DOS INIMIGOS PRA QUE ELES NÃO ACUMULEM EM UM SÓ POSIÇÃO
        if (telaPausar.isPausado()) {
            TimerNaveInimiga.stop();
            TimerMeteorito.stop();
        }
        repaint();
    }

    public void gerenciaFase() {
        // ALTERA A VELOCIDADE DA NAVE INIMIGA COM BASE NOS PONTOS GANHOS DO PERSONAGEM
        int[] pontosPersonagem = { 100, 600, 900, 1000 };
        double[] ajusteVelocidades = { 5, 5.5, 6.5, 7 };
        for (int i = 0; i < pontosPersonagem.length; i++) {
            if (personagem.getPontos() > pontosPersonagem[i]) {
                double aumentoVelocidade = ajusteVelocidades[i];
                NaveInimiga.forEach(naveTemporaria -> naveTemporaria.setVelocidade(aumentoVelocidade));
            }
        }

        // CONTROLE DA FASE
        if (personagem.isJogando() == false) {
            timerPiscar.stop();
            // PARA O SPAWN DE INIMIGOS
            TimerNaveInimiga.stop();
            TimerMeteorito.stop();
            // LIMPA A LISTA DE INMIGOS
            NaveInimiga.clear();
            MeteoritoInimigo.clear();
            // RESETA A POSIÇÃO DO JOGADOR
            if (personagem.getPosicaoEmX() != personagem.getPOSICAOINICIALX()
                    || personagem.getPosicaoEmY() != personagem.getPOSICAOINICIALY()) {
                personagem.setPosicaoEmX(personagem.getPOSICAOINICIALX());
                personagem.setPosicaoEmY(personagem.getPOSICAOINICIALY());
            }
        } else {
            TimerNaveInimiga.start();
            if (personagem.getPontos() >= 300) {
                TimerMeteorito.start();
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
    }

    public void checarColisoes() {
        // VALORES PARA CADA INIMIGO DESTRUIDO
        int VALORNAVES = 10;
        int VALORMETEORITO = 20;
        // FORMAS
        Rectangle personagemForma = personagem.getBounds();
        Rectangle naveInimigaForma;
        Rectangle meteoritoForma;

        // VERIFICA COLISÃO COM A BORDA EM 'X'
        if (personagem.getPosicaoEmX() < 0) {
            personagem.setPosicaoEmX(0); // POSIÇÃO MÍNIMA X
        } else if (personagem.getPosicaoEmX() + personagem.getAlturaImagem() > telaTamanho.getLARGURA_TELA()) {
            int maximoEmX = telaTamanho.getLARGURA_TELA() - personagem.getAlturaImagem(); // CALCULA A POSIÇÃO MÁXIMA
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
        for (int i = 0; i < NaveInimiga.size(); i++) {
            Naves naveTemporaria = NaveInimiga.get(i);
            naveInimigaForma = naveTemporaria.getBounds();
            long tempoAtual = System.currentTimeMillis();
            if (tempoAtual - ultimaColisao < delayColisao) {
                return;
            } else {
                contadorPiscar = 0; // REINICIA O CONTADOR
                timerPiscar.start(); // INICIA O TIMER
                if (personagemForma.getBounds().intersects(naveInimigaForma.getBounds())) {
                    // VERIFICA A VIDA DO PERSONAGEM
                    if (personagem.getVida() == 1) {
                        personagem.setJogando(false);
                        fimDeJogo.setVisibilidade(true);
                        personagem.setVisibilidade(false);
                    } else {
                        personagem.setVida(personagem.getVida() - 1);
                    }
                    // VERIFICA A VIDA DA NAVE INIMIGA
                    if (naveTemporaria.getInimigosvida() == 1) {
                        naveTemporaria.setVisibilidade(false);
                        personagem.setPontos(personagem.getPontos() + VALORNAVES);
                    } else {
                        naveTemporaria.setInimigosvida(naveTemporaria.getInimigosvida() - 1);
                    }
                    ultimaColisao = tempoAtual;
                }
            }
            timerPiscar.stop();
            piscando = false;
        }
        // VEREFICA COLISÃO DO TIRO NORMAL E SUPER COM A NAVE INIMIGA
        for (Naves inimigoNave : NaveInimiga) {
            Rectangle formaInimigoNave = inimigoNave.getBounds();
            // TIRO NORMAL
            for (Tiro tiro : personagem.getTiros()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoNave)) {
                    if (inimigoNave.getInimigosvida() == 1) {
                        inimigoNave.setVisibilidade(false);
                        personagem.setPontos(personagem.getPontos() + VALORNAVES);
                    } else {
                        inimigoNave.setInimigosvida(inimigoNave.getInimigosvida() - 1);
                    }
                    tiro.setVisibilidade(false);
                }
            }
            // SUPER TIRO
            for (SuperTiro superTiro : personagem.getSupertiro()) {
                Rectangle formaSuper = superTiro.getBounds();
                if (formaSuper.intersects(formaInimigoNave)) {
                    if (inimigoNave.getInimigosvida() == 1) {
                        inimigoNave.setVisibilidade(false);
                        personagem.setPontos(personagem.getPontos() + VALORNAVES);
                    } else {
                        inimigoNave.setInimigosvida(inimigoNave.getInimigosvida() - 1);
                    }
                    superTiro.setVisibilidade(false);
                }
            }
        }

        // VEREFICA A COLISÃO DO PERSONAGEM COM O METEORITO
        for (int k = 0; k < MeteoritoInimigo.size(); k++) {
            Meteorito meteritoTemporario = MeteoritoInimigo.get(k);
            meteoritoForma = meteritoTemporario.getBounds();
            if (personagemForma.getBounds().intersects(meteoritoForma.getBounds())) {
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
        for (Meteorito inimigoMeteorito : MeteoritoInimigo) {
            Rectangle formaInimigoMeteorito = inimigoMeteorito.getBounds();
            // TIRO NORMAL
            for (Tiro tiro : personagem.getTiros()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoMeteorito)) {
                    inimigoMeteorito.setVisibilidade(false);
                    tiro.setVisibilidade(false);
                    personagem.setPontos(personagem.getPontos() + VALORMETEORITO);
                }
            }
            // SUPER TIRO
            for (SuperTiro superTiro : personagem.getSupertiro()) {
                Rectangle formaSuper = superTiro.getBounds();
                if (formaSuper.intersects(formaInimigoMeteorito)) {
                    inimigoMeteorito.setVisibilidade(false);
                    superTiro.setVisibilidade(false);
                    personagem.setPontos(personagem.getPontos() + VALORMETEORITO);
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
                telaPausar.setVisibilidade(true);
            }
        }
    }

    public void controleFimDeJogo(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (fimDeJogo.isVisibilidade() == true) {
            if (tecla == KeyEvent.VK_ENTER) {
                if (fimDeJogo.getCursor() == 0) {
                    personagem.setJogando(true);
                    personagem.setPontos(0);
                    personagem.setVida(personagem.getVIDAINICIAL());
                    fimDeJogo.setVisibilidade(false);
                }
                if (fimDeJogo.getCursor() == 1) {
                    personagem.setPontos(0);
                    personagem.setVida(personagem.getVIDAINICIAL());
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
                personagem.mover(e);
                personagem.atirar(e);
                pausar(e);
            } else if (fimDeJogo.isVisibilidade() == true) {
                controleFimDeJogo(e);
                fimDeJogo.controleMenu(e);
            }

            // DESPAUSA O JOGO
            if (telaPausar.isPausado()) {
                int tecla = e.getKeyCode();
                telaPausar.menuPausado(e);
                
                if (tecla == KeyEvent.VK_ENTER) {
                    telaPausar.setPausado(false);
                    personagem.setJogando(true);
                    telaPausar.setVisibilidade(false);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.parar(e);
        }
    }
}