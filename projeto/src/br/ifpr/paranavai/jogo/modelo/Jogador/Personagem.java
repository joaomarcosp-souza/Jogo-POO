package br.ifpr.paranavai.jogo.modelo.Jogador;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.util.List;

public class Personagem {
    private int pontos;
    private int posicaoEmX, posicaoEmY;
    private int deslocamentoEmX, deslocamentoEmY;
    private int larguraImagem, alturaImagem, larguraImagem_Vida, alturaImagem_Vida;
    private Image imagem;
    private List<Tiro> tiros;
    private boolean visibilidade;
    private Image imagem_vida;
    private Font pixel = null;

    private static final int POSICAO_INICIAL_EM_X = 100;
    private static final int POSICAO_INICIAL_EM_Y = 300;
    private static final int VELOCIDADE = 5;

    public Personagem() {
        this.posicaoEmX = POSICAO_INICIAL_EM_X;
        this.posicaoEmY = POSICAO_INICIAL_EM_Y;
        this.visibilidade = true;

        tiros = new ArrayList<Tiro>();

        try {
            // CARREGA A FONTE A PARTIR DO ARQUIVO
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("recursos\\fontes\\pixel_fonte.ttf"));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void carregar() {
        // IMAGEM PERSONAGEM
        ImageIcon carregador = new ImageIcon("recursos\\sprites_personagem\\xwing.png");
        this.imagem = carregador.getImage();
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
        // IMAGEM VIDAS
        ImageIcon carrega_vida = new ImageIcon("recursos\\sprites_personagem\\heart.png");
        this.imagem_vida = carrega_vida.getImage();
        this.alturaImagem_Vida = this.imagem_vida.getWidth(null);
        this.larguraImagem_Vida = this.imagem_vida.getHeight(null);
    }

    // Classe para atualizar a posicao
    public void atualiza() {
        // Atualiza a posição do personagem
        posicaoEmX += deslocamentoEmX;
        posicaoEmY += deslocamentoEmY;
    }

    // MÉTODO TIRO
    public void tiro_simples() {
        this.tiros.add(new Tiro(posicaoEmX + (larguraImagem / 2), posicaoEmY + (alturaImagem / 2)));
    }

    public Rectangle getBounds() {
        return new Rectangle(posicaoEmX, posicaoEmY, larguraImagem, alturaImagem);

    }

    // MÉTODO DE PONTUAÇÃO DO JOGADOR
    public void pontos(Graphics pont) {
        // TITULO DO JOGO
        String pont_string = "PONTOS: " + pontos;
        Font fonte = pixel;
        // ESTILO DA FONTEd
        fonte = fonte.deriveFont(Font.BOLD, 20);
        // COR FONTE
        pont.setColor(new Color(255, 209, 70)); // COR DO TITULO AMARELO
        pont.setFont(fonte);
        // DESENHA A STRING COM A POSIÇÃO (x,y)
        pont.drawString(pont_string, 10, 20);
    }
    

    // COMEÇO DO METODO MOVIMENTO
    public void tecla_Precionada(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        // TECLA TIRO
        if (tecla == KeyEvent.VK_SPACE) {
            // dispararTiro = true;
            tiro_simples();
        }

        // TECLAS MOVIMENTAÇÃO
        if (tecla == KeyEvent.VK_W || tecla == KeyEvent.VK_UP) {
            deslocamentoEmY = -VELOCIDADE;
        }
        if (tecla == KeyEvent.VK_S || tecla == KeyEvent.VK_DOWN) {
            deslocamentoEmY = VELOCIDADE;
        }

        if (tecla == KeyEvent.VK_A || tecla == KeyEvent.VK_LEFT) {
            deslocamentoEmX = -VELOCIDADE;
        }
        if (tecla == KeyEvent.VK_D || tecla == KeyEvent.VK_RIGHT) {
            deslocamentoEmX = VELOCIDADE;
        }
    }

    public void tecla_solta(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        if (tecla == KeyEvent.VK_W || tecla == KeyEvent.VK_UP) {
            deslocamentoEmY = 0;
        }

        if (tecla == KeyEvent.VK_S || tecla == KeyEvent.VK_DOWN) {
            deslocamentoEmY = 0;
        }

        if (tecla == KeyEvent.VK_A || tecla == KeyEvent.VK_LEFT) {
            deslocamentoEmX = 0;
        }

        if (tecla == KeyEvent.VK_D || tecla == KeyEvent.VK_RIGHT) {
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

    public List<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(List<Tiro> tiros) {
        this.tiros = tiros;
    }

    public boolean isVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(boolean visibilidade) {
        this.visibilidade = visibilidade;
    }

    public Font getPixel() {
        return pixel;
    }

    public void setPixel(Font pixel) {
        this.pixel = pixel;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

}
