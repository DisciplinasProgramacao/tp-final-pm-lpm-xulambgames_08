import java.io.Serializable;

public class Jogos implements Serializable{
    private double precoOriginal;
    private Categoria categoria;
    private String nome;
    private double precoVenda;
    private int quantidadeVendido;

    public Jogos(double precoOriginal, 
             Categoria categoria, 
             String nome) {
        this.setPrecoOriginal(precoOriginal);
        this.categoria = categoria;
        this.nome = nome;
        this.precoVenda = 0;
        this.quantidadeVendido = 0;
    }
    

    private void setPrecoOriginal(double precoOriginal) {
        if (precoOriginal > 10 && precoOriginal <=500){
            this.precoOriginal = precoOriginal;
        }
        else{
            this.precoOriginal = 250;
        }
    }


    public double precoFinal(){
        return this.precoVenda == 0? this.precoOriginal * categoria.getValorPagar() : this.precoVenda;
    }

    public void mudarCategoria(Categoria categoria){
        this.categoria = categoria;
        this.precoVenda = 0;
    }

    public String mudarPrecoVenda(double preco){
        double porcentagemPreco = preco / this.precoOriginal;
        if (porcentagemPreco >= this.categoria.getMenorPreco() && porcentagemPreco < this.categoria.getMaiorPreco()){
            this.precoVenda = preco;
            return "Preco de venda alterado com sucesso";
        }
        return "Preco sugerido nao esta dentro do limite da categoria";
    }

    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder("Titulo: " + this.nome);
        retorno.append("\nPreco: R$" + this.precoFinal());
        retorno.append("\nCategoria: " + this.categoria);
        retorno.append("\nQuantidade vendida= " + this.quantidadeVendido);
        return retorno.toString();
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

    public void acrescentarVenda(){
        this.quantidadeVendido++;
    }

    public int getQuantidadeVendida(){
        return this.quantidadeVendido;
    }
}
