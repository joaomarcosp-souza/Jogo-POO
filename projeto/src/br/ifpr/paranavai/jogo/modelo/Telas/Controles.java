package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.FontMetrics;

public class Controles extends TelasEntidade {

    public Controles() {
        this.visibilidade = false;
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\FundoMenu.jpg");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    @Override
    public void titulo(Graphics g) {
        g.drawImage(imagem, 0, 0, null);
        String titulo = "CONTROLES";
        g.setColor(getCorAmarela());
        g.setFont(pixel.deriveFont(Font.BOLD, 85));
        // CRENTALIZA VERTICALMENTE
        FontMetrics fm = g.getFontMetrics();
        int tituloWidth = fm.stringWidth(titulo); // PEGA O TAMANHO DA STRING
        int x = (alturaImagem - tituloWidth) / 2;
        int y = 100 + (larguraImagem - fm.getHeight()) / 2;

        g.setColor(Color.WHITE);
        g.drawString(titulo, (x + 5), y - 280);
        g.setColor(getCorAmarela());
        g.drawString(titulo, x, y - 280);
    }

    @Override
    public void menu(Graphics g) {
        // SEM MENU AQUI POR ENQUANTO
    }
}
