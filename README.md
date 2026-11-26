# Sistema de contas bancárias

Simulação de contas bancárias em Java para estudar orientação a objetos, regras por tipo de conta e tratamento de exceções.

[![Compilação](https://github.com/guilhermeestrela10/sistema-conta-bancaria/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/guilhermeestrela10/sistema-conta-bancaria/actions/workflows/ci.yml)

[Execução](#executar-localmente) · [Modelagem](#modelagem) · [Verificações](#verificações) · [Enunciado](docs/atividade_POO_conta_bancaria.pdf)

## Escopo

Projeto acadêmico da disciplina de Programação Orientada a Objetos, Engenharia de Software — UNIFSA. A demonstração cria contas em memória e apresenta depósitos, saques, rendimentos, tributação, bloqueio e falhas esperadas. Não há integração com instituições financeiras nem persistência.

## Modelagem

| Responsabilidade | Implementação |
| --- | --- |
| Abstração e regras comuns | `ContaBancaria` |
| Comportamento por tipo de conta | `ContaCorrente`, `ContaPoupanca`, `ContaSalario` |
| Contratos | `Bloqueavel`, `Tributavel` |
| Falhas de domínio | `SaldoInsuficienteException`, `OperacaoInvalidaException` |
| Demonstração das operações | `Main` |

O [diagrama de classes](docs/diagrama_classes.drawio) pode ser aberto no diagrams.net.

## Executar localmente

Requisito: JDK 17 ou superior. Não há dependências externas.

```shell
git clone https://github.com/guilhermeestrela10/sistema-conta-bancaria.git
cd sistema-conta-bancaria
javac -encoding UTF-8 -d build src/*.java
java -cp build Main
```

## Estrutura

```text
src/       Classes, interfaces, exceções e ponto de entrada
docs/      Enunciado e diagrama de classes
build/     Compilação local, ignorada pelo Git
```

## Verificações

O GitHub Actions compila as classes e executa a demonstração com Java 17 e 21. A execução verifica que a demonstração termina sem erro inesperado; não substitui uma suíte de testes unitários das regras de negócio.

## Autoria

Guilherme Henrique Macedo Estrela · Engenharia de Software — UNIFSA.
