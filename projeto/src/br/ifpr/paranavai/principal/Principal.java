package br.ifpr.paranavai.principal;

import javax.swing.JFrame;

import br.ifpr.paranavai.jogo.modelo.Fase;

public class Principal extends JFrame {

    public Principal() {

        Fase fase = new Fase();
        super.add(fase);

        super.setTitle("Star Wars: Invader");
        super.setSize(1300, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setVisible(true);
    }

    public static void main(String[] args) {
        new Principal();
    }
}