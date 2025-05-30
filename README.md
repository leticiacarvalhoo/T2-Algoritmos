# Sistema de Gerenciamento de Impressão
 Bem-vindo ao Sistema de Gerenciamento de Impressão! Este projeto simula o gerenciamento de uma fila de impressão e uma pilha de reimpressão, permitindo adicionar, consultar, imprimir e reimprimir documentos, além de carregar documentos a partir de arquivos texto.

## Autores
<p><a href="https://github.com/brenoaugustoog" target="_blank">👨‍💻 Breno Augusto Oliveira Gandolfo - 24.01496-6</a></p>
<p><a href="https://github.com/gustavoseripierri" target="_blank">👨‍💻 Gustavo Seripierri - 24.00630-0</a></p>
<p><a href="https://github.com/leticiacarvalhoo" target="_blank">👩‍💻 Leticia de Carvalho Silva - 24.00141-4</a></p>
<p><a href="https://github.com/lyssaokawaperini" target="_blank">👩‍💻 Lyssa Okawa Perini - 24.01193-2</a></p>

## Índice
 - Descrição
 - Funcionalidades
 - Estrutura do Projeto
 - Como Executar
 - Como Usar
 - Formato do Arquivo de Entrada
 - Exemplo de Uso
 - Autores
 - Descrição
<p>Este sistema foi desenvolvido para simular o funcionamento de uma fila de impressão (FIFO) e uma pilha de reimpressão (LIFO), com capacidade limitada, gerenciamento de documentos e operações interativas via terminal. Ideal para estudos de algoritmos e estruturas de dados.</p>

## Funcionalidades
 - Adicionar documento à fila de impressão
 - Imprimir documento da fila
 - Consultar documento na fila
 - Exibir relatório da fila de impressão
 - Adicionar documento à pilha de reimpressão
 - Reimprimir documento da pilha
 - Consultar documento na pilha de reimpressão
 - Exibir relatório da pilha de reimpressão
 - Carregar documentos de arquivo texto
 - Mensagens de erro e sucesso para todas as operações
   
## Estrutura do Projeto
```
  T2-Algoritmos/
  ├─ Documento.java
  ├─ Fila.java
  ├─ FilaImpressao.java
  ├─ LeitorArquivos.java
  ├─ Pilha.java
  ├─ PilhaReimpressao.java
  ├─ README.md
  └─ SistemaGerenciamentoImpressao.java
```

## Como Executar
### Pré-requisitos:

Java JDK 8 ou superior instalado.

Todos os arquivos .java do projeto no mesmo diretório.

## Compilação:
No terminal, navegue até a pasta do projeto e execute:
```
  javac SistemaGerenciamentoImpressao.java
```

### Execução:
Após compilar, execute:
```
  java SistemaGerenciamentoImpressao
```

## Como Usar
Ao iniciar o sistema, será exibido um menu interativo:

```
  SISTEMA DE GERENCIAMENTO DE IMPRESSÃO
1. Adicionar documento à fila de impressão
2. Imprimir documento da fila
3. Consultar documento na fila
4. Exibir relatório da fila de impressão
5. Adicionar documento à pilha de reimpressão
6. Reimprimir documento da pilha
7. Consultar documento na pilha de reimpressão
8. Exibir relatório da pilha de reimpressão
9. Carregar documentos de arquivo texto
0. Sair
Escolha uma opção:
```

Digite o número da opção desejada e siga as instruções na tela.

## Como funciona o menu do sistema
### O sistema utiliza duas estruturas clássicas de dados:

Fila de Impressão (FIFO - First In, First Out): O primeiro documento a entrar é o primeiro a ser impresso.

Pilha de Reimpressão (LIFO - Last In, First Out): O último documento adicionado é o primeiro a ser reimpresso.

### Opções do Menu
#### 1. Adicionar documento à fila de impressão

O sistema solicitará o nome do arquivo e o nome do usuário. O documento será adicionado ao final da fila de impressão (FIFO).

#### 2. Imprimir documento da fila

Remove e imprime o documento mais antigo da fila (o que está há mais tempo aguardando). Exibe o nome do documento impresso e o tempo de espera.

#### 3. Consultar documento na fila

Solicita o nome do arquivo.
  
  - Se encontrado, mostra a posição na fila e detalhes do documento.
  
  - Se não encontrado, informa que o documento não está na fila.

#### 4. Exibir relatório da fila de impressão

Exibe todos os documentos atualmente na fila, em ordem de chegada (FIFO), com detalhes.

#### 5. Adicionar documento à pilha de reimpressão

Solicita o nome do arquivo e o nome do usuário. O documento será adicionado ao topo da pilha de reimpressão (LIFO).

#### 6. Reimprimir documento da pilha

Remove e imprime o documento do topo da pilha (o mais recentemente adicionado). Exibe o nome do documento reimpresso e o tempo de espera.

#### 7. Consultar documento na pilha de reimpressão

Solicita o nome do arquivo.

  - Se encontrado, mostra os detalhes do documento.

  - Se não encontrado, informa que o documento não está na pilha.

#### 8. Exibir relatório da pilha de reimpressão

Exibe todos os documentos presentes na pilha, do topo para a base (LIFO), com detalhes.

#### 9. Carregar documentos de arquivo texto

Solicita o caminho do arquivo (deve estar no formato esperado pelo método LeitorArquivos.lerDocumentosDeArquivo).

O sistema informará quantos documentos foram encontrados e perguntará se deseja adicionar à fila de impressão (FIFO) ou à pilha de reimpressão (LIFO).

Os documentos serão adicionados até atingir a capacidade máxima da fila ou pilha.

#### 0. Sair

Encerra o programa.

_Essas opções permitem gerenciar documentos de forma eficiente, utilizando os conceitos de FIFO (fila) e LIFO (pilha) para simular o funcionamento real de sistemas de impressão e reimpressão._

## Formato do Arquivo de Entrada
O arquivo texto deve conter um documento por linha, com os campos separados por vírgula:

```
  nomeArquivo1.pdf,Usuario1
  nomeArquivo2.docx,Usuario2
  nomeArquivo3.txt,Usuario3
```

Exemplo de caminho no Windows:

```
  C:\Users\SeuUsuario\documentos.txt
```

# Trabalho desenvolvido para a disciplina de Algoritmos, Estruturas de Dados e Programação.
