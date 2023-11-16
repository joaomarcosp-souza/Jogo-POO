package br.ifpr.paranavai.jogo.dao.player;

import java.util.List;
import br.ifpr.paranavai.jogo.model.Character.Player;

public interface PlayerDao {
    public List<Player> searchAll();
    public Player searchForId(Integer id);
    public void modify(Player player);
    public void delete(Player player);
    public void insert(Player player);
}
