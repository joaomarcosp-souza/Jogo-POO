package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Image;
import java.io.IOException;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.*;

import java.io.File;

public class TelaControles {
    private boolean controle_visibilidade;
    private Image imagem_controles;
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem, alturaImagem;
    private boolean historico_visibilidade;

    private Font pixel = null;

    public TelaControles() {
        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        this.historico_visibilidade = true;
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\fundo_menu.jpg");
        this.imagem_controles = carregando.getImage();
        this.alturaImagem = this.imagem_controles.getWidth(null);
        this.larguraImagem = this.imagem_controles.getHeight(null);
    }

    public void titulo_controle(Graphics g) {
        g.drawImage(imagem_controles, 0, 0, null);

        String titulo = "CONTROLES";
        Font fonte = pixel;

        fonte = fonte.deriveFont(Font.BOLD, 85);

        g.setColor(new Color(255, 209, 70)); // COR DO TITULO AMARELO
        g.setFont(fonte);
        // CRENTALIZA VERTICALMENTE
        FontMetrics fm = g.getFontMetrics();

        int tituloWidth = fm.stringWidth(titulo); // PEGA O TAMANHO DA STRING
        int x = (alturaImagem - tituloWidth) / 2;
        int y = 100 + (larguraImagem - fm.getHeight()) / 2;
        //
        g.setColor(Color.WHITE); // DEFINE A COR PRINCIPAL DO TÍTULO - BRANCO
        g.drawString(titulo, (x + 7), y - 280); // POSIÇÃO DO TITULO - AMARELO
        // SUBTITULO COR AMARELO
        // DESENHA O SUBTÍTULO NA TELA - AMARELO
        // t.setColor(new Color(255, 209, 70));

        g.setColor(new Color(255, 209, 70));
        g.drawString(titulo, x, y - 280); // POSIÇÃO DO TÍTULO PRINCIPAL - COR BRANCA
    }

    public Image getImagem_controles() {
        return imagem_controles;
    }

    public void setImagem_controles(Image imagem_controles) {
        this.imagem_controles = imagem_controles;
    }

    public int getPosicaoEmX() {
        return posicaoEmX;
    }

    public void setPosicaoEmX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPosicaoEmY() {
        return posicaoEmY;
    }

    public void setPosicaoEmY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
    }

    public int getLarguraImagem() {
        return larguraImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

    public int getAlturaImagem() {
        return alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }

    public boolean isHistorico_visibilidade() {
        return historico_visibilidade;
    }

    public void setHistorico_visibilidade(boolean historico_visibilidade) {
        this.historico_visibilidade = historico_visibilidade;
    }

    public boolean isControle_visibilidade() {
        return controle_visibilidade;
    }

    public void setControle_visibilidade(boolean controle_visibilidade) {
        this.controle_visibilidade = controle_visibilidade;
    }
}
