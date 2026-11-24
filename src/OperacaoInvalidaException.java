/**
 * Exceção lançada ao tentar realizar qualquer operação em uma conta bloqueada,
 * ou ao violar regras de negócio específicas de cada tipo de conta
 * (ex.: prazo de 30 dias na poupança, limite mensal de saques no salário).
 *
 * JUSTIFICATIVA — por que Checked (extends Exception)?
 * ------------------------------------------------------
 * Uma conta bloqueada é um estado legítimo e previsível do sistema; qualquer
 * código que chame depositar() ou sacar() DEVE lidar explicitamente com essa
 * possibilidade. O compilador exigindo o tratamento (try/catch ou throws)
 * é exatamente o comportamento desejado: evita que o programador esqueça de
 * tratar o bloqueio, garantindo integridade das operações bancárias.
 * Diferentemente de SaldoInsuficienteException — que pode ser prevenida
 * validando o saldo antes — uma conta pode ser bloqueada por outro thread
 * ou processo entre a verificação e a operação, tornando o Checked obrigatório.
 */
public class OperacaoInvalidaException extends Exception {

    private final String numeroConta;

    public OperacaoInvalidaException(String message) {
        super(message);
        this.numeroConta = null;
    }

    public OperacaoInvalidaException(String message, String numeroConta) {
        super(message);
        this.numeroConta = numeroConta;
    }

    public String getNumeroConta() { return numeroConta; }
}
