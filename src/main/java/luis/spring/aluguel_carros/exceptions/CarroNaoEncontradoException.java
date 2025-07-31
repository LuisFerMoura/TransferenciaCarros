package luis.spring.aluguel_carros.exceptions;

public class CarroNaoEncontradoException extends RuntimeException {
    public CarroNaoEncontradoException(String message) {
        super(message);
    }
    public CarroNaoEncontradoException(long id) {
        super("Carro com ID " + id + " não encontrado.");
    }
}
