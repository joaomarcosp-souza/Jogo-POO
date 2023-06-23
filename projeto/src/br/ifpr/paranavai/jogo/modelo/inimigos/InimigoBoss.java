package br.ifpr.paranavai.jogo.modelo.inimigos;

import java.awt.*;
import javax.swing.ImageIcon;

public class InimigoBoss {
    private int posicaoEmX, posicaoEmY;
    private int alturaImagem, larguraImagem;
    private Image imagem_boss;

    private static final int VELOCIDADE = 5;

    public InimigoBoss(int posicaoEmX, int posicaoEmY){
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
    }

    public void carregar(){
        ImageIcon carregador_boss = new ImageIcon("recursos\\sprites_inimigos\\starDestroyer.png");
        this.imagem_boss = carregador_boss.getImage();
        this.larguraImagem = imagem_boss.getWidth(null);
        this.alturaImagem = imagem_boss.getHeight(null);
    }

    public void atualizar() {
        this.posicaoEmX -= VELOCIDADE;
    }

    public Rectangle getBounds() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
    }

}
