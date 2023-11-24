import java.util.ArrayList;
import java.util.List;

public class Agendamentos {
    private List<Agendamento> agenda;

    public Agendamentos() {
        this.agenda = new ArrayList<>();
    }

    public boolean addAgenda(int data, int hora, double valor, Procedimentos procedimento) {
        for (Agendamento agendamento : agenda) {
            if (agendamento.getData() == data && agendamento.getHora() == hora) {
                System.out.println("Já existe um agendamento para esta data e hora.");
                return false;
            }
        }
        Agendamento novoAgendamento = new Agendamento(data, hora, valor, procedimento);
        agenda.add(novoAgendamento);
        return true;
    }

    public boolean removeAgenda(int data, int hora) {
        for (Agendamento agendamento : agenda) {
            if (agendamento.getData() == data && agendamento.getHora() == hora) {
                agenda.remove(agendamento);
                return true;
            }
        }
        System.out.println("Nenhum agendamento encontrado para esta data e hora.");
        return false;
    }
    public boolean verificarDisponibilidade(int data, int hora) {
        for (Agendamento agendamento : agenda) {
            if (agendamento.getData() == data && agendamento.getHora() == hora) {
                return false; // Se houver um agendamento na mesma data e hora, não está disponível
            }
        }
        return true; // Caso contrário, está disponível
    }
    public double calcularValorTotalDia(int dia) {
        double valorTotal = 0;
        for (Agendamento agendamento : agenda) {
            if (agendamento.getData() == dia) {
                valorTotal += agendamento.getValor();
            }
        }
        return valorTotal;
    }
    public List<Agendamento> procedimentosRealizadosNoDia(int dia) {
        List<Agendamento> procedimentosDia = new ArrayList<>();
        for (Agendamento agendamento : agenda) {
            if (agendamento.getData() == dia) {
                procedimentosDia.add(agendamento);
            }
        }
        return procedimentosDia;
    }
}
/*
 * Classe genérica para melhor forma de armazenamento.
 * Ela armazena individualmente as datas para que na classe acima ela faça as validações necessárias.
 */
class Agendamento {
    private int data;
    private int hora;
    private double valor;
    private Procedimentos procedimento;
    public Agendamento(int data, int hora, double valor, Procedimentos procedimento) {
        this.data = data;
        this.hora = hora;
        this.valor = valor;
        this.procedimento = procedimento;
    }
    public int getData() {
        return data;
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