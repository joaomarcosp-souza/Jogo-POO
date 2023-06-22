package br.ifpr.paranavai.jogo.modelo.Jogador;

import java.awt.Image;
import java.awt.*;

import javax.swing.ImageIcon;

public class Tiro {
    private Image imagem;
    private Image imagem_super;
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem, larguraImagem_super, alturaImagem, alturaImagem_super;
    private boolean visibilidade, super_visibilidade;

    // CONSTANTES
    private static int VELOCIDADE = 20; // VELOCIDADE DO TIRO
    private static final int LARGURA = 1300;

    // CONSTRUTOR
    public Tiro(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.visibilidade = true;
        this.super_visibilidade = true;
    }

    // MÉTODO PARA CARREGAR
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\sprites_tiros\\blaster1.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);

        ImageIcon carregador_super = new ImageIcon("recursos\\sprites_tiros\\bluetiro.png");
        this.imagem_super = carregador_super.getImage();
        this.alturaImagem_super = this.imagem_super.getWidth(null);
        this.larguraImagem_super = this.imagem_super.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmX += VELOCIDADE;
        if (this.posicaoEmX > LARGURA) {
            visibilidade = false;
        }
    }

    public void atualizarSuper() {
        this.posicaoEmX += VELOCIDADE;
        if (this.posicaoEmX > LARGURA) {
            this.super_visibilidade = false;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
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

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public static int getLargura() {
        return LARGURA;
    }

    public Image getImagem_super() {
        return imagem_super;
    }

    public void setImagem_super(Image imagem_super) {
        this.imagem_super = imagem_super;
    }

    public int getLarguraImagem_super() {
        return larguraImagem_super;
    }

    public void setLarguraImagem_super(int larguraImagem_super) {
        this.larguraImagem_super = larguraImagem_super;
    }

    public int getAlturaImagem_super() {
        return alturaImagem_super;
    }

    public void setAlturaImagem_super(int alturaImagem_super) {
        this.alturaImagem_super = alturaImagem_super;
    }

    public boolean isSuper_visibilidade() {
        return super_visibilidade;
    }

    public void setSuper_visibilidade(boolean super_visibilidade) {
        this.super_visibilidade = super_visibilidade;
    }
    
}
