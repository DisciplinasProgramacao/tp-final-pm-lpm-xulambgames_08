public enum Categoria {
    LANCAMENTOS(1.1),
    PREMIUM(1),
    REGULARES(0.7),
    PROMOCOES(0.5);

    private double valorPagar;

    Categoria(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public double getValorPagar(){
        return this.valorPagar;
    }

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
