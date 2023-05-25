package br.ifpr.paranavai.jogo.modelo;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
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

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g; // Tranformando o objeto em um 2D // CAST
        graficos.drawImage(this.fundo, 0, 0, null);// Desenhando a Imagem no BG
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null);
        g.dispose(); // Exibe o Desenho por meio do metodo
    }

    // Carregando os metodos criados na fase personagem e os sobreescrevendo com
    // polimorfismo
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            personagem.keypressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            personagem.keyRelease(e);
        }
    }
}