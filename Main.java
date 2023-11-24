import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dono dono = null;
        Pet pet = null;
        Agendamentos agendamentos = new Agendamentos();
        List<Dono> listaClientes = new ArrayList<>();
        List<Agendamento> procedimentosRealizados = new ArrayList<>();
        
        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Adicionar cliente");
            System.out.println("2 - Adicionar pet para um cliente");
            System.out.println("3 - Agendar procedimento");
            System.out.println("4 - Verificar calendário disponível");
            System.out.println("5 - Verificar valor total vendido no dia");
            System.out.println("6 - Verificar procedimentos realizados no dia atual");
            System.out.println("7 - Buscar cliente e listar os pets");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o CPF do cliente:");
                    String cpf = scanner.nextLine();
                    System.out.println("Digite o nome do cliente:");
                    String nome = scanner.nextLine();
                    System.out.println("Digite o email do cliente:");
                    String email = scanner.nextLine();
                    dono = new Dono(cpf, nome, email);
                    break;
                    
                case 2:
                    if (dono != null) {
                        System.out.println("Digite a raça do pet:");
                        String raca = scanner.nextLine();
                        System.out.println("Digite o peso do pet:");
                        double peso = scanner.nextDouble();
                        System.out.println("Digite o porte do pet:");
                        String porte = scanner.nextLine();
                        pet = new Pet(raca, peso, porte);
                        dono.addPetAoDono(pet);
                    } else {
                        System.out.println("Nenhum cliente cadastrado. Por favor, cadastre um cliente primeiro.");
                    }
                    break;

                case 3:
                    if (dono != null && pet != null) {
                        System.out.println("Digite a data do agendamento (dia):");
                        int data = scanner.nextInt();
                        System.out.println("Digite a hora do agendamento:");
                        int hora = scanner.nextInt();
                        System.out.println("Digite o valor do procedimento:");
                        double valor = scanner.nextDouble();
                        
                        System.out.println("Selecione o procedimento:");
                        System.out.println("1 - Consulta");
                        System.out.println("2 - Vacina");
                        System.out.println("3 - Banho");
                        System.out.println("4 - Tosa");
                        System.out.println("5 - Cirurgia");
                        int opcaoProcedimento = scanner.nextInt();                        
                        Procedimentos procedimentoSelecionado = null;
                        switch (opcaoProcedimento) {
                            case 1:
                                procedimentoSelecionado = Procedimentos.CONSULTA;
                                break;
                            case 2:
                                procedimentoSelecionado = Procedimentos.VACINA;
                                break;
                            case 3:
                                procedimentoSelecionado = Procedimentos.BANHO;
                                break;
                            case 4:
                                procedimentoSelecionado = Procedimentos.TOSA;
                                break;
                            case 5:
                                procedimentoSelecionado = Procedimentos.CIRURGIA;
                                break;
                            default:
                                System.out.println("Procedimento não disponível!");
                                break;
                        }
                        if (procedimentoSelecionado != null) {
                            boolean agendado = agendamentos.addAgenda(data, hora, valor, procedimentoSelecionado);
                            if (agendado) {
                                System.out.println("Procedimento agendado com sucesso!");
                            } else {
                                System.out.println("Não foi possível agendar o procedimento!");
                            }
                        }
                    } else {
                        System.out.println("Cadastre um cliente e um pet antes de agendar um procedimento!");
                    }
                    break;
                case 4:
                    System.out.println("Digite a data (dia) para verificar a disponibilidade: ");
                    int dataVerificar = scanner.nextInt();
                    System.out.println("Digite a hora para verificar a disponibilidade: ");
                    int horaVerificar = scanner.nextInt();                    
                    boolean disponivel = agendamentos.verificarDisponibilidade(dataVerificar, horaVerificar);
                    if (disponivel) {
                        System.out.println("Horário disponível para agendamento!");
                    } else {
                        System.out.println("Horário indisponível para agendamento!");
                    }
                    break;
                case 5:
                    System.out.println("Digite o dia para verificar o valor total vendido:");
                    int dia = scanner.nextInt();
                    double valorTotal = agendamentos.calcularValorTotalDia(dia);
                    System.out.println("Valor total vendido no dia " + dia + ": " + valorTotal);
                    break;
                case 6:
                    LocalDate dataAtual = LocalDate.now();
                    List<Agendamento> procedimentosDiaAtual = agendamentos.procedimentosRealizadosNoDia(dataAtual.getDayOfMonth());
                    if (procedimentosDiaAtual.isEmpty()) {
                        System.out.println("Nenhum procedimento realizado hoje!");
                    } else {
                        System.out.println("Procedimentos realizados hoje: ");
                        for (Agendamento procedimentoRealizado : procedimentosDiaAtual) {
                            System.out.println("Data: " + procedimentoRealizado.getData() +
                                    ", Hora: " + procedimentoRealizado.getHora() +
                                    ", Procedimento: " + procedimentoRealizado.getProcedimento());
                        }
                    }
                    break;
                case 7:
                    System.out.println("Digite o nome do cliente: ");
                    String nomeCliente = scanner.nextLine();
                    boolean encontrado = false;
                    for(Dono cliente : listaClientes){
                        if(cliente.getNome().equalsIgnoreCase(nomeCliente)){
                            cliente.listarPetsDoDono();
                            encontrado = true;
                            break;
                        }
                        if(!encontrado) System.out.println("Cliente não encontrado!");
                    }
                    break;
                case 0:
                    System.out.println("Desligando, obrigado!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}