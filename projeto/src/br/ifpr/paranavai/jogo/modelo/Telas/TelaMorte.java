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

public class TelaMorte {
    private Image banner_fundo_morte;
    private Image caveira_fundo;
    private int posicaoEmX, posicaoEmY;
    private int larguraImagem, alturaImagem;
    private boolean telaMorteVisibilidade;
    private int posicaoEmx_Caveira = 600;
    private int posicaoEmY_Caveira = 15;

    private Font pixel = null;
    Image teste;

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
        ImageIcon caveira = new ImageIcon("recursos\\sprites_fundos\\caveira.gif");
        this.caveira_fundo = caveira.getImage();
        // 'YOU DIE' IMG
        ImageIcon banner_fundo = new ImageIcon("recursos\\sprites_fundos\\banner_fundo_morte.png");
        this.banner_fundo_morte = banner_fundo.getImage();

        ImageIcon teste_care = new ImageIcon("recursos\\sprites_fundos\\fundo_morte.gif");
        this.teste = teste_care.getImage();
    }

    public void fundoTelaMorte(Graphics g) {
        String enter = "'ENTER' - RESETAR PARTIDA.";
        String voltar_menu = "'ESC' - VOLTAR AO MENU.";

        int larguraTela = 1300;
        int alturaTela = 600;
        g.setColor(new Color(255, 209, 70));
        // Set font for the strings
        g.setFont(pixel.deriveFont(Font.PLAIN, 25));
        FontMetrics fm = g.getFontMetrics();
        // CENTRALIZANDO PARA CADA STRING
        int enterX = (larguraTela - fm.stringWidth(enter)) / 2;
        int voltarMenuX = (larguraTela - fm.stringWidth(voltar_menu)) / 2;
        int y = alturaTela; // POSICÇÃO CENTRAL VERTICAL

        g.drawImage(teste, 0, 0, null);
        g.drawImage(this.caveira_fundo, posicaoEmx_Caveira, posicaoEmY_Caveira, null);
        g.drawImage(this.banner_fundo_morte, 0, 0, null);

        g.drawString(enter, enterX, y);
        g.drawString(voltar_menu, voltarMenuX, y + 30);
    }

    public Image getBanner_fundo_morte() {
        return banner_fundo_morte;
    }

    public void setBanner_fundo_morte(Image banner_fundo_morte) {
        this.banner_fundo_morte = banner_fundo_morte;
    }

    public Image getCaveira_fundo() {
        return caveira_fundo;
    }

    public void setCaveira_fundo(Image caveira_fundo) {
        this.caveira_fundo = caveira_fundo;
    }

    public int getPosicaoEmX() {
        return posicaoEmX;
    }

    public void setPosicaoEmX(int posicaoEmX) {
        this.posicaoEmX = posicaoEmX;
    }

    public int getPosicaoEmY() {
        return posicaoEmY;
    }

    public void setPosicaoEmY(int posicaoEmY) {
        this.posicaoEmY = posicaoEmY;
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

    public boolean isTelaMorteVisibilidade() {
        return telaMorteVisibilidade;
    }

    public void setTelaMorteVisibilidade(boolean telaMorteVisibilidade) {
        this.telaMorteVisibilidade = telaMorteVisibilidade;
    }

    public int getPosicaoEmx_Caveira() {
        return posicaoEmx_Caveira;
    }

    public void setPosicaoEmx_Caveira(int posicaoEmx_Caveira) {
        this.posicaoEmx_Caveira = posicaoEmx_Caveira;
    }

    public int getPosicaoEmY_Caveira() {
        return posicaoEmY_Caveira;
    }

    public void setPosicaoEmY_Caveira(int posicaoEmY_Caveira) {
        this.posicaoEmY_Caveira = posicaoEmY_Caveira;
    }

    public Font getPixel() {
        return pixel;
    }

    public void setPixel(Font pixel) {
        this.pixel = pixel;
    }

}