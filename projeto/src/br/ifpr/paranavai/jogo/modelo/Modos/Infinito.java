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
import br.ifpr.paranavai.jogo.modelo.Jogador.Personagem;
import br.ifpr.paranavai.jogo.modelo.Jogador.Tiro;
import br.ifpr.paranavai.jogo.modelo.Jogador.TiroEspecial;
import br.ifpr.paranavai.jogo.modelo.Telas.Controles;
import br.ifpr.paranavai.jogo.modelo.Telas.Historico;
import br.ifpr.paranavai.jogo.modelo.Telas.MenuInicial;
import br.ifpr.paranavai.jogo.modelo.Telas.FimDeJogo;
import br.ifpr.paranavai.jogo.modelo.inimigos.Meteorito;
import br.ifpr.paranavai.jogo.modelo.inimigos.Naves;

public class Infinito extends JPanel implements ActionListener {
    // TELAS
    private Image fundo;
    private Image fundo2;
    // LISTA PARA INIMIGOS
    private List<Naves> NaveInimiga;
    private List<Meteorito> MeteoritoInimigo;
    private boolean jogando;
    // TAMANHO TELA
    private int LARGURATELA = 1600;
    private int ALTURATELA = 780; // PRECISA SER UM POUCO MENOR QUE O TAMANHO DA TELA
    // INSTANCIAS
    private MenuInicial telaMenu;
    private Personagem personagem;
    private Historico telaHistorico;
    private Controles telaControles;
    private FimDeJogo fimDeJogo;
    // TIMERS
    private Timer TimerNaveInimiga;
    private Timer TimerMeteorito;
    private int delayInimigoNave = 450;
    private int delayInimigoMeteorito = 2500;
    private Timer timerGlobal;
    // 'TIMER' PARA A COLISAO
    private long ultimaColisao;
    private int delayColisao;
    // ATRIBUTOS PARA O EFEITO 'PISCANDO'
    private boolean piscando;
    private Timer timerPiscar;
    private int delayPiscando = 500;
    private int contadorPiscar = 0;

