
package com.livro.api.livroapi.model;

public enum Genero {
    
    NARRATIVO("Narrativo"),
    LIRICO("Lírico"),
    DRAMATICO("Dramático"),
    MEMORIAS("Memórias"),
    AUTOBIOGRAFIA("Autobiografia"),
    BIOGRAFIA("Biografia"),
    POESIA("Poesia"),
    SATIRA("Sátira"),
    HISTORIAS("Histórias"),
    FANFICTION("Fanfiction");
    
    private String value;
    
    private Genero(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
}
