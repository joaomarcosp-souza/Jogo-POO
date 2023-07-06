package br.ifpr.paranavai.jogo.modelo.Jogador;

import javax.swing.ImageIcon;

public class Tiro extends EntidadeJogador {

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
        ImageIcon carregador = new ImageIcon("recursos\\Sprites\\Tiros\\tiro.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmX += VELOCIDADE;
        if (this.posicaoEmX > telaTamanho.getLARGURATELA()) {
            visibilidade = false;
        }
    }
}
