public class Jogos {
    private double precoOriginal;
    private Categoria categoria;
    private String nome;

    public Jogos(double precoOriginal, 
             Categoria categoria, 
             String nome) {
        this.precoOriginal = precoOriginal;
        this.categoria = categoria;
        this.nome = nome;
    }
    

    public double precoFinal(){
        return this.precoOriginal * categoria.getValorPagar();
    }

    @Override
    public String toString() {
        return "Titulo: " + this.nome + "\nPreco: R$" + this.precoFinal() + "\nCategoria: " + this.categoria;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public boolean equals(Jogos jogo) {
        return this.compareTo(jogo) == 0? true : false;
    }

    private int compareTo(Jogos jogo) {
        return this.nome.compareTo(jogo.getNome());
    }

    public String getNome() {
        return nome;
    }
}
