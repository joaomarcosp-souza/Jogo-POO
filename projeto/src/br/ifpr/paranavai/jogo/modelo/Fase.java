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


public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Personagem personagem;
    private List<Inimigo> inimigo;
    private int larguraTela = 1250; // Definindo o tamanho da Tela
    //private int alturaTela = 300; // Aqui pode ser um pouco menor do que a tela pros inimigos não nascerem em cima
                                  // da nave

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon carregando = new ImageIcon("recursos\\earthpixel.jpg");
        this.fundo = carregando.getImage();

        personagem = new Personagem();
        personagem.carregar();

        addKeyListener(new TecladoAdapter());

        Timer timer = new Timer(5, this); // atualizando o personagem e redensenhado na tela em intervalos regulares.
        timer.start(); // Iniciando

        inicializaInimigos(); // Iniciando o metodo

    }

    public void inicializaInimigos() {
        inimigo = new ArrayList<Inimigo>();

        // Tamanho dos Inimigos
        // int larguraInimigo = 30;
        int alturaInimigo = 30;

        Timer timer = new Timer(500, new ActionListener() { //intervalo (em milissegundos) para controlar a taxa de spawn de inimigos
            @Override
            public void actionPerformed(ActionEvent e) {
                int posicaoEmX = larguraTela; // Defina a posição inicial do novo inimigo
                int posicaoEmY = (int) (Math.random() * (600 - alturaInimigo));
                inimigo.add(new Inimigo(posicaoEmX, posicaoEmY));
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    // A SER FEITO AINDA
    public void colisao() {

    }

    public void paint(Graphics g) {
        super.paint(g); // Chama o método paint da superclasse para limpar a tela antes de a redesenhar

        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(this.fundo, 0, 0, null);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null);

        // Carregando inimigo
        for (int i = 0; i < inimigo.size(); i++) {
            Inimigo ini = inimigo.get(i);
            ini.carregar();
            graficos.drawImage(ini.getImagem(), ini.getPosicaoEmX(), ini.getPosicaoEmY(), null);
        }
        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atuzaliza();
        repaint();

        // Verificando se o inimigo esta visivel e atualizando a sua posicação atraves do metodo 'Atualizar', ao ficar invisivel o inimigo e excluido
        // A classe Iterator e como um ponteiro em C que permite interajir com um certo objeto(inimigo) de uma lista de forma especifica e em sequencia
        Iterator<Inimigo> iterator = inimigo.iterator();
        while (iterator.hasNext()) {
            Inimigo ini = iterator.next();
            if (ini.isVisivel()) {
                ini.atualizar();
            } else {
                iterator.remove();
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