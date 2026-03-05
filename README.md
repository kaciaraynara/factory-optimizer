Factory Optimization System
Ferramenta de Gestão e Planejamento de Produção
Este projeto consiste em uma solução Full-Stack desenvolvida para otimizar o faturamento em ambientes de manufatura e pesquisa. Através de um motor de cálculo inteligente, o sistema analisa o estoque de matérias-primas e sugere o melhor plano de produção possível para maximizar o lucro bruto.

Tecnologias Utilizadas
Back-end
Java 17 e Spring Boot 3: Estrutura robusta para API RESTful e injeção de dependências.

Spring Data JPA: Gerenciamento eficiente da camada de persistência.

H2 Database: Banco de dados em memória para execução rápida sem necessidade de configurações externas.

JUnit 5 e Mockito: Testes unitários para validar a integridade da lógica de otimização.

Front-end
Vue 3 (Composition API): Interface moderna, reativa e modular.

Vite: Tooling de nova geração para um ciclo de desenvolvimento ágil.

Axios: Integração fluida para consumo de serviços assíncronos.

Lógica de Otimização (Greedy Algorithm)
O diferencial técnico desta aplicação reside na implementação de um Algoritmo Ganancioso (Greedy Algorithm) dentro do FactoryService.

Funcionamento do Motor:

Simulação de Inventário: O sistema mapeia o estado atual do estoque de forma isolada para não comprometer a persistência durante os cálculos.

Priorização por Rentabilidade: Todos os produtos são ordenados de forma decrescente pelo preço de venda, garantindo que recursos escassos sejam aplicados primeiro nos itens de maior valor.

Cálculo de Viabilidade: O algoritmo percorre a lista e define a quantidade máxima de produção permitida pelos insumos disponíveis, deduzindo-os progressivamente do estoque simulado.

Instruções para Execução
1. Back-end
Certifique-se de possuir o Maven instalado.

Bash
cd backend
mvn clean spring-boot:run
A API estará disponível no endereço: http://localhost:8080

2. Front-end
Certifique-se de possuir o Node.js instalado.

Bash
cd frontend
npm install
npm run dev
Acesse a interface pelo endereço: http://localhost:5173