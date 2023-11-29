public enum Procedimentos {
    CONSULTA(30.00), 
    VACINA(70.00),
    BANHO(50.00), 
    TOSA(65.00), 
    CIRURGIA(250.00);

    private final double valor;

    Procedimentos(double valor){
        this.valor = valor;
    }
    public double getValor(){
        return valor;
    }
}