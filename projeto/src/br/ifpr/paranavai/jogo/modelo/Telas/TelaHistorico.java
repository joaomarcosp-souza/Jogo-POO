package br.ifpr.paranavai.jogo.modelo.Telas;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import java.awt.*;

public class TelaHistorico {
    private Image imagem_historico;
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem, alturaImagem;
    private boolean historico_visibilidade;

    private Font pixel = null;

    public TelaHistorico() {
        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));
            // DEFINE O TAMANHO DA FONTE DESEJADO

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        this.historico_visibilidade = false;
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\fundo_menu.jpg");
        this.imagem_historico = carregando.getImage();
        this.alturaImagem = this.imagem_historico.getWidth(null);
        this.larguraImagem = this.imagem_historico.getHeight(null);
    }

    public void titulo_Historico(Graphics g) {
        g.drawImage(imagem_historico, 0, 0, null);
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

    public boolean isHistorico_visibilidade() {
        return historico_visibilidade;
    }

    public void setHistorico_visibilidade(boolean historico_visibilidade) {
        this.historico_visibilidade = historico_visibilidade;
    }

    public Image getImagem_historico() {
        return imagem_historico;
    }

    public void setImagem_historico(Image imagem_historico) {
        this.imagem_historico = imagem_historico;
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

}
