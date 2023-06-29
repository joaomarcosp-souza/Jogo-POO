package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class MenuInicial extends TelasEntidade{
    private int cursor = 0;

    public MenuInicial() {
        this.visibilidade = true;
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
        // CARREGA FUNDO
        g.drawImage(imagem, 0, 0, null);
        // CONFIGURAÇÕES FONT
        String titulo = "STAR WARS";
        String subtitulo = "INVADERS";
        Font fonte = pixel;
        fonte = fonte.deriveFont(Font.BOLD, 85);
        g.setFont(fonte);
        // CRENTALIZA VERTICALMENTE
        FontMetrics fm = g.getFontMetrics();
        int tituloWidth = fm.stringWidth(titulo); // PEGA O TAMANHO DA STRING
        int subtituloWidth = fm.stringWidth(subtitulo); // PEGA O TAMANHO DO SUBTITULO
        int x = (alturaImagem - tituloWidth) / 2;
        int y = 100 + (larguraImagem - fm.getHeight()) / 2;
        // COR DO TITULO 'STAR WARS - BRANCO'
        g.setColor(Color.WHITE);
        g.drawString(titulo, (x + 5), y - 280);
        g.drawString(subtitulo, x + (tituloWidth - subtituloWidth) / 2, y - 200);
        // COR DO SUBTITULO 'INVADERS - BRANCO'
        g.setColor(Color.WHITE);
        g.drawString(subtitulo, ((x + 5) + (tituloWidth - subtituloWidth) / 2), y - 200);
        // COR DO TITULO 'STAR WARS - AMARELO'
        g.setColor(new Color(255, 209, 70));
        g.drawString(titulo, x, y - 280);
    }

    @Override
    public void menu(Graphics g) {
        String modo_fases = "MODO FASES";
        String modo_infinito = "MODO INFINITO";
        String tela_controles = "CONTROLES";
        String tela_historico = "HISTORICO";

        Font estilo_menu = pixel.deriveFont(Font.BOLD, 50);
        g.setFont(estilo_menu);
        FontMetrics fm = g.getFontMetrics();
        int arruma_posicao = 50;

        // OPÇÃO PARA A TELA 'MODO FASES'
        int modo_fases_Width = fm.stringWidth(modo_fases);
        int posicaoX_fase = (alturaImagem - modo_fases_Width) / 2;
        int posicaoY_fase = 450;
        if (cursor == 0) {
            g.setColor(new Color(255, 209, 70));
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(modo_fases, posicaoX_fase, posicaoY_fase);
        if (cursor == 0) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoX_fase - cursorWidth, posicaoY_fase);
        } // FIM MODO FASE

        // OPÇÃO PARA A TELA 'MODO INFINITO'
        int modo_infinito_Width = fm.stringWidth(modo_infinito);
        int posicaoX_infinito = (alturaImagem - modo_infinito_Width) / 2;
        int posicaoY_infinito = posicaoY_fase + arruma_posicao;
        if (cursor == 1) {
            g.setColor(new Color(255, 209, 70));
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(modo_infinito, posicaoX_infinito, posicaoY_infinito);
        if (cursor == 1) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoX_infinito - cursorWidth, posicaoY_infinito);
        } // FIM

        // OPÇÃO PARA A TELA 'CONTROLES'
        int controles_Width = fm.stringWidth(tela_controles);
        int posicaoX_controles = (alturaImagem - controles_Width) / 2;
        int posicaoY_controles = posicaoY_infinito + arruma_posicao;
        if (cursor == 2) {
            g.setColor(new Color(255, 209, 70));
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(tela_controles, posicaoX_controles, posicaoY_controles);
        if (cursor == 2) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoX_controles - cursorWidth, posicaoY_controles);
        }// FIM

        // OPÇÃO PARA A TELA 'HISTORICO'
        int historico_Width = fm.stringWidth(tela_historico);
        int posicaoX_historico = (alturaImagem - historico_Width) / 2;
        int posicaoY_historico = posicaoY_controles + arruma_posicao;
        if (cursor == 3) {
            g.setColor(new Color(255, 209, 70));
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(tela_historico, posicaoX_historico, posicaoY_historico);
        if (cursor == 3) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoX_historico - cursorWidth, posicaoY_historico);
        }
        g.dispose();
    }

    public void tecla_menu(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        if (tecla == KeyEvent.VK_UP) {
            cursor--;
            if (cursor < 0) {
                cursor = 3;
            }
        }
        if (tecla == KeyEvent.VK_DOWN) {
            cursor++;
            if (cursor > 3) {
                cursor = 0;
            }
        }
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }
    
}
