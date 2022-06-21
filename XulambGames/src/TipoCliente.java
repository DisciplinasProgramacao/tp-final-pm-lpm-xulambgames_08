public enum TipoCliente {
    CADASTRADOS(1f, 0f),
    EMPOLGADOS(.9f, 10f),
    FANATICOS(.7f, 25f);

    private float fatorDesconto;
    private float mensalidade;

    TipoCliente(float fatorDesconto, float mensalidade) {
        this.fatorDesconto = fatorDesconto;
        this.mensalidade = mensalidade;
    }

    public float getFatorDesconto(){
        return fatorDesconto;
    }

    public float getMensalidade(){
        return mensalidade;
    }

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
