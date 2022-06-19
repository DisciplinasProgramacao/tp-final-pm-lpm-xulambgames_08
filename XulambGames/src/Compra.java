import java.util.*;

public class Compra {
    private Date dataCompra;    
    private ArrayList<Jogos> jogos;
    private Cliente cliente;
    private HashMap<String, Integer> categorias;

    public Compra(Date dataCompra, Jogos
                  jogo, Cliente cliente) {
        this.dataCompra = dataCompra;
        this.cliente = cliente;
        this.jogos = new ArrayList<>();
        this.categorias = new HashMap<String, Integer>();
        this.adicionarJogo(jogo);
    }
    
    private void adicionarJogo(Jogos jogo) {
        this.jogos.add(jogo);
        String categoria = jogo.getCategoria().toString();
        this.categorias.put(categoria, categorias.get(categoria) + 1);
    }

    public void removerJogo(Jogos jogo){
        for (Jogos jogoCadastrado : jogos){
            if (jogoCadastrado.equals(jogo)){
                this.jogos.remove(jogoCadastrado);
                String categoria = jogo.getCategoria().toString();
                this.categorias.put(categoria, categorias.get(categoria) -1);
            }
        }
    }

    public String relatorio() {
        return "RELATORIO COMPRA\n Data compra: " +this.dataCompra + "\n Cliente: " + this.cliente + "\n Jogos: " ;
    }

    private String listaJogos(){
        
    }
    public String getDatas() {
        return null;
    }

    public Date getData() {
        return null;
    }

    private double descontoLancamento(){
        
    }

    public boolean procurarCategoria(Categoria categoria) {
        for (Jogos jogo : jogos){
            if (jogo.getCategoria() == categoria){
                return true;
            }
        }
        return false;
    }
    
}
