package br.ifpr.paranavai.jogo.modelo.Invasores;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;
import br.ifpr.paranavai.jogo.modelo.Base;

public class Asteroide extends Base {

    private Image selecionaImagem;
    private List<String> listaImagens = new ArrayList<>();
    private final String CAMINHO_PASTA = "recursos\\Sprites\\Inimigos\\asteroides";

    public Asteroide(int posicaoEmX, int posicaoEmY) {
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVelocidadeInicial(2);
        super.setVelocidade(super.getVelocidadeInicial());
        super.setVisibilidade(true);
        carregarImagensAleatorias();
    }

    private void carregarImagensAleatorias() {
        try {
            File diretorio = new File(CAMINHO_PASTA);
            File[] arquivos = diretorio.listFiles();

            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    // SE AS DUAS CONDIÇÕES FOREM VERDADEIRAS
                    if (arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".png")) {
                        // ENTÃO JUNTA OS DOIS CAMINHOS EM UM CAMINHO ABSOLUTO
                        listaImagens.add(arquivo.getAbsolutePath());
                    }
                }
            }

            if (listaImagens.isEmpty()) {
                System.out.println("Nenhuma imagem Carregada");
                return;
            }

            // GERA UM VALOR ALEATORIO PRA PEGAR UMA IMAGEM DIFERENTE
            Random random = new Random();
            int valorAleatorio = random.nextInt(listaImagens.size());
            String caminhoImagemSelecionada = listaImagens.get(valorAleatorio);

            // CARREGA A IMG SELECIONADA
            ImageIcon carregador = new ImageIcon(caminhoImagemSelecionada);
            selecionaImagem = carregador.getImage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void carregar() {
        super.setImagem(selecionaImagem);
        super.setLarguraImagem(selecionaImagem.getWidth(null));
        super.setAlturaImagem(selecionaImagem.getHeight(null));
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
