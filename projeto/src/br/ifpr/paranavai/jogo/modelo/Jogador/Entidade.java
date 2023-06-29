package br.ifpr.paranavai.jogo.modelo.Jogador;

import java.io.File;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.FontFormatException;

public abstract class Entidade {
    // INFO DA CLASSE PERSONAGEM
    protected int VIDAS;
    protected int pontos;
    // INFORMAÇÕES DAS IMAGENS
    protected Image imagem;
    protected Image imagem_vida;
    protected int posicaoEmX, posicaoEmY;
    protected int larguraImagem, alturaImagem;
    protected int larguraImagem_Vida, alturaImagem_Vida;
    // VELOCIDADES PERSONAGEM 
    protected int deslocamentoEmX, deslocamentoEmY;
    protected int VELOCIDADE;
    //
    protected boolean visibilidade;
    protected Font pixel = null;

    public Entidade() {
        // CARREGANDO UMA NOVA FONTE
        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
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

    public int getVelocidade() {
        return VELOCIDADE;
    }

    public int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public Font getPixel() {
        return pixel;
    }

    public void setPixel(Font pixel) {
        this.pixel = pixel;
    }

    public int getVIDAS() {
        return VIDAS;
    }

    public void setVIDAS(int vIDAS) {
        VIDAS = vIDAS;
    }

}
