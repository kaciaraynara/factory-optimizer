

# Factory Optimization System

### Solução de Inteligência para Gestão e Otimização de Produção

---

## Sobre o Projeto

O **Factory Optimization System** é uma plataforma full-stack projetada para solucionar desafios de gestão de inventário e planejamento de produção em escala industrial. O sistema utiliza um motor de cálculo avançado que analisa a disponibilidade de matérias-primas e as receitas de produtos para determinar o cenário produtivo que entrega a maior rentabilidade possível.

---

## Diferenciais Técnicos

* **Motor de Decisão Inteligente**: Implementação de um Algoritmo Ganancioso (Greedy Algorithm) para otimização de recursos.
* **Arquitetura Desacoplada**: Separação rigorosa entre a lógica de negócio no back-end e a interface reativa no front-end.
* **Persistência de Dados**: Uso de abstrações JPA para gerenciamento eficiente de estoque e histórico de produção.
* **Foco em Performance**: Front-end desenvolvido com Vue 3 e Vite, garantindo tempos de resposta reduzidos e alta fluidez na navegação.

---

## Stack Tecnológica

| Camada | Tecnologia | Finalidade |
| --- | --- | --- |
| **Back-end** | Java 17 / Spring Boot 3 | API REST e Motor de Cálculo |
| **Front-end** | Vue 3 (Composition API) | Interface Reativa e SPA |
| **Build Tool** | Vite / Maven | Gerenciamento de Dependências e Compilação |
| **Database** | H2 Database | Persistência em Memória para Prototipagem Ágil |
| **Testes** | JUnit 5 / Mockito | Validação da Lógica de Negócio e Otimização |

---

## O Motor de Otimização

A inteligência de maximização de lucro reside no `FactoryService`, operando sob três pilares fundamentais:

1. **Priorização de Ativos**: Organiza os produtos cadastrados com base no preço de venda, assegurando que os recursos sejam alocados primeiro onde há maior retorno financeiro.
2. **Mapeamento de Inventário**: Realiza uma simulação do estoque atual de forma isolada, permitindo cálculos complexos sem comprometer a integridade dos dados persistidos.
3. **Alocação de Insumos**: Reserva matérias-primas progressivamente para os itens mais rentáveis até que os recursos disponíveis sejam esgotados.

---

## Instruções para Execução

### Pré-requisitos

* Java JDK 17 ou superior
* Node.js (v18+)
* Maven

### Configuração do Back-end

```bash
cd backend
mvn clean compile
mvn spring-boot:run

```

O servidor será iniciado em: `http://localhost:8080`

### Configuração do Front-end

```bash
cd frontend
npm install
npm run dev

```

O painel de controle estará disponível em: `http://localhost:5173`
