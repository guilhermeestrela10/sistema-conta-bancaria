import java.time.LocalDate;

/**
 * Conta Salário — sem rendimento, sem imposto, máximo de 1 saque por mês.
 */
public class ContaSalario extends ContaBancaria {

    private LocalDate ultimoSaqueMes;

    public ContaSalario(String numero, String titular, double saldoInicial) {
        super(numero, titular, saldoInicial);
        this.ultimoSaqueMes = null;
    }

    // ─── Rendimento: nenhum ───────────────────────────────────────────────────
    @Override
    public double calcularRendimento() {
        return 0.0;
    }

    /**
     * Saque com limite de 1 operação por mês calendário.
     * @throws OperacaoInvalidaException se o limite mensal já foi atingido
     *         ou se a conta estiver bloqueada
     */
    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        if (!isAtiva()) {
            throw new OperacaoInvalidaException(
                "Saque negado: conta " + numero + " está bloqueada.", numero);
        }
        if (ultimoSaqueMes != null) {
            LocalDate hoje = LocalDate.now();
            if (ultimoSaqueMes.getMonth() == hoje.getMonth()
                    && ultimoSaqueMes.getYear() == hoje.getYear()) {
                throw new OperacaoInvalidaException(
                    "Conta Salário: limite de 1 saque por mês já utilizado. "
                        + "Próximo saque disponível em: " + hoje.withDayOfMonth(1).plusMonths(1),
                    numero
                );
            }
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }
        if (valor > saldo) {
            throw new SaldoInsuficienteException(
                String.format("Saldo insuficiente. Saldo: R$ %.2f | Solicitado: R$ %.2f",
                    saldo, valor),
                saldo, valor
            );
        }
        this.saldo -= valor;
        this.ultimoSaqueMes = LocalDate.now();
        System.out.printf("    Saque de R$ %.2f realizado. Saldo: R$ %.2f%n", valor, saldo);
    }
}
