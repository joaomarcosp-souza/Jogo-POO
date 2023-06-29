package br.ifpr.paranavai.jogo.modelo.inimigos;

import javax.swing.ImageIcon;

public class Chefao extends InimigosEntidade{

    public Chefao(int posicaoEmX, int posicaoEmY){
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.VELOCIDADE = 5;
    }

    @Override
    public void carregar(){
        ImageIcon carregador = new ImageIcon("recursos\\sprites_inimigos\\chefao.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = imagem.getWidth(null);
        this.alturaImagem = imagem.getHeight(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmX -= VELOCIDADE;
    }
}
