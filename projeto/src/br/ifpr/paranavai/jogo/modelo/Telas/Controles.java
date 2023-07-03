package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import java.awt.FontMetrics;

public class Controles extends TelasEntidade {

    //CONSTRUTOR 
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
        String titulo = "CONTROLES";
        g.setColor(getCorAmarela());
        g.setFont(pixel.deriveFont(Font.BOLD, getTitulosize()));
        // CRENTALIZA VERTICALMENTE
        FontMetrics fm = g.getFontMetrics();
        int tituloWidth = fm.stringWidth(titulo); // PEGA O TAMANHO DA STRING
        int x = (LARGURAJANELA - tituloWidth) / 2;
        g.setColor(Color.WHITE);
        g.drawString(titulo, (x + 5), Y);
        g.setColor(getCorAmarela());
        g.drawString(titulo, x, Y);
    }

    @Override
    public void menu(Graphics g) {
        int tamanhoStringBotao = 30;
        g.setFont(pixel.deriveFont(Font.BOLD, tamanhoStringBotao));
        String textoBotao = "ESC";
        FontMetrics fm = g.getFontMetrics();
        int posicaoBotaoX = getBotao().x + (getBotaowidth() - fm.stringWidth(textoBotao)) / 2;
        int posicaoBotaoY = (getBotao().y + 5) + (getBotaoheight() - fm.getHeight()) / 2 + fm.getAscent();
        g.setColor(getCorAmarela());
        g.fillRect(getBotao().x, getBotao().y, getBotaowidth(), getBotaoheight());
        g.setColor(Color.BLACK);
        g.drawString(textoBotao, posicaoBotaoX + 2, posicaoBotaoY);
        g.setColor(Color.WHITE);
        g.drawString(textoBotao, posicaoBotaoX, posicaoBotaoY);
    }
}
