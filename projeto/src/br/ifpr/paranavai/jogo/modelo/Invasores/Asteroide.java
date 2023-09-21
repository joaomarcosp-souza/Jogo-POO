package br.ifpr.paranavai.jogo.modelo.Invasores;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;
import br.ifpr.paranavai.jogo.modelo.Base;

public class Asteroide extends Base {

    private Image selectImage;
    private List<String> listImage = new ArrayList<>();
    private final String PATH_FOLDER = "recursos\\Sprites\\Inimigos\\asteroides";

    public Asteroide(int posicaoEmX, int posicaoEmY) {
        super.setPositionInX(posicaoEmX);
        super.setPositionInY(posicaoEmY);
        super.setInitialSpeed(2);
        super.setSpeed(super.getInitialSpeed());
        super.setVisibility(true);
        carregarImagensAleatorias();
    }

    private void carregarImagensAleatorias() {
        try {
            File directory = new File(PATH_FOLDER);
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    // SE AS DUAS CONDIÇÕES FOREM VERDADEIRAS
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".png")) {
                        // ENTÃO JUNTA OS DOIS CAMINHOS EM UM CAMINHO ABSOLUTO
                        listImage.add(file.getAbsolutePath());
                    }
                }
            }

            if (listImage.isEmpty()) {
                System.out.println("Nenhuma imagem Carregada");
                return;
            }

            // GERA UM VALOR ALEATORIO PRA PEGAR UMA IMAGEM DIFERENTE
            Random random = new Random();
            int randomValue = random.nextInt(listImage.size());
            String pathSelectedImage = listImage.get(randomValue);

            // CARREGA A IMG SELECIONADA
            ImageIcon charge = new ImageIcon(pathSelectedImage);
            selectImage = charge.getImage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void carregar() {
        super.setImage(selectImage);
        super.setWidthImage(selectImage.getWidth(null));
        super.setHeightImage(selectImage.getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPositionInX((int) (super.getPositionInX() - super.getSpeed()));
        if (super.getPositionInX() < -10) {
            super.setVisibility(false);
        }

        if (super.getPositionInY() < 0) {
            super.setPositionInY(0);
        } else if (super.getPositionInY() > super.getScreenResolution().HEIGHT_SCREEN - super.getHeightImage()) {
            super.setPositionInY(super.getScreenResolution().HEIGHT_SCREEN - super.getHeightImage());
        }
    }

}
