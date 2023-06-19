package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Image;

import javax.swing.ImageIcon;;

public class Tela_Historico {
    private Image imagem_historico;
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem, alturaImagem;
    private boolean historico_visibilidade;

    public Tela_Historico() {
        this.historico_visibilidade = true;
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\fundo_menu.jpg");
        this.imagem_historico = carregando.getImage();
        this.larguraImagem = this.imagem_historico.getWidth(null);
        this.alturaImagem = this.imagem_historico.getHeight(null);
    }

    public boolean isHistorico_visibilidade() {
        return historico_visibilidade;
    }

    public void setHistorico_visibilidade(boolean historico_visibilidade) {
        this.historico_visibilidade = historico_visibilidade;
    }

}
