public class Fanaticos extends Cliente{

    static final float DESCONTO;
    static final float MENSALIDADE;

    static{
        DESCONTO = 0.3f;
        MENSALIDADE = 25;
    }


    public Fanaticos(String nome, String nomeUsario, String senha) {
        super(nome, nomeUsario, senha);
        
    }
    

}
