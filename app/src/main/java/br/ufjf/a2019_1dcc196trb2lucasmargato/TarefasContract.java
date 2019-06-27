package br.ufjf.a2019_1dcc196trb2lucasmargato;

import android.provider.BaseColumns;

public final class TarefasContract {

    public static final class Tarefa implements BaseColumns {
        public static final String TABLE_NAME = "tarefa";
        public static final String COLLUMN_TITULO = "titulo";
        public static final String COLLUMN_DESCRICAO = "descricao";
        public static final String COLLUMN_DIFICULDADE = "dificuldade";
        public static final String COLLUMN_LIMITE = "limite";
        public static final String COLLUMN_ULTIMAMODIFICACAO = "ultimamodificacao";
        public static final String COLLUMN_ESTADO = "estado";


        public static final String CREATE_TABLE = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, " + // Titulo
                        "%s TEXT, " + // Descricao
                        "%s TEXT, " + // DIFICULDADE
                        "%s TEXT, " + // LIMITE
                        "%s TEXT, " + // Ultima mod
                        "%s TEXT)", // Estado
                TABLE_NAME, _ID, COLLUMN_TITULO, COLLUMN_DESCRICAO, COLLUMN_DIFICULDADE, COLLUMN_LIMITE, COLLUMN_ULTIMAMODIFICACAO, COLLUMN_ESTADO);

        public static final String DROP_TABLE = String.format("DROP TABLE %s", TABLE_NAME);

    }

    public static final class Tag implements BaseColumns {
        public static final String TABLE_NAME = "tag";
        public static final String COLLUMN_TAG = "tag";

        public static final String CREATE_TABLE = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT)",
                TABLE_NAME, _ID, COLLUMN_TAG);

        public static final String DROP_TABLE = String.format("DROP TABLE %s", TABLE_NAME);

    }

    public static final class TagsTarefa implements BaseColumns {
        public static final String TABLE_NAME = "tagstarefa";
        public static final String COLLUMN_TAG = "idtag";
        public static final String COLLUMN_TAREFA = "idtarefa";

        public static final String CREATE_TABLE = String.format("CREATE TABLE %s (" +
                        "%s INTEGER, " +
                        "%s INTEGER, " +
                        "PRIMARY KEY(%s, %s));",
                TABLE_NAME, COLLUMN_TAG, COLLUMN_TAREFA, COLLUMN_TAG, COLLUMN_TAREFA);

        public static final String DROP_TABLE = String.format("DROP TABLE %s", TABLE_NAME);

    }

}
