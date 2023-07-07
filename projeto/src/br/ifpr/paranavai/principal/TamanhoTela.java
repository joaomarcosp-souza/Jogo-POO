package br.ifpr.paranavai.principal;

import java.awt.Dimension;
import java.awt.Toolkit;

public class TamanhoTela {
    public int LARGURATELA;
    public int ALTURATELA;
    public Dimension tamanhoTela;

    public void carregar() {
        tamanhoTela = Toolkit.getDefaultToolkit().getScreenSize();
        LARGURATELA = (int) tamanhoTela.getWidth();
        ALTURATELA = (int) tamanhoTela.getHeight();
    }

    // GETTERS E SETTERS
    public int getLARGURATELA() {
        return LARGURATELA;
    }

    public void setLARGURATELA(int lARGURATELA) {
        LARGURATELA = lARGURATELA;
    }

    public int getALTURATELA() {
        return ALTURATELA;
    }

    public void setALTURATELA(int aLTURATELA) {
        ALTURATELA = aLTURATELA;
    }

}
