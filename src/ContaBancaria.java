/**
 * Classe abstrata que representa uma conta bancária genérica.
 * Define a estrutura e os comportamentos comuns a todos os tipos de conta.
 * Subclasses devem implementar calcularRendimento() e podem sobrescrever sacar().
 */
public abstract class ContaBancaria implements Bloqueavel {

    protected final String numero;
    protected final String titular;
    protected double saldo;
    private boolean ativa;

    /**
     * Construtor protegido — inicializa os dados comuns.
     * @throws IllegalArgumentException se o titular for nulo ou vazio
     */
    public ContaBancaria(String numero, String titular, double saldoInicial) {
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("Titular não pode ser nulo ou vazio.");
        }
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.ativa = true;
    }

    // ─── Método abstrato (R2) ────────────────────────────────────────────────
    /**
     * Calcula o rendimento mensal da conta.
     * Cada subclasse define sua própria lógica de rendimento.
     * @return valor do rendimento em reais (R$)
     */
    public abstract double calcularRendimento();

    // ─── Métodos concretos (R3) ──────────────────────────────────────────────
    /**
     * Realiza um depósito na conta.
     * @param valor valor a depositar (deve ser > 0)
     * @throws OperacaoInvalidaException se a conta estiver bloqueada
     * @throws IllegalArgumentException  se o valor for <= 0
     */
    public void depositar(double valor) throws OperacaoInvalidaException {
        if (!isAtiva()) {
            throw new OperacaoInvalidaException(
                "Depósito negado: conta " + numero + " está bloqueada.", numero);
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
        }
        this.saldo += valor;
        System.out.printf("    Depósito de R$ %.2f realizado. Saldo: R$ %.2f%n", valor, saldo);
    }

    /**
     * Realiza um saque na conta.
     * Subclasses podem sobrescrever para adicionar regras específicas
     * (cheque especial, carência, limite mensal).
     * @param valor valor a sacar (deve ser > 0)
     * @throws OperacaoInvalidaException    se a conta estiver bloqueada
     * @throws SaldoInsuficienteException   se o saldo for insuficiente (Unchecked)
     * @throws IllegalArgumentException     se o valor for <= 0
     */
    public void sacar(double valor) throws OperacaoInvalidaException {
        if (!isAtiva()) {
            throw new OperacaoInvalidaException(
                "Saque negado: conta " + numero + " está bloqueada.", numero);
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }
        if (valor > saldo) {
            throw new SaldoInsuficienteException(
                String.format("Saldo insuficiente. Saldo: R$ %.2f | Solicitado: R$ %.2f", saldo, valor),
                saldo, valor
            );
        }
        this.saldo -= valor;
        System.out.printf("    Saque de R$ %.2f realizado. Saldo: R$ %.2f%n", valor, saldo);
    }

    // ─── Implementação de Bloqueavel (R5) ────────────────────────────────────
    @Override
    public void bloquear() {
        this.ativa = false;
        System.out.println("    Conta " + numero + " bloqueada.");
    }

    @Override
    public void desbloquear() {
        this.ativa = true;
        System.out.println("    Conta " + numero + " desbloqueada.");
    }

    @Override
    public boolean isAtiva() { return ativa; }

    // ─── Getters ─────────────────────────────────────────────────────────────
    public String getNumero()  { return numero;  }
    public String getTitular() { return titular; }
    public double getSaldo()   { return saldo;   }

    @Override
    public String toString() {
        return String.format("[%s] %-15s | Conta: %s | Saldo: R$ %10.2f | %s",
            getClass().getSimpleName(), titular, numero, saldo,
            ativa ? "✔ Ativa" : "✘ Bloqueada");
    }
}
