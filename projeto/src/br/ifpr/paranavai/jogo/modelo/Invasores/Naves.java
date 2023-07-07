package br.ifpr.paranavai.jogo.modelo.Invasores;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;

public class Naves extends EntidadeInimigos {

    public Naves(int posicaoEmX, int posicaoEmY, int INIMIGOSVIDA) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.inimigosvida = INIMIGOSVIDA;
        this.velocidade = 2;
        this.visibilidade = true;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\Sprites\\Inimigos\\tfighter.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmX -= velocidade;
        if (this.posicaoEmX < -50) {
            visibilidade = false;
        }
    }

    // MÉTODO DE VIDA NAVES
    public void vidas(Graphics g) {
        int tamanhoCubo = 10; // TAMANHO DO CUBO
        int espacamentoCubo = 5; // ESPAÇAMENTO ENTRE OS CUBOS
        // CALCULANDO A POSIÇÃO DE CADA CUBO
        int maximoVidas = inimigosvida;
        int posicaoCuboX = posicaoEmX - ((tamanhoCubo + espacamentoCubo) * maximoVidas) / 2;
        int posicaoCuboY = posicaoEmY + alturaImagem + 5;
        // UM CUBO PARA CADA VIDA
        for (int i = 0; i < maximoVidas; i++) {
            // CALCULA A POSIÇÃO DO CUBO ATUAL
            int cuboPosX = posicaoCuboX + (tamanhoCubo + espacamentoCubo) * i;
            // DESENHADO O CUBO
            g.setColor(Color.RED);
            g.fillRect((cuboPosX + 44), posicaoCuboY, tamanhoCubo, tamanhoCubo);
        }
    }
}
