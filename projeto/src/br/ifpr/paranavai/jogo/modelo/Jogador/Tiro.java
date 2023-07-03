package br.ifpr.paranavai.jogo.modelo.Jogador;

import javax.swing.ImageIcon;

public class Tiro extends Entidade {

    // CONSTRUTOR
    public Tiro(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.visibilidade = true;
        this.VELOCIDADE = 8;
    }

    // MÉTODO PARA CARREGAR
    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\sprites_tiros\\tiro.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmX += VELOCIDADE;
        if (this.posicaoEmX > getLARGURATELA()) {
            visibilidade = false;
        }
    }
}
