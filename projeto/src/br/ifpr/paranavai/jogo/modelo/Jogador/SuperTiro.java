package br.ifpr.paranavai.jogo.modelo.Jogador;

import javax.swing.ImageIcon;
import br.ifpr.paranavai.jogo.modelo.Base;

public class SuperTiro extends Base {

    private int angulo;
    private static final int VELOCIDADE_SUPER = 8;
    private static final String IMAGEM_SUPER = "recursos\\Sprites\\Tiros\\superTiro.gif";

    public SuperTiro(int posicaoEmX, int posicaoEmY, int angulo) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVisibilidade(true);
        this.angulo = angulo;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(IMAGEM_SUPER);
        super.setImagem(carregador.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        // CONVERTE A VARIAVEL 'ANGULO' PARA RADIANOS
        double radianos = Math.toRadians(angulo);
        // CALCULANDO AS VELOCIDADES DE 'X' E 'Y' COM BASE NO ANGULO
        int velocidadeEmX = (int) (VELOCIDADE_SUPER * Math.cos(radianos));
        int velocidadeEmY = (int) (VELOCIDADE_SUPER * Math.sin(radianos));

        super.setPosicaoEmX(super.getPosicaoEmX() + velocidadeEmX);
        super.setPosicaoEmY(super.getPosicaoEmY() + velocidadeEmY);

        // VERIFICA O TAMANHO DA BORDA, PRA REMOVER OS TIROS POSTERIORMENTE
        if (super.getPosicaoEmX() > super.getTelaTamanho().LARGURA_TELA
                || super.getPosicaoEmY() > getTelaTamanho().ALTURA_TELA) {
            super.setVisibilidade(false);
        }
    }
}
