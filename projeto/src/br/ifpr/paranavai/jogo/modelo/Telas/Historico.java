package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import javax.swing.ImageIcon;

public class Historico extends EntidadeTelas {

    // CONSTRUTOR
    public Historico() {
        this.visibilidade = false;
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Sprites\\Fundos\\FundoMenu.jpg");
        this.imagem = carregando.getImage();
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        this.imagem = this.imagem.getScaledInstance(
                telaTamanho.getLARGURATELA(), telaTamanho.getALTURATELA(), Image.SCALE_FAST);
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    };

    @Override
    public void titulo(Graphics g) {
        String titulo = "HISTORICO";
        g.setFont(pixel.deriveFont(Font.BOLD, getTitulosize()));
        FontMetrics fm = g.getFontMetrics();
        int posicaoTituloX = (telaTamanho.getLARGURATELA() - fm.stringWidth(titulo)) / 2;
        // TITULO
        g.setColor(Color.WHITE);
        g.drawString(titulo, (posicaoTituloX + 5), posicaoTituloY);
        g.setColor(getCorAmarela());
        g.drawString(titulo, posicaoTituloX, posicaoTituloY);
    }

    @Override
    public void menu(Graphics g) {
        int tamanhoStringBotao = 30;
        g.setFont(pixel.deriveFont(Font.BOLD, tamanhoStringBotao));
        String textoBotao = "ESC";
        FontMetrics fm = g.getFontMetrics();
        int posicaoBotaoX = getBotao().x + (getBotaowidth() - fm.stringWidth(textoBotao)) / 2;
        int posicaoBotaoY = (getBotao().y + 5) + (getBotaoheight() - fm.getHeight()) / 2 + fm.getAscent();
        // PREENCHE O RETANGULO
        g.setColor(getCorAmarela());
        g.fillRect(getBotao().x, getBotao().y, getBotaowidth(), getBotaoheight());
        // DESENHA
        g.setColor(Color.WHITE);
        g.drawString(textoBotao, posicaoBotaoX, posicaoBotaoY);
    }
}
