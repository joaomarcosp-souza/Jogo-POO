package br.ifpr.paranavai.jogo.modelo.Telas;

import java.io.File;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;
import java.io.IOException;
import java.awt.FontFormatException;

import br.ifpr.paranavai.jogo.modelo.Base;
import br.ifpr.paranavai.principal.TamanhoTela;

public abstract class TelasBase extends Base{
    private int posicaoTituloY = 100; // POSIÇÃO DOS TITULOS EM 'Y'
    // MENU
    private int cursor = 0;
    private static final int MENU_TAMANHO = 50;
    private static final int TITULO_TAMANHO = 85;
    private static final Color COR_AMARELA = new Color(255, 209, 0); // COR
    // DIMENSÕES DO BOTÃO
    private Rectangle botao;
    private static final int TAMANHO_FONTE_BOTAO = 30;
    private static final int BOTAO_LARGURA = 70;
    private static final int BOTAO_ALTURA = 40;
    private static final int BOTAO_X = 5;
    private static final int BOTAO_Y = 5;
    private static Font PIXEL = null;
    // INSTANCIA
    private TamanhoTela telaTamanho;

    public TelasBase() {
        try {
            // CARREGA UMA NOVA FONTE A PARTIR DO ARQUIVO
            PIXEL = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\Fontes\\pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        telaTamanho = new TamanhoTela();
        telaTamanho.carregar();
        super.setVisibilidade(true);
        // DIMENSÕES DO BOTAO 'VOLTAR'
        this.botao = new Rectangle(BOTAO_X, BOTAO_Y, BOTAO_LARGURA, BOTAO_ALTURA);
    }

    // MÉTODOS ABSTRATOS
    public abstract void carregar();


    // GETTERS E SETTERS
    public static Color getCorAmarela() {
        return COR_AMARELA;
    }

    public Rectangle getBotao() {
        return botao;
    }

    public void setBotao(Rectangle botao) {
        this.botao = botao;
    }

    public static int getBotaoLargura() {
        return BOTAO_LARGURA;
    }

    public static int getBotaoAltura() {
        return BOTAO_ALTURA;
    }

    public static int getBotaoX() {
        return BOTAO_X;
    }

    public static int getBotaoY() {
        return BOTAO_Y;
    }

    public int getPosicaoTituloY() {
        return posicaoTituloY;
    }

    public void setPosicaoTituloY(int posicaoTituloY) {
        this.posicaoTituloY = posicaoTituloY;
    }

    public TamanhoTela getTelaTamanho() {
        return telaTamanho;
    }

    public void setTelaTamanho(TamanhoTela telaTamanho) {
        this.telaTamanho = telaTamanho;
    }

    public static int getTamanhoFonteBotao() {
        return TAMANHO_FONTE_BOTAO;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public static int getMenuTamanho() {
        return MENU_TAMANHO;
    }

    public static int getTituloTamanho() {
        return TITULO_TAMANHO;
    }

    public static Font getPIXEL() {
        return PIXEL;
    }

    public static void setPIXEL(Font pIXEL) {
        PIXEL = pIXEL;
    }

    
    
}
