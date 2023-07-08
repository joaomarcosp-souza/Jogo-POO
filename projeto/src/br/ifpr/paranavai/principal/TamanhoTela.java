package br.ifpr.paranavai.principal;

import java.awt.Dimension;
import java.awt.Toolkit;

public class TamanhoTela {
    public int LARGURA_TELA;
    public int ALTURA_TELA;
    public Dimension TAMANHOTELA;

    public void carregar() {
        TAMANHOTELA = Toolkit.getDefaultToolkit().getScreenSize();
        LARGURA_TELA = (int) TAMANHOTELA.getWidth();
        ALTURA_TELA = (int) TAMANHOTELA.getHeight();
    }

    // GETTERS E SETTERS
    public int getLARGURA_TELA() {
        return LARGURA_TELA;
    }

    public void setLARGURA_TELA(int lARGURA_TELA) {
        LARGURA_TELA = lARGURA_TELA;
    }

    public int getALTURA_TELA() {
        return ALTURA_TELA;
    }

    public void setALTURA_TELA(int aLTURA_TELA) {
        ALTURA_TELA = aLTURA_TELA;
    }
}
