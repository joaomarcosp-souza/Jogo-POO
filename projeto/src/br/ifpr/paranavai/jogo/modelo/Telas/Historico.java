package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Historico extends TelasBase {
    
    private Image imagemHistorico;
    // CAMINHO IMAGEM DE FUNDO
    private static final String IMAGEM_FUNDO = "recursos\\Sprites\\Fundos\\FundoTelas.jpg";
    private static final String IMAGEM_TITULO = "recursos\\Sprites\\Fundos\\TelaHistorico.png";

    public Historico() {
        super.setVisibilidade(false);
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(IMAGEM_FUNDO);
        super.setImagem(carregando.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
        super.setImagem(super.getImagem().getScaledInstance(super.getTelaTamanho().LARGURA_TELA,
                super.getTelaTamanho().ALTURA_TELA,
                Image.SCALE_FAST));

        // IMAGEM
        ImageIcon imgcontrole = new ImageIcon(IMAGEM_TITULO);
        this.imagemHistorico = imgcontrole.getImage();
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        this.imagemHistorico = this.imagemHistorico.getScaledInstance(getTelaTamanho().LARGURA_TELA,
                getTelaTamanho().ALTURA_TELA,
                Image.SCALE_FAST);
    }

    // CARREGANDO A IMAGEM DE FUNDO E A IMAGEM COM OS COMANDOS
    public void conteudo(Graphics g) {
        g.drawImage(super.getImagem(), 0, 0, null);
        g.drawImage(imagemHistorico, 0, 0, null);
    }
}
