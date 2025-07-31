package luis.spring.aluguel_carros.exceptions;

public class PessoaNaoEncontradaException extends RuntimeException {
    public PessoaNaoEncontradaException(String message) {
        super(message);
    }
    
    public PessoaNaoEncontradaException(long id) {
        super("Pessoa com ID " + id + " não encontrada.");
    }
    
}
