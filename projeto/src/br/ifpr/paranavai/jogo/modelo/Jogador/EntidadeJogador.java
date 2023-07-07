package br.ifpr.paranavai.jogo.modelo.Jogador;

import java.io.File;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import br.ifpr.paranavai.principal.TamanhoTela;

import java.awt.FontFormatException;

public abstract class EntidadeJogador {
    private Font pixel = null;
    private Image imagem;
    private boolean visibilidade;
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem, alturaImagem;

    protected TamanhoTela telaTamanho;

    public EntidadeJogador() {
        // CARREGANDO UMA NOVA FONTE
        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        // INICIANDO
        telaTamanho = new TamanhoTela();
        telaTamanho.carregar();
        // VISIBILIDADE DA TELA
        this.visibilidade = true;
    }

    // MÉTODO ABSTRATO BASICO DE CARREGAR E ATUALIZAR
    public abstract void carregar();

    public abstract void atualizar();

    // CAIXA DE COLISÃO
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

    public Font getPixel() {
        return pixel;
    }

    public void setPixel(Font pixel) {
        this.pixel = pixel;
    }
}
