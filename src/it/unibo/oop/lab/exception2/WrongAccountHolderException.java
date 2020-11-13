package it.unibo.oop.lab.exception2;

public class WrongAccountHolderException extends IllegalStateException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String toString() {
		return "Account sbagliato";
	}
}
