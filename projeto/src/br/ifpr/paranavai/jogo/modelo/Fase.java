package br.ifpr.paranavai.jogo.modelo;

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

import br.ifpr.paranavai.jogo.modelo.inimigos.Inimigo_naves;
import br.ifpr.paranavai.jogo.modelo.Jogador.Personagem;
import br.ifpr.paranavai.jogo.modelo.Jogador.Tiro;
import br.ifpr.paranavai.jogo.modelo.Telas.Tela_Controles;
import br.ifpr.paranavai.jogo.modelo.Telas.Tela_Historico;
import br.ifpr.paranavai.jogo.modelo.Telas.Tela_Menu;
import br.ifpr.paranavai.jogo.modelo.inimigos.Inimigo_meteorito;

import java.awt.FontMetrics;

public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Image fundo_fase_2;
    private Image banner_fundo_morte;
    private Image caveira_fundo;

    private Personagem personagem;
    private Tela_Menu tela_menu;
    private Tela_Historico tela_Historico;
    private Tela_Controles tela_Controles;

    private List<Inimigo_naves> inimigo_naves;
    private List<Inimigo_meteorito> inimigo_meteorito;
    private int larguraTela = 1200; // TAMANHO DA TELA
    private int alturaTela = 700;

    private boolean jogando;
    private boolean visibilidade_tela_morte;

    private Image explosao;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);
        // IMAGEM DE FUNDO
        ImageIcon fundo_principal = new ImageIcon("recursos\\sprites_Fundos\\fundo_fase.jpg");
        this.fundo = fundo_principal.getImage();
        // IMAGEM SEGUNDO FUNDO
        ImageIcon fundo_fase_2 = new ImageIcon("recursos\\sprites_Fundos\\fundo_fase_dois.jpg");
        this.fundo_fase_2 = fundo_fase_2.getImage();
        //
        ImageIcon explosao_img = new ImageIcon("recursos\\sprites_tiros\\explosao.png");
        this.explosao = explosao_img.getImage();
        // GIF DA CAVEIRA
        ImageIcon caveira = new ImageIcon("recursos\\sprites_fundos\\caveira.gif");
        this.caveira_fundo = caveira.getImage();
        // 'YOU DIE' IMG
        ImageIcon death = new ImageIcon("recursos\\sprites_fundos\\banner_fundo_morte.png");
        this.banner_fundo_morte = death.getImage();
        // INICIALIZANDO O PERSONAGEM
        personagem = new Personagem();
        personagem.carregar();

        tela_menu = new Tela_Menu();
        tela_menu.carregar();

        tela_Historico = new Tela_Historico();
        tela_Historico.carregar();

        tela_Controles = new Tela_Controles();
        tela_Controles.carregar();

        // INICIALIZANDO O METODO DE LEITURA DO TECLADO
        addKeyListener(new TecladoAdapter());

        // ATUALIZANDO O PERSONAGEM E REDENSENHADO NA TELA EM INTERVALOS REGULARES.
        Timer timer = new Timer(5, this);
        timer.start(); // START

        // Inicializar o temporizador para a explosão

        // INICIALIZANDO MÉTODOS INIMIGOS
        inicializa_Nave_Inimiga(); // NAVES
        inicializa_Metorito_Inimigo(); // METEORITOS
        visibilidade_tela_morte = false;
        jogando = false;
    }

    // INICIANDO POSIÇÃO DAS NAVES INIMIGAS ALEATORIAMENTE
    public void inicializa_Nave_Inimiga() {
        inimigo_naves = new ArrayList<Inimigo_naves>();
        // int larguraInimigo = 30; // Tamanho inimigos
        int alturaInimigo = 30;

        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        Timer timer = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = larguraTela; // Defina a posição inicial do novo inimigo
                int posicaoEmY = (int) (Math.random() * (600 - alturaInimigo));
                inimigo_naves.add(new Inimigo_naves(posicaoEmX, posicaoEmY));
            }
        });
        timer.setRepeats(true);
        timer.start(); // FIM DOS INIMIGOS NAVE
    }

    // INICIANDO POSIÇÃO DO METEORITO ALEATORIAMENTE
    public void inicializa_Metorito_Inimigo() {
        inimigo_meteorito = new ArrayList<Inimigo_meteorito>();
        int alturaInimigo = 40;

        // TIMER PARA SPAWNAR O METEORITO
        Timer timer_Meteorito = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int posicaoEmY = -alturaInimigo;
                int posicaoEmX = (int) (Math.random() * (larguraTela - alturaInimigo));
                inimigo_meteorito.add(new Inimigo_meteorito(posicaoEmX, posicaoEmY));
            }
        });
        timer_Meteorito.setRepeats(true);
        timer_Meteorito.start();
    }// FIM MÉTODO METEORITO

    public void paint(Graphics g) {
        // CHAMA O MÉTODO PAINT DA SUPERCLASSE PARA LIMPAR A TELA ANTES DE A REDESENHAR
        super.paint(g);
        Graphics2D graficos = (Graphics2D) g;

        if (tela_menu.isVisibilidade_menu() == true) {
            tela_menu.titulo(graficos);
            tela_menu.menu(graficos);
            jogando = false;
        } else if (tela_Controles.isControle_visibilidade() == true) {
            tela_Controles.titulo_controle(graficos);
        } else {
            //
            graficos.drawImage(this.fundo, 0, 0, null);

            if (personagem.getPontos() >= 200) {
                graficos.drawImage(this.fundo_fase_2, 0, 0, null);
            }
            // CARREGANDO TIRO PERSONAGEM
            List<Tiro> tiros = personagem.getTiros();
            for (int i = 0; i < tiros.size(); i++) {
                Tiro tiro = tiros.get(i);
                tiro.carregar();
                graficos.drawImage(tiro.getImagem(), tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);
                // Desenhar a imagem de explosão
            }
            // CARREGANDO A IMAGEM DO PERSNAGEM
            // ESTA AQUI EMBAIXO PARA A IMG FICAR ACIMA DA IMAGEM DO TIROW
            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(),
                    null);
            // CARREGANDO INIMIGO NAVE
            for (int i = 0; i < inimigo_naves.size(); i++) {
                Inimigo_naves ini = inimigo_naves.get(i);
                ini.carregar();
                graficos.drawImage(ini.getImagem(), ini.getPosicaoEmX(), ini.getPosicaoEmY(), null);
            } // FIM NAVE
              // CARREGANDO INIMIGO METEORITO

            for (int i = 0; i < inimigo_meteorito.size(); i++) {
                Inimigo_meteorito mete = inimigo_meteorito.get(i);
                mete.carregar();
                graficos.drawImage(mete.getImagem_meteoro(), mete.getPosicaoEmX(), mete.getPosicaoEmY(),
                        null);
            } // FIM METEORITO

            for (int i = 0; i < tiros.size(); i++) {
                Tiro tiro = tiros.get(i);
                if (!tiro.isVisibilidade()) {
                    graficos.drawImage(explosao, tiro.getPosicaoEmX(), tiro.getPosicaoEmY(), null);

                }
            }

            // CARREGANDO OS METODOS DAS INFORMAÇÃO DO JOGO(VIDA E PONTUAÇÃO)
            personagem.pontos(graficos);
            personagem.vidas(graficos);
        }
        // CARREGANDO A TELA DE MORTE CASO O JOGADOR MORRA(OBVIO)
        if (visibilidade_tela_morte == true) {
            int larguraTela = 1300;
            int posicaoEmx_Caveira = 600;
            int posicaoEmY_Caveira = 15;
            String enter = "PRESSIONE 'ENTER' PARA RESETAR.";
            FontMetrics fm = g.getFontMetrics();
            // PEGA O TAMANHO DA STRING E FAZ O CALCULO PARA CENTRALIZAR
            int enterWidth = fm.stringWidth(enter);
            int x = (larguraTela - enterWidth) / 2;
            int y = ((alturaTela + 250) - fm.getHeight()) / 2;
            // CARREGANDO OS FUNDOS
            if (personagem.getPontos() < 200) {
                graficos.drawImage(this.fundo, 0, 0, null);
            } else {
                graficos.drawImage(this.fundo_fase_2, 0, 0, null);
            }
            // CARREGANDO O GIF DA CAVEIRA
            graficos.drawImage(this.caveira_fundo, posicaoEmx_Caveira, posicaoEmY_Caveira, null);
            // PONTOS
            personagem.pontos(graficos);
            // BANNER FUNDO 'YOU DIE'
            graficos.drawImage(this.banner_fundo_morte, 0, 0, null);
            // STRING PARA RESETAR O JOGO
            graficos.drawString(enter, (x - 100), y);

        }
        g.dispose();
    }

    // MÉTODO PARA ATUALIZAÇÃO DAS IMAGENS
    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualiza();
        // VERIFICANDO SE O INIMIGO ESTA VISIVEL E ATUALIZANDO A SUA POSICAÇÃO ATRAVES
        // DO METODO 'ATUALIZAR', AO FICAR INVISIVEL O INIMIGO E EXCLUIDO
        // A CLASSE ITERATOR E COMO UM PONTEIRO EM C QUE PERMITE INTERAJIR COM UM CERTO
        // OBJETO(INIMIGO) DE UMA LISTA DE FORMA ESPECIFICA E EM SEQUENCIA
        Iterator<Inimigo_naves> iterator_naves = inimigo_naves.iterator();
        while (iterator_naves.hasNext()) {
            Inimigo_naves ini = iterator_naves.next();
            if (ini.isVisibilidade()) {
                ini.atualizar();
            } else {
                iterator_naves.remove();
            }
        } // FIM ITERATOR NAVES

        // ATUALIZA A POSIÇÃO DO METORITO
        Iterator<Inimigo_meteorito> iterator_meteorito = inimigo_meteorito.iterator();
        while (iterator_meteorito.hasNext()) {
            Inimigo_meteorito mete = iterator_meteorito.next();
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

        checarColisoes();
        repaint();
    }

    public void checarColisoes() {
        Rectangle forma_Nave_Personagem = personagem.getBounds();
        Rectangle Forma_Inimigo_Nave;
        Rectangle Forma_Inimig_Meteorito;
        Rectangle forma_Tiro;
        // COLISÃO DA NAVE DO PERSOANGEM COM A NAVE INIMIGA
        for (int i = 0; i < inimigo_naves.size(); i++) {
            Inimigo_naves temp_nave = inimigo_naves.get(i);
            Forma_Inimigo_Nave = temp_nave.getBounds();
            if (forma_Nave_Personagem.getBounds().intersects(Forma_Inimigo_Nave.getBounds())) {
                if (personagem.getVIDAS() == 1) {
                    jogando = false;
                    visibilidade_tela_morte = true;
                } else {
                    personagem.setVIDAS(personagem.getVIDAS() - 1);
                }
                personagem.setVisibilidade(false);
                temp_nave.setVisibilidade(false);
            }
        }
        // COLISÃO DA NAVE DO PERSOANGEM COM OS METEORITOS
        for (int k = 0; k < inimigo_meteorito.size(); k++) {
            Inimigo_meteorito temp_mete = inimigo_meteorito.get(k);
            Forma_Inimig_Meteorito = temp_mete.getBounds();
            if (forma_Nave_Personagem.getBounds().intersects(Forma_Inimig_Meteorito.getBounds())) {
                if (personagem.getVIDAS() == 1) {
                    jogando = false;
                    visibilidade_tela_morte = true;
                } else {
                    personagem.setVIDAS(personagem.getVIDAS() - 1);
                }
                temp_mete.setVisibilidade(false);
                personagem.setVisibilidade(false);
            }
        }
        // COLISÃO DOS TIROS COM A NAVE INIMIGA
        List<Tiro> tiros = personagem.getTiros();
        // FOR TIRO
        for (int i = 0; i < tiros.size(); i++) {
            Tiro temp_Tiro = tiros.get(i);
            forma_Tiro = temp_Tiro.getBounds();
            boolean colisao_NAVE = false; // VARIAVEL QUE VAI VERIFICAR A COLISÃO
            boolean colisao_METEORITO = false;
            // FOR NAVE INIMIGA
            for (int j = 0; j < inimigo_naves.size(); j++) {
                Inimigo_naves temp_nave = inimigo_naves.get(j);
                Forma_Inimigo_Nave = temp_nave.getBounds();
                // FOR METEORITO
                for (int b = 0; b < inimigo_meteorito.size(); b++) {
                    Inimigo_meteorito temp_mete = inimigo_meteorito.get(b);
                    Forma_Inimig_Meteorito = temp_mete.getBounds();
                    if (forma_Tiro.intersects(Forma_Inimigo_Nave)) {
                        temp_nave.setVisibilidade(false);
                        temp_Tiro.setVisibilidade(false);
                        colisao_NAVE = true; // COLISÃO DEU CERTO
                        break; // SAI DO LOOP INTERNO
                    }
                    if (forma_Tiro.intersects(Forma_Inimig_Meteorito)) {
                        temp_mete.setVisibilidade(false);
                        temp_Tiro.setVisibilidade(false);
                        colisao_METEORITO = true;
                        break;
                    }
                }
                if (colisao_NAVE) {
                    break; // SAI DO LOOP EXTERNO
                }
                if (colisao_METEORITO) {
                    break;
                }
            }
            if (colisao_NAVE) {
                // AUMENTA OS PONTOS SE A COLISÃO FOI DETECTADA PARA A NAVE INIMIGA
                personagem.setPontos(personagem.getPontos() + 10);
            }
            if (colisao_METEORITO) {
                // AUMENTA OS PONTOS SE A COLISÃO FOI DETECTADA PARA O METEORITO
                personagem.setPontos(personagem.getPontos() + 20);
            }
        }
    }

    // OPC DOS MENU INICIAL
    public void config_menu(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ENTER) {
            if (tela_menu.getCursor() == 0) {
                System.out.println("Teste - opc 0" + tecla);
            } else if (tela_menu.getCursor() == 1) {
                jogando = true;
                tela_menu.setVisibilidade_menu(false);
            } else if (tela_menu.getCursor() == 2) {
                jogando = false;
                tela_menu.setVisibilidade_menu(false);
                tela_Controles.setControle_visibilidade(true);
                if (tecla == KeyEvent.VK_BACK_SPACE) {
                    tela_menu.setVisibilidade_menu(true);
                    tela_Controles.setControle_visibilidade(false);
                }
            }
        }
    }// FIM

    // METODO PROVISORIO PARA RESETAR O JOGO
    public void resetar(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ENTER) {
            personagem.setVIDAS(3);
            jogando = true;
            visibilidade_tela_morte = false;
            personagem.setPontos(0);
        }
    }// FIM

    // CHAMANDO A LEITURA DOS TECLADOS
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (tela_menu.isVisibilidade_menu() == true) {
                tela_menu.tecla_menu(e);
                config_menu(e);
            } else if (visibilidade_tela_morte == true) {
                resetar(e);
            } else {
                personagem.tecla_Precionada(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.tecla_solta(e);
        }

    }
}