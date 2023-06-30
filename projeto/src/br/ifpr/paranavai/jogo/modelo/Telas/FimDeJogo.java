package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class FimDeJogo extends TelasEntidade {

    private static final int larguraTela = 1300;
    private static final int alturaTela = 600;

    public FimDeJogo() {
        this.visibilidade = false;
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\FundoMenu.jpg");
        this.imagem = carregando.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    @Override
    public void titulo(Graphics g) {
        g.drawImage(imagem, 0, 0, null);
        int alturaTela = 400;
        String tituloPricipal = "GAME";
        String tituloPricipal_2 = "GAME";
        String SubTitulo = "OVER";

        g.setFont(pixel.deriveFont(Font.PLAIN, 120));

        FontMetrics fm = g.getFontMetrics();
        int tituloPricipalX = (larguraTela - fm.stringWidth(tituloPricipal)) / 2;
        int subTituloX = (larguraTela - fm.stringWidth(SubTitulo)) / 2;
        int y = (alturaTela - fm.getHeight()) / 2;

        g.setColor(Color.white);
        g.drawString(tituloPricipal_2, tituloPricipalX + 5, y);
        g.setColor(getCorAmarela());
        g.drawString(tituloPricipal, tituloPricipalX, y);
        g.setColor(Color.white);
        g.drawString(SubTitulo, subTituloX, y + 100);
    }

    @Override
    public void menu(Graphics g) {
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
}