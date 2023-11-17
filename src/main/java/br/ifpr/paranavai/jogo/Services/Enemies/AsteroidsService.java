package br.ifpr.paranavai.jogo.Services.Enemies;

import java.util.List;

import br.ifpr.paranavai.jogo.dao.enemies.Asteroids.AsteroidDao;
import br.ifpr.paranavai.jogo.dao.enemies.Asteroids.AsteroidDaoImpl;
import br.ifpr.paranavai.jogo.model.Enemies.Asteroide;

public class AsteroidsService {

    private static AsteroidDao daoAsteroid = new AsteroidDaoImpl(); 

    public static List<Asteroide> searchAll() {
        return daoAsteroid.searchAll();
    }

    public static Asteroide searchForId(Integer id) {
        return daoAsteroid.searchForId(id);
    }

    public static void insert(Asteroide asteroide) {
        daoAsteroid.insert(asteroide);
    }

    public static void modify(Asteroide asteroide) {
        daoAsteroid.modify(asteroide);
    }

    public static void delete(Asteroide asteroide) {
        daoAsteroid.delete(asteroide);
    }
}
