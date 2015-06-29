package org.objectFake;

import org.interfaces.Entidade;
import java.util.HashMap;
import java.util.Map;

public class Source implements Entidade {

    private Map<String, Object> values = new HashMap<>();

    private Integer id;

    public static Source criarInstancia() {
        Source solicitacaoProdutoPojo = new Source();
        return criarInstancia(solicitacaoProdutoPojo);
    }

    public static Source criarInstancia(Source solicitacaoProdutoPojo) {
        if (solicitacaoProdutoPojo.values == null) {
            solicitacaoProdutoPojo.values = new HashMap<>();
        }
        return solicitacaoProdutoPojo;
    }

    public void put(String key, Object object) {
        values.put(key, object);
    }

    public Object next(String key) {
        Object get = values.get(key);
        return get;
    }

    public void remover(String key) {
        values.remove(key);
    }

    public void replace(String key, Object object) {
        values.remove(key);
        values.put(key, object);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getDisplay() {
        return null;
    }
}
