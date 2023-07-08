package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Controles extends EntidadeTelas {
    // VARIAVEIS PARA CORRIGIR A POSIÇÃO DE ALGUNS ITENS
    private int offSetBotaoX = 3;
    private int offSetBotaoY = 5;
    private int offsetTituloX = 5;
    // TEXTOS DA CLASSE
    private static final String TEXTO_BOTAO = "ESC";
    private static final String TITULO = "CONTROLE";
    // CAMINHO IMAGEM DE FUNDO
    private static final String IMAGEM_FUNDO = "recursos\\Sprites\\Fundos\\FundoMenu.jpg";
    // FONTES
    private Font FONTE_TITULOS = getPIXEL().deriveFont(Font.BOLD, super.getTituloTamanho());
    private Font FONTE_BOTAO = getPIXEL().deriveFont(Font.BOLD, super.getTamanhoFonteBotao());

    public Controles() {
        super.setVisibilidade(false);
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(IMAGEM_FUNDO);
        super.setImagem(carregando.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        super.setImagem(super.getImagem().getScaledInstance(getTelaTamanho().LARGURA_TELA, getTelaTamanho().ALTURA_TELA,
                Image.SCALE_FAST));
    };

    @Override
    public void titulo(Graphics g) {
        g.setColor(super.getCorAmarela());
        g.setFont(FONTE_TITULOS);
        // CRENTALIZA VERTICALMENTE
        FontMetrics fm = g.getFontMetrics();
        int tituloLargura = fm.stringWidth(TITULO); // PEGA O TAMANHO DA STRING
        int posicaoTituloX = (getTelaTamanho().LARGURA_TELA - tituloLargura) / 2;
        g.setColor(Color.WHITE);
        g.drawString(TITULO, (posicaoTituloX + offsetTituloX), super.getPosicaoTituloY());
        g.setColor(getCorAmarela());
        g.drawString(TITULO, posicaoTituloX, super.getPosicaoTituloY());
    }

    @Override
    public void menu(Graphics g) {
        g.setFont(FONTE_BOTAO);
        FontMetrics fm = g.getFontMetrics();
        int posicaoBotaoX = (getBotao().x + offSetBotaoX) + (super.getBotaoLargura() - fm.stringWidth(TEXTO_BOTAO)) / 2;
        int posicaoBotaoY = (getBotao().y + offSetBotaoY) + (super.getBotaoAltura() - fm.getHeight()) / 2
                + fm.getAscent();
        g.setColor(getCorAmarela());
        g.fillRect(getBotao().x, getBotao().y, super.getBotaoLargura(), super.getBotaoAltura());
        g.setColor(Color.WHITE);
        g.drawString(TEXTO_BOTAO, posicaoBotaoX, posicaoBotaoY);
    }
}
