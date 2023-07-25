package br.ifpr.paranavai.jogo.modelo.Invasores;

import javax.swing.ImageIcon;

public class Meteorito extends EntidadeInimigos {

    private double VELOCIDADEINICIAL = 4;
    private static final String IMAGEMNAVE = "recursos\\Sprites\\Inimigos\\meteorito.png";

    public Meteorito(int posicaoEmX, int posicaoEmY) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVelocidade(VELOCIDADEINICIAL);
        super.setVisibilidade(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(IMAGEMNAVE);
        super.setImagem(carregador.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmY((int) (super.getPosicaoEmY() + VELOCIDADEINICIAL));
        if (super.getPosicaoEmY() > super.getTamanhoTela().ALTURA_TELA) {
            super.setVisibilidade(false);
        }
    }
}
