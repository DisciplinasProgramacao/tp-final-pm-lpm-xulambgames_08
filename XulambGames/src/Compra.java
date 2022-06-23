import java.io.Serializable;
import java.util.*;

public class Compra implements Serializable{
    private final static double DESCONTO20;
    private final static double DESCONTO10;

    static{
        DESCONTO20 = .8;
        DESCONTO10 = .9;
    }

    private Data dataCompra;    
    private ArrayList<Jogos> jogos;
    private Cliente cliente;
    private HashMap<String, Integer> categorias;

    public Compra(Data dataCompra, Jogos
                  jogo, Cliente cliente) {
        this.dataCompra = dataCompra;
        this.cliente = cliente;
        this.jogos = new ArrayList<>();
        this.categorias = new HashMap<String, Integer>();
        this.adicionarJogo(jogo);
        this.cliente.adicionarCompra(this);
    }
    
    public void adicionarJogo(Jogos jogo) {
        Categoria categoria = jogo.getCategoria();
        Integer quantidade = this.quantidadeJogosPorCategoria(categoria);
        this.jogos.add(jogo);
        this.categorias.put(categoria.toString(), quantidade + 1);
        jogo.acrescentarVenda();
    }

    // public void removerJogo(Jogos jogo){
    //     for (Jogos jogoCadastrado : jogos){
    //         if (jogoCadastrado.equals(jogo)){
    //             this.jogos.remove(jogoCadastrado);
    //             String categoria = jogo.getCategoria().toString();
    //             this.categorias.put(categoria, categorias.get(categoria) -1);
    //         }
    //     }
    // }

    public String relatorio() {
        return "RELATORIO COMPRA\nData compra: " +this.dataCompra.dataFormatada() + "\nCliente: " + this.cliente + "\n" + this.listaJogos() + "\nValor compra: R$" + this.valorCompra();
    }

    private String listaJogos(){
        StringBuilder lista = new StringBuilder("Lista Jogos:\n");
        for (Jogos jogo : jogos){
            lista.append(jogo);
        }
        return lista.toString();
    }

    @Override
    public String toString() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("\nData compra: " + this.dataCompra);
        relatorio.append("\nCliente:" + this.cliente);
        relatorio.append("\nJogos: " + this.listaJogos());
        return relatorio().toString();
    }

    public Data getData() {
        return this.dataCompra;
    }

    private double descontoCompra(){
        double descontoFinal = 1;
        ArrayList<Double> descontos = this.verificaDescontos();  
        for (Double desconto : descontos){
            if (desconto < descontoFinal){
                descontoFinal = desconto;
            }
        }
        return descontoFinal;
    }

    private ArrayList<Double> verificaDescontos(){
        ArrayList<Double> descontos = new ArrayList<>();  
        descontos.add(this.descontoLancamento());
        descontos.add(this.descontoPremium());
        descontos.add(this.descontoRegulares());
        return descontos;
    }

    private double descontoLancamento(){
        Categoria categoria = Categoria.LANCAMENTOS;
        int quantidade = this.quantidadeJogosPorCategoria(categoria); 
        return quantidade >= 2? DESCONTO20 : 1;
    }

    private double descontoPremium(){
        Categoria categoria = Categoria.PREMIUM;
        int quantidadePremium = this.quantidadeJogosPorCategoria(categoria);
        int quantidadeJogos = this.quantidadeJogos();
        double desconto = 1;

        if ((quantidadePremium == 2 && quantidadeJogos >= 3) ||quantidadePremium >= 3){
            desconto = DESCONTO20;
        }else if(quantidadePremium == 2){
            desconto = DESCONTO10;
        }
        return desconto;
    }

    private double descontoRegulares(){
        int quantidadeRegulares = this.quantidadeJogosPorCategoria(Categoria.REGULARES);
        int quantidadeJogosPremiumLancamentos = this.quantidadeJogosPorCategoria(Categoria.PREMIUM) + this.quantidadeJogosPorCategoria(Categoria.LANCAMENTOS);
        double desconto = 1;

        if (quantidadeRegulares == 5 || (quantidadeRegulares == 3 && quantidadeJogosPremiumLancamentos >= 1)){
            desconto = DESCONTO20;
        }
        else if (quantidadeRegulares == 4){
            desconto = DESCONTO10;            
        }
        return desconto;
    }

    private int quantidadeJogosPorCategoria(Categoria categoria){
        Integer quantidade = this.categorias.get(categoria.toString());
        return quantidade == null ? 0 : quantidade;
    }

    private int quantidadeJogos(){
        int quantidadeJogos = 0;
        for (Map.Entry<String, Integer> categoria : this.categorias.entrySet()){
            quantidadeJogos += categoria.getValue();
        }
        return quantidadeJogos;
    }

    public boolean procurarCategoria(Categoria categoria) {
        for (Jogos jogo : jogos){
            if (jogo.getCategoria() == categoria){
                return true;
            }
        }
        return false;
    }

    public double valorCompra(){
        return this.valorJogos() * this.descontoCompra() * this.cliente.getTipoCliente().getFatorDesconto();
    }

    private double valorJogos(){
        double preco = 0;
        for (Jogos jogo : jogos){
            preco += jogo.precoFinal();
        }
        return preco;
    }

    public boolean verificarMes(int mes) {
        return this.dataCompra.getMes() == mes;
    }
    
}
