package br.ifpr.paranavai.jogo.modelo.Telas;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.IOException;


public class Tela_Login extends JPanel {
    private Image imagem_fundo;
    private int larguraImagem, alturaImagem;
    private int cursor = 0;

    private Font pixel = null;
    private Font broken = null;

    public Tela_Login() {
        ImageIcon carregando = new ImageIcon("recursos\\sprites_fundos\\starwars.jpg");
        this.imagem_fundo = carregando.getImage();
        this.alturaImagem = this.imagem_fundo.getWidth(null);
        this.larguraImagem = this.imagem_fundo.getHeight(null);

        // INICIALIZANDO O METODO DE LEITURA DO TECLADO

        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));
            broken = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));
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
        String titulo = "STAR WARS";
        String subtitulo = "INVADERS";
        Font fonte = pixel;
        fonte = fonte.deriveFont(Font.BOLD, 85);

        t.setColor(new Color(255, 209, 70)); // COR DO TITULO AMARELO
        t.setFont(fonte);
        // CRENTALIZA VERTICALMENTE
        FontMetrics fm = t.getFontMetrics();

        int tituloWidth = fm.stringWidth(titulo); // PEGA O TAMANHO DA STRING
        int subtituloWidth = fm.stringWidth(subtitulo); // PEGA O TAMANHO DO SUBTITULO
        int x = (getWidth() - tituloWidth) / 2;
        int y = 100 + (getHeight() - fm.getHeight()) / 2;

        t.drawString(titulo, (x + 5), y - 280); // POSIÇÃO DO TITULO - AMARELO

        // SUBTITULO COR AMARELO
        t.setColor(Color.WHITE);
        t.drawString(subtitulo, x + (tituloWidth - subtituloWidth) / 2, y - 200);
        // DESENHA O SUBTÍTULO NA TELA - AMARELO
        t.setColor(new Color(255, 209, 70));
        // t.setColor(new Color(255, 209, 70));
        t.drawString(subtitulo, ((x + 5) + (tituloWidth - subtituloWidth) / 2), y - 200);

        t.setColor(Color.WHITE); // DEFINE A COR PRINCIPAL DO TÍTULO - BRANCO
        t.drawString(titulo, x, y - 280); // POSIÇÃO DO TÍTULO PRINCIPAL - COR BRANCA
    }

    public void menu(Graphics m) {
        String jogar = "NOVO JOGO";
        String historico = "MODO INFINITO";
        String sair = "SAIR";

        Font estilo_menu = broken.deriveFont(Font.BOLD, 50);
        m.setFont(estilo_menu);
        FontMetrics fm = m.getFontMetrics();

        int stringWidth = fm.stringWidth(jogar);
        int x = (getWidth() - stringWidth) / 2;
        int y = 450;

        m.setColor(Color.white);
        stringWidth = fm.stringWidth(jogar);
        x = (getWidth() - stringWidth) / 2;
        y = 450;
        m.drawString(jogar, x, y);
        //
        if (cursor == 0) {
            // Desenha o cursor na frente da string
            int cursorWidth = fm.stringWidth(">");
            m.drawString(">", x - cursorWidth, y);
        }
        //

        m.setColor(Color.white);
        stringWidth = fm.stringWidth(historico);
        x = (getWidth() - stringWidth) / 2;
        y = 505;
        m.drawString(historico, x, y);
        //
        if (cursor == 1) {
            // Desenha o cursor na frente da string
            int cursorWidth = fm.stringWidth(">");
            m.drawString(">", x - cursorWidth, y);
        }
        //

        m.setColor(Color.white);
        stringWidth = fm.stringWidth(sair);
        x = (getWidth() - stringWidth) / 2;
        y = 555;
        m.drawString(sair, x, y);
        //
        if (cursor == 2) {
            // Desenha o cursor na frente da string
            int cursorWidth = fm.stringWidth(">");
            m.drawString(">", x - cursorWidth, y);
        } //
        m.dispose();
    }

    public void tecla_menu(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        if (tecla == KeyEvent.VK_W) {
            cursor++;
            if (cursor < 0) {
                cursor = 2;
            }
            System.out.println("PTESTES");
        }
        if (tecla == KeyEvent.VK_S) {

            cursor--;
            if (cursor > 2) {
                cursor = 0;
            }
            System.out.println("AAAAAAAAA");
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

}
