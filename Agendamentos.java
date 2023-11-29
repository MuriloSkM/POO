import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Agendamentos {
    private List<Agendamento> agenda;
    private List<Procedimentos> listaProcedimentos;

    public Agendamentos() {
        this.agenda = new ArrayList<>();
        this.listaProcedimentos = new ArrayList<>(Arrays.asList(Procedimentos.values()));
    }

    public boolean addAgenda(int dia, int mes, int ano, int hora, double valor, Procedimentos procedimento) {
        for (Agendamento agendamento : agenda) {
            if (agendamento.getDia() == dia && agendamento.getHora() == hora) {
                System.out.println("Já existe um agendamento para esta data e hora.");
                return false;
            }
        }
        Agendamento novoAgendamento = new Agendamento(dia, mes, ano, hora, valor, procedimento);
        agenda.add(novoAgendamento);
        return true;
    }
    /*
     * Método para validação de data disponivel
     * Se sim, retorna o dia para agendamento, se não, retorna que não está disponível por data indisponível, horário inválido ou data não trabalhada.
     */
    public boolean verificarDisponibilidade(int dia, int hora, int mes, int ano) {
        try (Scanner scanData = new Scanner(System.in)) {
            LocalDate dataVerificar = LocalDate.of(ano, mes, dia);
            DayOfWeek dayOfWeek = dataVerificar.getDayOfWeek();
            //Validação de um dia que não é trabalhado na semana
            if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
                System.out.println("Data indisponível, não trabalhamos sábado e domingo.");
                return false;
            }
            if(hora < 9 || hora > 18) {
                System.out.println("Horário de funcionamento é das 9h às 18h.");
                return false;
            }
            //Delimitação de '/' para fazer com que o usuario digite barras de separação para dia, mês e ano.
            scanData.useDelimiter("/");
            System.out.println("Digite a data (dd/mm/aa) para verificar a disponibilidade: ");
            String dataValida = scanData.next();
            String regex = "^\\d{2}/\\d{2}/\\d{2}$"; //Expressão de validação de digitos de 0 a 9, separados por barra e limitados a 2 digitos entre cada barra.

            //Validação de data e verificação de disponibilidade na agenda
            if(dataValida.matches(regex)){
                for (Agendamento agendamento : agenda) {
                    if (agendamento.getDia() == dia && agendamento.getHora() == hora) {
                        return false; // Se houver um agendamento na mesma data e hora, não está disponível
                    }
                }
            return true; // Caso contrário, está disponível
            } else {
                System.out.println("Formato inválido! Utilize DIA/MÊS/ANO");
                return false;
            }
        }
    }
    /*
     * Método criado pra atribuir o valor para um dia e somar esse novo valor com o valor que já temos
     */
    public double calcularValorTotalDia(int dia) {
        double valorTotal = 0;
        for (Agendamento agendamento : agenda) {
            if (agendamento.getDia() == dia) {
                valorTotal += agendamento.getValor();
            }
        }
        return valorTotal;
    }
    /*
     * Método criado para criar um arrayList com os procedimentos realizados no dia
     */
    public List<Agendamento> procedimentosRealizadosNoDia(int dia) {
        List<Agendamento> procedimentosDia = new ArrayList<>();
        for (Agendamento agendamento : agenda) {
            if (agendamento.getDia() == dia) {
                procedimentosDia.add(agendamento);
            }
        }
        return procedimentosDia;
    }
    public List<Agendamento> getAgenda() {
        return agenda;
    }
}

/*
 * Classe genérica para melhor forma de armazenamento.
 * Ela armazena individualmente as datas para que na classe acima ela faça as validações necessárias.
 */
class Agendamento {
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private double valor;
    private Procedimentos procedimento;
    public Agendamento(int dia, int mes, int ano, int hora, double valor, Procedimentos procedimento) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.valor = valor;
        this.procedimento = procedimento;
    }
    public int getDia() {
        return dia;
    }
    public int getMes(){
        return mes;
    }
    public int getAno(){
        return ano;
    }
    public int getHora() {
        return hora;
    }
    public double getValor() {
        return valor;
    }
    public Procedimentos getProcedimento() {
        return procedimento;
    }
}