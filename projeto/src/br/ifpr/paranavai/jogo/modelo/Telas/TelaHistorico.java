package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class TelaHistorico {
    private Image imagem_historico;
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem, alturaImagem;
    private boolean historico_visibilidade;

    public TelaHistorico() {
        this.historico_visibilidade = true;
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\fundo_menu.jpg");
        this.imagem_historico = carregando.getImage();
        this.larguraImagem = this.imagem_historico.getWidth(null);
        this.alturaImagem = this.imagem_historico.getHeight(null);
    }

    public void titulo_Historico(Graphics g) {
        g.drawImage(imagem_historico, 0, 0, null);
    }

    public boolean isHistorico_visibilidade() {
        return historico_visibilidade;
    }

    public void setHistorico_visibilidade(boolean historico_visibilidade) {
        this.historico_visibilidade = historico_visibilidade;
    }

    public Image getImagem_historico() {
        return imagem_historico;
    }

    public void setImagem_historico(Image imagem_historico) {
        this.imagem_historico = imagem_historico;
    }

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

    

}
