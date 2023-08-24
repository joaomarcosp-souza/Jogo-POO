package br.ifpr.paranavai.jogo.modelo.Invasores;

import javax.swing.ImageIcon;

import br.ifpr.paranavai.jogo.modelo.Base;

public class Chefao extends Base {

    private static final String IMAGEMCHEF = "recursos\\Sprites\\Inimigos\\chefao.png";

    public Chefao(int posicaoEmX, int posicaoEmY) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVelocidadeInicial(3);
        super.setVelocidade(super.getVelocidadeInicial());
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
        super.setPosicaoEmX((int) (super.getPosicaoEmX() - super.getVelocidadeInicial()));
    }
}
