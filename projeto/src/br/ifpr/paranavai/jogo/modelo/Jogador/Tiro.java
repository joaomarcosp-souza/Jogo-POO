package br.ifpr.paranavai.jogo.modelo.Jogador;

import javax.swing.ImageIcon;

public class Tiro extends EntidadeJogador {
    private static final int VELOCIDADETIRO = 8;

    public Tiro(int posicaoEmX, int posicaoEmY) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVisibilidade(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\Sprites\\Tiros\\tiro.png");
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
