package br.ifpr.paranavai.jogo.modelo.view;

import javax.swing.JFrame;

import br.ifpr.paranavai.jogo.modelo.model.Fase;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Principal extends JFrame {

    public Principal() {
        Fase fase = new Fase();
        super.add(fase);
        super.setTitle("Star Wars: Invader");
        super.setVisible(true);
        // PEGA O TAMANHO DO MONITOR PRINCIPAL
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        // CRIA O TAMANHO DA JANELA JFRAME COM BASE NO TAMANHO DO MONITOR
        super.setSize(screenWidth, screenHeight);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
    }
    
    public static void main(String[] args) {
        new Principal();
    }
}