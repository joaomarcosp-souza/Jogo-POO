package br.ifpr.paranavai.jogo.modelo.Invasores;

import javax.swing.ImageIcon;

public class Chefao extends EntidadeInimigos {

    private double VELOCIDADEINICIAL = 3;
    private static final String IMAGEMCHEF = "recursos\\Sprites\\Inimigos\\chefao.png";

    public Chefao(int posicaoEmX, int posicaoEmY) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVelocidade(VELOCIDADEINICIAL);
        super.setVisibilidade(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(IMAGEMCHEF);
        super.setImagem(carregador.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX((int) (super.getPosicaoEmX() - VELOCIDADEINICIAL));
    }
}
