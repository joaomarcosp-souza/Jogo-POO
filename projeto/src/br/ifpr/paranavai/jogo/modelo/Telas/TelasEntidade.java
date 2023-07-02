package br.ifpr.paranavai.jogo.modelo.Telas;

import java.io.File;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.awt.FontFormatException;

public abstract class TelasEntidade {
    // TEALA
    protected int Y = 100; // POSIÇÃO DOS TIRULOS
    protected Image imagem;
    protected int posicaoEmX, posicaoEmY;
    protected int larguraImagem, alturaImagem;
    protected int LARGURAJANELA = 1600, ALTURAJANELA = 800;
    protected boolean visibilidade;
    protected Font pixel = null;
    // MENU
    protected int cursor = 0;
    protected static final Color COR_AMARELA = new Color(255, 209, 70); // COR
    protected static final int TITULOSIZE = 85;
    protected static final int MENUSIZE = 50;
    //BOTAO
    private Rectangle botao;
    private static final int BOTAOWIDTH = 70;
    private static final int BOTAOHEIGHT = 40;
    private static final int BOTAO_X = 5;
    private static final int BOTAO_Y = 5;

    public TelasEntidade() {
        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\Fontes\\pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        this.botao = new Rectangle(BOTAO_X, BOTAO_Y, BOTAOWIDTH, BOTAOHEIGHT); // TAMANHO E POSIÇÃO DO BOTÃO
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

    public static Color getCorAmarela() {
        return COR_AMARELA;
    }

    public int getLARGURAJANELA() {
        return LARGURAJANELA;
    }

    public void setLARGURAJANELA(int lARGURAJANELA) {
        LARGURAJANELA = lARGURAJANELA;
    }

    public int getALTURAJANELA() {
        return ALTURAJANELA;
    }

    public void setALTURAJANELA(int aLTURAJANELA) {
        ALTURAJANELA = aLTURAJANELA;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public static int getTitulosize() {
        return TITULOSIZE;
    }

    public static int getMenusize() {
        return MENUSIZE;
    }

    public Rectangle getBotao() {
        return botao;
    }

    public void setBotao(Rectangle botao) {
        this.botao = botao;
    }

    public static int getBotaowidth() {
        return BOTAOWIDTH;
    }

    public static int getBotaoheight() {
        return BOTAOHEIGHT;
    }

    public static int getBotaoX() {
        return BOTAO_X;
    }

    public static int getBotaoY() {
        return BOTAO_Y;
    }

    
}
