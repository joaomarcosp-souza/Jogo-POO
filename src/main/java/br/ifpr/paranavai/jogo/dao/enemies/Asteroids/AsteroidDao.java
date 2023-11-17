package br.ifpr.paranavai.jogo.dao.enemies.Asteroids;

import java.util.List;

import br.ifpr.paranavai.jogo.model.Enemies.Asteroide;

public interface AsteroidDao {
    public List<Asteroide> searchAll();
    public Asteroide searchForId(Integer id);
    public void modify(Asteroide meteorito);
    public void delete(Asteroide meteorito);
    public void insert(Asteroide meteorito);
}
