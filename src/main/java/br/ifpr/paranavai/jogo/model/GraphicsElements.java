package br.ifpr.paranavai.jogo.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.awt.Font;
import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import br.ifpr.paranavai.jogo.Util.ScreenSize;
import java.awt.FontFormatException;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GraphicsElements {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Integer identificador;
    @Column(name= "vida")
    private int life;
    @Transient
    private Image image;
    @Column(name = "velocidade")
    @Transient
    private Font pixel = null;
    @Column(name = "visibilidade")
    private boolean visibility;
    @Column(name = "posicaoX")    
    private int positionInX;
    @Column(name = "posicaoY")
    private int positionInY;
    @Column(name = "largura_imagem")
    private int widthImage;
    @Column(name= "altura_imagem")
    private int heightImage;
    @Column(name= "velocidade")
    private double speed;
    @Transient
    private ScreenSize screenResolution;
    private double initialSpeed;

    public GraphicsElements() {
        this.visibility = true;
        screenResolution = new ScreenSize();
        screenResolution.carregar();
        try {
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
    // MÉTODOS ABSTRATO BASICO PARA 'CARREGAR' É 'ATUALIZAR'
    public abstract void load();
    public void update(){}
    
    // CAIXA DE COLISÃO
    public Rectangle getBounds() {
        return new Rectangle(positionInX, positionInY, widthImage, heightImage);
    }

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

    public ScreenSize getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(ScreenSize screenResolution) {
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
