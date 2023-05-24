package br.ifpr.paranavai.principal;

import java.awt.Color;

import javax.swing.JFrame;

public class Principal extends JFrame {
    public Principal() {
        setVisible(true);
        setTitle("Invasores do Espaço");
        setSize(300, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    };

    public static void main(String[] args) {
        Principal principal = new Principal();
    }
}
