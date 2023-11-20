package br.ifpr.paranavai.jogo.dao.enemies.meteor;

import java.util.List;

import br.ifpr.paranavai.jogo.model.Enemies.Meteorito;

public interface  MeteorDao {
    public List<Meteorito> searchAll();
    public Meteorito searchForId(Integer id);
    public void modify(Meteorito meteorito);
    public void delete(Meteorito meteorito);
    public void insert(Meteorito meteorito);
}
