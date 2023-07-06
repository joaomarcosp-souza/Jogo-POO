package br.ifpr.paranavai.jogo.modelo.Inimigos;

import javax.swing.ImageIcon;

public class Chefao extends EntidadeInimigos{

    public Chefao(int posicaoEmX, int posicaoEmY){
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.velocidade = 5;
    }

    @Override
    public void carregar(){
        ImageIcon carregador = new ImageIcon("recursos\\Sprites\\Inimigos\\chefao.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = imagem.getWidth(null);
        this.alturaImagem = imagem.getHeight(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmX -= velocidade;
    }
}
