public abstract class Animal {
    private String nomePet;
    private String especie;
    private double peso;

    public Animal(String nomePet, String especie, double peso) {
        this.nomePet = nomePet;
        this.especie = especie;
        this.peso = peso;
    }

    public String getNomePet(){
        return nomePet;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public abstract void emitirSom();
}