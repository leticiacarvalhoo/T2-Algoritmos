public class FilaImpressao {
    private Documento[] dados;
    private int primeiro, ultimo, ocupacao;

    public FilaImpressao(int capacidade) {
        dados = new Documento[capacidade];
        primeiro = 0;
        ultimo = 0;
        ocupacao = 0;
    }

    public FilaImpressao() {
        this(10);
    }

    public boolean filaVazia() {
        return ocupacao == 0;
    }

    public boolean filaCheia() {
        return ocupacao == dados.length;
    }

    private int proximaPosicao(int posicao) {
        return (posicao + 1) % dados.length;
    }

    public void adicionarDocumento(Documento doc) {
        if (filaCheia())
            throw new RuntimeException("Fila cheia");
        dados[ultimo] = doc;
        ultimo = proximaPosicao(ultimo);
        ocupacao++;
    }

    public Documento imprimirDocumento() {
        if (filaVazia())
            throw new RuntimeException("Fila vazia");
        Documento doc = dados[primeiro];
        primeiro = proximaPosicao(primeiro);
        ocupacao--;
        return doc;
    }

    public Documento consultarDocumento(String nomeArquivo) {
        for (int i = primeiro, cont = 0; cont < ocupacao; cont++, i = proximaPosicao(i)) {
            if (dados[i].getNomeArquivo().equals(nomeArquivo)) {
                return dados[i];
            }
        }
        return null;
    }

    public int buscarPosicaoDocumento(String nomeArquivo) {
        for (int i = primeiro, cont = 0, pos = 0; cont < ocupacao; cont++, i = proximaPosicao(i), pos++) {
            if (dados[i].getNomeArquivo().equals(nomeArquivo)) {
                return pos;
            }
        }
        throw new DocumentoNaoEncontradoException(nomeArquivo);
    }

    public String gerarRelatorio() {
        if (filaVazia())
            return "Fila vazia";

        StringBuilder sb = new StringBuilder();
        sb.append("Relatório de Impressão:\n");
        sb.append("Ocupação: ").append(ocupacao).append("/").append(dados.length).append("\n");
        sb.append("Documentos na fila:\n");

        for (int i = primeiro, cont = 0, pos = 1; cont < ocupacao; cont++, i = proximaPosicao(i), pos++) {
            sb.append(pos).append(". ").append(dados[i]).append("\n");
        }
        return sb.toString();
    }

    public class DocumentoNaoEncontradoException extends RuntimeException {
        public DocumentoNaoEncontradoException(String nomeArquivo) {
            super("Documento não encontrado: " + nomeArquivo);
        }
    }
}