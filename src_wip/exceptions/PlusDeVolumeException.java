package exceptions;

public class PlusDeVolumeException extends Exception {
	public PlusDeVolumeException() {
		super("Le vin n'a plus assez de volume.");
	}
}
