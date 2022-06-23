import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

class Cliente implements Serializable{
    private String nome;
    private String nomeUsuario;
    private String senha;
    private TipoCliente tipoCliente; 
    private ArrayList<Compra> historicoCompras;

    public Cliente(String nome, 
                    String nomeUsario,
                    String senha,
                    TipoCliente tipoCliente){
        this.nome = nome;
        this.nomeUsuario = nomeUsario;
        this.senha = senha;
        this.tipoCliente = tipoCliente;
        System.out.println(this);
        historicoCompras = new ArrayList<Compra>();
    }

    public void adicionarCompra(Compra compra){
        historicoCompras.add(compra);
    }

    public void filtrarCompraCategoria(Categoria categoria){
        for (Compra compra : historicoCompras){
            if (compra.procurarCategoria(categoria)){
                compra.relatorio();
            }
        }
    }

    public void filtrarCompraData(Data data){
        for (Compra compra : historicoCompras){
            if (compra.getData().equals(data)){
                compra.relatorio();
            }
        }
    }

    public TipoCliente getTipoCliente() {
        return this.tipoCliente;
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nNome usuario: " + this.nomeUsuario + "\nTipo cliente: " + this.tipoCliente;
    }


}