package br.ifpr.paranavai.jogo.modelo.Jogador;

import javax.swing.ImageIcon;

public class TiroEspecial extends EntidadeJogador {

    public TiroEspecial(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.visibilidade = true;
        this.VELOCIDADE = 10;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\Sprites\\Tiros\\super.png");
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
