/**
 * Interface que define o contrato de tributação.
 * Implementada apenas por ContaCorrente e ContaPoupanca,
 * pois ContaSalario é isenta de impostos conforme regra de negócio.
 */
public interface Tributavel {
    /**
     * Calcula o imposto devido pela conta.
     * Cada tipo de conta aplica uma alíquota diferente.
     * @return valor do imposto em reais (R$)
     */
    double calcularImposto();
}
