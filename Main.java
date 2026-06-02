import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal — demonstra polimorfismo (R9) e trata ambas as excecoes.
 */
public class Main {

    public static void main(String[] args) {
        separador("SISTEMA DE CONTA BANCARIA DIGITAL");

        // Criacao das contas
        ContaCorrente corrente = new ContaCorrente("CC-001", "Joao Silva",   2000.00);
        ContaPoupanca poupanca = new ContaPoupanca("CP-002", "Maria Souza",  5000.00);
        ContaSalario  salario  = new ContaSalario ("CS-003", "Carlos Lima",  3000.00);

        // R9: List<ContaBancaria> — polimorfismo
        List<ContaBancaria> contas = new ArrayList<>();
        contas.add(corrente);
        contas.add(poupanca);
        contas.add(salario);

        // ==============================================================
        secao("1. POLIMORFISMO — calcularRendimento() para cada conta");
        for (ContaBancaria c : contas) {
            System.out.printf("  %-25s -> Rendimento mensal: R$ %.2f%n",
                c.getClass().getSimpleName() + " (" + c.getTitular() + ")",
                c.calcularRendimento());
        }

        // ==============================================================
        secao("2. OPERACOES NORMAIS (depositos e saques)");
        try {
            System.out.println("  [ContaCorrente - Joao]");
            corrente.depositar(500.00);
            corrente.sacar(300.00);

            System.out.println("  [ContaPoupanca - Maria]");
            poupanca.depositar(1000.00);
            // Nao vamos sacar da poupanca aqui para preservar o teste de SaldoInsuficiente

            System.out.println("  [ContaSalario - Carlos]");
            salario.depositar(2500.00);
            salario.sacar(1000.00);

        } catch (OperacaoInvalidaException e) {
            System.out.println("  ERRO inesperado: " + e.getMessage());
        }

        // ==============================================================
        // Conta auxiliar sem restricoes de carencia para testar SaldoInsuficiente
        ContaCorrente contaTeste = new ContaCorrente("CC-999", "Teste Saldo", 100.00);

        secao("3. SaldoInsuficienteException (Unchecked) — saque > saldo + limite");
        System.out.printf("  Conta de teste com saldo R$ 100,00 + limite R$ 1.000,00 = R$ 1.100,00%n");
        System.out.println("  Tentando sacar R$ 5.000,00...");
        try {
            contaTeste.sacar(5_000.00);
        } catch (SaldoInsuficienteException e) {
            // Unchecked: nao exige declaracao de throws, mas deve ser capturada
            System.out.println("  [CAPTURADO - RuntimeException] " + e.getMessage());
            System.out.printf("  Saldo atual: R$ %.2f | Valor solicitado: R$ %.2f%n",
                e.getSaldoAtual(), e.getValorSolicitado());
        } catch (OperacaoInvalidaException e) {
            System.out.println("  [Inesperado] " + e.getMessage());
        }

        // ==============================================================
        secao("4. OperacaoInvalidaException (Checked) — operar conta bloqueada");
        try {
            System.out.println("  Bloqueando a Conta Corrente de Joao...");
            corrente.bloquear();
            System.out.println("  Tentando depositar R$ 200,00 na conta bloqueada...");
            corrente.depositar(200.00);
        } catch (OperacaoInvalidaException e) {
            // Checked: compilador obriga o try/catch ou throws na assinatura
            System.out.println("  [CAPTURADO - Exception] " + e.getMessage());
            System.out.println("  Numero da conta: " + e.getNumeroConta());
        } finally {
            try { corrente.desbloquear(); } catch (Exception ignored) {}
        }

        // ==============================================================
        secao("5. CHEQUE ESPECIAL — ContaCorrente aceita saldo negativo");
        try {
            System.out.printf("  Saldo atual de Joao: R$ %.2f%n", corrente.getSaldo());
            System.out.println("  Sacando R$ 2.500,00 (saldo ~2.200 + limite 1.000 = 3.200)...");
            corrente.sacar(2_500.00);
            System.out.printf("  Saldo pos-saque: R$ %.2f (cheque especial em uso)%n", corrente.getSaldo());
        } catch (SaldoInsuficienteException | OperacaoInvalidaException e) {
            System.out.println("  ERRO: " + e.getMessage());
        }

        // ==============================================================
        secao("6. CALCULO DE IMPOSTOS (interface Tributavel)");
        for (ContaBancaria c : contas) {
            if (c instanceof Tributavel t) {
                System.out.printf("  %-14s (%s) -> Imposto: R$ %.4f%n",
                    c.getClass().getSimpleName(), c.getTitular(), t.calcularImposto());
            } else {
                System.out.printf("  %-14s (%s) -> Isenta de imposto%n",
                    c.getClass().getSimpleName(), c.getTitular());
            }
        }

        // ==============================================================
        secao("7. OperacaoInvalidaException — ContaSalario limite mensal atingido");
        try {
            System.out.println("  Carlos ja realizou 1 saque este mes. Tentando segundo saque...");
            salario.sacar(500.00);
        } catch (OperacaoInvalidaException e) {
            System.out.println("  [CAPTURADO] " + e.getMessage());
        } catch (SaldoInsuficienteException e) {
            System.out.println("  [Inesperado] " + e.getMessage());
        }

        // ==============================================================
        secao("8. ESTADO FINAL DAS CONTAS");
        for (ContaBancaria c : contas) {
            System.out.println("  " + c);
        }

        // ==============================================================
        secao("9. IllegalArgumentException — titular nulo ou vazio");
        try {
            new ContaCorrente("XX-000", "   ", 100.0);
        } catch (IllegalArgumentException e) {
            System.out.println("  [CAPTURADO] " + e.getMessage());
        }

        separador("FIM DA DEMONSTRACAO");
    }

    private static void separador(String titulo) {
        System.out.println("\n==================================================");
        System.out.println("  " + titulo);
        System.out.println("==================================================");
    }

    private static void secao(String titulo) {
        System.out.println("\n-- " + titulo + " --");
    }
}
