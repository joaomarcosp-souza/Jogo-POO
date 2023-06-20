package br.ifpr.paranavai.jogo.modelo.Telas;

import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Tela_Menu {
    private Image imagem_fundo;
    private int larguraImagem, alturaImagem;
    private int cursor = 0;
    private Font pixel = null;
    private Font broken = null;
    private boolean visibilidade_menu;

    public Tela_Menu() {
        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));
            broken = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));
            // DEFINE O TAMANHO DA FONTE DESEJADO

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        this.visibilidade_menu = true;
    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\fundo_menu.jpg");
        this.imagem_fundo = carregando.getImage();
        this.alturaImagem = this.imagem_fundo.getWidth(null);
        this.larguraImagem = this.imagem_fundo.getHeight(null);
    }

    public void titulo(Graphics t) {
        t.drawImage(imagem_fundo, 0, 0, null);
        //
        String titulo = "STAR WARS";
        String subtitulo = "INVADERS";
        Font fonte = pixel;
        fonte = fonte.deriveFont(Font.BOLD, 85);
        //
        t.setFont(fonte);
        // CRENTALIZA VERTICALMENTE
        FontMetrics fm = t.getFontMetrics();
        int tituloWidth = fm.stringWidth(titulo); // PEGA O TAMANHO DA STRING
        int subtituloWidth = fm.stringWidth(subtitulo); // PEGA O TAMANHO DO SUBTITULO
        int x = (alturaImagem - tituloWidth) / 2;
        int y = 100 + (larguraImagem - fm.getHeight()) / 2;
        // COR DO TITULO 'STAR WARS - BRANCO'
        t.setColor(Color.WHITE);
        t.drawString(titulo, (x + 5), y - 280);
        t.drawString(subtitulo, x + (tituloWidth - subtituloWidth) / 2, y - 200);
        // COR DO SUBTITULO 'INVADERS - BRANCO'
        t.setColor(Color.WHITE);
        t.drawString(subtitulo, ((x + 5) + (tituloWidth - subtituloWidth) / 2), y - 200);
        // COR DO TITULO 'STAR WARS - AMARELO'
        t.setColor(new Color(255, 209, 70));
        t.drawString(titulo, x, y - 280);
    }

    public void menu(Graphics m) {
        String modo_fases = "MODO FASES";
        String modo_infinito = "MODO INFINITO";
        String tela_controles = "CONTROLES";
        String tela_historico = "HISTORICO";

        Font estilo_menu = broken.deriveFont(Font.BOLD, 50);
        m.setFont(estilo_menu);
        FontMetrics fm = m.getFontMetrics();
        int arruma_posicao = 50;

        // OPÇÃO PARA A TELA 'MODO FASES'
        int modo_fases_Width = fm.stringWidth(modo_fases);
        int posicaoX_fase = (alturaImagem - modo_fases_Width) / 2;
        int posicaoY_fase = 450;
        if (cursor == 0) {
            m.setColor(new Color(255, 209, 70));
        } else {
            m.setColor(Color.WHITE);
        }
        m.drawString(modo_fases, posicaoX_fase, posicaoY_fase);
        if (cursor == 0) {
            int cursorWidth = fm.stringWidth(">");
            m.drawString(">", posicaoX_fase - cursorWidth, posicaoY_fase);
        } // FIM MODO FASE

        // OPÇÃO PARA A TELA 'MODO INFINITO'
        int modo_infinito_Width = fm.stringWidth(modo_infinito);
        int posicaoX_infinito = (alturaImagem - modo_infinito_Width) / 2;
        int posicaoY_infinito = posicaoY_fase + arruma_posicao;
        if (cursor == 1) {
            m.setColor(new Color(255, 209, 70));
        } else {
            m.setColor(Color.WHITE);
        }
        m.drawString(modo_infinito, posicaoX_infinito, posicaoY_infinito);
        if (cursor == 1) {
            int cursorWidth = fm.stringWidth(">");
            m.drawString(">", posicaoX_infinito - cursorWidth, posicaoY_infinito);
        } // FIM

        // OPÇÃO PARA A TELA 'CONTROLES'
        int controles_Width = fm.stringWidth(tela_controles);
        int posicaoX_controles = (alturaImagem - controles_Width) / 2;
        int posicaoY_controles = posicaoY_infinito + arruma_posicao;
        if (cursor == 2) {
            m.setColor(new Color(255, 209, 70));
        } else {
            m.setColor(Color.WHITE);
        }
        m.drawString(tela_controles, posicaoX_controles, posicaoY_controles);
        if (cursor == 2) {
            int cursorWidth = fm.stringWidth(">");
            m.drawString(">", posicaoX_controles - cursorWidth, posicaoY_controles);
        }// FIM

        // OPÇÃO PARA A TELA 'HISTORICO'
        int historico_Width = fm.stringWidth(tela_historico);
        int posicaoX_historico = (alturaImagem - historico_Width) / 2;
        int posicaoY_historico = posicaoY_controles + arruma_posicao;
        if (cursor == 3) {
            m.setColor(new Color(255, 209, 70));
        } else {
            m.setColor(Color.WHITE);
        }
        m.drawString(tela_historico, posicaoX_historico, posicaoY_historico);
        if (cursor == 3) {
            int cursorWidth = fm.stringWidth(">");
            m.drawString(">", posicaoX_historico - cursorWidth, posicaoY_historico);
        }// FIM
        m.dispose();
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

    public Image getImagem_fundo() {
        return imagem_fundo;
    }

    public void setImagem_fundo(Image imagem_fundo) {
        this.imagem_fundo = imagem_fundo;
    }

    public int getLarguraImagem() {
        return larguraImagem;
    }

    public void setLarguraImagem(int larguraImagem) {
        this.larguraImagem = larguraImagem;
    }

    public int getAlturaImagem() {
        return alturaImagem;
    }

    public void setAlturaImagem(int alturaImagem) {
        this.alturaImagem = alturaImagem;
    }

    public Font getPixel() {
        return pixel;
    }

    public void setPixel(Font pixel) {
        this.pixel = pixel;
    }

    public Font getBroken() {
        return broken;
    }

    public void setBroken(Font broken) {
        this.broken = broken;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public boolean isVisibilidade_menu() {
        return visibilidade_menu;
    }

    public void setVisibilidade_menu(boolean visibilidade_menu) {
        this.visibilidade_menu = visibilidade_menu;
    }

}
