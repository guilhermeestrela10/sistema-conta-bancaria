<div align="center">

# 🏦 Sistema de Conta Bancária

**Simulação de um sistema bancário em Java, aplicando os pilares da Programação Orientada a Objetos**

![Java](https://img.shields.io/badge/Java-100%25-orange?logo=openjdk&logoColor=white)
![POO](https://img.shields.io/badge/Paradigma-POO-blue)
![Status](https://img.shields.io/badge/Status-Concluído-brightgreen)
![UNIFSA](https://img.shields.io/badge/Projeto-Acadêmico-purple)

</div>

---

## 📖 Sobre o projeto

Este projeto foi desenvolvido como atividade prática da disciplina de **Programação Orientada a Objetos** no curso de **Engenharia de Software** do **UNIFSA (Centro Universitário Santo Agostinho)**.

A ideia é modelar um sistema de contas bancárias usando **herança, abstração, polimorfismo, encapsulamento, interfaces e tratamento de exceções personalizadas**.

---

## ✨ Conceitos aplicados

| Conceito | Onde aparece |
|---|---|
| **Abstração** | Classe abstrata `ContaBancaria` |
| **Herança** | `ContaCorrente`, `ContaPoupanca` e `ContaSalario` herdam de `ContaBancaria` |
| **Polimorfismo** | Cada tipo de conta se comporta de forma própria nas operações |
| **Encapsulamento** | Atributos protegidos, acessados por métodos |
| **Interfaces** | `Tributavel` e `Bloqueavel` |
| **Exceções personalizadas** | `SaldoInsuficienteException` e `OperacaoInvalidaException` |

---

## 🗂️ Estrutura do repositório

```text
sistema-conta-bancaria/
├── src/          Classes Java, interfaces, exceções e Main.java
├── docs/         Enunciado e diagrama de classes
└── README.md
```

---

## 🧩 Diagrama de classes

O diagrama completo está no arquivo [`diagrama_classes.drawio`](docs/diagrama_classes.drawio) e pode ser aberto em [app.diagrams.net](https://app.diagrams.net).

---

## 🚀 Como executar

### Pré-requisitos

- [JDK 17+](https://adoptium.net/) instalado
- Terminal (PowerShell, CMD, Bash...)

### Passo a passo

```bash
# 1. Clone o repositório
git clone https://github.com/guilhermeestrela10/sistema-conta-bancaria.git

# 2. Entre na pasta
cd sistema-conta-bancaria

# 3. Compile todos os arquivos
javac -encoding UTF-8 -d build src/*.java

# 4. Execute o programa
java -cp build Main
```

> 💡 Também funciona abrindo a pasta no **VS Code** ou **IntelliJ** e rodando a classe `Main`.

---

## 🛠️ Tecnologias

- ☕ **Java**
- 🎨 **draw.io** (diagrama UML)
- 🐙 **Git & GitHub**

---

## 👨‍💻 Autor

**Guilherme Henrique Macedo Estrela** (Gui)
Estudante de Engenharia de Software — UNIFSA

[![GitHub](https://img.shields.io/badge/GitHub-guilhermeestrela10-181717?logo=github)](https://github.com/guilhermeestrela10)

---

<div align="center">

Feito com ☕ e muito estudo de POO

</div>
