package br.ifpr.paranavai.jogo.modelo.Jogador;

import javax.swing.ImageIcon;

public class SuperTiro extends EntidadeJogador {

    private int angulo;
    private static final int VELOCIDADETIRO = 4;
    private static final String IMAGEM_TIROSUPER = "recursos\\Sprites\\Tiros\\superTiro.gif";

    public SuperTiro(int posicaoEmX, int posicaoEmY, int angulo) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVisibilidade(true);
        this.angulo = angulo;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(IMAGEM_TIROSUPER);
        super.setImagem(carregador.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        // CONVERTE A VARIAVEL 'ANGULO' PARA RADIANOS
        double radianos = Math.toRadians(angulo);
        // CALCULANDO AS VELOCIDADES DE 'X' E 'Y' COM BASE NO ANGULO
        int velocidadeEmX = (int) (VELOCIDADETIRO * Math.cos(radianos));
        int velocidadeEmY = (int) (VELOCIDADETIRO * Math.sin(radianos));

        super.setPosicaoEmX(super.getPosicaoEmX() + velocidadeEmX);
        super.setPosicaoEmY(super.getPosicaoEmY() + velocidadeEmY);

        // VERIFICA O TAMANHO DA BORDA, PRA REMOVER OS TIROS POSTERIORMENTE
        if (super.getPosicaoEmX() > super.getTelaTamanho().LARGURA_TELA
                || super.getPosicaoEmY() > getTelaTamanho().ALTURA_TELA) {
            super.setVisibilidade(false);
        }
    }
}