    public Infinito() {
        setFocusable(true);
        setDoubleBuffered(true);
        // VALOR DO DELAY PARA A COLISAO
        this.delayColisao = 1500;
        // IMAGEM DE FUNDO
        ImageIcon carregando = new ImageIcon("recursos\\sprites_Fundos\\FundoUm.jpg");
        this.fundo = carregando.getImage();
        // IMAGEM SEGUNDO FUNDO
        ImageIcon carregandoDois = new ImageIcon("recursos\\sprites_Fundos\\FundoDois.jpg");
        this.fundo2 = carregandoDois.getImage();

        personagem = new Personagem();
        personagem.carregar();
        //
        telaMenu = new MenuInicial();
        telaMenu.carregar();
        //
        telaHistorico = new Historico();
        telaHistorico.carregar();
        //
        telaControles = new Controles();
        telaControles.carregar();
        //
        fimDeJogo = new FimDeJogo();
        fimDeJogo.carregar();
        // INICIALIZANDO O METODO DE LEITURA DO TECLADO
        addKeyListener(new TecladoAdapter());
        // ATUALIZANDO O PERSONAGEM E REDENSENHADO NA TELA EM INTERVALOS REGULARES.
        timerGlobal = new Timer(5, this);
        timerGlobal.start(); // START

        // TIMER PARA O EFEITO DE PISCANDO
        piscando = false;
        timerPiscar = new Timer(delayPiscando, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                piscando = !piscando; // ALTERNA A VISIBILIDADE DO PERSONAGEM
            }
        });
        timerPiscar.setRepeats(true);

        // INICIALIZANDO MÉTODOS INIMIGOS
        InicializaNaveInimiga(); // NAVES
        InicializaMeteorito(); // METEORITOS
        jogando = false;
    }

    // INICIANDO POSIÇÃO DAS NAVES INIMIGAS ALEATORIAMENTE
    public void InicializaNaveInimiga() {
        NaveInimiga = new ArrayList<Naves>();
        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        TimerNaveInimiga = new Timer(delayInimigoNave, new ActionListener() {
            int y = 730;
            int alturaInimigo = 80;

            @Override
            public void actionPerformed(ActionEvent e) {
                int vidaInimigos = 1;
                int posicaoEmX = personagem.getLARGURATELA(); // POSICÃO INICIAL DO PERSONAGEM
                int posicaoEmY = (int) (Math.random() * (y - alturaInimigo));
                if (posicaoEmY < 10 || posicaoEmY > 750) {
                    posicaoEmY = (int) (Math.random() * (y - alturaInimigo));
                }
                // AUMENTA A VIDA DO INIMIGO
                if (personagem.getPontos() > 50) {
                    vidaInimigos = 2;
                }
                if (personagem.getPontos() > 100) {
                    vidaInimigos = 3;
                }
                // Calcula a quantidade de vida restante em relação à vida máxima
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
                int posicaoEmX = (int) (Math.random() * (personagem.getLARGURATELA() - alturaInimigo));
                if (posicaoEmX < 10 || posicaoEmX > 1200) {
                    posicaoEmX = (int) (Math.random() * (personagem.getLARGURATELA() - alturaInimigo));
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
            jogando = false;
        } else if (telaControles.isVisibilidade() == true) {
            graficos.drawImage(telaControles.getImagem(), 0, 0, null);
            // CARRREGANDO OS COMPONENTES DA CLASSE 'CONTROLES(EM IMPLEMENTAÇÃO)'
            telaControles.titulo(graficos);
            telaControles.menu(graficos);
            jogando = false;
        } else if (telaHistorico.isVisibilidade() == true) {
            graficos.drawImage(telaHistorico.getImagem(), 0, 0, null);
            // CARRREGANDO OS COMPONENTES DA CLASSE 'HISTORICO(EM IMPLEMENTAÇÃO)'
            telaHistorico.titulo(graficos);
            telaHistorico.menu(graficos);
            jogando = false;
        }
        if (jogando == true) {
            // FUNDO DA FASE INFINITA(PRINCIPAL)
            graficos.drawImage(this.fundo, 0, 0, null);
            // SEGUNDO FUNDO DA FASE CASO A CONDIÇÃO SEJA VERDADEIRA
            if (personagem.getPontos() >= 200) {
                graficos.drawImage(this.fundo2, 0, 0, null);
            }
            // CARREGANDO TIRO
            List<Tiro> tiros = personagem.getTiros();
            for (int i = 0; i < tiros.size(); i++) {
                Tiro tiro = tiros.get(i);
                tiro.carregar();
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);
            }
            // CARREGANDO TIRO ESPECIAL
            List<TiroEspecial> tiroSuper = personagem.getSupertiro();
            for (int i = 0; i < tiroSuper.size(); i++) {
                TiroEspecial tiroEspecial = tiroSuper.get(i);
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
            personagem.pontos(graficos);
            personagem.vidas(graficos);
        }
        // CARREGANDO A TELA DE MORTE CASO O JOGADOR MORRA(OBVIO)
        if (fimDeJogo.isVisibilidade() == true) {
            fimDeJogo.titulo(graficos);
            fimDeJogo.menu(graficos);
            personagem.pontos(graficos);
        }
        g.dispose();
    }

    // MÉTODO PARA ATUALIZAÇÃO DAS IMAGENS
    @Override
    public void actionPerformed(ActionEvent e) {
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
        List<TiroEspecial> tirosEspecial = personagem.getSupertiro();
        Iterator<TiroEspecial> iteratorTiroEspecial = tirosEspecial.iterator();
        while (iteratorTiroEspecial.hasNext()) {
            TiroEspecial tiroSuper = iteratorTiroEspecial.next();
            if (tiroSuper.isVisibilidade()) {
                tiroSuper.atualizar();
            } else {
                iteratorTiroEspecial.remove();
            }
        } // FIM

        // CARREGANDO DEMAIS METODOS DA CLASSE
        gerenciaFase();
        checarColisoes();
        // REPINTANDO OS OBJETOS
        personagem.atualizar();
        repaint();
    }

    public void gerenciaFase() {
        // ALTERA A VELOCIDADE DA NAVE INIMIGA COM BASE NOS PONTOS GANHOS DO PERSONAGEM
        int[] pontosPersonagem = { 300, 600, 900, 1000 };
        int[] ajusteVelocidades = { 4, 5, 6, 7 };
        for (int i = 0; i < pontosPersonagem.length; i++) {
            if (personagem.getPontos() > pontosPersonagem[i]) {
                int aumentoVelocidade = ajusteVelocidades[i];
                NaveInimiga.forEach(naveTemporaria -> naveTemporaria.setVELOCIDADE(aumentoVelocidade));
                if (i >= 1) {
                    MeteoritoInimigo.forEach(meteritoTemporario -> meteritoTemporario.setVELOCIDADE(aumentoVelocidade));
                }
            }
        }

        // CONTROLE DA FASE
        if (jogando == false) {
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
            if (personagem.getPontos() > 300) {
                TimerMeteorito.start();
            }
        }

        // FUNÇÃO PARA FAZER O PERSONAGEM 'PISCAR' CASO COLIDA
        if (contadorPiscar % 2 == 1) {
            piscando = false; // SE FOR IMPAR O PERSONAGEM TA VISIVEL
        } else {
            piscando = true; // SE FOR PAR O PERSONAGEM ESTA PISCANDO
        }
        if (contadorPiscar == 5) { // VEZES PARA 'PISCAR'
            timerPiscar.stop();
            piscando = false;
        }
        contadorPiscar++; // CONTADOR
    }

    public void checarColisoes() {
        Rectangle personagemForma = personagem.getBounds();
        Rectangle naveInimigaForma;
        Rectangle meteoritoForma;
        // VALORES PARA CADA INIMIGO DESTRUIDO
        int VALORNAVES = 10;
        int VALORMETEORITO = 20;

        // VERIFICA COLISÃO COM A BORDA EM 'X'
        if (personagem.getPosicaoEmX() < 0) {
            personagem.setPosicaoEmX(0); // POSIÇÃO MÍNIMA X
        } else if (personagem.getPosicaoEmX() + personagem.getAlturaImagem() > LARGURATELA) {
            int maximoEmX = LARGURATELA - personagem.getAlturaImagem(); // CALCULA A POSIÇÃO MÁXIMA
            personagem.setPosicaoEmX(maximoEmX);
        }
        // VERIFICA COLISÃO COM A BORDA EM 'Y'
        if (personagem.getPosicaoEmY() < 0) {
            personagem.setPosicaoEmY(0); // POSIÇÃO MÍNIMA Y
        } else if (personagem.getPosicaoEmY() + personagem.getAlturaImagem() > ALTURATELA) {
            int maximoEmY = ALTURATELA - personagem.getAlturaImagem();
            personagem.setPosicaoEmY(maximoEmY);
        }

        // COLISÃO PERSONAGEM COM A NAVE INIMIGA
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
                    if (personagem.getVIDAS() == 1) {
                        jogando = false;
                        fimDeJogo.setVisibilidade(true);
                        personagem.setVisibilidade(false);
                    } else {
                        personagem.setVIDAS(personagem.getVIDAS() - 1);
                    }
                    // VERIFICA A VIDA DA NAVE INIMIGA
                    if (naveTemporaria.getVidaInimigos() == 1) {
                        naveTemporaria.setVisibilidade(false);
                        personagem.setPontos(personagem.getPontos() + VALORNAVES);
                    } else {
                        naveTemporaria.setVidaInimigos(naveTemporaria.getVidaInimigos() - 1);
                    }
                    ultimaColisao = tempoAtual;
                }
            }
            timerPiscar.stop();
            piscando = false;
        }

        // VEREFICA A COLISÃO DO PERSONAGEM COM O METEORITO
        for (int k = 0; k < MeteoritoInimigo.size(); k++) {
            Meteorito meteritoTemporario = MeteoritoInimigo.get(k);
            meteoritoForma = meteritoTemporario.getBounds();
            if (personagemForma.getBounds().intersects(meteoritoForma.getBounds())) {
                if (personagem.getVIDAS() == 1) {
                    jogando = false;
                    fimDeJogo.setVisibilidade(true);
                } else {
                    personagem.setVIDAS(personagem.getVIDAS() - 1);
                }
                meteritoTemporario.setVisibilidade(false);
                personagem.setVisibilidade(false);
            }
        }

        // VEREFICA COLISÃO DOS TIROS COM OS INIMIGOS
        for (Naves inimigoNave : NaveInimiga) {
            Rectangle formaInimigoNave = inimigoNave.getBounds();
            for (Tiro tiro : personagem.getTiros()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoNave)) {
                    if (inimigoNave.getVidaInimigos() == 1) {
                        inimigoNave.setVisibilidade(false);
                        personagem.setPontos(personagem.getPontos() + VALORNAVES);
                    } else {
                        inimigoNave.setVidaInimigos(inimigoNave.getVidaInimigos() - 1);
                    }
                    tiro.setVisibilidade(false);
                }
            }
        }

        // VEREFICA COLISÃO COM INIMIGOS METEORITO
        for (Meteorito inimigoMeteorito : MeteoritoInimigo) {
            Rectangle formaInimigoMeteorito = inimigoMeteorito.getBounds();
            for (Tiro tiro : personagem.getTiros()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoMeteorito)) {
                    inimigoMeteorito.setVisibilidade(false);
                    tiro.setVisibilidade(false);
                    personagem.setPontos(personagem.getPontos() + VALORMETEORITO);
                }
            }
        }
    }

    public void MenuInicial(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ENTER) {
            if (telaMenu.getCursor() == 0) { // TELA JOGO FASE - IMPLEMENTAR
                System.out.println("Tela Fase - Implementando");
            } else if (telaMenu.getCursor() == 1) { // TELA JOGO
                jogando = true;
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

    public void VisibilidadeTelas(KeyEvent e) {
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

    public void fimDeJogoMENU(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (fimDeJogo.isVisibilidade() == true) {
            if (tecla == KeyEvent.VK_ENTER) {
                if (fimDeJogo.getCursor() == 0) {
                    jogando = true;
                    personagem.setPontos(personagem.getPONTOSINICIAIS());
                    personagem.setVIDAS(personagem.getVIDAINICIAL());
                    fimDeJogo.setVisibilidade(false);
                }
                if (fimDeJogo.getCursor() == 1) {
                    personagem.setPontos(personagem.getPONTOSINICIAIS());
                    personagem.setVIDAS(personagem.getVIDAINICIAL());
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
                MenuInicial(e);
                telaMenu.controleMenu(e);
            } else if (telaControles.isVisibilidade() == true) {
                VisibilidadeTelas(e);
            } else if (telaHistorico.isVisibilidade() == true) {
                VisibilidadeTelas(e);
            } else if (jogando == true) {
                personagem.mover(e);
                personagem.tiroNormal(e);
                personagem.tiroEspecial(e);
            } else if (fimDeJogo.isVisibilidade() == true) {
                fimDeJogoMENU(e);
                fimDeJogo.controleMenu(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.parar(e);
        }
    }
}