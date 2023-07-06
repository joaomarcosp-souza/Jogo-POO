package br.ifpr.paranavai.jogo.modelo.Telas;

import java.io.File;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import br.ifpr.paranavai.principal.TamanhoTela;

import java.awt.FontFormatException;

public abstract class EntidadeTelas {
    // TELA
    protected int posicaoTituloY = 100; // POSIÇÃO DOS TITULOS EM 'Y'
    protected Image imagem;
    protected int posicaoEmX, posicaoEmY; // POSIÇÃO DOS INIMIGOS
    protected int larguraImagem, alturaImagem; // LARGURA E ALTURA PARA AS IMGS
    // PEGA A LARGURA E ALTURA DA TELA DO COMPUTADOR AUTOMATICAMENTE
    protected boolean visibilidade;
    protected Font pixel = null;
    // MENU
    protected int cursor = 0;
    protected static final Color COR_AMARELA = new Color(255, 209, 70); // COR
    protected static final int TITULOSIZE = 85;
    protected static final int MENUSIZE = 50;
    // DIMENSÕES DO BOTÃO
    private Rectangle botao;
    private static final int BOTAOWIDTH = 70;
    private static final int BOTAOHEIGHT = 40;
    private static final int BOTAO_X = 5;
    private static final int BOTAO_Y = 5;
    // INSTANCIA
    protected TamanhoTela telaTamanho;

    public EntidadeTelas() {
        try {
            // CARREGA UMA NOVA FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\Fontes\\pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        // DIMENSÕES DO BOTAO 'VOLTAR'
        this.botao = new Rectangle(BOTAO_X, BOTAO_Y, BOTAOWIDTH, BOTAOHEIGHT); // TAMANHO E POSIÇÃO DO BOTÃO
        // CARREGANDO A CONFIG DA TELACONFIGURAÇÃO
        telaTamanho = new TamanhoTela();
        telaTamanho.carregar();
        // VISIBILIDADE DA CLASSE
        this.visibilidade = true;
    }

    // MÉTODO ABSTRATO BASICO PARA CRIAR OS MÉTODOS 'TITULO', 'MENU' E 'CARREGAR'
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

    public int getPosicaoTituloY() {
        return posicaoTituloY;
    }

    public void setPosicaoTituloY(int posicaoTituloY) {
        this.posicaoTituloY = posicaoTituloY;
    }
}
