package luis.spring.aluguel_carros.exceptions;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String message) {
        super(message);
    }

    public SaldoInsuficienteException(String nome, double precoCarro) {
        super("Saldo insuficiente para  " + nome + " comprar o carro com preço " + precoCarro);
    }
    
}
