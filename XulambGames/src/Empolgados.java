class Empolgados extends Cliente{
    static final float DESCONTO;
    static final float MENSALIDADE;

    static{
        DESCONTO = 0.1f;
        MENSALIDADE = 10;
    }


    public Empolgados(String nome, String nomeUsario, String senha) {
        super(nome, nomeUsario, senha);
        
    }

}