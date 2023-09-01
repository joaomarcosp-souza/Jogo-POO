package br.ifpr.paranavai.jogo.modelo.Invasores;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;

import br.ifpr.paranavai.jogo.modelo.Base;

public class Asteroide extends Base {

    private final String CAMINHO_IMGS = "recursos\\Sprites\\Inimigos\\asteroides";
    private List<String> listaImagens = new ArrayList<>();
    private Image imagemSelecionada;

    public Asteroide(int posicaoEmX, int posicaoEmY) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVelocidadeInicial(4);
        super.setVelocidade(super.getVelocidadeInicial());
        super.setVisibilidade(true);
        carregarImagensAleatorias();
    }

    private void carregarImagensAleatorias() {
        try {
            File diretorio = new File(CAMINHO_IMGS);
            File[] arquivos = diretorio.listFiles();

            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    if (arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".png")) {
                        listaImagens.add(arquivo.getAbsolutePath());
                    }
                }
            }

            if (listaImagens.isEmpty()) {
                System.out.println("Nenhuma imagem");
                return;
            }

            Random random = new Random();
            int indiceAleatorio = random.nextInt(listaImagens.size());
            String caminhoImagemSelecionada = listaImagens.get(indiceAleatorio);

            // CARREGA A IMG SELECIONADA
            ImageIcon carregador = new ImageIcon(caminhoImagemSelecionada);
            imagemSelecionada = carregador.getImage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void carregar() {
        super.setImagem(imagemSelecionada);
        super.setLarguraImagem(imagemSelecionada.getWidth(null));
        super.setAlturaImagem(imagemSelecionada.getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX((int) (super.getPosicaoEmX() - super.getVelocidade()));
        if (super.getPosicaoEmX() < -10) {
            super.setVisibilidade(false);
        }

        // VERIFICANDO SE A NAVE INIMIGA ESTA NASCENDO DENTRO DOS LIMETES DA TELA
        if (super.getPosicaoEmY() < 0) {
            super.setPosicaoEmY(0);
        } else if (super.getPosicaoEmY() > super.getTelaTamanho().ALTURA_TELA - super.getAlturaImagem()) {
            super.setPosicaoEmY(super.getTelaTamanho().ALTURA_TELA - super.getAlturaImagem());
        }
    }

}
