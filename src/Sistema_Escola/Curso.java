package Sistema_Escola;

class Curso {
    private String codigoCurso;
    private String nomeCurso;
    private int cargaHoraria;
    private String descricao;

    public Curso(String codigoCurso, String nomeCurso, int cargaHoraria, String descricao) {
        this.codigoCurso = codigoCurso;
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;
        this.descricao = descricao;
    }

    // Getters

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String getDescricao() {
        return descricao;
    }
}