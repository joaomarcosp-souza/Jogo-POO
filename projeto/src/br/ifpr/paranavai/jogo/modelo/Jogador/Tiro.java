package br.ifpr.paranavai.jogo.modelo.Jogador;

import javax.swing.ImageIcon;

public class Tiro extends Entidade {

    private static final int LARGURA = 1300;

    // CONSTRUTOR
    public Tiro(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.visibilidade = true;
        this.VELOCIDADE = 10;
    }

    // MÉTODO PARA CARREGAR
    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\sprites_tiros\\blaster1.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmX += VELOCIDADE;
        if (this.posicaoEmX > LARGURA) {
            visibilidade = false;
        }
    }
}
