package br.ifpr.paranavai.jogo.modelo.Jogador;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

public class Personagem extends Entidade {
    // VARIAVEIS PARA O TIRO
    private List<Tiro> tiros;
    private long tempoUltimoTiro;
    private long delayTiro;
    // TELA
    private int LARGURATELA = 1200;
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
        this.visibilidade = true;
        this.delayTiro = 100;
        this.VELOCIDADE = VELOCIDADEINICIAL;
        this.VIDAS = VIDAINICIAL;
        this.pontos = PONTOSINICIAIS;
        tiros = new ArrayList<Tiro>();
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

    // MÉTODO TECLA PARA ATIRAR
    public void atirar(KeyEvent teclado) {
        long tempoAtual = System.currentTimeMillis();
        int tecla = teclado.getKeyCode();

        if (tempoAtual - tempoUltimoTiro < delayTiro) {
            return;
        } else {
            if (tecla == KeyEvent.VK_SPACE) {
                tiro_simples();
            }
        }

        tempoUltimoTiro = tempoAtual;

    }

    // MÉTODO DE PONTUAÇÃO DO PERSONAGEM
    public void pontos(Graphics g) {
        String pontosSTR = "PONTOS: " + pontos;
        g.setFont(pixel.deriveFont(Font.PLAIN, 22));
        g.setColor(new Color(255, 209, 70));
        g.drawString(pontosSTR, 20, 25);
    }

    // MÉTODO DE VIDA DO PERSONAGEM
    public void vidas(Graphics life) {
        JPanel panel = new JPanel();
        // DEFINE A STRING DE EXIBIÇÃO
        String vida = "Vidas";
        // CRIA UM ESTILO DE FONTE
        Font estilo = new Font("Consolas", Font.BOLD, 10);
        // CRIA A MÉTRICA DA FONTE
        FontMetrics metrica = panel.getFontMetrics(estilo);
        // OBTÉM O TAMANHO DA STRING NA TELA
        int width = metrica.stringWidth(vida);
        // CALCUPA A DISTÂNCIA DA BORDA PARA POSICIONAR A VIDA
        int distancia = (15 * VIDAS) + (5 * VIDAS) + 100 + width;
        // PARA CADA VIDA DO JOGADOR, DESENHA UMA IMAGEM DA VIDA,
        // ALTERANDO A POSIÇÃO COM BASE NOS CALCULOS PARA DEFINIR
        // A POSIÇÃO DE CADA UMA EM (x,y)
        for (int i = 0; i < VIDAS; i++) {
            life.drawImage(imagem_vida, (LARGURATELA + 30) - width, 10, null);
            width += alturaImagem_Vida + 5;
        }
        // SETA A COR DA FONTE
        life.setColor(Color.WHITE);
        // SETA O ESTILO DE FONTE
        life.setFont(estilo);
        // DESENHA A STRING COM A POSIÇÃO (x,y)
        life.drawString(vida, larguraImagem_Vida - distancia, 10);
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
}
