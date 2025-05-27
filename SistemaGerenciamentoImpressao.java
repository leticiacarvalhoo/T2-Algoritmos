import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
        System.out.println("\nSISTEMA DE GERENCIAMENTO DE IMPRESSÃO");
        System.out.println("1. Adicionar documento à fila de impressão");
        System.out.println("2. Imprimir documento da fila");
        System.out.println("3. Consultar documento na fila");
        System.out.println("4. Exibir relatório da fila de impressão");
        System.out.println("5. Adicionar documento à pilha de reimpressão");
        System.out.println("6. Reimprimir documento da pilha");
        System.out.println("7. Consultar documento na pilha de reimpressão");
        System.out.println("8. Exibir relatório da pilha de reimpressão");
        System.out.println("9. Carregar documentos de arquivo texto");
        System.out.println("10. Buscar documento no sistema");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
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
                case 9:
                    carregarDocumentosDeArquivo();
                    break;
                case 10:
                    buscarDocumentoNoSistema();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, tente novamente.");
            }
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void adicionarDocumentoFila() {
        System.out.print("Nome do arquivo: ");
        String nomeArquivo = scanner.nextLine();

        System.out.print("Nome do usuário: ");
        String nomeUsuario = scanner.nextLine();

        filaImpressao.adicionarDocumento(new Documento(nomeArquivo, nomeUsuario));
        System.out.println("Documento adicionado com sucesso: " + nomeArquivo);
    }

    private void imprimirDocumento() {
        Documento doc = filaImpressao.imprimirDocumento();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String horario = LocalDateTime.now().format(formatter);

        System.out.println("Documento impresso com sucesso: " + doc.getNomeArquivo());
        System.out.println("Horário da impressão: " + horario);
        System.out.println("Tempo de espera: " + doc.calcularTempoEspera() + " segundos");
    }

    private void consultarDocumentoFila() {
        System.out.print("Nome do arquivo a consultar: ");
        String nomeArquivo = scanner.nextLine();

        try {
            int posicao = filaImpressao.buscarPosicaoDocumento(nomeArquivo);
            Documento doc = filaImpressao.consultarDocumento(nomeArquivo);
            System.out.println("Documento encontrado na posição " + (posicao + 1) + " da fila:");
            System.out.println(doc);
        } catch (FilaImpressao.DocumentoNaoEncontradoException e) {
            System.out.println("Documento não encontrado na fila.");
        }
    }

    private void adicionarDocumentoPilha() {
        System.out.print("Nome do arquivo para reimpressão: ");
        String nomeArquivo = scanner.nextLine();

        System.out.print("Nome do usuário: ");
        String nomeUsuario = scanner.nextLine();

        Documento doc = new Documento(nomeArquivo, nomeUsuario);
        pilhaReimpressao.push(doc);
        System.out.println("Documento adicionado à pilha de reimpressão: " + doc.getNomeArquivo());
    }

    private void reimprimirDocumento() {
        Documento doc = pilhaReimpressao.pop();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String horario = LocalDateTime.now().format(formatter);

        System.out.println("Documento reimpresso com sucesso: " + doc.getNomeArquivo());
        System.out.println("Horário da reimpressão: " + horario);
        System.out.println("Tempo de espera: " + doc.calcularTempoEspera() + " segundos");
    }

    private void consultarDocumentoPilha() {
        System.out.print("Nome do arquivo a consultar: ");
        String nomeArquivo = scanner.nextLine();

        try {
            Documento doc = pilhaReimpressao.consultarDocumento(nomeArquivo);
            System.out.println("Documento encontrado na pilha de reimpressão:");
            System.out.println(doc);
        } catch (Pilha.DocumentoNaoEncontradoException e) {
            System.out.println("Documento não encontrado na pilha.");
        }
    }

    private void carregarDocumentosDeArquivo() {
        System.out.print("Informe o caminho do arquivo de documentos: ");
        String caminho = scanner.nextLine();

        List<Documento> documentos = LeitorArquivos.lerDocumentosDeArquivo(caminho);
        System.out.println("Foram encontrados " + documentos.size() + " documentos no arquivo.");

        System.out.print("Adicionar à fila de impressão (F) ou pilha de reimpressão (P)? ");
        String destino = scanner.nextLine().toUpperCase();

        int adicionados = 0;
        for (Documento doc : documentos) {
            try {
                if (destino.equals("F")) {
                    filaImpressao.adicionarDocumento(doc);
                } else if (destino.equals("P")) {
                    pilhaReimpressao.push(doc);
                }
                adicionados++;
            } catch (RuntimeException e) {
                System.out.println("Não foi possível adicionar mais documentos: " + e.getMessage());
                break;
            }
        }
        System.out.println("Foram adicionados " + adicionados + " documentos com sucesso.");
    }

    private void buscarDocumentoNoSistema() {
        System.out.print("Digite o nome do documento a buscar: ");
        String nomeArquivo = scanner.nextLine();
        
        Documento docEncontrado = LeitorArquivos.buscarDocumento(nomeArquivo, filaImpressao, pilhaReimpressao);
        
        if (docEncontrado != null) {
            System.out.println("Tempo de espera: " + docEncontrado.calcularTempoEspera() + " segundos");
        } else {
            System.out.println("Deseja adicionar este documento ao sistema? (S/N)");
            String resposta = scanner.nextLine().toUpperCase();
            if (resposta.equals("S")) {
                System.out.print("Nome do usuário: ");
                String nomeUsuario = scanner.nextLine();
                
                System.out.print("Adicionar à fila de impressão (F) ou pilha de reimpressão (P)? ");
                String destino = scanner.nextLine().toUpperCase();
                
                Documento novoDoc = new Documento(nomeArquivo, nomeUsuario);
                if (destino.equals("F")) {
                    filaImpressao.adicionarDocumento(novoDoc);
                    System.out.println("Documento adicionado à fila de impressão.");
                } else if (destino.equals("P")) {
                    pilhaReimpressao.push(novoDoc);
                    System.out.println("Documento adicionado à pilha de reimpressão.");
                }
            }
        }
    }

    public static void main(String[] args) {
        SistemaGerenciamentoImpressao sistema = new SistemaGerenciamentoImpressao();
        sistema.iniciar();
    }
}
