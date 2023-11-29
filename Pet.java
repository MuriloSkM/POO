/*
 * Classe genérica Pet para extender animal.
 * Tentando utilizar um pouco de polimorfismo com uma validação para diferentes tipos de animais.
 */

class Pet extends Animal {
    private String porte;

    public Pet(String nomePet, String especie, double peso, String porte) {
        super(nomePet, especie, peso);
        this.porte = porte;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    @Override
    public void emitirSom() {
        if (getEspecie().equalsIgnoreCase("Cachorro")) {
            System.out.println("Au au!");
        } else if (getEspecie().equalsIgnoreCase("Gato")) {
            System.out.println("Miau!");
        } else {
            System.out.println("Que bicho exótico!");
        }
    }
}