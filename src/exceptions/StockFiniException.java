package exceptions;

public class StockFiniException extends Exception {
	public StockFiniException() {
		super("Il n'y a plus de vin disponible en stockage.");
	}
}
