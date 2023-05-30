package br.ifpr.paranavai.jogo.modelo.inimigos;

import java.awt.*;
import javax.swing.ImageIcon;

public class Inimigo_boss {
    private int posicaoEmX, posicaoEmY;
    private int alturaImagem, larguraImagem;
    private Image imagem_boss;
    private boolean visibilidade;

    private int velocidade = 5;

    public Inimigo_boss(int posicaoEmX, int posicaoEmY){
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.visibilidade = true;
    }

    public void carregar(){
        ImageIcon carregador_boss = new ImageIcon("recursos\\sprites_inimigos\\starDestroyer.png");
        this.imagem_boss = carregador_boss.getImage();
        this.alturaImagem = imagem_boss.getWidth(null);
        this.alturaImagem = imagem_boss.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmX -= velocidade;
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

    public int getAlturaImagem() {
        return alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }

    public int getLarguraImagem() {
        return larguraImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

    public Image getImagem_boss() {
        return imagem_boss;
    }

    public void setImagem_boss(Image imagem_boss) {
        this.imagem_boss = imagem_boss;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }
}
