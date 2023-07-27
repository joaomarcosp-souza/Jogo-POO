package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class MenuInicial extends EntidadeTelas {
    // OPÇÕES DO MENU
    private static final int offSetPosX = 5;
    private static final int offSubTituloY = 80;
    private static final int espacamentoTituloY = 50;
    // TEXTOS DA CLASSE
    private static final String MODOFASES = "MODO FASES";
    private static final String MODOINFINITO = "MODO INFINITO";
    private static final String CONTROLETELA = "CONTROLES";
    private static final String HISTORICOTELA = "HISTORICO";
    // TITULO MENU
    private static final String TITULO = "STAR WARS";
    private static final String SUBTITULO = "INVADERS";
    // FONTES
    private Font FONTE_TITULOS = getPIXEL().deriveFont(Font.BOLD, super.getTituloTamanho());
    private Font FONTE_MENU = getPIXEL().deriveFont(Font.BOLD, super.getMenuTamanho());
    // CAMINHO IMAGEM DE FUNDO
    private static final String IMAGEM_FUNDO = "recursos\\Sprites\\Fundos\\FundoMenu.gif";

    public MenuInicial() {
        setVisibilidade(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(IMAGEM_FUNDO);
        setImagem(carregando.getImage());
        setLarguraImagem(getImagem().getWidth(null));
        setAlturaImagem(getImagem().getHeight(null));
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        setImagem(getImagem().getScaledInstance(getTelaTamanho().LARGURA_TELA, getTelaTamanho().ALTURA_TELA,
                Image.SCALE_FAST));
    }

    public void titulo(Graphics g) {
        g.drawImage(getImagem(), 0, 0, null);
        g.setFont(FONTE_TITULOS);
        // CENTRALIZA VERTICALMENTE
        FontMetrics fm = g.getFontMetrics();
        int posicaoTituloX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(TITULO)) / 2;
        int subTituloLargura = fm.stringWidth(SUBTITULO);
        // COR DO TITULO 'STAR WARS - BRANCO'
        g.setColor(Color.WHITE);
        g.drawString(TITULO, (posicaoTituloX + offSetPosX), getPosicaoTituloY());
        // COR DO TITULO 'STAR WARS - AMARELO'
        g.setColor(getCorAmarela());
        g.drawString(TITULO, posicaoTituloX, getPosicaoTituloY());
        // TITULO 'INVADERS'
        g.setColor(Color.WHITE);
        g.drawString(SUBTITULO, posicaoTituloX + (fm.stringWidth(TITULO) - subTituloLargura) / 2,
                (getPosicaoTituloY() + offSubTituloY));
    }

    public void menu(Graphics g) {
        g.setFont(FONTE_MENU);
        FontMetrics fm = g.getFontMetrics();
        // OPÇÃO PARA A TELA 'MODO FASES'
        int posicaoX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(MODOFASES)) / 2;
        int posicaoY = getTelaTamanho().ALTURA_TELA - 200;
        if (super.getCursor() == 0) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(MODOFASES, posicaoX, posicaoY);
        if (super.getCursor() == 0) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoX - cursorWidth, posicaoY);
        } // FIM MODO FASE

        // OPÇÃO PARA A TELA 'MODO INFINITO'
        int infinitoPosX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(MODOINFINITO)) / 2;
        int infinitoPosY = posicaoY + espacamentoTituloY;
        if (super.getCursor() == 1) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(MODOINFINITO, infinitoPosX, infinitoPosY);
        if (super.getCursor() == 1) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", infinitoPosX - cursorWidth, infinitoPosY);
        }

        // OPÇÃO PARA A TELA 'CONTROLES'
        int controlePosX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(CONTROLETELA)) / 2;
        int controlePosY = infinitoPosY + espacamentoTituloY;
        if (super.getCursor() == 2) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(CONTROLETELA, controlePosX, controlePosY);
        if (super.getCursor() == 2) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", controlePosX - cursorWidth, controlePosY);
        }

        // OPÇÃO PARA A TELA 'HISTORICO'
        int historicoPosX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(HISTORICOTELA)) / 2;
        int historicoPosY = controlePosY + espacamentoTituloY;
        if (super.getCursor() == 3) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(HISTORICOTELA, historicoPosX, historicoPosY);
        if (super.getCursor() == 3) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", historicoPosX - cursorWidth, historicoPosY);
        }
    }

    public void controleMenu(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();
        // CONTROLA A POSIÇÃO DO CURSOR
        if (tecla == KeyEvent.VK_UP) {
            super.setCursor(getCursor() - 1);
            if (super.getCursor() < 0) {
                super.setCursor(3);
            }
        }
        if (tecla == KeyEvent.VK_DOWN) {
            super.setCursor(getCursor() + 1);
            if (super.getCursor() > 3) {
                super.setCursor(0);
            }
        }
    }
}
