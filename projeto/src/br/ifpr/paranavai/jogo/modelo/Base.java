package br.ifpr.paranavai.jogo.modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.awt.Font;
import java.io.IOException;
import java.awt.FontFormatException;
import br.ifpr.paranavai.principal.TamanhoTela;

public abstract class Base {
    
    private int life;
    private Image image;
    private double speed;
    private Font pixel = null;
    private boolean visibility;
    private TamanhoTela screenResolution;
    private double initialSpeed;
    private int positionInX, positionInY;
    private int widthImage, heightImage;

    public Base() {
        this.visibility = true;
        //PUXANDO INFORMAÇÕES DA TELA 
        screenResolution = new TamanhoTela();
        screenResolution.carregar();
        // CARREGANDO UMA NOVA FONTE
        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("src/br/ifpr/paranavai/recursos/Fontes/pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    // MÉTODOS ABSTRATO BASICO PARA 'CARREGAR' É 'ATUALIZAR'
    public abstract void carregar();
    
    // CAIXA DE COLISÃO
    public Rectangle getBounds() {
        return new Rectangle(positionInX, positionInY, widthImage, heightImage);
    }
    //NÃO E UM MÉTODO ABSTRAT, POR CAUSA DAS CLASSE DE TELA
    public void atualizar(){}

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Font getPixel() {
        return pixel;
    }

    public void setPixel(Font pixel) {
        this.pixel = pixel;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public TamanhoTela getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(TamanhoTela screenResolution) {
        this.screenResolution = screenResolution;
    }

    public double getInitialSpeed() {
        return initialSpeed;
    }

    public void setInitialSpeed(double initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public int getPositionInX() {
        return positionInX;
    }

    public void setPositionInX(int positionInX) {
        this.positionInX = positionInX;
    }

    public int getPositionInY() {
        return positionInY;
    }

    public void setPositionInY(int positionInY) {
        this.positionInY = positionInY;
    }

    public int getWidthImage() {
        return widthImage;
    }

    public void setWidthImage(int widthImage) {
        this.widthImage = widthImage;
    }

    public int getHeightImage() {
        return heightImage;
    }

    public void setHeightImage(int heightImage) {
        this.heightImage = heightImage;
    };

    //GETTERS E SETTERS
    
}
