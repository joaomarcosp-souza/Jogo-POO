package br.ifpr.paranavai.jogo.services.enemies;

import java.util.List;

import br.ifpr.paranavai.jogo.dao.enemies.meteor.MeteorDao;
import br.ifpr.paranavai.jogo.dao.enemies.meteor.MeteorDaoImpl;
import br.ifpr.paranavai.jogo.model.Enemies.Meteorito;

public class MeteorService {

    private static MeteorDao daoMeteor = new MeteorDaoImpl();

    public static List<Meteorito> searchAll() {
        return daoMeteor.searchAll();
    }

    public static Meteorito searchForId(Integer id) {
        return daoMeteor.searchForId(id);
    }

    public static void insert(Meteorito meteorito) {
        daoMeteor.insert(meteorito);
    }

    public static void modify(Meteorito meteorito) {
        daoMeteor.modify(meteorito);
    }

    public static void delete(Meteorito meteorito) {
        daoMeteor.delete(meteorito);
    }
}
