package br.ifpr.paranavai.jogo.modelo.inimigos;

import javax.swing.ImageIcon;

public class Meteorito extends InimigosEntidade {

    public Meteorito(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.VELOCIDADE = 3;
        this.vidaInimigos = 2;
        this.visibilidade = true;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\sprites_inimigos\\meteorito.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmY += VELOCIDADE;
        if (this.posicaoEmY > 700) {
            visibilidade = false;
        }
    }
}
