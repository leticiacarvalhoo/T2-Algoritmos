import java.util.ArrayList;

public class FilaImpressao {
    private Fila indicesFila;
    private ArrayList<Documento> documentos;
    private int capacidade;
    private int proxId = 0;

    public FilaImpressao(int capacidade) {
        this.capacidade = capacidade;
        this.indicesFila = new Fila(capacidade);
        this.documentos = new ArrayList<>(capacidade);
    }

    public boolean filaVazia() {
        return indicesFila.filaVazia();
    }

    public boolean filaCheia() {
        return indicesFila.filaCheia();
    }

    public void adicionarDocumento(Documento doc) {
        if (filaCheia()) 
            throw new RuntimeException("Fila cheia");
        documentos.add(doc);
        indicesFila.enfileira(proxId++);
        System.out.println("Documento adicionado: " + doc.getNomeArquivo());
    }

    public Documento imprimirDocumento() {
        if (filaVazia()) 
            throw new RuntimeException("Fila vazia");
        int indice = indicesFila.desenfileira();
        Documento doc = documentos.get(indice);
        
        System.out.println("Documento impresso: " + doc.getNomeArquivo());
        System.out.println("Tempo de espera: " + doc.calcularTempoEspera() + " segundos");

        return doc;
    }

    public int buscarDocumento(String nomeArquivo) {
        if (filaVazia()) return -1;

        int posicao = 0;
        for (int i = indicesFila.primeiro, cont = 0; cont < indicesFila.ocupacao; 
                cont++, i = indicesFila.proximaPosicao(i)) {
            
            int indice = indicesFila.dados[i];
            Documento doc = documentos.get(indice);
            
            if (doc.getNomeArquivo().equals(nomeArquivo)) {
                return posicao;
            }
            posicao++;
        }
        return -1;
    }

    public Documento consultarDocumento(String nomeArquivo) {
        for (int i = indicesFila.primeiro, cont=0; cont < indicesFila.ocupacao; 
                cont++, i = indicesFila.proximaPosicao(i)) {
            
            int indice = indicesFila.dados[i];
            Documento doc = documentos.get(indice);
            
            if (doc.getNomeArquivo().equals(nomeArquivo)) {
                return doc;
            }
        }
        return null;
    }

    public String gerarRelatorio() {
        if (filaVazia()) return "Fila vazia";

        StringBuilder sb = new StringBuilder();
        sb.append("Relatório de Impressão:\n");
        sb.append("Ocupação: ").append(indicesFila.ocupacao).append("/").append(capacidade).append("\n");
        sb.append("Documentos na fila:\n");

        int posicao = 1;
        for (int i = indicesFila.primeiro, cont = 0; cont < indicesFila.ocupacao; 
                cont++, i = indicesFila.proximaPosicao(i)) {
            
            int indice = indicesFila.dados[i];
            sb.append(posicao++).append(". ").append(documentos.get(indice).toString()).append("\n");
            posicao++;
        }
        return sb.toString();
    }
}