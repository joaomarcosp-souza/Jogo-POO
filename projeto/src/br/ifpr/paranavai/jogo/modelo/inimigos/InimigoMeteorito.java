package br.ifpr.paranavai.jogo.modelo.inimigos;

import java.awt.*;
import javax.swing.ImageIcon;

public class InimigoMeteorito {
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem, alturaImagem;
    private Image imagem_meteoro;
    private boolean visibilidade;

     private int VELOCIDADE = 3; //Constante que não deve ser alterada

    public InimigoMeteorito(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.visibilidade = true;
    }

    public void carregar() {
        ImageIcon carregador_meteorito = new ImageIcon("recursos\\sprites_inimigos\\meteorito01.png");
        this.imagem_meteoro = carregador_meteorito.getImage();
        this.larguraImagem = this.imagem_meteoro.getWidth(null);
        this.alturaImagem = this.imagem_meteoro.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmY += VELOCIDADE;
        if (this.posicaoEmY > 700) {
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

    public Image getImagem_meteoro() {
        return imagem_meteoro;
    }

    public void setImagem_meteoro(Image imagem_meteoro) {
        this.imagem_meteoro = imagem_meteoro;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public void setVELOCIDADE(int velocidade) {
        this.VELOCIDADE = velocidade;
    }

    

}
