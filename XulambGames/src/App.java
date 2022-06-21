import java.util.Date;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        Categoria categoria = Categoria.PROMOCOES;
        Categoria categoria1 = Categoria.LANCAMENTOS;

        Jogos jogo = new Jogos(150, categoria1, "Jogo Doido");

        Jogos novo = new Jogos(10, categoria, "Jogo Doido");
        // System.out.println(jogo.equals(novo));

        System.out.println(novo.getCategoria() == categoria);

        HashMap<String, Integer> mapa = new HashMap<>();
        Integer quantidade = mapa.get("cachorro");
        quantidade = quantidade == null? 0 : quantidade;
        mapa.put("cachorro", quantidade + 1);
        quantidade = mapa.get("cachorro");
        quantidade = quantidade == null? 0 : quantidade;
        mapa.put("cachorro", quantidade+ 1);


        System.out.println(mapa.get("cachorro"));

    }
}
