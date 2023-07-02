package br.ifpr.paranavai.jogo.modelo.inimigos;

import javax.swing.ImageIcon;

public class Naves extends InimigosEntidade {


    public Naves(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.VELOCIDADE = 3;
        this.VIDASINIMIGOS = 2;
        this.visibilidade = true;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\sprites_inimigos\\tfighter.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmX -= VELOCIDADE;
        if (this.posicaoEmX < -50) {
            visibilidade = false;
        }
    }
}
