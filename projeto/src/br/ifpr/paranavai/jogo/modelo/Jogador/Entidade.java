package br.ifpr.paranavai.jogo.modelo.Jogador;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Entidade {
    protected int pontos;
    protected int posicaoEmX, posicaoEmY;
    protected int deslocamentoEmX, deslocamentoEmY;
    protected int larguraImagem, alturaImagem, larguraImagem_Vida, alturaImagem_Vida;
    protected Image imagem;
    protected boolean visibilidade;
    protected Image imagem_vida;
    protected int VELOCIDADE = 5;

    public Entidade(){
        this.visibilidade = true;
    }

    // MÉTODO ABSTRATO BASICO DE CARREGAR E ATUALIZAR
    public abstract void carregar();

    public abstract void atualizar();

    // CAIXA DE COLISÃO
    public Rectangle getBounds() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
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

    public int getDeslocamentoEmX() {
        return deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
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

    public int getLarguraImagem_Vida() {
        return larguraImagem_Vida;
    }

    public void setLarguraImagem_Vida(int larguraImagem_Vida) {
        this.larguraImagem_Vida = larguraImagem_Vida;
    }

    public int getAlturaImagem_Vida() {
        return alturaImagem_Vida;
    }

    public void setAlturaImagem_Vida(int alturaImagem_Vida) {
        this.alturaImagem_Vida = alturaImagem_Vida;
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

    public Image getImagem_vida() {
        return imagem_vida;
    }

    public void setImagem_vida(Image imagem_vida) {
        this.imagem_vida = imagem_vida;
    }

    public  int getVelocidade() {
        return VELOCIDADE;
    }

    

}
