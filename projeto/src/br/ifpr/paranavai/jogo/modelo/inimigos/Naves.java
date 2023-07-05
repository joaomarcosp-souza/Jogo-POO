package br.ifpr.paranavai.jogo.modelo.inimigos;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

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

    // VIDA NAVE
    public void vidas(Graphics g) {
        int tamanhoCubo = 10; // TAMANHO DO CUBO
        int espacamentoCubo = 5; // ESPAÇAMENTO

        // CALCULANDO A POSIÇÃO DO CUBOD
        int numVidas = vidaInimigos;
        int posicaoCuboX = posicaoEmX - ((tamanhoCubo + espacamentoCubo) * numVidas) / 2;
        int posicaoCuboY = posicaoEmY + alturaImagem + 5;

        for (int i = 0; i < numVidas; i++) {
            // CALCULA A POSIÇÃO DO CUBO ATUAL
            int cuboPosX = posicaoCuboX + (tamanhoCubo + espacamentoCubo) * i;

            g.setColor(Color.RED);
            g.fillRect((cuboPosX + 44), posicaoCuboY, tamanhoCubo, tamanhoCubo);
        }
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
