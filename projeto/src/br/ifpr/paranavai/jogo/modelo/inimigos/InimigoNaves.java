package br.ifpr.paranavai.jogo.modelo.inimigos;

import java.awt.*;
import javax.swing.ImageIcon;

public class InimigoNaves {
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem;
    private int alturaImagem;
    private Image imagem_nave;
    private boolean visibilidade;
    private int VIDASINIMIGOS = 2;
    private int tempoVisivel;

    private int VELOCIDADE = 4;

    public InimigoNaves(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.visibilidade = true;
    }

    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\sprites_inimigos\\tfighter.png");
        // ImageIcon carregador = new
        // ImageIcon("recursos\\sprites_inimigos\\starDestroyer.png");
        this.imagem_nave = carregador.getImage();
        this.larguraImagem = this.imagem_nave.getWidth(null);
        this.alturaImagem = this.imagem_nave.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmX -= VELOCIDADE;
        if (this.posicaoEmX < -50) {
            visibilidade = false;
        }
    }

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

    public Image getImagem() {
        return imagem_nave;
    }

    public void setImagem(Image imagem) {
        this.imagem_nave = imagem;
    }

    public int getAlturaImagem() {
        return alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }

    public Image getImagem_nave() {
        return imagem_nave;
    }

    public void setImagem_nave(Image imagem_nave) {
        this.imagem_nave = imagem_nave;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public int getVIDASINIMIGOS() {
        return VIDASINIMIGOS;
    }

    public void setVIDASINIMIGOS(int vIDASINIMIGOS) {
        VIDASINIMIGOS = vIDASINIMIGOS;
    }

    public int getTempoVisivel() {
        return tempoVisivel;
    }

    public void setTempoVisivel(int tempoVisivel) {
        this.tempoVisivel = tempoVisivel;
    }

    public int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public void setVELOCIDADE(int VELOCIDADE) {
        this.VELOCIDADE = VELOCIDADE;
    }

}
