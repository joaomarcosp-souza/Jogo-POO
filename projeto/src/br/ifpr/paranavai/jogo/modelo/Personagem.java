package br.ifpr.paranavai.jogo.modelo;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Personagem {
    private int posicaoEmX, posicaoEmY;
    private int deslocamentoEmX, deslocamentoEmY;
    private int larguraImagem, alturaImagem;
    private Image imagem;

    public Personagem() {
        this.posicaoEmX = 500;
        this.posicaoEmY = 500;
    }

    public void carregar() {
        ImageIcon carregador = new ImageIcon("recursos\\starfighter.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    // Classe para atualizar a posicao
    public void atuzaliza() {
        // Atualiza a posição do personagem
        posicaoEmX += deslocamentoEmX;
        posicaoEmY += deslocamentoEmY;
    }

    public Rectangle getBounds() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);
        
    }

    // COMEÇO DO METODO MOVIMENTO
    public void tecla_Precionada(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        if (tecla == KeyEvent.VK_W) {
            deslocamentoEmY = -3;
        }
        if (tecla == KeyEvent.VK_S) {
            deslocamentoEmY = 3;
        }

        if (tecla == KeyEvent.VK_A) {
            deslocamentoEmX = -3;
        }
        if (tecla == KeyEvent.VK_D) {
            deslocamentoEmX = 3;
        }
    }

    public void tecla_solta(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        if (tecla == KeyEvent.VK_W || tecla == KeyEvent.VK_S) {
            deslocamentoEmY = 0;
        }
        if (tecla == KeyEvent.VK_A || tecla == KeyEvent.VK_D) {
            deslocamentoEmX = 0;
        }
    }

    // metodos gets e sets
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

    public int getDeslocamentoEmX() {
        return deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return deslocamentoEmY;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
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

}
