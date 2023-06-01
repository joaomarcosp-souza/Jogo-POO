package br.ifpr.paranavai.jogo.modelo.Telas;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tela_Login extends JPanel {
    private Image imagem_fundo;
    private int larguraImagem, alturaImagem;
    // private int cursor = 0;

    private Font pixel = null;
    private Font broken = null;

    public Tela_Login() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\starwars.jpg");
        this.imagem_fundo = carregando.getImage();
        this.alturaImagem = this.imagem_fundo.getWidth(null);
        this.larguraImagem = this.imagem_fundo.getHeight(null);

        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));
            broken = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\broken_console.ttf"));
            // DEFINE O TAMANHO DA FONTE DESEJADO

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(this.imagem_fundo, 0, 0, null);

        titulo(graficos);
        menu(graficos);
        g.dispose();
    }

    public void titulo(Graphics t) {
        String titulo = "STAR INVADER";
        Font fonte = pixel;
        fonte = fonte.deriveFont(Font.BOLD, 85);
        t.setColor(new Color(255, 209, 70));
        t.setFont(fonte);
        // Centralizar verticalmente
        FontMetrics fm = t.getFontMetrics();
        int stringWidth = fm.stringWidth(titulo);
        int x = (getWidth() - stringWidth) / 2;
        int y = 100 + (getHeight() - fm.getHeight()) / 2;
        t.drawString(titulo, x + 5, y - 275);

        t.setColor(Color.WHITE);
        t.drawString(titulo, x, y - 280);
    }

    public void menu(Graphics m) {
        String jogar = "Novo Jogo";
        Font estilo_menu = broken;
        estilo_menu = broken.deriveFont(Font.BOLD, 50);
        m.setFont(estilo_menu);
        m.setColor(Color.white);
        // Centralizar verticalmente
        FontMetrics fm = m.getFontMetrics();
        int stringWidth = fm.stringWidth(jogar);
        int x = (getWidth() - stringWidth) / 2;
        int y = 450;
        m.drawString(jogar, x, y);

        String historico = "Modo Infinito";
        estilo_menu = broken.deriveFont(Font.BOLD, 50);
        m.setFont(estilo_menu);
        m.setColor(Color.white);
        // Centralizar verticalmente
        stringWidth = fm.stringWidth(historico);
        x = (getWidth() - stringWidth) / 2;
        y = 505;
        m.drawString(historico, x, y);

        String sair = "Sair";
        estilo_menu = broken.deriveFont(Font.BOLD, 50);
        m.setFont(estilo_menu);
        m.setColor(Color.white);
        // Centralizar verticalmente
        stringWidth = fm.stringWidth(sair);
        x = (getWidth() - stringWidth) / 2;
        y = 555;
        m.drawString(sair, x, y);
    }

    public void botoes() {

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

}
