package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import javax.swing.ImageIcon;

public class Historico extends TelasEntidade {

    public Historico() {
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
        // CARREGA O FUNDO
        g.drawImage(imagem, 0, 0, null);
        // CONFIG FONTES
        String titulo = "HISTORICO";
        Font fonte = pixel;
        fonte = fonte.deriveFont(Font.BOLD, 85);
        g.setColor(new Color(255, 209, 70)); // COR DO TITULO AMARELO
        g.setFont(fonte);
        // CRENTALIZA VERTICALMENTE
        FontMetrics fm = g.getFontMetrics();
        int tituloWidth = fm.stringWidth(titulo); // PEGA O TAMANHO DA STRING
        int x = (alturaImagem - tituloWidth) / 2;
        int y = 100 + (larguraImagem - fm.getHeight()) / 2;

        g.setColor(Color.WHITE); // DEFINE A COR PRINCIPAL DO TÍTULO - BRANCO
        g.drawString(titulo, (x + 5), y - 280); // POSIÇÃO DO TITULO - AMARELO
        g.setColor(new Color(255, 209, 70));
        g.drawString(titulo, x, y - 280); // POSIÇÃO DO TÍTULO PRINCIPAL - COR BRANCA
    }

    @Override
    public void menu(Graphics g) {
        // SEM MENU AQUI POR ENQUANTO
    }
}
