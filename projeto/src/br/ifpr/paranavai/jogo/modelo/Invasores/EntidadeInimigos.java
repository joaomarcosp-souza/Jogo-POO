package br.ifpr.paranavai.jogo.modelo.Invasores;

import java.awt.Image;
import java.awt.Rectangle;

import br.ifpr.paranavai.principal.TamanhoTela;

public abstract class EntidadeInimigos {
    // POSIÇÃO DOS INIMIGOS NA TELA
    protected int posicaoEmX;
    protected int posicaoEmY;
    // INFORMAÇÕES DAS IMAGENS
    //
    protected Image imagem;
    protected int larguraImagem, alturaImagem;
    // IMG EXPLOSAO
    protected Image imagemExplosao;
    protected int larguraImagemExplosao, alturaImagemExplosao;
    protected boolean visibilidade;
    // STATUS DOS INIMIGOS
    protected double velocidade;
    protected int inimigosvida;
    //INSTANCIA
    private TamanhoTela tamanhoTela;

    // CONSTRUTOR
    public EntidadeInimigos() {
        this.visibilidade = true;

        // INICIANDO
        tamanhoTela = new TamanhoTela();
        tamanhoTela.carregar();
    }

    // MÉTODOS ABSTRATO BASICO PARA 'CARREGAR' É 'ATUALIZAR'
    public abstract void carregar();

    public abstract void atualizar();

    // CAIXA DE COLISÃO
    public Rectangle getBounds() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
    }

    // GETTERS E SETTERS DA ENTIDADE
    public int getPosicaoEmX() {
        return posicaoEmX;
    }

    public void setPosicaoEmX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPosicaoEmY() {
        return posicaoEmY;
    }

    public void setPosicaoEmY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
    }

    public int getLarguraImagem() {
        return larguraImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

    public int getAlturaImagem() {
        return alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }

    public int getInimigosvida() {
        return inimigosvida;
    }

    public void setInimigosvida(int inimigosvida) {
        this.inimigosvida = inimigosvida;
    }

    public TamanhoTela getTamanhoTela() {
        return tamanhoTela;
    }

    public void setTamanhoTela(TamanhoTela tamanhoTela) {
        this.tamanhoTela = tamanhoTela;
    }

    

}
