package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;

public class FimDeJogo extends TelasBase {
    // OPÇÕES MENU
    private static final String OPCAO_SIM = "SIM";
    private static final String OPCAO_NAO = "NAO";
    private static final String MENSAGEM = "JOGAR NOVAMENTE?";
    // TITULO
    private static final String SUBTITULO = "OVER";
    private static final String TITULO_PRINCIPAL = "GAME";
    // FONTES
    private final Font FONTE_MENU = super.getPixel().deriveFont(Font.BOLD, super.getTamanhoFonte());
    private final Font FONTE_TITULOS = super.getPixel().deriveFont(Font.BOLD, super.getTituloTamanho());
    // CAMINHO IMAGEM DE FUNDO
    private static final String IMAGEM_FUNDO = "recursos\\Sprites\\Fundos\\TelaFimDeJogo.gif";

    public FimDeJogo() {
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

    public void titulo(Graphics g) {
        g.drawImage(super.getImagem(), 0, 0, null);
        g.setFont(FONTE_TITULOS);

        FontMetrics fm = g.getFontMetrics();
        int tituloPosX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(TITULO_PRINCIPAL)) / 2;
        int subTituloLargura = (fm.stringWidth(SUBTITULO));
        // EFEITO PARA O TITULO
        g.setColor(Color.WHITE);
        g.drawString(TITULO_PRINCIPAL, tituloPosX + 5, super.getPOSICAO_TITULO_Y());
        g.setColor(super.getCorAmarela());
        g.drawString(TITULO_PRINCIPAL, tituloPosX, (super.getPOSICAO_TITULO_Y()));
        // DESENHA O SUBTITULO
        g.setColor(Color.WHITE);
        g.drawString(SUBTITULO, tituloPosX + (fm.stringWidth(TITULO_PRINCIPAL) - subTituloLargura) / 2,
                (super.getPOSICAO_TITULO_Y() + 80));
    }

    public void menu(Graphics g) {
        int OFFSETY = 150;
        // COMEÇO DA FRASE
        g.setFont(FONTE_MENU);
        FontMetrics fm = g.getFontMetrics();
        int posicaoEmX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(MENSAGEM)) / 2;
        int posicaoEmY = getTelaTamanho().ALTURA_TELA - OFFSETY;
        g.drawString(MENSAGEM, posicaoEmX, posicaoEmY);
        int mensagemPosX = posicaoEmX + fm.stringWidth(MENSAGEM) / 2;

        // CENTRALIZA AS OPCÇÕES
        int simLargura = fm.stringWidth(OPCAO_SIM);
        int naoLargura = fm.stringWidth(OPCAO_NAO);
        int larguraTotal = simLargura + naoLargura;

        // 'SIM'
        int posicaoXsim = (mensagemPosX - larguraTotal / 2) - 10;
        int posicaoYsim = posicaoEmY + 40;
        if (super.getCursor() == 0) {
            g.setColor(getCorAmarela());
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoXsim - cursorWidth, posicaoYsim);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(OPCAO_SIM, posicaoXsim, posicaoYsim);

        // 'NÃO'
        int posicaoXnao = (posicaoXsim + simLargura) + 30;
        int posicaoYnao = posicaoYsim;
        if (super.getCursor() == 1) {
            g.setColor(getCorAmarela());
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoXnao - cursorWidth, posicaoYnao);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(OPCAO_NAO, posicaoXnao, posicaoYnao);
    }

    public void controleMenu(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        if (tecla == KeyEvent.VK_LEFT) {
            super.setCursor(super.getCursor() - 1);
            if (super.getCursor() < 0) {
                super.setCursor(1);
            }
        }
        if (tecla == KeyEvent.VK_RIGHT) {
            super.setCursor(super.getCursor() + 1);
            if (super.getCursor() > 1) {
                super.setCursor(0);
            }
        }
    }
}