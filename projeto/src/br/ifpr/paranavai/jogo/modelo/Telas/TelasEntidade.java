package br.ifpr.paranavai.jogo.modelo.Telas;

import java.io.File;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.io.IOException;
import java.awt.FontFormatException;

public abstract class TelasEntidade {
    protected Image imagem;
    protected int posicaoEmX, posicaoEmY;
    protected int larguraImagem, alturaImagem;
    protected boolean visibilidade;
    protected int cursor = 0;
    protected Font pixel = null;

    public TelasEntidade() {
        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\Fontes\\pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        this.visibilidade = true;
    }

    // MÉTODO ABSTRATO BASICO DE CARREGAR E TITULO
    public abstract void carregar();

    public abstract void titulo(Graphics g);
    
    public abstract void menu(Graphics g);

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

    public Font getPixel() {
        return pixel;
    }

    public void setPixel(Font pixel) {
        this.pixel = pixel;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

}
