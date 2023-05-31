package br.ifpr.paranavai.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;

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
import br.ifpr.paranavai.jogo.modelo.inimigos.Inimigo_boss;
import br.ifpr.paranavai.jogo.modelo.inimigos.Inimigo_meteorito;

import java.awt.Font;
import java.awt.FontMetrics;

public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Personagem personagem;
    private List<Inimigo_naves> inimigo_naves;
    private List<Inimigo_meteorito> inimigo_meteorito;
    private List<Inimigo_boss> inimigo_boss;
    private int larguraTela = 1200; // TAMANHO DA TELA

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        // IMAGEM DE FUNDO
        ImageIcon carregando = new ImageIcon("recursos\\sprites_Fundos\\planeta.jpg");
        this.fundo = carregando.getImage();

        // INICIALIZANDO O PERSONAGEM
        personagem = new Personagem();
        personagem.carregar();

        // INICIALIZANDO O METODO DE LEITURA DO TECLADO
        addKeyListener(new TecladoAdapter());

        // ATUALIZANDO O PERSONAGEM E REDENSENHADO NA TELA EM INTERVALOS REGULARES.
        Timer timer = new Timer(5, this);
        timer.start(); // START

        // INICIALIZANDO MÉTODOS INIMIGOS
        inicializa_Nave_Inimiga(); // NAVES
        inicializa_Metorito_Inimigo(); // METEORITOS
        inicializa_boss(); // BOSS(TESTE)

    }

    // INICIANDO POSIÇÃO DAS NAVES INIMIGAS ALEATORIAMENTE
    public void inicializa_Nave_Inimiga() {
        inimigo_naves = new ArrayList<Inimigo_naves>();
        // int larguraInimigo = 30; // Tamanho inimigos
        int alturaInimigo = 30;

        // INTERVALO (EM MILISSEGUNDOS) PARA CONTROLAR A TAXA DE // SPAWN DE INIMIGOS
        Timer timer = new Timer(900, new ActionListener() {
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
        int alturaInimigo = 50;

        // TIMER PARA SPAWNAR O METEORITO
        Timer timer_Meteorito = new Timer(900, new ActionListener() {
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

    // INICIALIZANDO A POSIÇÃO DO BOSS ALEATORIAMENTE
    public void inicializa_boss() {
        inimigo_boss = new ArrayList<Inimigo_boss>();
        int alturaInimigo = 30;

        // TIMER PARA SPAWNAR O BOSS (TESTE)
        Timer timer_inimigo_boss = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = larguraTela;
                int posicaoEmY = (int) (Math.random() * (500 - alturaInimigo));
                inimigo_boss.add(new Inimigo_boss(posicaoEmX, posicaoEmY));
            }
        });
        timer_inimigo_boss.setRepeats(true); // SETAR PARA REPETIR OU NÃO
        timer_inimigo_boss.start(); // STARTANDO
    }// FIM MÉTODO BOSS

    public void paint(Graphics g) {
        // CHAMA O MÉTODO PAINT DA SUPERCLASSE PARA LIMPAR A TELA ANTES DE A REDESENHAR
        super.paint(g);

        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(this.fundo, 0, 0, null);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null);

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
            graficos.drawImage(mete.getImagem_meteoro(), mete.getPosicaoEmX(), mete.getPosicaoEmY(), null);
        } // FIM METEORITO

        // CARREGANDO INIMIGO BOSS
        // for (int i = 0; i < inimigo_boss.size(); i++) {
        // Inimigo_boss boss = inimigo_boss.get(i);
        // boss.carregar();
        // graficos.drawImage(boss.getImagem_boss(), boss.getPosicaoEmX(),
        // boss.getPosicaoEmY(), null);
        // } // FIM BOSS

        // CARREGANDO OS METODOS DAS INFORMAÇÃO DO JOGO(VIDA E PONTUAÇÃO)
        pontos(graficos);
        vidas(graficos);

        g.dispose();
    }

    // MÉTODO DE PONTUAÇÃO DO JOGADOR
    public void pontos(Graphics pont) {
        // TITULO DO JOGO
        String pontos = "Pontuação: " + 150;
        // ESTILO DA FONTE
        Font fonte = new Font("Arial", Font.BOLD, 15);
        // COR FONTE
        pont.setColor(Color.WHITE);
        pont.setFont(fonte);
        // DESENHA A STRING COM A POSIÇÃO (x,y)
        pont.drawString(pontos, 10, 20);
    }

    // MÉTODO DA VIDA DO JOGADOR
    public void vidas(Graphics life) {
        // DEFINE A STRING DE EXIBIÇÃO
        String vida = "Vidas";
        // CRIA UM ESTILO DE FONTE
        Font estilo = new Font("Consolas", Font.BOLD, 10);
        // CRIA A MÉTRICA DA FONTE
        FontMetrics metrica = this.getFontMetrics(estilo);

        // OBTÉM O TAMANHO DA STRING NA TELA
        int width = metrica.stringWidth(vida);
        // CALCUPA A DISTÂNCIA DA BORDA PARA POSICIONAR A STRING
        int distancia = (15 * personagem.getVidas()) + (5 * personagem.getVidas()) + 100 + width;

        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        // A POSIÇÃO DE CADA UMA EM (x,y)
        for (int i = 0; i < personagem.getVidas(); i++) {
            life.drawImage(personagem.getImagem_vida(), (larguraTela + 50) - width, 10, this);
            width += personagem.getAlturaImagem_Vida() + 5;
        }

        // SETA A COR DA FONTE
        life.setColor(Color.WHITE);
        // SETA O ESTILO DE FONTE
        life.setFont(estilo);
        // DESENHA A STRING COM A POSIÇÃO (x,y)
        life.drawString(vida, personagem.getLarguraImagem_Vida() - distancia, 10);
    }

    // MÉTODO DE COLISÃO (A FAZER)
    public void colisao() {

    }

    // MÉTODO PARA ATUALIZAÇÃO DAS IMAGENS
    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atuzaliza();
        repaint();

        // VERIFICANDO SE O INIMIGO ESTA VISIVEL E ATUALIZANDO A SUA POSICAÇÃO ATRAVES
        // DO METODO 'ATUALIZAR', AO FICAR INVISIVEL O INIMIGO E EXCLUIDO
        // A CLASSE ITERATOR E COMO UM PONTEIRO EM C QUE PERMITE INTERAJIR COM UM CERTO
        // OBJETO(INIMIGO) DE UMA LISTA DE FORMA ESPECIFICA E EM SEQUENCIA
        Iterator<Inimigo_naves> iterator_naves = inimigo_naves.iterator();
        while (iterator_naves.hasNext()) {
            Inimigo_naves ini = iterator_naves.next();
            if (ini.isVisivel()) {
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

        // METODO DE ATUALIZAÇÃO DO BOSS
        Iterator<Inimigo_boss> iterator_boss = inimigo_boss.iterator();
        while (iterator_boss.hasNext()) {
            Inimigo_boss boss = iterator_boss.next();
            if (boss.isVisibilidade()) {
                boss.atualizar();
            } else {
                iterator_boss.remove();
            }
        }

    }

    // CHAMANDO A LEITURA DOS TECLADOS
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            personagem.tecla_Precionada(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.tecla_solta(e);
        }
    }
}