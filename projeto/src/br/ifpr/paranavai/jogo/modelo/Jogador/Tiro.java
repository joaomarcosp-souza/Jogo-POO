package br.ifpr.paranavai.jogo.modelo.Jogador;

import javax.swing.ImageIcon;

public class Tiro extends EntidadeJogador {

    private static final int VELOCIDADETIRO = 8;
    private static final String IMAGEMTIRO = "recursos\\Sprites\\Tiros\\tiroNormal.gif";

    public Tiro(int posicaoEmX, int posicaoEmY) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVisibilidade(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(IMAGEMTIRO);
        super.setImagem(carregador.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADETIRO);
        if (super.getPosicaoEmX() > getTelaTamanho().LARGURA_TELA) {
            super.setVisibilidade(false);
        }
    }
}
