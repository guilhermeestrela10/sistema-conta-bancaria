import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Conta Poupança — rendimento de 0,5% a.m., imposto de 22,5% sobre o rendimento,
 * mínimo de 30 dias entre saques consecutivos.
 */
public class ContaPoupanca extends ContaBancaria implements Tributavel {

    private static final double TAXA_RENDIMENTO_MENSAL = 0.005;   // 0,5%
    private static final double ALIQUOTA_IR            = 0.225;   // 22,5%

    private LocalDate ultimoSaque;

    public ContaPoupanca(String numero, String titular, double saldoInicial) {
        super(numero, titular, saldoInicial);
        this.ultimoSaque = null;
    }

    // ─── Rendimento: 0,5% ao mês ─────────────────────────────────────────────
    @Override
    public double calcularRendimento() {
        return saldo * TAXA_RENDIMENTO_MENSAL;
    }

    // ─── Imposto: 22,5% sobre o rendimento ───────────────────────────────────
    @Override
    public double calcularImposto() {
        return calcularRendimento() * ALIQUOTA_IR;
    }

    /**
     * Saque com carência de 30 dias entre operações.
     * @throws OperacaoInvalidaException se o prazo mínimo não foi respeitado
     *         ou se a conta estiver bloqueada
     */
    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        if (!isAtiva()) {
            throw new OperacaoInvalidaException(
                "Saque negado: conta " + numero + " está bloqueada.", numero);
        }
        if (ultimoSaque != null) {
            long diasPassados = ChronoUnit.DAYS.between(ultimoSaque, LocalDate.now());
            if (diasPassados < 30) {
                long diasRestantes = 30 - diasPassados;
                throw new OperacaoInvalidaException(
                    String.format("Conta Poupança: aguarde %d dia(s) para o próximo saque. "
                        + "Último saque em: %s", diasRestantes, ultimoSaque),
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
        this.ultimoSaque = LocalDate.now();
        System.out.printf("    Saque de R$ %.2f realizado. Saldo: R$ %.2f%n", valor, saldo);
    }

    public LocalDate getUltimoSaque() { return ultimoSaque; }
}
