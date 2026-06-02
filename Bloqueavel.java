/**
 * Interface que define o contrato de bloqueio de conta.
 * Implementada por todas as contas bancárias do sistema.
 * Permite bloquear, desbloquear e verificar o status de atividade.
 */
public interface Bloqueavel {
    /** Bloqueia a conta, impedindo qualquer operação financeira. */
    void bloquear();

    /** Desbloqueia a conta, restaurando o acesso às operações. */
    void desbloquear();

    /**
     * Verifica se a conta está ativa (desbloqueada).
     * @return true se ativa, false se bloqueada
     */
    boolean isAtiva();
}
