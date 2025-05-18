import java.util.List;
import java.util.Scanner;

public class SistemaGerenciamentoImpressao {
    private static final int CAPACIDADE_FILA = 10;
    private static final int CAPACIDADE_PILHA = 5;
    
    private FilaImpressao filaImpressao;
    private PilhaReimpressao pilhaReimpressao;
    private Scanner scanner;
    
    public SistemaGerenciamentoImpressao() {
        this.filaImpressao = new FilaImpressao(CAPACIDADE_FILA);
        this.pilhaReimpressao = new PilhaReimpressao(CAPACIDADE_PILHA);
        this.scanner = new Scanner(System.in);
    }
    
    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
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
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
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
                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
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
        System.out.println("Documento impresso com sucesso: " + doc.getNomeArquivo());
        System.out.println("Tempo de espera: " + doc.calcularTempoEspera() + " segundos");
    }
    
    private void consultarDocumentoFila() {
        System.out.print("Nome do arquivo a consultar: ");
        String nomeArquivo = scanner.nextLine();
        
        int posicao = filaImpressao.buscarDocumento(nomeArquivo);
        if (posicao != -1) {
            Documento doc = filaImpressao.consultarDocumento(nomeArquivo);
            System.out.println("Documento encontrado na posição " + (posicao + 1) + " da fila:");
            System.out.println(doc);
        } else {
            System.out.println("Documento não encontrado na fila.");
        }
    }
    
    private void adicionarDocumentoPilha() {
        System.out.print("Nome do arquivo para reimpressão: ");
        String nomeArquivo = scanner.nextLine();
        
        System.out.print("Nome do usuário: ");
        String nomeUsuario = scanner.nextLine();
        
        Documento doc = new Documento(nomeArquivo, nomeUsuario);
        pilhaReimpressao.adicionarDocumento(new Documento(nomeArquivo, nomeUsuario));
        System.out.println("Documento adicionado à pilha de reimpressão: " + doc.getNomeArquivo());
    }
    
    private void reimprimirDocumento() {
        Documento doc = pilhaReimpressao.reimprimirDocumento();
        System.out.println("Documento reimpresso com sucesso: " + doc.getNomeArquivo());
        System.out.println("Tempo de espera: " + doc.calcularTempoEspera() + " segundos");
    }
    
    private void consultarDocumentoPilha() {
        System.out.print("Nome do arquivo a consultar: ");
        String nomeArquivo = scanner.nextLine();
        
        Documento doc = pilhaReimpressao.consultarDocumento(nomeArquivo);
        if (doc != null) {
            System.out.println("Documento encontrado na pilha de reimpressão:");
            System.out.println(doc);
        } else {
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
                    pilhaReimpressao.adicionarDocumento(doc);
                }
                adicionados++;
            } catch (RuntimeException e) {
                System.out.println("Não foi possível adicionar mais documentos: " + e.getMessage());
                break;
            }
        }
        
        System.out.println("Foram adicionados " + adicionados + " documentos com sucesso.");
    }

    public static void main(String[] args) {
        SistemaGerenciamentoImpressao sistema = new SistemaGerenciamentoImpressao();
        sistema.iniciar();
    }
}