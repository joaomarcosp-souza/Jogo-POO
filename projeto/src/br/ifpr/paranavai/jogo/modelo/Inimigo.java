package br.ifpr.paranavai.jogo.modelo;

import java.awt.*;
import javax.swing.ImageIcon;

public class Inimigo {
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem;
    private int alturaImagem;
    private Image imagem;
    private boolean visivel;

    private static int VELOCIDADE = 2;

    public Inimigo(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        visivel = true;
    }

    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\tfighterdefinitiva.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmX -= VELOCIDADE;
    }

    public Rectangle getBounds() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
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

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
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

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }


}
