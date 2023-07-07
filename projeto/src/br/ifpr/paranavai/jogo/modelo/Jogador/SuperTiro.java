package br.ifpr.paranavai.jogo.modelo.Jogador;

import javax.swing.ImageIcon;

public class SuperTiro extends EntidadeJogador {
    private static final int VELOCIDADETIRO = 10;

    public SuperTiro(int posicaoEmX, int posicaoEmY) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVisibilidade(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\Sprites\\Tiros\\super.png");
        super.setImagem(carregador.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADETIRO);
        if (super.getPosicaoEmX() > telaTamanho.getLARGURATELA()) {
            super.setVisibilidade(false);
        }
    }
}
