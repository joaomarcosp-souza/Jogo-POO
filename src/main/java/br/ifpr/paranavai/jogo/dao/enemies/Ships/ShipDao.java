package br.ifpr.paranavai.jogo.dao.enemies.Ships;

import java.util.List;
import br.ifpr.paranavai.jogo.model.Enemies.Naves;

public interface ShipDao {
    public List<Naves> searchAll();
    public Naves searchForId(Integer id);
    public void modify(Naves ships);
    public void delete(Naves ships);
    public void insert(Naves ships);
}
