package br.ifpr.paranavai.jogo.modelo;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fase extends JPanel implements ActionListener {
    private Image fundo;
    private Personagem personagem;

    public Fase() {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon carregando = new ImageIcon("recursos\\fundo.jpg");
        this.fundo = carregando.getImage();

        personagem = new Personagem();
        personagem.carregar();

        addKeyListener(new TecladoAdapter()); // Carregando o metodo

    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(this.fundo, 0, 0, null);
        graficos.drawImage(personagem.getImagem(), personagem.getPosicaoEmX(), personagem.getPosicaoEmY(), null);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        personagem.atualizar();
        repaint();
    }

    // polimorfismo dos metodos criados na classe PersonagemS
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
