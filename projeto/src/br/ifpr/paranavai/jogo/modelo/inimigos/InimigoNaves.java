package br.ifpr.paranavai.jogo.modelo.inimigos;

import javax.swing.ImageIcon;

public class InimigoNaves extends EntidadeInimigo{
    // ATRIBUTO 'UNICO DO INIMIGO'
    private int VIDASINIMIGOS = 2;

    public InimigoNaves(int posicaoEmX, int posicaoEmY) {
        this.posicaoEmX = posicaoEmX;
        this.posicaoEmY = posicaoEmY;
        this.VELOCIDADE = 3;
        this.visibilidade = true;
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\sprites_inimigos\\tfighter.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
    }

    @Override
    public void atualizar() {
        this.posicaoEmX -= VELOCIDADE;
        if (this.posicaoEmX < -50) {
            visibilidade = false;
        }
    }

    // GETTERS E SETTER DA CLASSE INIMIGA NAVE
    public int getVIDASINIMIGOS() {
        return VIDASINIMIGOS;
    }

    public void setVIDASINIMIGOS(int vIDASINIMIGOS) {
        VIDASINIMIGOS = vIDASINIMIGOS;
    }
}
