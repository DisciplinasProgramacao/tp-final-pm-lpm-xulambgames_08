import java.sql.Date;
import java.util.ArrayList;

abstract class Cliente{
    private String nome;
    private String nomeUsuario;
    private String senha;
    private ArrayList<Compra> historicoCompras;

    public Cliente(String nome, 
                    String nomeUsario,
                    String senha){
        this.nome = nome;
        this.nomeUsuario = nomeUsario;
        this.senha = senha;

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

    public void filtrarCompraData(Date data){
        for (Compra compra : historicoCompras){
            if (compra.getData() == data){
                compra.relatorio();
            }
        }
    }


}