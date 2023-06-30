package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Historico extends TelasEntidade {
    private Rectangle botao;
    private static final int BOTAOWIDTH = 60;
    private static final int BOTAOHEIGHT = 40;
    private static final int BOTAO_X = 5;
    private static final int BOTAO_Y = 5;
    private static final int OFFSETY = 280;
    private static final int OFFSETX = 95;

    public Historico() {
        this.visibilidade = false;
        this.botao = new Rectangle(BOTAO_X, BOTAO_Y, BOTAOWIDTH, BOTAOHEIGHT); // TAMANHO E POSIÇÃO DO BOTÃO
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\FundoMenu.jpg");
        this.imagem = carregando.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    public void titulo(Graphics g) {
        // CARREGA O FUNDO
        g.drawImage(imagem, 0, 0, null);
        FontMetrics fm = g.getFontMetrics();
        // BOTÃO
        g.setColor(getCorAmarela());
        g.fillRect(botao.x, botao.y, botao.width, botao.height);
        // STRING DO BOTÃO
        g.setFont(pixel.deriveFont(Font.BOLD, 15));
        String textoBotao = "ESC";
        int stringWidth = fm.stringWidth(textoBotao);
        int stringHeight = fm.getHeight();
        int posicaoBotaoX = (botao.x - 2) + (botao.width - stringWidth) / 2;
        int posicaoBotaoY = botao.y + (botao.height - stringHeight) / 2 + fm.getAscent();
        g.setColor(Color.WHITE);
        g.drawString(textoBotao, posicaoBotaoX, posicaoBotaoY);

        // CONFIG FONTES
        g.setFont(pixel.deriveFont(Font.BOLD, 85));
        String titulo = "HISTORICO";
        int tituloWidth = fm.stringWidth(titulo); // PEGA O TAMANHO DA STRING
        // CENTRALIZA VERTICALMENTE
        int tituloX = OFFSETX + (alturaImagem - tituloWidth) / 2;
        int tituloY = 100 + (alturaImagem - fm.getHeight()) / 2;
        g.setColor(Color.WHITE); // DEFINE A COR PRINCIPAL DO TÍTULO - BRANCO
        g.drawString(titulo, (tituloX + 5), tituloY - OFFSETY); // POSIÇÃO DO TITULO - AMARELO
        g.setColor(getCorAmarela());
        g.drawString(titulo, tituloX, tituloY - OFFSETY); // POSIÇÃO DO TÍTULO PRINCIPAL - COR BRANCA
    }

    @Override
    public void menu(Graphics g) {
        // SEM MENU AQUI POR ENQUANTO
    }
}
