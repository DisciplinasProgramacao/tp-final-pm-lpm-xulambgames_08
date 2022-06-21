public enum Categoria {
    LANCAMENTOS(0d, 1.1),
    PREMIUM(0d, 1),
    REGULARES(0.7, 1d),
    PROMOCOES(0.3, 0.5);

    private double menorPreco;
    private double maiorPreco;


    Categoria(double menorPreco,double maiorPreco) {
        this.menorPreco = menorPreco;
        this.maiorPreco = maiorPreco;
    }

    public double getValorPagar(){
        return this.menorPreco == 0 ? this.maiorPreco : (this.maiorPreco + this.menorPreco) / 2;
    }

    public double getMaiorPreco(){
        return this.maiorPreco;
    }

    public double getMenorPreco(){
        return this.menorPreco;
    }


    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
