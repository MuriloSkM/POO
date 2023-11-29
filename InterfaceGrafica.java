import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InterfaceGrafica {
    public static void exibirRelatorio(){
        JFrame frame = new JFrame("Relatório");

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JButton exibirRelatorio = new JButton("Exibir Relatório");
        exibirRelatorio.addActionListener(e -> {
            try(BufferedReader reader = new BufferedReader(new FileReader("relatorio.txt"))) {
                StringBuilder conteudo = new StringBuilder();
                String linha;
                while((linha = reader.readLine()) != null){
                    conteudo.append(linha).append("\n");
                }
                textArea.setText(conteudo.toString());
                
            } catch (IOException ex) {
                textArea.setText("Erro ao ler relatório: " + ex.getMessage());
            }
        });

        JButton sair = new JButton("Sair");
        sair.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(exibirRelatorio);
        panel.add(sair);

        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
