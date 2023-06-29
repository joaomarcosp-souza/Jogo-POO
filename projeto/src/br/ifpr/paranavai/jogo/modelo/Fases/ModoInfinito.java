package br.ifpr.paranavai.jogo.modelo.Fases;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

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
import br.ifpr.paranavai.jogo.modelo.Telas.Controles;
import br.ifpr.paranavai.jogo.modelo.Telas.Historico;
import br.ifpr.paranavai.jogo.modelo.Telas.MenuInicial;
import br.ifpr.paranavai.jogo.modelo.Telas.FimDeJogo;
import br.ifpr.paranavai.jogo.modelo.inimigos.Meteorito;
import br.ifpr.paranavai.jogo.modelo.inimigos.Naves;

public class ModoInfinito extends JPanel implements ActionListener {
    private Image fundo;
    private Image fundo2;
    private List<Naves> inimigo_naves;
    private List<Meteorito> inimigo_meteorito;
    private int larguraTela = 1300; // TAMANHO DA TELA
    private boolean jogando;
    private Timer timer_inimigo_nave;
    private Timer timer_inimigo_Meteorito;

    private MenuInicial telaMenu;
    private Personagem personagem;
    private Historico telaHistorico;
    private Controles telaControles;
    private FimDeJogo fimDeJogo;

    private int delayInimigoNave = 500;
    private int delayInimigoMeteorito = 2500;

    public ModoInfinito() {
        setFocusable(true);
        setDoubleBuffered(true);
        // IMAGEM DE FUNDO
        ImageIcon carregando = new ImageIcon("recursos\\sprites_Fundos\\FundoUm.jpg");
        this.fundo = carregando.getImage();
        // IMAGEM SEGUNDO FUNDO
        ImageIcon carregandoDois = new ImageIcon("recursos\\sprites_Fundos\\FundoDois.jpg");
        this.fundo2 = carregandoDois.getImage();
        //
        // ImageIcon explosao_img = new
        // ImageIcon("recursos\\sprites_tiros\\explosao.png");
        // INICIALIZANDO O PERSONAGEM
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

        fimDeJogo = new FimDeJogo();
        fimDeJogo.carregar();
        // INICIALIZANDO O METODO DE LEITURA DO TECLADO
        addKeyListener(new TecladoAdapter());
        // ATUALIZANDO O PERSONAGEM E REDENSENHADO NA TELA EM INTERVALOS REGULARES.
        Timer timer = new Timer(5, this);
        timer.start(); // START

        // INICIALIZANDO MÉTODOS INIMIGOS
        InicializaNaveInimiga(); // NAVES
        InicializaMeteorito(); // METEORITOS
        jogando = false;
    }

