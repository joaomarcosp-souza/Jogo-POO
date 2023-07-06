package br.ifpr.paranavai.jogo.modelo.Inimigos;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class EntidadeInimigos {
    // POSIÇÃO DOS INIMIGOS NA TELA
    protected int posicaoEmX;
    protected int posicaoEmY;
    // INFORMAÇÕES DAS IMAGENS
    protected Image imagem;
    protected int larguraImagem, alturaImagem;
    // IMG EXPLOSAO
    protected Image imagemExplosao;
    protected int larguraImagemExplosao, alturaImagemExplosao;
    protected boolean visibilidade;
    // STATUS DOS INIMIGOS
    protected int velocidade;
    protected int inimigosvida;

    // CONSTRUTOR
    public EntidadeInimigos() {
        this.visibilidade = true;
    }

    // MÉTODO ABSTRATO BASICO PARA 'CARREGAR' É 'ATUALIZAR'
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

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getInimigosvida() {
        return inimigosvida;
    }

    public void setInimigosvida(int inimigosvida) {
        this.inimigosvida = inimigosvida;
    }

}
