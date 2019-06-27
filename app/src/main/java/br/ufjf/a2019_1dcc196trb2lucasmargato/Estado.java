package br.ufjf.a2019_1dcc196trb2lucasmargato;

public enum Estado { AFAZER(0), EMEXECUCAO(1), BLOQUEADA(2), CONCLUIDA(3);

    public int valEstado;
    Estado(int i) {
        valEstado = i;
    }
    public int getValor(){
        return valEstado;
    }

    public static Estado getEstado(String s){
        if (s.equals("AFAZER")) {
            return Estado.AFAZER;
        } else if (s.equals("EMEXECUCAO")) {
            return Estado.EMEXECUCAO;
        } else if (s.equals("BLOQUEADA")) {
            return Estado.BLOQUEADA;
        }else if (s.equals("CONCLUIDA")) {
            return Estado.CONCLUIDA;
        } else {
            return null;
        }
    }
}
