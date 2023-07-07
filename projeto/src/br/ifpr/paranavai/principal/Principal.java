package br.ifpr.paranavai.principal;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
//import java.awt.GraphicsDevice;
//import java.awt.GraphicsEnvironment;

import br.ifpr.paranavai.jogo.modelo.Modos.Infinito;

public class Principal extends JFrame {

    public Principal() {
        Infinito fase = new Infinito();
        super.add(fase);
        super.setTitle("Star Wars: Invader");
        // TESTE TELA CHEIA
        // GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        // PEGA O TAMANHO DA TELA
        Dimension telaTamanho = Toolkit.getDefaultToolkit().getScreenSize();
        int telaLargura = (int) telaTamanho.getWidth();
        int telaAltura = (int) telaTamanho.getHeight();
        // CRIA O TAMANHO DO JFRAME COM BASE NO TAMANHO DA TELA DO COMPUTADOR
        super.setSize(telaLargura, telaAltura);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setFocusableWindowState(true);
        super.setVisible(true);
        // TELA CHEIA
        // device.setFullScreenWindow(this);
    }

    public static void main(String[] args) {
        new Principal();
    }
}