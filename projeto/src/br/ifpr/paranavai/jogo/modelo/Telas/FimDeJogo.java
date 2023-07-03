package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class FimDeJogo extends TelasEntidade {

    private int TITULOSIZE = 120;

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
        int OFFSETY = 100;
        String tituloPricipal = "GAME";
        String tituloPricipal_2 = "GAME";
        String SubTitulo = "OVER";

        g.setFont(pixel.deriveFont(Font.PLAIN, TITULOSIZE));
        FontMetrics fm = g.getFontMetrics();
        int tituloPricipalX = (LARGURAJANELA - fm.stringWidth(tituloPricipal)) / 2;
        int subTituloX = (LARGURAJANELA - fm.stringWidth(SubTitulo)) / 2;

        g.setColor(Color.white);
        g.drawString(tituloPricipal_2, tituloPricipalX + 5, Y + 5);
        g.setColor(getCorAmarela());
        g.drawString(tituloPricipal, tituloPricipalX, Y + 5);
        g.setColor(Color.white);
        g.drawString(SubTitulo, subTituloX, Y + OFFSETY);
    }

    @Override
    public void menu(Graphics g) {
        String frase = "JOGAR NOVAMENTE?";
        String sim = "SIM";
        String nao = "NAO";
        int OFFSETY = 200;

        // COMEÇO DA FRASE
        g.setFont(pixel.deriveFont(Font.PLAIN, 35));
        FontMetrics fm = g.getFontMetrics();
        int posicaoEmX = (LARGURAJANELA - fm.stringWidth(frase)) / 2;
        int posicaoEmY = ALTURAJANELA - OFFSETY;
        g.drawString(frase, posicaoEmX, posicaoEmY);

        // Centralização das opções SIM e NÃO
        int fraseCenterX = posicaoEmX + fm.stringWidth(frase) / 2;
        int simWidth = fm.stringWidth(sim);
        int naoWidth = fm.stringWidth(nao);

        int totalWidth = simWidth + naoWidth;
        int posicaoXsim = fraseCenterX - totalWidth / 2;
        int posicaoYsim = posicaoEmY + 50;
        if (cursor == 0) {
            g.setColor(getCorAmarela());
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoXsim - cursorWidth, posicaoYsim);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(sim, posicaoXsim, posicaoYsim);
        
        // CONFIGURAÇÕES OPC 'NÃO'
        int posicaoXnao = posicaoXsim + simWidth + 25; // Adiciona um espaço extra entre as opções
        int posicaoYnao = posicaoYsim;
        if (cursor == 1) {
            g.setColor(getCorAmarela());
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoXnao - cursorWidth, posicaoYnao);
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(nao, posicaoXnao, posicaoYnao);
    }

    public void controleMenu(KeyEvent teclado) {
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