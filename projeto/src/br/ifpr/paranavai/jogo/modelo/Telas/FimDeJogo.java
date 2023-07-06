package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;

public class FimDeJogo extends EntidadeTelas {

    private int TITULOSIZE = 120;

    

    // CONSTRUTOR
    public FimDeJogo() {
        this.visibilidade = false;
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Sprites\\Fundos\\FundoMenu.jpg");
        this.imagem = carregando.getImage();
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        this.imagem = this.imagem.getScaledInstance(
                telaTamanho.getLARGURATELA(), telaTamanho.getALTURATELA(), Image.SCALE_FAST);
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    };

    @Override
    public void titulo(Graphics g) {
        g.drawImage(imagem, 0, 0, null);
        String tituloPricipal = "GAME";
        String SubTitulo = "OVER";
        int OFFSETY = 100;

        g.setFont(pixel.deriveFont(Font.PLAIN, TITULOSIZE));
        FontMetrics fm = g.getFontMetrics();
        int tituloPricipalX = (telaTamanho.getLARGURATELA() - fm.stringWidth(tituloPricipal)) / 2;
        int subTituloX = (telaTamanho.getLARGURATELA() - fm.stringWidth(SubTitulo)) / 2;
        // EFEITO PARA O TITULO
        g.setColor(Color.WHITE);
        g.drawString(tituloPricipal, tituloPricipalX + 5, posicaoTituloY);
        // TITULO PRINCIPAL
        g.setColor(getCorAmarela());
        g.drawString(tituloPricipal, tituloPricipalX, (posicaoTituloY + 5));
        g.setColor(Color.WHITE);
        g.drawString(SubTitulo, subTituloX, (posicaoTituloY + OFFSETY));
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
        int posicaoEmX = (telaTamanho.getLARGURATELA() - fm.stringWidth(frase)) / 2;
        int posicaoEmY = telaTamanho.getALTURATELA() - OFFSETY;
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