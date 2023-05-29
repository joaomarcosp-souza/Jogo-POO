package br.ifpr.paranavai.jogo.modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

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
import br.ifpr.paranavai.jogo.modelo.inimigos.Inimigo_meteorito;

public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Personagem personagem;
    private List<Inimigo_naves> inimigo_naves;
    private List<Inimigo_meteorito> inimigo_meteorito;
    private int larguraTela = 1200; // Definindo o tamanho da Tela

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon carregando = new ImageIcon("recursos\\sprites_Fundos\\planeta.jpg");
        this.fundo = carregando.getImage();

        personagem = new Personagem();
        personagem.carregar();

        addKeyListener(new TecladoAdapter());

        Timer timer = new Timer(5, this); // atualizando o personagem e redensenhado na tela em intervalos regulares.
        timer.start(); // Iniciando

        // Metodos dos Inimigos
        inicializa_Nave_Inimiga(); // Iniciando as Naves
        inicializa_Metorito_Inimigo(); // Iniciando os Meteorito

    }

    public void inicializa_Nave_Inimiga() {

        // COMEÇO DA INICIALIZAÇÃO DAS NAVES
        inimigo_naves = new ArrayList<Inimigo_naves>();

        // int larguraInimigo = 30; // Tamanho inimigos
        int alturaInimigo = 30;

        Timer timer = new Timer(900, new ActionListener() { // intervalo (em milissegundos) para controlar a taxa de
                                                            // spawn de inimigos
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

    public void inicializa_Metorito_Inimigo() {
        inimigo_meteorito = new ArrayList<Inimigo_meteorito>();
        int alturaInimigo = 50;

        Timer timer_Meteorito = new Timer(1200, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Defina a posição inicial do novo inimigo
                int posicaoEmY = -alturaInimigo; // Negativando para que ele venha de cima para baixo;
                int posicaoEmX = (int) (Math.random() * (1250 + alturaInimigo));
                inimigo_meteorito.add(new Inimigo_meteorito(posicaoEmX, posicaoEmY));
            }
        });
        timer_Meteorito.setRepeats(true);
        timer_Meteorito.start();
    }

    // A SER FEITO AINDA
    public void colisao() {

    }

    public void paint(Graphics g) {
        super.paint(g); // Chama o método paint da superclasse para limpar a tela antes de a redesenhar

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

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atuzaliza();
        repaint();

        // Verificando se o inimigo esta visivel e atualizando a sua posicação atraves
        // do metodo 'Atualizar', ao ficar invisivel o inimigo e excluido
        // A classe Iterator e como um ponteiro em C que permite interajir com um certo
        // objeto(inimigo) de uma lista de forma especifica e em sequencia
        Iterator<Inimigo_naves> iterator_naves = inimigo_naves.iterator();
        while (iterator_naves.hasNext()) {
            Inimigo_naves ini = iterator_naves.next();
            if (ini.isVisivel()) {
                ini.atualizar();
            } else {
                iterator_naves.remove();
            }
        } // FIM ITERATOR NAVES

        Iterator<Inimigo_meteorito> iterator_meteorito = inimigo_meteorito.iterator();
        while (iterator_meteorito.hasNext()) {
            Inimigo_meteorito mete = iterator_meteorito.next();
            if (mete.isVisibilidade()) {
                mete.atualizar();
            } else {
                iterator_meteorito.remove();
            }
        }

    }

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