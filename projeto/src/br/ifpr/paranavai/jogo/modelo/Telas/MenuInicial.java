package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class MenuInicial extends TelasBase {
    
    private Image imagemTitulo;
    // VARIAVEIS DE AJUSTE
    private static final int ESPACAMENTO_OPCOES_Y = 50;
    // OPCÕES DO MENU
    private static final String MODO_ONDAS = "MODO ONDAS";
    private static final String TELA_CONTROLES = "CONTROLES";
    private static final String TELA_HISTORICO = "HISTORICO";
    private static final String MODO_INFINITO = "MODO INFINITO";
    // CAMINHO IMAGEM DE FUNDO
    private static final String FUNDO_GIF = "recursos\\Sprites\\Fundos\\FundoMenu.gif";
    private static final String TITULO_IMAGEM = "recursos\\Sprites\\Fundos\\MenuPrincipal.png";
    // FONTES
    private final Font FONTE_MENU = super.getPixel().deriveFont(Font.BOLD, super.getMenuTamanho());

    public MenuInicial() {
        setVisibilidade(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(FUNDO_GIF);
        setImagem(carregando.getImage());
        setLarguraImagem(getImagem().getWidth(null));
        setAlturaImagem(getImagem().getHeight(null));
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        setImagem(getImagem().getScaledInstance(getTelaTamanho().LARGURA_TELA, getTelaTamanho().ALTURA_TELA,
                Image.SCALE_FAST));

        // IMAGEM DO TITULO
        ImageIcon carregandoTitulo = new ImageIcon(TITULO_IMAGEM);
        this.imagemTitulo = carregandoTitulo.getImage();
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        this.imagemTitulo = this.imagemTitulo.getScaledInstance(getTelaTamanho().LARGURA_TELA,
                getTelaTamanho().ALTURA_TELA,
                Image.SCALE_FAST);
    }

    public void conteudo(Graphics g) {
        g.drawImage(super.getImagem(), 0, 0, null);
        g.drawImage(this.imagemTitulo, 0, 0, null);

        g.setFont(FONTE_MENU);
        FontMetrics fm = g.getFontMetrics();
        // OPÇÃO PARA A TELA 'MODO FASES'
        int posicaoX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(MODO_ONDAS)) / 2;
        int posicaoY = getTelaTamanho().ALTURA_TELA - 200;
        if (super.getCursor() == 0) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(MODO_ONDAS, posicaoX, posicaoY);
        if (super.getCursor() == 0) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoX - cursorWidth, posicaoY);
        } // FIM MODO FASE

        // OPÇÃO PARA A TELA 'MODO INFINITO'
        int infinitoPosX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(MODO_INFINITO)) / 2;
        int infinitoPosY = posicaoY + ESPACAMENTO_OPCOES_Y;
        if (super.getCursor() == 1) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(MODO_INFINITO, infinitoPosX, infinitoPosY);
        if (super.getCursor() == 1) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", infinitoPosX - cursorWidth, infinitoPosY);
        }

        // OPÇÃO PARA A TELA 'CONTROLES'
        int controlePosX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(TELA_CONTROLES)) / 2;
        int controlePosY = infinitoPosY + ESPACAMENTO_OPCOES_Y;
        if (super.getCursor() == 2) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(TELA_CONTROLES, controlePosX, controlePosY);
        if (super.getCursor() == 2) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", controlePosX - cursorWidth, controlePosY);
        }

        // OPÇÃO PARA A TELA 'HISTORICO'
        int historicoPosX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(TELA_HISTORICO)) / 2;
        int historicoPosY = controlePosY + ESPACAMENTO_OPCOES_Y;
        if (super.getCursor() == 3) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(TELA_HISTORICO, historicoPosX, historicoPosY);
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
