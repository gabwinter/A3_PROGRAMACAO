package Sistema_Escola;

class Sala {
    private String nome;
    private String local;
    private int capacidadeTotal;

    public Sala(String nome, String local, int capacidadeTotal) {
        this.nome = nome;
        this.local = local;
        this.capacidadeTotal = capacidadeTotal;
    }

    // Getters

    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public int getCapacidadeTotal() {
        return capacidadeTotal;
    }
}