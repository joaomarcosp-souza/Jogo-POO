package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Pausar extends TelasBase {
    
    private boolean pausado;
    // OPÇÕES MENU
    private static final String OPCAO_SIM = "SIM";
    private static final String OPCAO_NAO = "NAO";
    private static final String MENSAGEM_MENU = "VOLTAR AO JOGO?";
    // FONTES
    private static final String CAMINHO_IMAGEM = "recursos\\Sprites\\Fundos\\TelaPause.png";
    private Font FONTE_MENU = super.getPixel().deriveFont(Font.BOLD, super.getTamanhoFonte());

    public Pausar() {
        super.setVisibilidade(false);
        this.pausado = false;
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(CAMINHO_IMAGEM);
        super.setImagem(carregando.getImage());
        // RESIMENSIONA A IMG
        super.setImagem(super.getImagem().getScaledInstance(getTelaTamanho().LARGURA_TELA, getTelaTamanho().ALTURA_TELA,
                Image.SCALE_FAST));
    }

    public void menu(Graphics g) {
        int OFFSETY = 150;
        g.drawImage(super.getImagem(), 0, 0, null);
        // COMEÇO DA FRASE
        g.setColor(Color.WHITE);
        g.setFont(FONTE_MENU);
        FontMetrics fm = g.getFontMetrics();
        int posicaoEmX = (getTelaTamanho().LARGURA_TELA - fm.stringWidth(MENSAGEM_MENU)) / 2;
        int posicaoEmY = getTelaTamanho().ALTURA_TELA - OFFSETY;
        g.drawString(MENSAGEM_MENU, posicaoEmX, posicaoEmY);
        int mensagemPosX = posicaoEmX + fm.stringWidth(MENSAGEM_MENU) / 2;

        // CENTRALIZA AS OPCÇÕES
        int simLargura = fm.stringWidth(OPCAO_SIM);
        int naoLargura = fm.stringWidth(OPCAO_NAO);
        int larguraTotal = simLargura + naoLargura;

        // 'SIM'
        int posicaoXsim = (mensagemPosX - larguraTotal / 2) - 10;
        int posicaoYsim = posicaoEmY + 40;
        if (super.getCursor() == 0) {
            g.setColor(getCorAmarela());
            int cursorWidth = fm.stringWidth(" ");
            g.drawString(" ", posicaoXsim - cursorWidth, posicaoYsim);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(OPCAO_SIM, posicaoXsim, posicaoYsim);

        // 'NÃO'
        int posicaoXnao = (posicaoXsim + simLargura) + 30;
        int posicaoYnao = posicaoYsim;
        if (super.getCursor() == 1) {
            g.setColor(getCorAmarela());
            int cursorWidth = fm.stringWidth(" ");
            g.drawString(" ", posicaoXnao - cursorWidth, posicaoYnao);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(OPCAO_NAO, posicaoXnao, posicaoYnao);
    }

    public void menuPausado(KeyEvent teclado) {
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

    // GETTERS E SETTERS
    public boolean isPausado() {
        return pausado;
    }

    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }
}
