package br.ifpr.paranavai.jogo.modelo;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Personagem {
    private int posicaoEmX;
    private int posicaoEmY;
    private int deslocamentoEmX;
    private int deslocamentoEmY;
    private Image imagem;
    private int larguraImagem;
    private int alturaImagem;


    public Personagem(){
        this.posicaoEmX = 100;
        this.posicaoEmY = 100;
    }
    
    public void carregar(){
        ImageIcon carregador = new ImageIcon("recursos\\starfighter.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    }

    //COMEÇO DO METODO MOVIMENTO
    public void keypressed(KeyEvent teclado){ // Chamando o objeto para conseguir mexer o boneco
        int tecla = teclado.getKeyCode();

        // Movimentando o Personagem atráves das teclas 'W'(Pra cima) é 'S'(Pra baixo)
        if(tecla == KeyEvent.VK_UP){
            deslocamentoEmY = -3;
        };
        if(tecla == KeyEvent.VK_DOWN){
            deslocamentoEmY = 3;
        }

        // Movimentando o Personagem atráves das teclas 'A'(Para a Esquerda) é 'S'(Para Direita)
        if(tecla == KeyEvent.VK_LEFT){
            deslocamentoEmX = -3;
        };
        if(tecla == KeyEvent.VK_RIGHT){
            deslocamentoEmX = 3;
        };
    }
    //FIM MOVIMENTO

    public void keyRelease(KeyEvent teclado){
        int tecla = teclado.getKeyCode();

        //Ao soltar as teclas o personagem tera seu movimento zerado
        if(tecla == KeyEvent.VK_UP){
            deslocamentoEmY = 0;
        };
        if(tecla == KeyEvent.VK_DOWN){
            deslocamentoEmY = 0;
        };

        if(tecla == KeyEvent.VK_LEFT){
            deslocamentoEmX = 0;
        };
        if(tecla == KeyEvent.VK_RIGHT){
            deslocamentoEmX = 0;
        };

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
