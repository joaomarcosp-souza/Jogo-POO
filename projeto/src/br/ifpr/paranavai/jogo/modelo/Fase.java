package br.ifpr.paranavai.jogo.modelo;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Fase extends JPanel {
    private Image fundinho;

    public Fase() {
        ImageIcon carregando = new ImageIcon("recursos\\fundo.jpg");
        fundinho = carregando.getImage();

        Personagem personagem = new Personagem();
        personagem.carregar();
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g; // Tranformando o objeto em um 2D
        graficos.drawImage(fundinho, 0, 0, null);// Desenhando a Imagem no BG
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), this);
        g.dispose(); // Exibe o Desenho por meio do metodo
    }
}