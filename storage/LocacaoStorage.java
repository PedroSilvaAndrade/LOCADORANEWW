package storage;

import classes.Locacao;
import java.util.ArrayList;
import java.util.List;

public class LocacaoStorage {

    // Instância única da classe (Singleton)
    private static final LocacaoStorage instance = new LocacaoStorage();

    // Lista de locações
    private final List<Locacao> locacoes;

    // Construtor privado para evitar instanciação externa
    private LocacaoStorage() {
        locacoes = new ArrayList<>();
    }

    // Método para obter a instância única da classe
    public static LocacaoStorage getInstance() {
        return instance;
    }

    // Método para adicionar uma locação
    public void adicionarLocacao(Locacao locacao) {
        locacoes.add(locacao);
    }

    // Método para obter a lista de locações
    public List<Locacao> getLocacoes() {
        return locacoes;
    }
}