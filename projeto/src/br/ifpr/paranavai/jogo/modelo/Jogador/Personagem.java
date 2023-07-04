package br.ifpr.paranavai.jogo.modelo.Jogador;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class Personagem extends Entidade {
    // VARIAVEIS PARA O TIRO
    private List<Tiro> tiros;
    private List<TiroEspecial> supertiro;
    private long tempoUltimoTiroNormal, tempoUltimoTiroSuper;
    private long delayTiro;
    private long delaySuper;
    // TELA
    private final int POSICAOINICIALX = 100;
    private final int POSICAOINICIALY = 300;
    // VALORES PARA CADA
    private final int VIDAINICIAL = 3;
    private final int PONTOSINICIAIS = 0;
    private final int VELOCIDADEINICIAL = 3;

    // CONTRUTOR
    public Personagem() {
        this.posicaoEmX = POSICAOINICIALX;
        this.posicaoEmY = POSICAOINICIALY;
        this.delayTiro = 200;
        this.delaySuper = 2500;
        this.visibilidade = true;
        this.VIDAS = VIDAINICIAL;
        this.pontos = PONTOSINICIAIS;
        this.VELOCIDADE = VELOCIDADEINICIAL;
        tiros = new ArrayList<Tiro>();
        supertiro = new ArrayList<TiroEspecial>();
    }

    @Override
    public void carregar() {
        // IMAGEM PERSONAGEM
        ImageIcon carregador = new ImageIcon("recursos\\sprites_personagem\\navePrincipal.png");
        this.imagem = carregador.getImage();
        this.larguraImagem = this.imagem.getWidth(null);
        this.alturaImagem = this.imagem.getHeight(null);
        // IMAGEM VIDAS
        ImageIcon carregadorVida = new ImageIcon("recursos\\sprites_personagem\\coracao.png");
        this.imagem_vida = carregadorVida.getImage();
        this.larguraImagem_Vida = this.imagem_vida.getWidth(null);
        this.alturaImagem_Vida = this.imagem_vida.getHeight(null);
    }

    @Override
    public void atualizar() {
        posicaoEmX += deslocamentoEmX;
        posicaoEmY += deslocamentoEmY;
    }

    // METODO MOVIMENTO
    public void mover(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();
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

    public void parar(KeyEvent teclado) {
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

    // MÉTODO TIRO
    public void tiro_simples() {
        this.tiros.add(new Tiro(posicaoEmX + larguraImagem / 2, posicaoEmY + (alturaImagem / 2)));
    }

    // MÉTODO TIRO ESPECIAL
    public void superTiro() {
        this.supertiro.add(new TiroEspecial(posicaoEmX + larguraImagem / 2, posicaoEmY + (alturaImagem / 2)));
    }

    // MÉTODO ATIRAR
    public void tiroNormal(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();
        long tempoAtual = System.currentTimeMillis();
        if (tempoAtual - tempoUltimoTiroNormal < delayTiro) {
            return;
        } else {
            if (tecla == KeyEvent.VK_SPACE) {
                tiro_simples();
            }
        }
        tempoUltimoTiroNormal = tempoAtual;
    }

    // MÉTODO SUPER TIRO
    public void tiroEspecial(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();
        long tempoAtual = System.currentTimeMillis();
        if (tempoAtual - tempoUltimoTiroSuper < delaySuper) {
            return;
        } else {
            if (tecla == KeyEvent.VK_R) {
                superTiro();
            }
        }
        tempoUltimoTiroSuper = tempoAtual;
    }

    // MÉTODO DE PONTUAÇÃO DO PERSONAGEM
    public void pontos(Graphics g) {
        String pontosSTR = "PONTOS: " + pontos;
        g.setFont(pixel.deriveFont(Font.PLAIN, 22));
        g.setColor(new Color(255, 209, 70));
        g.drawString(pontosSTR, 20, 25);
    }

    // MÉTODO DE VIDA DO PERSONAGEM
    public void vidas(Graphics g) {
        int diferenca = 60;
        // CALCUPA A DISTÂNCIA DA BORDA PARA POSICIONAR A VIDA
        int distancia = (15 * VIDAS) + (5 * VIDAS) + diferenca;
        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        // A POSIÇÃO DE CADA UMA EM (x,y)
        for (int i = 0; i < VIDAS; i++) {
            g.drawImage(imagem_vida, (getLARGURATELA()) - diferenca, 10, null);
            diferenca += alturaImagem_Vida + 5;
            if(VIDAS == 2){
                g.drawImage(imagem, (getLARGURATELA()) - diferenca, 10, null);
                diferenca += alturaImagem_Vida + 5;
            }
        }
        // DESENHA AS IMAGENS COM BASE NA POSIÇÃO (x,y)
        g.drawString("", larguraImagem_Vida - distancia, 10);
    }

    // MÉTODOS GETTERS E SETTERS EXCLUISIVOS PERSONAGEM
    public List<Tiro> getTiros() {
        return tiros;
    }

    public void setTiros(List<Tiro> tiros) {
        this.tiros = tiros;
    }

    public int getPOSICAOINICIALX() {
        return POSICAOINICIALX;
    }

    public int getPOSICAOINICIALY() {
        return POSICAOINICIALY;
    }

    public int getVIDAINICIAL() {
        return VIDAINICIAL;
    }

    public int getPONTOSINICIAIS() {
        return PONTOSINICIAIS;
    }

    public List<TiroEspecial> getSupertiro() {
        return supertiro;
    }

    public void setSupertiro(List<TiroEspecial> supertiro) {
        this.supertiro = supertiro;
    }
}