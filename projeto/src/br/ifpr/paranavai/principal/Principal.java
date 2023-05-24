package br.ifpr.paranavai.principal;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
// import javax.swing.JOptionPane;

import br.ifpr.paranavai.jogo.modelo.Fase;

public class Principal extends JFrame {

    public Principal() {
        setVisible(true);
        setTitle("Invasores do Espaço");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // JButton botao = new JButton("Clique aqui se tiver coragem");

        /*
         * // Adiciona um ActionListener ao botão
         * botao.addActionListener(e -> {
         * JOptionPane.showMessageDialog(this, "Carai Cuzao!");
         * });
         * 
         * add(botao);
         */

        setVisible(true);

    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        
    }
}