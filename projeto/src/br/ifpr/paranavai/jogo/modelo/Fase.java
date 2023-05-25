package br.ifpr.paranavai.jogo.modelo;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class Fase extends JPanel {
    private Image fundo;
    private Personagem personagem;

    public Fase() {
        ImageIcon carregando = new ImageIcon("recursos\\espaco.jpg");
        this.fundo = carregando.getImage();

        personagem = new Personagem();
        personagem.carregar();

        addKeyListener(new TecladoAdapter()); // Carregando o metodo

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(735, 413); // Definindo as dimenões do jogo
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(this.fundo, 0, 0, null);

        // Atualizar a posição do personagem
        int novaPosicaoX = personagem.getPosicaoEmX() + personagem.getDeslocamentoEmX();
        int novaPosicaoY = personagem.getPosicaoEmY() + personagem.getDeslocamentoEmY();
        personagem.setPosicaoEmX(novaPosicaoX);
        personagem.setPosicaoEmY(novaPosicaoY);
        /*
         * graficos.drawImage(this.fundo, 0, 0, null);// Desenhando a Imagem no BG
         * graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(),
         * personagem.getPosicaoEmY(), null);
         */
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null);
        g.dispose();
    }

    // polimorfismo dos metodos criados na classe PersonagemS
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            personagem.keyTyped(e);
            repaint(); //atualização da tela
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.keyRelease(e);
            repaint(); // atualização da tela 
        }
    }

}
