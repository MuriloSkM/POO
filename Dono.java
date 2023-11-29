import java.util.ArrayList;
import java.util.List;

public class Dono {
    private String cpf;
    private String nome;
    private String email;
    private List<Pet> petsDoDono;

    public Dono(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.petsDoDono = new ArrayList<>();
    }
    public String getNome() {
        return nome;
    }
    public String getCpf(){
        return cpf;
    }
    public String getEmail(){
        return email;
    }
    public List<Pet> getPetsDoDono(){
        return petsDoDono;
    }

    public void addPetAoDono(Pet pet) {
        petsDoDono.add(pet);
    }

    public void listarPetsDoDono() {
        System.out.println("Filhotes do(a) " + nome + ":");
        for (Pet pet : petsDoDono) {
            System.out.println("Nome: " + pet.getNomePet());
            System.out.println("Raça: " + pet.getEspecie());
            System.out.println("Porte: " + pet.getPorte());
            System.out.println("Peso: " + pet.getPeso() + " kg" + "\n");
        }
    }
}