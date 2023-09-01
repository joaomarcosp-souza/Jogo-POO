package br.ifpr.paranavai.jogo.modelo.Invasores;

import javax.swing.ImageIcon;

import br.ifpr.paranavai.jogo.modelo.Base;

public class DeathStar extends Base {

    private static final String IMAGEM_NUVEM = "recursos\\Sprites\\Inimigos\\chefao.png";

    public DeathStar(int posicaoEmX, int posicaoEmY) {
        super.setVelocidadeInicial(2);
        super.setVelocidade(super.getVelocidadeInicial());
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVisibilidade(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(IMAGEM_NUVEM);
        super.setImagem(carregador.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX((int) (super.getPosicaoEmX() - super.getVelocidade()));
        if (super.getPosicaoEmX() < -1000) {
            super.setVisibilidade(false);
        }

        if (super.getPosicaoEmY() < 0) {
            super.setPosicaoEmY(0);
        } else if (super.getPosicaoEmY() > super.getTelaTamanho().ALTURA_TELA - super.getAlturaImagem()) {
            super.setPosicaoEmY(super.getTelaTamanho().ALTURA_TELA - super.getAlturaImagem());
        }
    }
}
