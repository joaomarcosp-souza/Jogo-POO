package br.ifpr.paranavai.jogo.modelo.Invasores;

import javax.swing.ImageIcon;

import br.ifpr.paranavai.jogo.modelo.Base;

public class Meteorito extends Base {
    private static final String IMAGEMINIMIGO = "recursos\\Sprites\\Inimigos\\meteorito.png";

    public Meteorito(int posicaoEmX, int posicaoEmY) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVelocidadeInicial(4);
        super.setVelocidade(super.getVelocidadeInicial());
        super.setVisibilidade(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(IMAGEMINIMIGO);
        super.setImagem(carregador.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmY((int) (super.getPosicaoEmY() + super.getVelocidadeInicial()));
        if (super.getPosicaoEmY() > super.getTelaTamanho().ALTURA_TELA) {
            super.setVisibilidade(false);
        }
    }
}
