/**
 * Conta Corrente — sem rendimento, com cheque especial de R$ 1.000,00
 * e tributação de 0,38% sobre cada saque.
 */
public class ContaCorrente extends ContaBancaria implements Tributavel {

    private static final double LIMITE_CHEQUE_ESPECIAL = 1_000.0;
    private static final double ALIQUOTA_IOF           = 0.0038;   // 0,38% por saque

    public ContaCorrente(String numero, String titular, double saldoInicial) {
        super(numero, titular, saldoInicial);
    }

    // ─── Rendimento: 0% ──────────────────────────────────────────────────────
    @Override
    public double calcularRendimento() {
        return 0.0;
    }

    // ─── Imposto: 0,38% sobre o valor de cada saque ──────────────────────────
    @Override
    public double calcularImposto() {
        // Retorna o imposto estimado sobre o saldo atual como base de cálculo
        return saldo * ALIQUOTA_IOF;
    }

    /**
     * Saque com suporte a cheque especial.
     * Permite sacar até saldo + R$ 1.000,00. Saldo pode ficar negativo.
     */
    @Override
    public void sacar(double valor) throws OperacaoInvalidaException {
        if (!isAtiva()) {
            throw new OperacaoInvalidaException(
                "Saque negado: conta " + numero + " está bloqueada.", numero);
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }
        double limiteTotal = saldo + LIMITE_CHEQUE_ESPECIAL;
        if (valor > limiteTotal) {
            throw new SaldoInsuficienteException(
                String.format("Limite excedido. Disponível (saldo + cheque especial): "
                    + "R$ %.2f | Solicitado: R$ %.2f", limiteTotal, valor),
                saldo, valor
            );
        }
        this.saldo -= valor;
        System.out.printf("    Saque de R$ %.2f realizado. Saldo: R$ %.2f%s%n",
            valor, saldo, saldo < 0 ? "  ← usando cheque especial" : "");
    }

    public double getLimiteChequeEspecial() { return LIMITE_CHEQUE_ESPECIAL; }
}
