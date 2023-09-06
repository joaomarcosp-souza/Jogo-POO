package br.ifpr.paranavai.jogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.awt.Font;
import java.io.IOException;
import java.awt.FontFormatException;
import br.ifpr.paranavai.principal.TamanhoTela;

public abstract class Base {
    private int vida;
    private Image imagem;
    private double velocidade;
    private Font pixel = null;
    private boolean visibilidade;
    private TamanhoTela telaTamanho;
    private double velocidadeInicial;
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem, alturaImagem;

    public Base() {
        this.visibilidade = true;
        //PUXANDO INFORMAÇÕES DA TELA 
        telaTamanho = new TamanhoTela();
        telaTamanho.carregar();
        // CARREGANDO UMA NOVA FONTE
        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
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
    

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
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

    public Font getPixel() {
        return pixel;
    }

    public void setPixel(Font pixel) {
        this.pixel = pixel;
    }

    public TamanhoTela getTelaTamanho() {
        return telaTamanho;
    }

    public void setTelaTamanho(TamanhoTela telaTamanho) {
        this.telaTamanho = telaTamanho;
    }
}
