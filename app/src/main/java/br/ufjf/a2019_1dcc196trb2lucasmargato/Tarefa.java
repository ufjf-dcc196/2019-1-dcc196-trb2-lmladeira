package br.ufjf.a2019_1dcc196trb2lucasmargato;

import java.util.Date;

public class Tarefa {
    private String titulo;
    private String descricao;
    private Dificuldade dificuldade;
    private String limite;
    private String ultimaModificacao;
    private Estado estado;

    public Tarefa(String titulo, String descricao, Dificuldade dificuldade, String limite) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
        this.limite = limite;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getLimite() {
        return limite;
    }

    public void setLimite(String limite) {
        this.limite = limite;
    }

    public String getUltimaModificacao() {
        return ultimaModificacao;
    }

    public void setUltimaModificacao(String ultimaModificacao) {
        this.ultimaModificacao = ultimaModificacao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
