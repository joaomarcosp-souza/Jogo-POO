package br.ifpr.paranavai.jogo.modelo;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Personagem {
    private int posicaoEmX, posicaoEmY;
    private int deslocamentoEmX, deslocamentoEmY;
    private int larguraImagem, alturaImagem, larguraImagem_Vida, alturaImagem_Vida;
    private Image imagem;

    private Image imagem_vida;
    private int vidas = 3;

    public Personagem() {
        this.posicaoEmX = 100;
        this.posicaoEmY = 100;
    }

    public void carregar() {
        // IMAGEM PERSONAGEM
        ImageIcon carregador = new ImageIcon("recursos\\sprites_personagem\\xwing.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
        //IMAGEM VIDAS
        ImageIcon carrega_vida = new ImageIcon("recursos\\sprites_personagem\\heart.png");
        this.imagem_vida = carrega_vida.getImage();
        this.alturaImagem_Vida = this.imagem_vida.getWidth(null);
        this.larguraImagem_Vida = this.imagem_vida.getHeight(null);
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
            deslocamentoEmY = -10;
        }
        if (tecla == KeyEvent.VK_S) {
            deslocamentoEmY = 10;
        }

        if (tecla == KeyEvent.VK_A) {
            deslocamentoEmX = -10;
        }
        if (tecla == KeyEvent.VK_D) {
            deslocamentoEmX = 10;
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

    public Image getImagem_vida() {
        return imagem_vida;
    }

    public void setImagem_vida(Image imagem_vida) {
        this.imagem_vida = imagem_vida;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getLarguraImagem_Vida() {
        return larguraImagem_Vida;
    }

    public void setLarguraImagem_Vida(int larguraImagem_Vida) {
        this.larguraImagem_Vida = larguraImagem_Vida;
    }

    public int getAlturaImagem_Vida() {
        return alturaImagem_Vida;
    }

    public void setAlturaImagem_Vida(int alturaImagem_Vida) {
        this.alturaImagem_Vida = alturaImagem_Vida;
    }
}
