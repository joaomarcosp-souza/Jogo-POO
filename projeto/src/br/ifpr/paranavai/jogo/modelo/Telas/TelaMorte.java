package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

import java.awt.event.KeyEvent;

public class TelaMorte {
    private boolean telaMorteVisibilidade;
    private int cursor;
    private Image imagem;
    private int alturaImagem, larguraImagem;
    private static final int larguraTela = 1300;
    private static final int alturaTela = 600;
    private Font pixel = null;

    public TelaMorte() {
        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        this.telaMorteVisibilidade = false;
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\fundo_menu.jpg");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    public void mensagem(Graphics t) {
        t.drawImage(imagem, 0, 0, null);
        int alturaTela = 400;
        String tituloPricipal = "GAME";
        String tituloPricipal_2 = "GAME";
        String SubTitulo = "OVER";

        t.setFont(pixel.deriveFont(Font.PLAIN, 120));

        FontMetrics fm = t.getFontMetrics();
        int tituloPricipalX = (larguraTela - fm.stringWidth(tituloPricipal)) / 2;
        int subTituloX = (larguraTela - fm.stringWidth(SubTitulo)) / 2;
        int y = (alturaTela - fm.getHeight()) / 2;

        t.setColor(Color.white);
        t.drawString(tituloPricipal_2, tituloPricipalX + 5, y);
        t.setColor(new Color(255, 209, 70));
        t.drawString(tituloPricipal, tituloPricipalX, y);
        t.setColor(Color.white);
        t.drawString(SubTitulo, subTituloX, y + 100);
    }

    public void opcoesMenu(Graphics g) {
        String frase = "JOGAR NOVAMENTE?";
        String sim = "SIM";
        String nao = "NAO";

        g.setFont(pixel.deriveFont(Font.PLAIN, 35));
        FontMetrics fm = g.getFontMetrics();
        // COMEÇO DA FRASE
        int posicaoFraseEmX = (larguraTela - fm.stringWidth(frase)) / 2;
        int posicaoFraseEmY = alturaTela - 100;
        g.drawString(frase, posicaoFraseEmX - 5, posicaoFraseEmY);
        // DECLARAÇÃO DAS OPÇÕES
        int simWidth = fm.stringWidth(sim);
        int naoWidth = fm.stringWidth(nao);
        int totalWidth = simWidth + naoWidth + 100;
        // COMEÇO DA OPÇÃO 'SIM'
        int posicaoSimEmX = (larguraTela - totalWidth) / 2;
        int posicaoSimEmY = posicaoFraseEmY + 100;
        if (cursor == 0) {
            g.setColor(new Color(255, 209, 70));
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(sim, posicaoSimEmX, posicaoSimEmY);
        if (cursor == 0) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoSimEmX - cursorWidth, posicaoSimEmY);
        }
        // COMEÇO DA OPÇÃO 'NÃO'
        int posicaoNaoEmX = posicaoSimEmX + simWidth + 100;
        int posicaoNaoEmY = posicaoSimEmY;
        if (cursor == 1) {
            g.setColor(new Color(255, 209, 70));
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(nao, posicaoNaoEmX, posicaoNaoEmY);
        if (cursor == 1) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoNaoEmX - cursorWidth, posicaoNaoEmY);
        }
    }

    public void menuMorto(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        if (tecla == KeyEvent.VK_LEFT) {
            cursor--;
            if (cursor < 0) {
                cursor = 1;
            }
        }
        if (tecla == KeyEvent.VK_RIGHT) {
            cursor++;
            if (cursor > 1) {
                cursor = 0;
            }
        }
    }

    public boolean isTelaMorteVisibilidade() {
        return telaMorteVisibilidade;
    }

    public void setTelaMorteVisibilidade(boolean telaMorteVisibilidade) {
        this.telaMorteVisibilidade = telaMorteVisibilidade;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public int getAlturaImagem() {
        return alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }

    public int getLarguraImagem() {
        return larguraImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

}