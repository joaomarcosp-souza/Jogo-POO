package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class MenuInicial extends EntidadeTelas {

    // CONSTRUTOR
    public MenuInicial() {
        this.visibilidade = true;
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon("recursos\\Sprites\\Fundos\\FundoMenuInicial.gif");
        this.imagem = carregando.getImage();
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        this.imagem = this.imagem.getScaledInstance(
                telaTamanho.getLARGURATELA(), telaTamanho.getALTURATELA(), Image.SCALE_FAST);
        this.alturaImagem = this.imagem.getWidth(null);
        this.larguraImagem = this.imagem.getHeight(null);
    };

    @Override
    public void titulo(Graphics g) {
        // CARREGA FUNDO
        g.drawImage(imagem, 0, 0, null);
        // CONFIGURAÇÕES FONT
        String titulo = "STAR WARS";
        String subtitulo = "INVADERS";
        Font fonte = getPixel().deriveFont(Font.BOLD, getTitulosize());
        g.setFont(fonte);
        // CRENTALIZA VERTICALMENTE
        FontMetrics fm = g.getFontMetrics();
        int posicaoTituloX = (telaTamanho.getLARGURATELA() - fm.stringWidth(titulo)) / 2;
        int subTituloLargura = fm.stringWidth(subtitulo); // PEGA O TAMANHO DO SUBTITULO
        // COR DO TITULO 'STAR WARS - BRANCO'
        g.setColor(Color.WHITE);
        g.drawString(titulo, (posicaoTituloX + 5), posicaoTituloY);
        // TITULO 'INVADERS'
        g.drawString(subtitulo, posicaoTituloX + (fm.stringWidth(titulo) - subTituloLargura) / 2,
                (posicaoTituloY + 80));
        // COR DO TITULO 'STAR WARS - AMARELO'
        g.setColor(getCorAmarela());
        g.drawString(titulo, posicaoTituloX, posicaoTituloY);
    }

    @Override
    public void menu(Graphics g) {
        String MODOFASES = "MODO FASES";
        String MODOINFINITO = "MODO INFINITO";
        String telaControle = "CONTROLES";
        String telaHistorico = "HISTORICO";
        int offSetY = 50;
        Font menuConfig = getPixel().deriveFont(Font.BOLD, getMenusize());

        g.setFont(menuConfig);
        FontMetrics fm = g.getFontMetrics();
        // OPÇÃO PARA A TELA 'MODO FASES'
        int posicaoX = (telaTamanho.getLARGURATELA() - fm.stringWidth(MODOFASES)) / 2;
        int posicaoY = telaTamanho.getALTURATELA() - 200;
        if (cursor == 0) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(MODOFASES, posicaoX, posicaoY);
        if (cursor == 0) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoX - cursorWidth, posicaoY);
        } // FIM MODO FASE

        // OPÇÃO PARA A TELA 'MODO INFINITO'
        int posicaoX_infinito = (telaTamanho.getLARGURATELA() - fm.stringWidth(MODOINFINITO)) / 2;
        int posicaoY_infinito = posicaoY + offSetY;
        if (cursor == 1) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(MODOINFINITO, posicaoX_infinito, posicaoY_infinito);
        if (cursor == 1) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoX_infinito - cursorWidth, posicaoY_infinito);
        } // FIM

        // OPÇÃO PARA A TELA 'CONTROLES'
        int posicaoXcontrole = (telaTamanho.getLARGURATELA() - fm.stringWidth(telaControle)) / 2;
        int posicaoYcontrole = posicaoY_infinito + offSetY;
        if (cursor == 2) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(telaControle, posicaoXcontrole, posicaoYcontrole);
        if (cursor == 2) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoXcontrole - cursorWidth, posicaoYcontrole);
        } // FIM

        // OPÇÃO PARA A TELA 'HISTORICO'
        int posicaoXhistorico = (telaTamanho.getLARGURATELA() - fm.stringWidth(telaHistorico)) / 2;
        int posicaoYhistorico = posicaoYcontrole + offSetY;
        if (cursor == 3) {
            g.setColor(getCorAmarela());
        } else {
            g.setColor(Color.WHITE);
        }
        g.drawString(telaHistorico, posicaoXhistorico, posicaoYhistorico);
        if (cursor == 3) {
            int cursorWidth = fm.stringWidth(">");
            g.drawString(">", posicaoXhistorico - cursorWidth, posicaoYhistorico);
        }
        g.dispose();
    }

    public void controleMenu(KeyEvent teclado) {
        int tecla = teclado.getKeyCode();

        // CONTROLA A POSIÇÃO DO CURSOR PRA ELE NÃO SAIR DAS OPCÕES
        if (tecla == KeyEvent.VK_UP) {
            cursor--;
            if (cursor < 0) {
                cursor = 3;
            }
        }
        if (tecla == KeyEvent.VK_DOWN) {
            cursor++;
            if (cursor > 3) {
                cursor = 0;
            }
        }
    }
}
