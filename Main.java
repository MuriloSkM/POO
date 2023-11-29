import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Scanner scanOpt = new Scanner(System.in);
        Scanner scanWhile = new Scanner(System.in);
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
            System.out.println("8 - Imprimir relatório do dia");
            System.out.println("9 - Abrir a interface gráfica");
            System.out.println("0 - Sair");

            int opcao = scanOpt.nextInt();
            System.out.println("");
            
            
            switch (opcao) {
                /*
                 * Case 1 voltado para adicionar os dados do cliente em um arrayList Dono e chamar a classe Dono para armazenar os dados e adicionar o cliente adicionado ao relatório.
                 */
                case 1:
                    try {
                    System.out.println("Digite o CPF do cliente:");
                    String cpf = scanWhile.nextLine();
                    System.out.println("Digite o nome do cliente:");
                    String nome = scanWhile.nextLine();
                    System.out.println("Digite o email do cliente:");
                    String email = scanWhile.nextLine();
                    Dono novoCliente = new Dono(cpf, nome, email);
                    listaClientes.add(novoCliente); // Adicionando o cliente à lista de clientes
                    System.out.println("Cliente adicionado com sucesso!");
                    System.out.println(" ");
                } catch (Exception e) {
                    System.out.println("Ocorreu um erro ao adicionar o cliente. " + e.getMessage());
                }
                break;

                /*
                 * Case 2 voltado para adicionar o pet do cliente a um cliente já previamente adicionado no case 1.
                 * Try-catch externo pensado para garantir a existência de um cliente
                 * Try-catch interno voltado para encontrar um cliente e lançar uma exceção caso saia do escopo acharCliente != naoAcharCliente
                 * if~else para, quando encontrar o cliente, adicionar o pet àquele cliente achado e adicionar junto a lista Dono, listando os pets
                 * while criado para adicionar o cliente caso o cliente seja encontrado como null
                 */
                case 2:
                    try {
                        System.out.println("Digite o nome do cliente: ");
                        String nomeCliente = scanWhile.nextLine();
                        Dono clienteExistente = buscarClientePorNome(nomeCliente, listaClientes);

                        if (clienteExistente != null) {
                            System.out.println("Digite o nome do pet: ");
                            String nomePet = scanWhile.nextLine();
                            System.out.println("Digite a espécie do pet: ");
                            String especie = scanWhile.nextLine();
                            System.out.println("Digite o peso do pet (em KG): ");
                            double peso = scanWhile.nextDouble();
                            scanWhile.nextLine();
                            System.out.println("Digite o tamanho do pet: ");
                            String porte = scanWhile.nextLine();

                            Pet novoPet = new Pet(nomePet,especie, peso, porte);
                            clienteExistente.addPetAoDono(novoPet);
                            System.out.println("Pet adicionado ao cliente " + nomeCliente + " com sucesso!");
                        } else {
                            System.out.println("Cliente não encontrado. Cadastre o cliente:");
                            System.out.println("Digite o CPF do cliente: ");
                            String cpf = scanWhile.nextLine();
                            System.out.println("Digite o e-mail do cliente: ");
                            String email = scanWhile.nextLine();
                            Dono novoCliente = new Dono(cpf, nomeCliente, email);
                            listaClientes.add(novoCliente);
                            System.out.println("Cliente adicionado com sucesso!");
                        }
                    } catch (Exception e) {
                            System.out.println("Ocorreu um erro ao buscar o cliente. " + e.getMessage());
                    }
                break;

                /*
                 * Case 3 busca adicionar um agendamento baseado na collection LocalDate, que valida dia e mês para validar se existe disponibilidade
                 * if~else externo busca validar a existencia tanto do pet quanto do dono na lista para garantir que existe o pet, senão, cria o cliente na lista.
                 * switch case interno mostra os procedimentos do enum Procedimenos e faz a seleção dele.
                 * if para criação da validação de se o procedimento foi selecionado com um boolean para validar dentro de if~else se existe a data do procedimento disponível e criar a data.
                 */
                case 3:
                    System.out.println("Digite o nome do cliente para agendar o procedimento:");
                    String nomeCliente = scanWhile.nextLine();
                    Dono clienteExistente = buscarClientePorNome(nomeCliente, listaClientes);
            
                    if (clienteExistente != null && clienteExistente.getPetsDoDono().size() > 0) {
                        System.out.println("Digite a data do agendamento (dd/mm/aa): ");
                        int dia = scanWhile.nextInt();
                        int mes = scanWhile.nextInt();
                        int ano = scanWhile.nextInt();
                        System.out.println("Digite a hora do agendamento: ");
                        int hora = scanWhile.nextInt();
                        System.out.println("Selecione o procedimento e o valor do procedimento: ");
                        System.out.println("1 - Consulta(R$30,00)");
                        System.out.println("2 - Vacina(R$70,00)");
                        System.out.println("3 - Banho(R$50,00)");
                        System.out.println("4 - Tosa(R$65,00)");
                        System.out.println("5 - Cirurgia(R$250,00)");
                        int opcaoProcedimento = scanWhile.nextInt();
                        double valor = scanWhile.nextDouble();
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
                            boolean agendado = agendamentos.addAgenda(dia, mes, ano, hora, valor, procedimentoSelecionado);
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
                /*
                 * Case 4 é voltado para a disponibilidade de agenda e verificar a agenda como um todo.
                 * Chama a classe Agendamentos, para verificar a data disponível e a hora disponível.
                 * Mostra as datas disponíveis desconsiderando finais de semana sábado e domingo.
                 */
                case 4:
                    try{
                        System.out.println("Digite uma data (dd/mm/aa) para verificar disponibilidade: ");
                        int diaVerificar = scanWhile.nextInt();
                        int mesVerificar = scanWhile.nextInt();
                        int anoVerificar = scanWhile.nextInt();
                        System.out.println("Digite um horario para verificar disponibilidade: ");
                        int horaVerificar = scanWhile.nextInt();
                        if(agendamentos.verificarDisponibilidade(diaVerificar, mesVerificar, anoVerificar, horaVerificar)){
                            if(agendamentos.verificarDisponibilidade(diaVerificar, mesVerificar, anoVerificar, horaVerificar)){
                                System.out.println("Horario disponível!");
                            } else {
                                System.out.println("Horario indisponível.");
                            }
                        } else {
                            System.out.println("Data inválida!");
                        }
                    } catch(Exception ex){
                        System.out.println("Houve um erro: " + ex.getMessage());
                    }
                    break;
                /*
                 * Case 5 mostra o valor vendido no dia.
                 * Calcula o total de agendamentos e faz isto baseado em valores criados por agendamentos.
                 * Cada agendamento possui um valor específico criado anteriormente.
                 */
                case 5:
                    try{
                        System.out.println("Digite um dia para verificar o saldo total do dia: ");
                        int dia = scanWhile.nextInt();
                        double valorTotal = 0;
                        for(Agendamento agendamento : agendamentos.procedimentosRealizadosNoDia(dia)){
                            valorTotal += agendamento.getProcedimento().getValor();
                        }
                        System.out.println("Saldo total do dia " + dia + ": R$" + valorTotal);
                    }catch (Exception ex){
                        System.out.println("Houve um erro: " + ex.getMessage());
                    }
                    break;
                /*
                 * Case 6 mostra todos os procedimentos realizados no dia escolhido.
                 * Isto vai ser buscado em um relatório pré-estipulado com valores da data anterior para exemplo.
                 */
                case 6:
                    try {
                        System.out.println("Digite um dia para verificar os procedimentos realizados no dia: ");
                        int diaConsulta = scanWhile.nextInt();
                        List<Agendamento> procedimentosDiaAtual = agendamentos.procedimentosRealizadosNoDia(diaConsulta);
                        if(procedimentosDiaAtual.isEmpty()){
                            System.out.println("Nenhum procedimento realizado no dia.");
                        } else {
                            procedimentosRealizados = new ArrayList<>(procedimentosDiaAtual);
                            System.out.println("Procedimentos realizados no dia: " + diaConsulta);

                            for(Agendamento agendamento : procedimentosRealizados){
                                System.out.println("Data: " + agendamento.getDia() + "/" + agendamento.getMes() + "/" + agendamento.getAno()
                                + "Hora: " + agendamento.getHora()
                                + "Procedimento: " + agendamento.getProcedimento()
                                + "Valor cobrado: " + agendamento.getValor());
                            }
                        }
                        
                    } catch (Exception ex) {
                        System.out.println("Houve um erro: " + ex.getMessage());
                    }
                    break;
                /*
                 * Case 7 mostra todos os clientes com todos os pets ligados e este cliente em específico.
                 * Aqui temos o uso de expressões lambda
                 */
                case 7:
                    System.out.println("Lista de clientes e seus pets: ");
                    listaClientes.forEach(cliente -> {
                    System.out.println("Cliente: " + cliente.getNome());
                    cliente.getPetsDoDono().forEach(pet -> {
                        System.out.println("Pet: " + pet.getNomePet() + " - Espécie: " + pet.getEspecie());
                    });
                });
                    break;
                /*
                 * Case 8 é a criação do relatório com as informações e quantidades
                 * Soma todos os pets cadastrados e lista
                 * Mostra todos os procedimentos realizados e lista
                 * Mostra todos os agendamentos realizados e lista
                 * Mostra o valor total arrecadado
                 */
                case 8:
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio.txt", true))){
                        //Quantidade de animais cadastrados
                        int totalAnimais = 0;
                        for(Dono cliente : listaClientes){
                            totalAnimais += cliente.getPetsDoDono().size();
                        }
                        writer.write("Relatório do dia: " + LocalDateTime.now() + "\n");
                        //Informações sobre os animais cadastrados
                        writer.write("Total de animais cadastrados: " + totalAnimais + "\n");
                        //Informações sobre procedimentos realizados
                        procedimentosRealizados = agendamentos.getAgenda();
                        writer.write("Total de procedimentos realizados: " + procedimentosRealizados.size() + "\n");
                        for(Agendamento procedimento : procedimentosRealizados){
                            writer.write("Procedimentos: " + procedimento.getProcedimento() + "\n");
                        }
                        //Informações sobre os agentamentos realizados no dia
                        List<Agendamento> agendamentosDoDia = agendamentos.procedimentosRealizadosNoDia(LocalDateTime.now().getDayOfMonth());
                        writer.write("Total de agendamentos no dia de hoje: " + agendamentosDoDia.size() + "\n");
                        for (Agendamento agendamento : agendamentosDoDia) {
                            writer.write("Data: " + agendamento.getDia() + "/" + agendamento.getMes() + "/" + agendamento.getAno() + " - Hora: " + agendamento.getHora()
                            + " - Procedimento realizado: " + agendamento.getProcedimento()
                            + " - Valor do procedimento: " + agendamento.getValor() + "\n");
                        }
                        //Informações sobre o valor arrecadado no dia
                        double valorTotal = agendamentos.calcularValorTotalDia(LocalDateTime.now().getDayOfMonth());
                        writer.write("Valor total arrecadado no dia: " + valorTotal + "\n");
                        writer.write("----------------------------------");

                    } catch (IOException e) {
                        System.out.println("Erro ao gerar relatorio: " + e.getMessage());
                    }
                    break;
                /*
                 * Case 9 voltado para abrir a interface gráfica e chamar a classe InterfaceGrafica
                 */
                case 9:
                    InterfaceGrafica.exibirRelatorio();
                    break;
                case 0:
                    System.out.println("Desligando, obrigado!");
                    scanOpt.close();
                    scanWhile.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
    private static Dono buscarClientePorNome(String nomeCliente, List<Dono> listaClientes) {
        for (Dono cliente : listaClientes) {
            if (cliente.getNome().equalsIgnoreCase(nomeCliente)) {
                return cliente;
            }
        }
        return null;
    }    
}