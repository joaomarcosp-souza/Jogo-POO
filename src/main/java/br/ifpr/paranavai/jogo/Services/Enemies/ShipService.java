package br.ifpr.paranavai.jogo.Services.Enemies;

import java.util.List;

import br.ifpr.paranavai.jogo.dao.enemies.Ships.ShipDao;
import br.ifpr.paranavai.jogo.dao.enemies.Ships.ShipDaoImpl;
import br.ifpr.paranavai.jogo.model.Enemies.Naves;

public class ShipService {

    private static ShipDao daoAsteroid = new ShipDaoImpl();

    public static List<Naves> searchAll() {
        return daoAsteroid.searchAll();
    }

    public static Naves searchForId(Integer id) {
        return daoAsteroid.searchForId(id);
    }

    public static void insert(Naves naves) {
        daoAsteroid.insert(naves);
    }

    public static void modify(Naves naves) {
        daoAsteroid.modify(naves);
    }

    public static void delete(Naves naves) {
        daoAsteroid.delete(naves);
    }
}
