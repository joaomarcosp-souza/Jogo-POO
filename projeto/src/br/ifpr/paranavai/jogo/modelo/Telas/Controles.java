package br.ifpr.paranavai.jogo.modelo.Telas;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Controles extends EntidadeTelas {
    // VARIAVEL PARA CORRIGIR A POSIÇÃO DE ALGUNS ITENS
    private Image imageControle;
    
    // CAMINHO IMAGEM DE FUNDO
    private static final String IMAGEMFUNDO = "recursos\\Sprites\\Fundos\\FundoTelas.jpg";
    private static final String IMGCONTROLES = "recursos\\Sprites\\Fundos\\TelaControle.png";
    

    public Controles() {
        super.setVisibilidade(false);
    }

    @Override
    public void carregar() {
        ImageIcon carregando = new ImageIcon(IMAGEMFUNDO);
        super.setImagem(carregando.getImage());
        super.setImagem(super.getImagem().getScaledInstance(getTelaTamanho().LARGURA_TELA, getTelaTamanho().ALTURA_TELA,
                Image.SCALE_FAST));

        // IMAGEM SOBREPOSIÇÃO
        ImageIcon imgcontrole = new ImageIcon(IMGCONTROLES);
        this.imageControle = imgcontrole.getImage();
        // REDIMENSIONA O TAMANHO DA IMAGEM PARA O TAMANHO DA TELA
        this.imageControle = this.imageControle.getScaledInstance(getTelaTamanho().LARGURA_TELA,
                getTelaTamanho().ALTURA_TELA,
                Image.SCALE_FAST);
    };

    // CARREGANDO A IMAGEM DE FUNDO E A IMAGEM COM OS COMANDOS
    public void conteudo(Graphics g) {
        g.drawImage(super.getImagem(), 0, 0, null);
        g.drawImage(imageControle, 0, 0, null);
    }

}