    // INICIANDO POSIÇÃO DAS NAVES INIMIGAS ALEATORIAMENTE
    public void InicializaNaveInimiga() {
        inimigo_naves = new ArrayList<Naves>();

        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        timer_inimigo_nave = new Timer(delayInimigoNave, new ActionListener() {
            int y = 630;
            int alturaInimigo = 80;

            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = larguraTela; // Defina a posição inicial do novo inimigo
                int posicaoEmY = (int) (Math.random() * (y - alturaInimigo));
                if (posicaoEmY < 10 || posicaoEmY > 650) {
                    posicaoEmY = 50;
                }
                inimigo_naves.add(new Naves(posicaoEmX, posicaoEmY));
            }
        });
        timer_inimigo_nave.setRepeats(true);
    }

    // INICIANDO POSIÇÃO DO METEORITO ALEATORIAMENTE
    public void InicializaMeteorito() {
        inimigo_meteorito = new ArrayList<Meteorito>();
        int alturaInimigo = 100;
        // TIMER PARA SPAWNAR O METEORITO
        timer_inimigo_Meteorito = new Timer(delayInimigoMeteorito, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmY = -alturaInimigo;
                int posicaoEmX = (int) (Math.random() * (larguraTela - alturaInimigo));
                if (posicaoEmX < 10 || posicaoEmX > 1200) {
                    posicaoEmX = 100;
                }
                inimigo_meteorito.add(new Meteorito(posicaoEmX, posicaoEmY));
            }
        });
        timer_inimigo_Meteorito.setRepeats(true);
    }// FIM MÉTODO METEORITO

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graficos = (Graphics2D) g;

        if (telaMenu.isVisibilidade() == true) {
            // CARRREGANDO OS COMPONENTES DA CLASSE 'MENU'
            telaMenu.titulo(graficos);
            telaMenu.menu(graficos);
            jogando = false;
        } else if (telaControles.isVisibilidade() == true) {
            // CARRREGANDO OS COMPONENTES DA CLASSE 'CONTROLES(EM IMPLEMENTAÇÃO)'
            telaControles.titulo(graficos);
            jogando = false;
        } else if (telaHistorico.isVisibilidade() == true) {
            // CARRREGANDO OS COMPONENTES DA CLASSE 'HISTORICO(EM IMPLEMENTAÇÃO)'
            telaHistorico.titulo(graficos);
            jogando = false;
        }
        if (jogando == true) {
            // FUNDO DA FASE INFINITA(PRINCIPAL)
            graficos.drawImage(this.fundo, 0, 0, null);
            // SEGUNDO FUNDO DA FASE CASO A CONDIÇÃO SEJA VERDADEIRA
            if (personagem.getPontos() >= 200) {
                graficos.drawImage(this.fundo2, 0, 0, null);
            }
            // CARREGANDO TIRO PERSONAGEM
            List<Tiro> tiros = personagem.getTiros();
            for (int i = 0; i < tiros.size(); i++) {
                Tiro tiro = tiros.get(i);
                tiro.carregar();
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);
            }
            // CARREGANDO A IMAGEM DO PERSONAGEM
            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(),
                    null);

            // COMEÇO METEORO E NAVES INIMIGAS
            for (int i = 0; i < inimigo_meteorito.size(); i++) {
                Meteorito mete = inimigo_meteorito.get(i);
                mete.carregar();
                graficos.drawImage(mete.getImagem(), mete.getPosicaoEmX(), mete.getPosicaoEmY(),
                        null);
                if (!mete.isVisibilidade()) {
                    graficos.setColor(Color.white);
                    graficos.setFont(personagem.getPixel().deriveFont(Font.PLAIN, 35));
                    graficos.drawString("+20", mete.getPosicaoEmX() + 3, mete.getPosicaoEmY() + 3);
                }
            }

            for (int i = 0; i < inimigo_naves.size(); i++) {
                Naves ini = inimigo_naves.get(i);
                ini.carregar();
                graficos.drawImage(ini.getImagem(), ini.getPosicaoEmX(), ini.getPosicaoEmY(), null);
            }
            // FIM METEORO E NAVES INIMIGAS

            // HITBOX
            graficos.setColor(Color.RED);
            Rectangle hitboxPersonagem = personagem.getBounds();
            graficos.drawRect(hitboxPersonagem.x, hitboxPersonagem.y,
                    hitboxPersonagem.width, hitboxPersonagem.height);
            graficos.setColor(Color.BLUE);
            for (Naves ini : inimigo_naves) {
                Rectangle hitboxInimigo = ini.getBounds();
                graficos.drawRect(hitboxInimigo.x, hitboxInimigo.y, hitboxInimigo.width,
                        hitboxInimigo.height);
            }
            for (Meteorito mete : inimigo_meteorito) {
                Rectangle hitboxInimigo = mete.getBounds();
                graficos.drawRect(hitboxInimigo.x, hitboxInimigo.y, hitboxInimigo.width,
                        hitboxInimigo.height);
            } // FIM HITBOX

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
        Iterator<Naves> iterator_naves = inimigo_naves.iterator();
        while (iterator_naves.hasNext()) {
            Naves ini = iterator_naves.next();
            if (ini.isVisibilidade()) {
                ini.atualizar();
            } else {
                iterator_naves.remove();
            }
        } // FIM NAVES

        // ATUALIZA A POSIÇÃO DO METEORO
        Iterator<Meteorito> iterator_meteorito = inimigo_meteorito.iterator();
        while (iterator_meteorito.hasNext()) {
            Meteorito mete = iterator_meteorito.next();
            if (mete.isVisibilidade()) {
                mete.atualizar();
            } else {
                iterator_meteorito.remove();
            }
        } // FIM

        // ATUALIZA POSICAÇÃO DO TIRO
        List<Tiro> tiros = personagem.getTiros();
        Iterator<Tiro> iterator_tiro = tiros.iterator();
        while (iterator_tiro.hasNext()) {
            Tiro tiro = iterator_tiro.next();
            if (tiro.isVisibilidade()) {
                tiro.atualizar();
            } else {
                iterator_tiro.remove();
            }
        } // FIM

        // CARREGANDO DEMAIS METODOS DA CLASSE
        checarColisoes();
        gerenciarInimigos();
        // REPINTANDO OS OBJETOS
        personagem.atualizar();
        repaint();
    }

    public void gerenciarInimigos() {
        if (personagem.getPontos() >= 300) {
            int aumentoVelocidade = 5;
            inimigo_naves.forEach(temp_nave -> temp_nave.setVELOCIDADE(aumentoVelocidade));
        }
        if (personagem.getPontos() > 600) {
            int aumentoVelocidade = 6;
            inimigo_naves.forEach(temp_nave -> temp_nave.setVELOCIDADE(aumentoVelocidade));
            inimigo_meteorito.forEach(temp_mete -> temp_mete.setVELOCIDADE(aumentoVelocidade));
        }
        if (personagem.getPontos() > 900) {
            int aumentoVelocidade = 7;
            inimigo_naves.forEach(temp_nave -> temp_nave.setVELOCIDADE(aumentoVelocidade));
        }
        if (personagem.getPontos() > 1000) {
            int aumentoVelocidade = 8;
            inimigo_naves.forEach(temp_nave -> temp_nave.setVELOCIDADE(aumentoVelocidade));
            inimigo_meteorito.forEach(temp_mete -> temp_mete.setVELOCIDADE(aumentoVelocidade));
        }

        if (jogando == false) {
            // PARA O SPAWN DE INIMIGOS
            timer_inimigo_nave.stop();
            timer_inimigo_Meteorito.stop();
            // LIMPA A LISTA DE INMIGOS CASO O JOGADOR ESTEJA MORTO
            inimigo_naves.clear();
            inimigo_meteorito.clear();
            // RESETA A POSIÇÃO DO JOGADOR
            if (personagem.getPosicaoEmX() != personagem.getPOSICAOINICIALX()
                    || personagem.getPosicaoEmY() != personagem.getPOSICAOINICIALY()) {
                personagem.setPosicaoEmX(personagem.getPOSICAOINICIALX());
                personagem.setPosicaoEmY(personagem.getPOSICAOINICIALY());
            }
        } else {
            timer_inimigo_nave.start();
            if (personagem.getPontos() > 200) {
                timer_inimigo_Meteorito.start();
            }
        }
    }

    public void checarColisoes() {
        Rectangle forma_Nave_Personagem = personagem.getBounds();
        Rectangle Forma_Inimigo_Nave;
        Rectangle Forma_Inimig_Meteorito;
        int telaLargura = 1250; // Largura da tela
        int telaAltura = 665; // Altura da tela

        // VERIFICA COLISÃO COM A BORDA EM 'X'
        if (personagem.getPosicaoEmX() < 0) {
            personagem.setPosicaoEmX(0); // POSIÇÃO MÍNIMA X
        } else if (personagem.getPosicaoEmX() + personagem.getAlturaImagem() > telaLargura) {
            int maximoEmX = telaLargura - personagem.getAlturaImagem(); // CALCULA A POSIÇÃO MÁXIMA
            personagem.setPosicaoEmX(maximoEmX);
        }
        // VERIFICA COLISÃO COM A BORDA EM 'Y'
        if (personagem.getPosicaoEmY() < 0) {
            personagem.setPosicaoEmY(0); // POSIÇÃO MÍNIMA Y
        } else if (personagem.getPosicaoEmY() + personagem.getAlturaImagem() > telaAltura) {
            int maximoEmY = telaAltura - personagem.getAlturaImagem();
            personagem.setPosicaoEmY(maximoEmY);
        }
        // COLISÃO DA NAVE DO PERSOANGEM COM A NAVE INIMIGA
        for (int i = 0; i < inimigo_naves.size(); i++) {
            Naves temp_nave = inimigo_naves.get(i);
            Forma_Inimigo_Nave = temp_nave.getBounds();
            if (forma_Nave_Personagem.getBounds().intersects(Forma_Inimigo_Nave.getBounds())) {
                if (personagem.getVIDAS() == 1) {
                    jogando = false;
                    fimDeJogo.setVisibilidade(true);
                } else {
                    personagem.setVIDAS(personagem.getVIDAS() - 1);
                }
                personagem.setVisibilidade(false);
                temp_nave.setVisibilidade(false);
            }
        }
        for (int k = 0; k < inimigo_meteorito.size(); k++) {
            Meteorito temp_mete = inimigo_meteorito.get(k);
            Forma_Inimig_Meteorito = temp_mete.getBounds();
            if (forma_Nave_Personagem.getBounds().intersects(Forma_Inimig_Meteorito.getBounds())) {
                if (personagem.getVIDAS() == 1) {
                    jogando = false;
                    fimDeJogo.setVisibilidade(true);
                } else {
                    personagem.setVIDAS(personagem.getVIDAS() - 1);
                }
                temp_mete.setVisibilidade(false);
                personagem.setVisibilidade(false);
            }
        }
        // VEREFICA COLISÃO COM INIMIGOS METEORITO
        for (Naves inimigoNave : inimigo_naves) {
            Rectangle formaInimigoNave = inimigoNave.getBounds();
            for (Tiro tiro : personagem.getTiros()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoNave)) {
                    personagem.setPontos(personagem.getPontos() + 10);
                    if (personagem.getPontos() < 200) {
                        inimigoNave.setVisibilidade(false);
                    } else if (personagem.getPontos() > 200) {
                        if (inimigoNave.getVIDASINIMIGOS() == 1) {
                            inimigoNave.setVisibilidade(false);
                        } else {
                            inimigoNave.setVIDASINIMIGOS(inimigoNave.getVIDASINIMIGOS() - 1);
                        }
                    }
                    tiro.setVisibilidade(false);
                }
            }
        }

        // VEREFICA COLISÃO COM INIMIGOS METEORITO
        for (Meteorito inimigoMeteorito : inimigo_meteorito) {
            Rectangle formaInimigoMeteorito = inimigoMeteorito.getBounds();
            for (Tiro tiro : personagem.getTiros()) {
                Rectangle formaTiro = tiro.getBounds();
                if (formaTiro.intersects(formaInimigoMeteorito)) {
                    inimigoMeteorito.setVisibilidade(false);
                    tiro.setVisibilidade(false);
                    personagem.setPontos(personagem.getPontos() + 20);
                }
            }
        }

    }

    // OPC DOS MENU INICIAL
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

    public void MenuTelaMorte(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (fimDeJogo.isVisibilidade() == true) {
            if (tecla == KeyEvent.VK_ENTER) {
                if (fimDeJogo.getCursor() == 0) {
                    jogando = true;
                    personagem.setPontos(0);
                    personagem.setVIDAS(3);
                    fimDeJogo.setVisibilidade(false);
                }
                if (fimDeJogo.getCursor() == 1) {
                    personagem.setPontos(0);
                    personagem.setVIDAS(3);
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
                telaMenu.tecla_menu(e);
                MenuInicial(e);
            }
            if (telaControles.isVisibilidade() == true) {
                VisibilidadeTelas(e);
            }
            if (telaHistorico.isVisibilidade() == true) {
                VisibilidadeTelas(e);
            }
            if (jogando == true) {
                personagem.mover(e);
                personagem.atirar(e);
            }
            if (fimDeJogo.isVisibilidade() == true) {
                MenuTelaMorte(e);
                fimDeJogo.menuMorto(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.parar(e);
        }
    }
}