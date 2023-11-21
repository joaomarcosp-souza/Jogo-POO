package br.ifpr.paranavai.jogo.Services.Enemies;

import java.util.List;

import br.ifpr.paranavai.jogo.dao.enemies.Ships.ShipDao;
import br.ifpr.paranavai.jogo.dao.enemies.Ships.ShipDaoImpl;
import br.ifpr.paranavai.jogo.model.Enemies.Naves;

public class ShipService {

    private static ShipDao shipDao = new ShipDaoImpl();

    public static List<Naves> searchAll() {
        return shipDao.searchAll();
    }

    public static Naves searchForId(Integer id) {
        return shipDao.searchForId(id);
    }

    public static void insert(Naves naves) {
        shipDao.insert(naves);
    }

    public static void modify(Naves naves) {
        shipDao.modify(naves);
    }

    public static void delete(Naves naves) {
        shipDao.delete(naves);
    }
}
