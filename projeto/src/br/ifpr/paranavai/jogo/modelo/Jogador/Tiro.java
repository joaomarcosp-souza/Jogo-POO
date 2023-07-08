package br.ifpr.paranavai.jogo.modelo.Jogador;

import javax.swing.ImageIcon;

public class Tiro extends EntidadeJogador {

    private static final int VELOCIDADE_TIRO = 4;
    private static final String IMAGEM_TIRO = "recursos\\Sprites\\Tiros\\tiro.png";

    public Tiro(int posicaoEmX, int posicaoEmY) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVisibilidade(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(IMAGEM_TIRO);
        super.setImagem(carregador.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + VELOCIDADE_TIRO);
        if (super.getPosicaoEmX() > getTelaTamanho().LARGURA_TELA) {
            super.setVisibilidade(false);
        }
    }
}
