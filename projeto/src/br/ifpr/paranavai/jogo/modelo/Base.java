package br.ifpr.paranavai.jogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;

import br.ifpr.paranavai.principal.TamanhoTela;

public abstract class Base {
    private int vida;
    private Image imagem;
    private boolean visibilidade;
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem, alturaImagem;
    private int deslocamentoEmX, deslocamentoEmY;
    private TamanhoTela telaTamanho;
    private double velocidade;
    private double velocidadeInicial;

    public Base() {
        this.visibilidade = true;
        // INICIANDO
        telaTamanho = new TamanhoTela();
        telaTamanho.carregar();
    }

    // MÉTODOS ABSTRATO BASICO PARA 'CARREGAR' É 'ATUALIZAR'
    public abstract void carregar();
    
    // CAIXA DE COLISÃO
    public Rectangle getBounds() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
    }
    //NÃO E UM MÉTODO ABSTRAT, POR CAUSA DAS CLASSE DE TELA
    public void atualizar(){};


    //GETTERS E SETTERS
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

    public TamanhoTela getTelaTamanho() {
        return telaTamanho;
    }

    public void setTelaTamanho(TamanhoTela telaTamanho) {
        this.telaTamanho = telaTamanho;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDeslocamentoEmY() {
        return deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }


    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getDeslocamentoEmX() {
        return deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public double getVelocidadeInicial() {
        return velocidadeInicial;
    }

    public void setVelocidadeInicial(double velocidadeInicial) {
        this.velocidadeInicial = velocidadeInicial;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    

}
