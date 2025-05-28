public class Pilha {
    private No topo;
    private int capacidade;
    private int tamanho;

    private static class No {
        private Documento info;
        private No proximo;

        public No(Documento info) {
            this.info = info;
            this.proximo = null;
        }

        public Documento getInfo() {
            return info;
        }

        public No getProximo() {
            return proximo;
        }

        public void setProximo(No proximo) {
            this.proximo = proximo;
        }
    }

    public class DocumentoNaoEncontradoException extends RuntimeException {
        public DocumentoNaoEncontradoException(String nomeArquivo) {
            super("Documento nao encontrado: " + nomeArquivo);
        }
    }

    public Pilha(int capacidade) {
        this.topo = null;
        this.capacidade = capacidade;
        this.tamanho = 0;
    }

    public boolean pilhaVazia() {
        return topo == null;
    }

    public boolean pilhaCheia() {
        return tamanho >= capacidade;
    }

    public void push(Documento doc) {
        if (pilhaCheia()) {
            throw new RuntimeException("Pilha cheia, nao e possivel adicionar mais documentos.");
        }
        No novo = new No(doc);
        novo.setProximo(topo); 
        topo = novo;
        tamanho++;
    }

    public Documento pop() {
        if (pilhaVazia()) {
            throw new RuntimeException("Pilha vazia, nao e possivel remover documentos.");
        }
        Documento info = topo.getInfo();
        topo = topo.getProximo();
        tamanho--;
        return info;
    }

    public Documento peek() {
        if (pilhaVazia()) {
            throw new RuntimeException("Pilha vazia, nao ha documentos para consultar.");
        }
        return topo.getInfo();
    }

    public Pilha copiar() {
        Pilha copia = new Pilha(this.capacidade); 
        Pilha aux = new Pilha(this.capacidade);
        No atual = topo;
        while (atual != null) {
            aux.push(atual.getInfo());
            atual = atual.getProximo();
        }

        while (!aux.pilhaVazia()) {
            Documento d = aux.pop();
            copia.push(d);
        }
        return copia;
    }

    public Documento consultarDocumento(String nomeArquivo) {
        Pilha aux = this.copiar();
        while (!aux.pilhaVazia()) {
            Documento d = aux.pop();
            if (d.getNomeArquivo().equals(nomeArquivo)) {
                return d; 
            }
        }
        throw new DocumentoNaoEncontradoException(nomeArquivo);
    }

    public String gerarRelatorio() {
        if (pilhaVazia()) {
            return "Pilha de documentos vazia.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Relatorio da Pilha de Documentos:\n");
        sb.append("Ocupacao: ").append(tamanho).append("/").append(capacidade).append("\n");
        sb.append("Documentos na pilha (do topo para a base):\n");

        Pilha aux = this.copiar();
        int pos = 1;

        while (!aux.pilhaVazia()) {
            Documento d = aux.pop();
            sb.append(pos++).append(". ").append(d).append("\n");
        }
        return sb.toString();
    }
}
