package br.ifpr.paranavai.principal;

import javax.swing.JFrame;

import br.ifpr.paranavai.jogo.modelo.Fase;

public class Principal extends JFrame {

    public Principal() {
        super.add(new Fase());
        super.setTitle("Invasores Espaciais");
        super.setSize(1020, 600);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }

    public static void main(String[] args) {
       new Principal();
    }
}