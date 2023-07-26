package br.ifpr.paranavai.jogo.modelo.Invasores;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;

public class Naves extends EntidadeInimigos {

    private double VELOCIDADEINICIAL = 4;
    private static final String IMAGEMINIMIGO = "recursos\\Sprites\\Inimigos\\tfighter.png";

    public Naves(int posicaoEmX, int posicaoEmY, int INIMIGOSVIDA) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setInimigosvida(INIMIGOSVIDA);
        super.setVelocidade(VELOCIDADEINICIAL);
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
        super.setPosicaoEmX((int) (super.getPosicaoEmX() - super.getVelocidade()));
        if (super.getPosicaoEmX() < -50) {
            super.setVisibilidade(false);
        }
    }

    // MÉTODO DE VIDA NAVES
    public void vidas(Graphics g) {
        int tamanhoCubo = 10; // TAMANHO DO CUBO
        int espacamentoCubo = 5; // ESPAÇAMENTO ENTRE OS CUBOS
        int offsetRetangulo = 44;
        // CALCULANDO A POSIÇÃO DE CADA CUBO
        int maximoVidas = super.getInimigosvida();
        int posicaoCuboX = super.getPosicaoEmX() - ((tamanhoCubo + espacamentoCubo) * maximoVidas) / 2;
        int posicaoCuboY = super.getPosicaoEmY() + super.getAlturaImagem() + 5;
        // UM CUBO PARA CADA VIDA
        for (int i = 0; i < maximoVidas; i++) {
            // CALCULA A POSIÇÃO DO CUBO ATUAL
            int cuboPosX = posicaoCuboX + (tamanhoCubo + espacamentoCubo) * i;
            // DESENHADO O CUBO
            g.setColor(Color.RED);
            g.fillRect((cuboPosX + offsetRetangulo), posicaoCuboY, tamanhoCubo, tamanhoCubo);
        }
    }
}
