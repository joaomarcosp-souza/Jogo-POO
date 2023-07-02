package br.ifpr.paranavai.jogo.modelo.inimigos;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class InimigosEntidade {
    // POSIÇÃO NA TELA
    protected int posicaoEmX;
    protected int posicaoEmY;
    // TAMANHO DA IMAGEM
    protected int larguraImagem,larguraImagemExplosao, alturaImagem, alturaImagemExplosao;
    protected Image imagem, imagemExplosao;
    protected boolean visibilidade;

    // INFORMAÇÕES BASICAS DO INIMIGO
    protected int VELOCIDADE;
    protected int VIDASINIMIGOS;


    // CONSTRUTOR
    public InimigosEntidade() {
        this.visibilidade = true;
    }

    // MÉTODO ABSTRATO BASICO DE CARREGAR E ATUALIZAR
    public abstract void carregar();

    public abstract void atualizar();

    // CAIXA DE COLISÃO
    public Rectangle getBounds() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
    }

    // GETTERS E SETTERS DA ENTIDADE
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

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public int getVIDASINIMIGOS() {
        return VIDASINIMIGOS;
    }

    public void setVIDASINIMIGOS(int vIDASINIMIGOS) {
        VIDASINIMIGOS = vIDASINIMIGOS;
    }

    public Image getImagemExplosao() {
        return imagemExplosao;
    }

    public void setImagemExplosao(Image imagemExplosao) {
        this.imagemExplosao = imagemExplosao;
    }

    public int getLarguraImagemExplosao() {
        return larguraImagemExplosao;
    }

    public void setLarguraImagemExplosao(int larguraImagemExplosao) {
        this.larguraImagemExplosao = larguraImagemExplosao;
    }

    public int getAlturaImagemExplosao() {
        return alturaImagemExplosao;
    }

    public void setAlturaImagemExplosao(int alturaImagemExplosao) {
        this.alturaImagemExplosao = alturaImagemExplosao;
    }
}
