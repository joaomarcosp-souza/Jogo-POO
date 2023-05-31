package br.ifpr.paranavai.jogo.modelo.Telas;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Tela_Login extends JPanel {
    private Image imagem_fundo;
    private int larguraImagem, alturaImagem;
    private Font fonte = null;

    public Tela_Login() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\fundo_home_screen.jpg");
        this.imagem_fundo = carregando.getImage();
        this.alturaImagem = this.imagem_fundo.getWidth(null);
        this.larguraImagem = this.imagem_fundo.getHeight(null);

        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            fonte = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\broken_console.ttf"));
            // DEFINE O TAMANHO DA FONTE DESEJADO
            fonte = fonte.deriveFont(Font.BOLD, 85);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(this.imagem_fundo, 0, 0, null);

        titulo(graficos);
        g.dispose();
    }

    public void titulo(Graphics t) {
        // TITULO DO JOGO
        //t.setColor(new Color(80, 96, 100));
        String titulo = "star invader";
        // ESTILO DA FONTE
        Font estilo = fonte;
        

        // COR FONTE // SOMBRA
        t.setColor(Color.DARK_GRAY);
        t.setFont(estilo);
        t.drawString(titulo, 300+5, 100+5);

        // COR PRINCIPAL
        t.setColor(Color.WHITE);
        t.drawString(titulo, 300, 100);
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

    public Font getFonte() {
        return fonte;
    }

    public void setFonte(Font fonte) {
        this.fonte = fonte;
    }

    
}
