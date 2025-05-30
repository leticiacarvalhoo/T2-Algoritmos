/*                            
Breno Augusto Oliveira Gandolfo - 24.01496-6
Gustavo Seripierri - 24.00630-0
Leticia de Carvalho Silva - 24.00141-4
Lyssa Okawa Perini - 24.01193-2
*/

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class SistemaGerenciamentoImpressao {
    private static final int CAPACIDADE_FILA = 10; 
    private static final int CAPACIDADE_PILHA = 5; 

    private FilaImpressao filaImpressao;       
    private Pilha pilhaReimpressao;            
    private Scanner scanner;                  

    public SistemaGerenciamentoImpressao() {
        this.filaImpressao = new FilaImpressao(CAPACIDADE_FILA);
        this.pilhaReimpressao = new Pilha(CAPACIDADE_PILHA);
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("\nSISTEMA DE GERENCIAMENTO DE IMPRESSAO");
        System.out.println("1. Adicionar documento a fila de impressao");
        System.out.println("2. Imprimir documento da fila");
        System.out.println("3. Consultar documento na fila");
        System.out.println("4. Exibir relatorio da fila de impressao");
        System.out.println("5. Adicionar documento a pilha de reimpressao");
        System.out.println("6. Reimprimir documento da pilha");
        System.out.println("7. Consultar documento na pilha de reimpressao");
        System.out.println("8. Exibir relatorio da pilha de reimpressao");
        System.out.println("0. Sair");
        System.out.print("\nEscolha uma opcao: ");
    }

    private int lerOpcao() {
        try {
            int op = scanner.nextInt();
            return op;
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Por favor, digite um numero.");
            return -1;
        }
    }

    private void processarOpcao(int opcao) {
        try {
            switch (opcao) {
                case 1:
                    adicionarDocumentoFila();
                    break;
                case 2:
                    imprimirDocumento();
                    break;
                case 3:
                    consultarDocumentoFila();
                    break;
                case 4:
                    System.out.println(filaImpressao.gerarRelatorio());
                    break;
                case 5:
                    adicionarDocumentoPilha();
                    break;
                case 6:
                    reimprimirDocumento();
                    break;
                case 7:
                    consultarDocumentoPilha();
                    break;
                case 8:
                    System.out.println(pilhaReimpressao.gerarRelatorio());
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida! Por favor, tente novamente.");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void adicionarDocumentoFila() {
        scanner.nextLine(); 
        System.out.print("Nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();

        System.out.print("Nome do usuario: ");
        String nomeUsuario = scanner.nextLine();

        filaImpressao.adicionarDocumento(new Documento(nomeArquivo, nomeUsuario));
        System.out.println("Documento adicionado com sucesso: " + nomeArquivo);
    }

    private void imprimirDocumento() {
        Documento doc = filaImpressao.imprimirDocumento();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String horario = LocalDateTime.now().format(formatter);

        System.out.println("Documento impresso com sucesso: " + doc.getNomeArquivo());
        System.out.println("Horario da impressao: " + horario);
        System.out.println("Tempo de espera: " + doc.calcularTempoEspera() + " segundos");
    }

    private void consultarDocumentoFila() {
        scanner.nextLine();
        System.out.print("Nome do arquivo a consultar: ");
        String nomeArquivo = scanner.nextLine();

        try {
            int posicao = filaImpressao.buscarPosicaoDocumento(nomeArquivo);
            Documento doc = filaImpressao.consultarDocumento(nomeArquivo);
            System.out.println("Documento encontrado na posicao " + (posicao + 1) + " da fila:");
            System.out.println(doc);
        } catch (FilaImpressao.DocumentoNaoEncontradoException e) {
            System.out.println("Documento nao encontrado na fila.");
        }
    }

    private void adicionarDocumentoPilha() {
        scanner.nextLine(); 
        System.out.print("Nome do arquivo para reimpressao: ");
        String nomeArquivo = scanner.nextLine();

        System.out.print("Nome do usuario: ");
        String nomeUsuario = scanner.nextLine();

        Documento doc = new Documento(nomeArquivo, nomeUsuario);
        pilhaReimpressao.push(doc);
        System.out.println("Documento adicionado a pilha de reimpressao: " + doc.getNomeArquivo());
    }

    private void reimprimirDocumento() {
        Documento doc = pilhaReimpressao.pop();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String horario = LocalDateTime.now().format(formatter);

        System.out.println("Documento reimpresso com sucesso: " + doc.getNomeArquivo());
        System.out.println("Horario da reimpressao: " + horario);
        System.out.println("Tempo de espera: " + doc.calcularTempoEspera() + " segundos");
    }

    private void consultarDocumentoPilha() {
        scanner.nextLine(); 
        System.out.print("Nome do arquivo a consultar: ");
        String nomeArquivo = scanner.nextLine();

        try {
            Documento doc = pilhaReimpressao.consultarDocumento(nomeArquivo);
            System.out.println("Documento encontrado na pilha de reimpressao:");
            System.out.println(doc);
        } catch (Pilha.DocumentoNaoEncontradoException e) {
            System.out.println("Documento nao encontrado na pilha.");
        }
    }

    public static void main(String[] args) {
        SistemaGerenciamentoImpressao sistema = new SistemaGerenciamentoImpressao();
        sistema.iniciar();
    }
}
