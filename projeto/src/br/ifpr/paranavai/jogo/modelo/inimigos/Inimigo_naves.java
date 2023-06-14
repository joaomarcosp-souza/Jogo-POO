package br.ifpr.paranavai.jogo.modelo.inimigos;

import java.awt.*;
import javax.swing.ImageIcon;

public class Inimigo_naves {
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem;
    private int alturaImagem;
    private Image imagem_nave;
    private boolean visibilidade;

     private static final int VELOCIDADE = 6; //Constante que não deve ser alterada

    public Inimigo_naves(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.visibilidade = true;
    }

    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\sprites_inimigos\\tfighter.png");
        //ImageIcon carregador = new ImageIcon("recursos\\sprites_inimigos\\starDestroyer.png");
        this.imagem_nave = carregador.getImage();
        this.alturaImagem = this.imagem_nave.getWidth(null);
        this.larguraImagem = this.imagem_nave.getHeight(null);
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
        return imagem_nave;
    }

    public void setImagem(Image imagem) {
        this.imagem_nave = imagem;
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

    public Image getImagem_nave() {
        return imagem_nave;
    }

    public void setImagem_nave(Image imagem_nave) {
        this.imagem_nave = imagem_nave;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public static int getVelocidade() {
        return VELOCIDADE;
    }

    


}
