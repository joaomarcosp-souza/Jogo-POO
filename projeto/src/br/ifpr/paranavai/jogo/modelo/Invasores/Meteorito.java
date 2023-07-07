package br.ifpr.paranavai.jogo.modelo.Invasores;

import javax.swing.ImageIcon;

public class Meteorito extends EntidadeInimigos {

    public Meteorito(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.velocidade = 3;
        this.visibilidade = true;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\Sprites\\Inimigos\\meteorito.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmY += velocidade;
        if (this.posicaoEmY > 800) {
            visibilidade = false;
        }
    }
}
