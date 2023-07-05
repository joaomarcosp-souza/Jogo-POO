package br.ifpr.paranavai.jogo.modelo.inimigos;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

public class Naves extends InimigosEntidade {

    int LARGURATELA = 1600;
    private Image imagemVida;
    private int larguraImgVida, alturaImgVida;

    public Naves(int posicaoEmX, int posicaoEmY, int vidaInimigos) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.vidaInimigos = vidaInimigos;
        this.VELOCIDADE = 3;
        this.visibilidade = true;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\sprites_inimigos\\tfighter.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);

        ImageIcon carregadorVida = new ImageIcon("recursos\\sprites_inimigos\\vidas\\vida.png");
        this.imagemVida = carregadorVida.getImage();
        this.larguraImgVida = this.imagem.getWidth(null);
        this.alturaImgVida = this.imagem.getHeight(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmX -= VELOCIDADE;
        if (this.posicaoEmX < -50) {
            visibilidade = false;
        }
    }

    public void vidas(Graphics life) {
        int diferenca = 2;
        // CALCUPA A DISTÂNCIA DA BORDA PARA POSICIONAR A VIDA
        int distancia = (5 * vidaInimigos) + (1 * vidaInimigos) + diferenca;
        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        // A POSIÇÃO DE CADA UMA EM (x,y)
        for (int i = 0; i < vidaInimigos; i++) {
            life.drawImage(imagemVida, posicaoEmX + (larguraImgVida - diferenca), posicaoEmY + 85, null);
            diferenca += alturaImagem + 5;
        }
        // DESENHA AS IMAGENS COM BASE NA POSIÇÃO (x,y)
        life.drawString("", alturaImagem - distancia, 10);
    }

    // GETTERS E SETTERS
    public Image getImagemVida() {
        return imagemVida;
    }

    public void setImagemVida(Image imagemVida) {
        this.imagemVida = imagemVida;
    }

    public int getLarguraImgVida() {
        return larguraImgVida;
    }

    public void setLarguraImgVida(int larguraImgVida) {
        this.larguraImgVida = larguraImgVida;
    }

    public int getAlturaImgVida() {
        return alturaImgVida;
    }

    public void setAlturaImgVida(int alturaImgVida) {
        this.alturaImgVida = alturaImgVida;
    }
}
