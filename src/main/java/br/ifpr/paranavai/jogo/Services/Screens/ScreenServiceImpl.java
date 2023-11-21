package br.ifpr.paranavai.jogo.services.Screens;

import java.awt.event.KeyEvent;

import br.ifpr.paranavai.jogo.model.StageModel;
import br.ifpr.paranavai.jogo.services.stage.StageServiceImpl;

public class ScreenServiceImpl implements ScreenService {

    private StageModel stageModel;
    private StageServiceImpl serviceImpl;

    public ScreenServiceImpl(StageModel stageModel, StageServiceImpl serviceImpl) {
        this.stageModel = stageModel;
        this.serviceImpl = serviceImpl;
    }

    @Override
    public void visibilityControlMenu(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ENTER) {
            if (stageModel.getScreenMenu().getCursor() == 0) {
                stageModel.getPlayer().setPlaying(true);
                stageModel.getScreenMenu().setVisibility(false);
            } else if (stageModel.getScreenMenu().getCursor() == 1) {
                if (serviceImpl.isGamesaved() == true) {
                    serviceImpl.loadLastSaveElements();
                    stageModel.getPlayer().setPlaying(true);
                    stageModel.getScreenMenu().setVisibility(false);
                }
            } else if (stageModel.getScreenMenu().getCursor() == 2) {
                stageModel.getScreenControls().setVisibility(true);
                stageModel.getScreenMenu().setVisibility(false);
            } else if (stageModel.getScreenMenu().getCursor() == 3) {
                // stageModel.getScreenMenu().setVisibility(false);
                // stageModel.getScreenHistory().setVisibility(true);
            }
        }
    }

    @Override
    public void visibilityControlScreens(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (stageModel.getScreenControls().isVisibility() == true) {
            if (tecla == KeyEvent.VK_ESCAPE) {
                stageModel.getScreenControls().setVisibility(false);
                stageModel.getScreenMenu().setVisibility(true);
            }
        }
        if (stageModel.getScreenHistory().isVisibility() == true) {
            if (tecla == KeyEvent.VK_ESCAPE) {
                stageModel.getScreenHistory().setVisibility(false);
                stageModel.getScreenMenu().setVisibility(true);
            }
        }
    }

    @Override
    public void visibilityScreenPause(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_ESCAPE) {
            if (stageModel.getPlayer().isPlaying()) {
                stageModel.getPlayer().setPlaying(false);
                stageModel.getScreenPaused().setPaused(true);
            }
        }
    }

    @Override
    public void visibilityControlEndGame(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (stageModel.getScreenEndGame().isVisibility() == true) {
            if (tecla == KeyEvent.VK_ENTER) {
                if (stageModel.getScreenEndGame().getCursor() == 0) {
                    stageModel.getPlayer().setPlaying(true);
                    stageModel.getScreenEndGame().setVisibility(false);
                }
                if (stageModel.getScreenEndGame().getCursor() == 1) {
                    stageModel.getScreenEndGame().setVisibility(false);
                    stageModel.getScreenMenu().setVisibility(true);
                }
            }
        }
    }
}
