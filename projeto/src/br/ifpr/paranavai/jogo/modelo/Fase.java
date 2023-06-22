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

import br.ifpr.paranavai.jogo.modelo.Jogador.Personagem;
import br.ifpr.paranavai.jogo.modelo.Jogador.Tiro;
import br.ifpr.paranavai.jogo.modelo.Telas.TelaControles;
import br.ifpr.paranavai.jogo.modelo.Telas.TelaHistorico;
import br.ifpr.paranavai.jogo.modelo.Telas.TelaMenu;
import br.ifpr.paranavai.jogo.modelo.Telas.TelaMorte;
import br.ifpr.paranavai.jogo.modelo.inimigos.InimigoMeteorito;
import br.ifpr.paranavai.jogo.modelo.inimigos.InimigoNaves;

public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Image fundo_fase_2;
    private List<InimigoNaves> inimigo_naves;
    private List<InimigoMeteorito> inimigo_meteorito;
    private int larguraTela = 1200; // TAMANHO DA TELA
    private boolean jogando;
    private Timer timer_inimigo_nave;
    private Timer timer_inimigo_Meteorito;
    private Image explosao;

    private TelaMenu tela_menu;
    private Personagem personagem;
    private TelaHistorico tela_Historico;
    private TelaControles tela_Controles;
    private TelaMorte telaMorte;

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
        // INICIALIZANDO O PERSONAGEM
        personagem = new Personagem();
        personagem.carregar();
        //
        tela_menu = new TelaMenu();
        tela_menu.carregar();
        //
        tela_Historico = new TelaHistorico();
        tela_Historico.carregar();
        //
        tela_Controles = new TelaControles();
        tela_Controles.carregar();

        telaMorte = new TelaMorte();
        telaMorte.carregar();
        // INICIALIZANDO O METODO DE LEITURA DO TECLADO
        addKeyListener(new TecladoAdapter());
        // ATUALIZANDO O PERSONAGEM E REDENSENHADO NA TELA EM INTERVALOS REGULARES.
        Timer timer = new Timer(5, this);
        timer.start(); // START

        // INICIALIZANDO MÉTODOS INIMIGOS
        inicializa_Nave_Inimiga(); // NAVES
        inicializa_Metorito_Inimigo(); // METEORITOS
        jogando = false;
    }

    // INICIANDO POSIÇÃO DAS NAVES INIMIGAS ALEATORIAMENTE
    public void inicializa_Nave_Inimiga() {
        inimigo_naves = new ArrayList<InimigoNaves>();
        // int larguraInimigo = 30; // Tamanho inimigos
        int alturaInimigo = 30;

        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        timer_inimigo_nave = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = larguraTela; // Defina a posição inicial do novo inimigo
                int posicaoEmY = (int) (Math.random() * (600 - alturaInimigo));
                inimigo_naves.add(new InimigoNaves(posicaoEmX, posicaoEmY));
            }
        });
        timer_inimigo_nave.setRepeats(true);
    }

    // INICIANDO POSIÇÃO DO METEORITO ALEATORIAMENTE
    public void inicializa_Metorito_Inimigo() {
        inimigo_meteorito = new ArrayList<InimigoMeteorito>();
        int alturaInimigo = 40;

        // TIMER PARA SPAWNAR O METEORITO
        timer_inimigo_Meteorito = new Timer(1600, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int posicaoEmY = -alturaInimigo;
                int posicaoEmX = (int) (Math.random() * (larguraTela - alturaInimigo));
                inimigo_meteorito.add(new InimigoMeteorito(posicaoEmX, posicaoEmY));
            }
        });
        timer_inimigo_Meteorito.setRepeats(true);
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
            jogando = false;
        } else if (tela_Historico.isHistorico_visibilidade() == true) {
            tela_Historico.titulo_Historico(graficos);
            jogando = false;
        }

        if (jogando == true) {
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
            List<Tiro> superTiro = personagem.getTiros_super();
            for (int i = 0; i < superTiro.size(); i++) {
                Tiro super_Tiro = superTiro.get(i);
                super_Tiro.carregar();
                graficos.drawImage(super_Tiro.getImagem_super(), super_Tiro.getPosicaoEmX(), super_Tiro.getPosicaoEmY(),
                        null);
                // Desenhar a imagem de explosão
            }
            // CARREGANDO A IMAGEM DO PERSNAGEM
            // ESTA AQUI EMBAIXO PARA A IMG FICAR ACIMA DA IMAGEM DO TIROW
            graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(),
                    null);
            // CARREGANDO INIMIGO NAVE
            for (int i = 0; i < inimigo_naves.size(); i++) {
                InimigoNaves ini = inimigo_naves.get(i);
                ini.carregar();
                graficos.drawImage(ini.getImagem(), ini.getPosicaoEmX(), ini.getPosicaoEmY(), null);
            } // FIM NAVE
              // CARREGANDO INIMIGO METEORITO
            for (int i = 0; i < inimigo_meteorito.size(); i++) {
                InimigoMeteorito mete = inimigo_meteorito.get(i);
                mete.carregar();
                graficos.drawImage(mete.getImagem_meteoro(), mete.getPosicaoEmX(), mete.getPosicaoEmY(),
                        null);
            } // FIM METEORITO
              //
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
        if (telaMorte.isTelaMorteVisibilidade() == true) {
            telaMorte.fundoTelaMorte(graficos);
            personagem.pontos(graficos);
        }
        g.dispose();
    }

    // MÉTODO PARA ATUALIZAÇÃO DAS IMAGENS
    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualiza();
        // VEREFICA SE O INIMIGO ESTA VISIVEL E ATUALIZA A SUA POSICAÇÃO ATRAVES
        // DO METODO 'ATUALIZAR', AO FICAR INVISIVEL O INIMIGO E EXCLUIDO

        // A CLASSE ITERATOR E COMO UM PONTEIRO EM C QUE PERMITE INTERAJIR COM UM CERTO
        // OBJETO(INIMIGO) DE UMA LISTA DE FORMA ESPECIFICA E EM SEQUENCIA
        Iterator<InimigoNaves> iterator_naves = inimigo_naves.iterator();
        while (iterator_naves.hasNext()) {
            InimigoNaves ini = iterator_naves.next();
            if (ini.isVisibilidade()) {
                ini.atualizar();
            } else {
                iterator_naves.remove();
            }

        } // FIM ITERATOR NAVES

        // ATUALIZA A POSIÇÃO DO METORITO
        Iterator<InimigoMeteorito> iterator_meteorito = inimigo_meteorito.iterator();
        while (iterator_meteorito.hasNext()) {
            InimigoMeteorito mete = iterator_meteorito.next();
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

        List<Tiro> super_tiro = personagem.getTiros_super();
        Iterator<Tiro> iterator_super = super_tiro.iterator();
        while (iterator_super.hasNext()) {
            Tiro tiro_super = iterator_super.next();
            if (tiro_super.isSuper_visibilidade()) {
                tiro_super.atualizarSuper();
            } else {
                iterator_super.remove();
            }
        } // FIM
        if (jogando == false) {
            // PARA O SPAWN DE INIMIGOS
            timer_inimigo_nave.stop();
            timer_inimigo_Meteorito.stop();
            // LIMPA A LISTA DE INMIGOS CASO O JOGADOR ESTEJA MORTO
            inimigo_naves.clear();
            inimigo_meteorito.clear();
            // RESETA A POSIÇÃO DO JOGADOR
            if (personagem.getPosicaoEmX() != personagem.getPosicaoInicialEmX()
                    || personagem.getPosicaoEmY() != personagem.getPosicaoInicialEmY()) {
                personagem.setPosicaoEmX(personagem.getPosicaoInicialEmX());
                personagem.setPosicaoEmY(personagem.getPosicaoInicialEmY());
            } else {
                timer_inimigo_Meteorito.start();
                timer_inimigo_nave.start();
            }
        }

        checarColisoes();
        repaint();
    }

    public void checarColisoes() {
        Rectangle forma_Nave_Personagem = personagem.getBounds();
        Rectangle Forma_Inimigo_Nave;
        Rectangle Forma_Inimig_Meteorito;
        Rectangle forma_Tiro;
        int telaLargura = 1300; // Largura da tela
        int telaAltura = 700; // Altura da tela

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
            InimigoNaves temp_nave = inimigo_naves.get(i);
            Forma_Inimigo_Nave = temp_nave.getBounds();
            if (forma_Nave_Personagem.getBounds().intersects(Forma_Inimigo_Nave.getBounds())) {
                if (personagem.getVIDAS() == 1) {
                    jogando = false;
                    telaMorte.setTelaMorteVisibilidade(true);
                } else {
                    personagem.setVIDAS(personagem.getVIDAS() - 1);
                }
                personagem.setVisibilidade(false);
                temp_nave.setVisibilidade(false);
            }
        }
        // COLISÃO DA NAVE DO PERSOANGEM COM OS METEORITOS
        for (int k = 0; k < inimigo_meteorito.size(); k++) {
            InimigoMeteorito temp_mete = inimigo_meteorito.get(k);
            Forma_Inimig_Meteorito = temp_mete.getBounds();
            if (forma_Nave_Personagem.getBounds().intersects(Forma_Inimig_Meteorito.getBounds())) {
                if (personagem.getVIDAS() == 1) {
                    jogando = false;
                    telaMorte.setTelaMorteVisibilidade(true);
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
                InimigoNaves temp_nave = inimigo_naves.get(j);
                Forma_Inimigo_Nave = temp_nave.getBounds();
                // FOR METEORITO
                for (int b = 0; b < inimigo_meteorito.size(); b++) {
                    InimigoMeteorito temp_mete = inimigo_meteorito.get(b);
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
            if (tela_menu.getCursor() == 0) { // TELA JOGO FASE - IMPLEMENTAR
                System.out.println("Tela Fase - Implementando");
            } else if (tela_menu.getCursor() == 1) { // TELA JOGO
                jogando = true;
                tela_menu.setVisibilidade_menu(false);
            } else if (tela_menu.getCursor() == 2) { // TELA CONTROLES
                tela_menu.setVisibilidade_menu(false);
                tela_Controles.setControle_visibilidade(true);
            } else if (tela_menu.getCursor() == 3) { // TELA HISTORICO - IMPLEMENTAR
                tela_menu.setVisibilidade_menu(false);
                tela_Historico.setHistorico_visibilidade(true);
            }
        } // FIM
    }

    public void tela_visibilidade(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (tela_Controles.isControle_visibilidade() == true) {
            if (tecla == KeyEvent.VK_ESCAPE) {
                tela_Controles.setControle_visibilidade(false);
                tela_menu.setVisibilidade_menu(true);
            }
        }
        if (tela_Historico.isHistorico_visibilidade() == true) {
            if (tecla == KeyEvent.VK_ESCAPE) {
                tela_Historico.setHistorico_visibilidade(false);
                tela_menu.setVisibilidade_menu(true);
            }
        }
    }

    public void resetar_partida(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (telaMorte.isTelaMorteVisibilidade() == true) {
            if (tecla == KeyEvent.VK_ESCAPE) {
                personagem.setPontos(0);
                personagem.setVIDAS(3);
                telaMorte.setTelaMorteVisibilidade(false);
                tela_menu.setVisibilidade_menu(true);
            }
            if (tecla == KeyEvent.VK_ENTER) {
                jogando = true;
                personagem.setPontos(0);
                personagem.setVIDAS(3);
                telaMorte.setTelaMorteVisibilidade(false);
            }
        }
    }

    // CHAMANDO A LEITURA DOS TECLADOS
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (tela_menu.isVisibilidade_menu() == true) {
                tela_menu.tecla_menu(e);
                config_menu(e);
            }
            if (tela_Controles.isControle_visibilidade() == true) {
                tela_visibilidade(e);
            }
            if (tela_Historico.isHistorico_visibilidade() == true) {
                tela_visibilidade(e);
            }
            if (jogando == true) {
                personagem.tecla_Precionada(e);
            }
            if (telaMorte.isTelaMorteVisibilidade() == true) {
                resetar_partida(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.tecla_solta(e);
        }

    }
}