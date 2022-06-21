import java.net.PasswordAuthentication;
import java.util.ArrayList;

public class XulambGames {
    private ArrayList<Compra> compras;
    private ArrayList<Cliente> clientes;
    private ArrayList<Jogos> jogos;

    private static XulambGames instance;

    private XulambGames(){}


    public static XulambGames getInstance(){
        if (instance == null){
            instance = new XulambGames();
        } 
        return instance;
    }

    public float valorMensalVendido(){
        return 1.6f;
    }

    public float valorMedioCompras(){
        return 1.6f;
    }

    public float jogosExtremos(){
        return 1.6f;
    }

    public void adicionarCliente(Cliente cliente){
        
    }

    public void adicionarJogo(Jogos jogo){   
    }
}
