import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class XulamTest {

    Jogos jogo;
    Categoria categoria;
    Cliente cliente;
    Compra compra;

    @BeforeEach
    public void init(){
        categoria = Categoria.REGULARES;
    	jogo = new Jogos(150, categoria, "Jogos Mortais 7");
        cliente = new Cliente("Vitor Salem", "Costinha123", "123456", TipoCliente.FANATICOS);
        Date data = new Date(2022, 6, 19);
        compra = new Compra(data, jogo, cliente);
    }

    @Test
    public void precoJogo(){
        assertEquals(127.5d, jogo.precoFinal(), 0.1);
    }

    @Test
    public void MudarPrecoJogo(){
        assertEquals("Preco de venda alterado com sucesso", jogo.mudarPrecoVenda(120));
    }

    @Test
    public void precoJogoAlterado(){
        jogo.mudarPrecoVenda(120);
        assertEquals(120, jogo.precoFinal(), 0.1);
    }

    @Test
    public void precoCategoriaAlterada(){
        jogo.mudarPrecoVenda(120);
        categoria = Categoria.PROMOCOES;
        jogo.mudarCategoria(categoria);
        assertEquals(60, jogo.precoFinal(), 0.1);
    }

    @Test
    public void MudarsPrecoJogo(){
        assertEquals("Preco sugerido nao esta dentro do limite da categoria", jogo.mudarPrecoVenda(160));
    }

    @Test
    public void valorCompra(){
        assertEquals(89.25, compra.valorCompra(), 0.2);
    }

    @Test
    public void valorCompraDesconto(){
        compra.adicionarJogo(jogo);
        compra.adicionarJogo(jogo);
        compra.adicionarJogo(jogo);
        assertEquals(321.3, compra.valorCompra(), 0.1);
    }



}
