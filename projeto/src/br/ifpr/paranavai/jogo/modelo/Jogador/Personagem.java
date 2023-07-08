package br.ifpr.paranavai.jogo.modelo.Jogador;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.Image;

public class Personagem extends EntidadeJogador {
    private List<Tiro> tiros;
    private List<SuperTiro> supertiro;
    private long delayTiro;
    private long ultimoTiro;
    private int vida;
    private int pontos;
    private int velocidade;
    private int deslocamentoEmX, deslocamentoEmY;
    private Image IMAGEM_VIDA;
    private int ALTURA_IMAGEM_VIDA;
    private boolean supertiroUsado = false;

    private static final int VIDAINICIAL = 1;
    private static final int PONTOSINICIAIS = 0;
    private static final int POSICAOINICIALX = 100;
    private final int POSICAOINICIALY = getTelaTamanho().ALTURA_TELA / 2;
    private final int VELOCIDADEINICIAL = 4;

    // CONTRUTOR
    public Personagem() {
        super.setPosicaoEmX(POSICAOINICIALX);
        super.setPosicaoEmY(POSICAOINICIALY);
        super.setVisibilidade(true);

        this.delayTiro = 200;
        this.vida = VIDAINICIAL;
        this.pontos = PONTOSINICIAIS;
        this.velocidade = VELOCIDADEINICIAL;

        tiros = new ArrayList<Tiro>();
        supertiro = new ArrayList<SuperTiro>();
    }

    @Override
    public void carregar() {
        // IMAGEM PERSONAGEM
        ImageIcon carregando = new ImageIcon("recursos\\Sprites\\Personagem\\navePrincipal.png");
        super.setImagem(carregando.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
        // IMAGEM VIDAS
        ImageIcon carregadorVida = new ImageIcon("recursos\\Sprites\\Personagem\\coracao.png");
        this.IMAGEM_VIDA = carregadorVida.getImage();
        this.ALTURA_IMAGEM_VIDA = this.IMAGEM_VIDA.getHeight(null);
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX(super.getPosicaoEmX() + deslocamentoEmX);
        super.setPosicaoEmY(super.getPosicaoEmY() + deslocamentoEmY);
    }

    // METODO MOVIMENTO
    public void mover(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();
        if (tecla == KeyEvent.VK_W || tecla == KeyEvent.VK_UP) {
            deslocamentoEmY = -velocidade;
        }
        if (tecla == KeyEvent.VK_S || tecla == KeyEvent.VK_DOWN) {
            deslocamentoEmY = velocidade;
        }
        if (tecla == KeyEvent.VK_A || tecla == KeyEvent.VK_LEFT) {
            deslocamentoEmX = -velocidade;
        }
        if (tecla == KeyEvent.VK_D || tecla == KeyEvent.VK_RIGHT) {
            deslocamentoEmX = velocidade;
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

    // MÉTODO ATIRAR
    public void atirar(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();
        long tempoAtual = System.currentTimeMillis();

        int centroPersonagemX = super.getPosicaoEmX() + super.getLarguraImagem() / 2;
        int centroPersonagemY = super.getPosicaoEmY() + super.getAlturaImagem() / 2;

        if (tempoAtual - ultimoTiro < delayTiro) {
            return;
        } else {
            if (tecla == KeyEvent.VK_SPACE) {
                Tiro tiro = new Tiro(centroPersonagemX, centroPersonagemY);
                this.tiros.add(tiro);
            }
            // SUPER TIRO
            if (tecla == KeyEvent.VK_R && this.getPontos() % 100 == 0 && !supertiroUsado) {
                SuperTiro tiro = new SuperTiro(centroPersonagemX, centroPersonagemY);
                this.supertiro.add(tiro);
                supertiroUsado = true; // DEFINE O SUPERTIRO COMO USADO
            }
        }
        ultimoTiro = tempoAtual;
        // VERIFICANDO SE A PONTUAÇÃO NÃO E MAIS VALIDA E REDEFININDO A VARIAVEL
        // supertiroUsado
        if (this.getPontos() % 100 != 0) {
            supertiroUsado = false;
        }
    }

    // MÉTODO DE PONTUAÇÃO DO PERSONAGEM
    public void desenhaPontos(Graphics g) {
        String pontosSTR = "PONTOS: " + pontos;
        g.setFont(super.getPixel().deriveFont(Font.PLAIN, 22));
        g.setColor(new Color(255, 209, 70));
        g.drawString(pontosSTR, 20, 25);
    }

    public void desenhaVida(Graphics g) {
        int diferenca = 70;
        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        for (int i = 0; i < this.vida; i++) {
            g.drawImage(IMAGEM_VIDA, getTelaTamanho().LARGURA_TELA - diferenca, 10, null);
            diferenca += ALTURA_IMAGEM_VIDA + 5;
        }
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

    public List<SuperTiro> getSupertiro() {
        return supertiro;
    }

    public void setSupertiro(List<SuperTiro> supertiro) {
        this.supertiro = supertiro;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public static int getPontosiniciais() {
        return PONTOSINICIAIS;
    }

}