package br.ifpr.paranavai.jogo.Services.player;

import java.util.List;

import br.ifpr.paranavai.jogo.dao.player.PlayerDao;
import br.ifpr.paranavai.jogo.dao.player.PlayerDaoImpl;
import br.ifpr.paranavai.jogo.model.Character.Player;

public class PlayerService {
    private static PlayerDao dao = new PlayerDaoImpl();

    public static List<Player> searchAll() {
        return dao.searchAll();
    }

    public static Player searchForId(Integer id) {
        return dao.searchForId(id);
    }

    public static void insert(Player player) {
        dao.insert(player);
    }

    public static void modify(Player player) {
        dao.modify(player);
    }

    public static void delete(Player player) {
        dao.delete(player);
    }

}
