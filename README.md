# T2 - Algoritmos

## Gerenciamento de Arquivos em um Sistema de Impressão

Este projeto simula o funcionamento de um sistema de gerenciamento de impressões em uma gráfica rápida, utilizando estruturas de dados como **Fila (FIFO)** e **Pilha (LIFO)** para organizar os documentos.

---

## Parte 1: Impressora com Fila de Impressão (FIFO)

### Descrição

Nesta parte, os documentos enviados para impressão entram em uma **fila**, onde o primeiro a entrar é o primeiro a ser impresso (**First In, First Out**).

### Funcionalidades

* **Entrada de Documentos**:
  Adiciona um novo documento ao final da fila.
  Cada documento registra:

  * Nome do arquivo
  * Nome do usuário
  * Horário da solicitação

* **Impressão de Documentos**:
  Remove o primeiro documento da fila e registra:

  * Horário de impressão
  * Tempo total de espera

* **Consulta de Documento**:
  Permite:

  * Verificar se um documento está na fila
  * Consultar sua posição
  * Ver o horário de solicitação

* **Capacidade da Fila**:
  A fila tem uma **capacidade máxima** definida.
  Quando atingida, novos documentos não podem ser adicionados.

---

## Parte 2: Pilha de Reimpressão Emergencial (LIFO)

### Descrição

Nesta parte, documentos que precisam ser **reimpressos com urgência** são adicionados a uma **pilha**, onde o último a entrar é o primeiro a ser reimpresso (**Last In, First Out**).

### Funcionalidades

* **Solicitação de Reimpressão**:
  Adiciona um documento ao topo da pilha.
  Cada documento registra:

  * Nome do arquivo
  * Nome do usuário
  * Horário da solicitação

* **Execução de Reimpressão**:
  Remove o documento do topo da pilha e registra:

  * Horário da reimpressão
  * Tempo decorrido desde a solicitação

* **Consulta de Documento**:
  Permite:

  * Verificar se um documento está na pilha
  * Consultar sua posição (a partir do topo)
  * Ver o horário de solicitação

* **Capacidade da Pilha**:
  A pilha tem uma **capacidade máxima** definida.
  Quando atingida, novas solicitações são recusadas.

---

## Objetivos Gerais

* Implementar um sistema funcional que simule o envio, gerenciamento e reimpressão de documentos.
* Utilizar corretamente estruturas de dados:

  * **Fila** para a impressão normal.
  * **Pilha** para a reimpressão emergencial.
* Fornecer relatórios com:

  * Ocupação atual das estruturas
  * Tempo de espera ou reimpressão de cada documento