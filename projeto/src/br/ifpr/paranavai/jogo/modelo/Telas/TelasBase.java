package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Color;
import br.ifpr.paranavai.jogo.modelo.Base;

public abstract class TelasBase extends Base{
    private int posicaoTituloY = 100; // POSIÇÃO DOS TITULOS EM 'Y'
    // MENU
    private int cursor = 0;
    private static final int MENU_TAMANHO = 50;
    private static final int TITULO_TAMANHO = 85;
    private static final Color COR_AMARELA = new Color(255, 209, 0); // COR
    private static final int TAMANHO_FONTE = 30;

    public TelasBase() {
        super.setVisibilidade(true);
        //this.botao = new Rectangle(BOTAO_X, BOTAO_Y, BOTAO_LARGURA, BOTAO_ALTURA);
    }

    // MÉTODOS ABSTRATOS
    public abstract void carregar();
    // GETTERS E SETTERS
    public static Color getCorAmarela() {
        return COR_AMARELA;
    }

    public int getPosicaoTituloY() {
        return posicaoTituloY;
    }

    public void setPosicaoTituloY(int posicaoTituloY) {
        this.posicaoTituloY = posicaoTituloY;
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

    public static int getTamanhoFonte() {
        return TAMANHO_FONTE;
    }
}
