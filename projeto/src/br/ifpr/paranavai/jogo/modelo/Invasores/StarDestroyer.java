package br.ifpr.paranavai.jogo.modelo.Invasores;

import javax.swing.ImageIcon;
import br.ifpr.paranavai.jogo.modelo.Base;

public class StarDestroyer extends Base {

    private boolean chefaoVivo;
    private static final String IMAGEM_STAR_DESTROYER = "recursos\\Sprites\\Inimigos\\starDestroyer.png";

    public StarDestroyer(int posicaoEmX, int posicaoEmY) {
        super.setVelocidadeInicial(3);
        super.setVelocidade(super.getVelocidadeInicial());
        super.setPosicaoEmX(posicaoEmX);
        super.setPosicaoEmY(posicaoEmY);
        super.setVisibilidade(true);
    }

    @Override
    public void carregar() {
        ImageIcon carregador = new ImageIcon(IMAGEM_STAR_DESTROYER);
        super.setImagem(carregador.getImage());
        super.setLarguraImagem(super.getImagem().getWidth(null));
        super.setAlturaImagem(super.getImagem().getHeight(null));
    }

    @Override
    public void atualizar() {
        super.setPosicaoEmX((int) (super.getPosicaoEmX() - super.getVelocidade()));
        if (super.getPosicaoEmX() < -1000) {
            super.setVisibilidade(false);
        }

        if (super.getPosicaoEmY() < 0) {
            super.setPosicaoEmY(0);
        } else if (super.getPosicaoEmY() > super.getTelaTamanho().ALTURA_TELA - super.getAlturaImagem()) {
            super.setPosicaoEmY(super.getTelaTamanho().ALTURA_TELA - super.getAlturaImagem());
        }
    }

    // GETTERS E SETTERS
    public boolean isChefaoVivo() {
        return chefaoVivo;
    }

    public void setChefaoVivo(boolean chefaoVivo) {
        this.chefaoVivo = chefaoVivo;
    }
}
