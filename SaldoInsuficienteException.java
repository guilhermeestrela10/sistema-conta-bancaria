/**
 * Exceção lançada quando o saldo (+ limite, se houver) é insuficiente para um saque.
 *
 * JUSTIFICATIVA — por que Unchecked (extends RuntimeException)?
 * ---------------------------------------------------------------
 * Saldo insuficiente representa uma violação de regra de negócio causada por
 * dados de entrada inválidos em tempo de execução. O chamador sabe de antemão
 * que precisa verificar o saldo antes de sacar; não obrigá-lo a declarar o
 * catch no compilador segue o padrão das exceções "de programador" (como
 * IllegalArgumentException). Além disso, forçar checked aqui poluiria
 * assinaturas em cadeia sem agregar segurança real — o tratamento adequado
 * é validar o saldo antes da chamada, não capturar silenciosamente depois.
 */
public class SaldoInsuficienteException extends RuntimeException {

    private final double saldoAtual;
    private final double valorSolicitado;

    public SaldoInsuficienteException(String message, double saldoAtual, double valorSolicitado) {
        super(message);
        this.saldoAtual = saldoAtual;
        this.valorSolicitado = valorSolicitado;
    }

    public double getSaldoAtual()      { return saldoAtual;      }
    public double getValorSolicitado() { return valorSolicitado; }
}
