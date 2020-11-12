package it.unibo.oop.lab.exception1;

public class NotEnoughBatteryException extends IllegalStateException {
	
	private static final long serialVersionUID = 1L;
	private final double batterylevel;
	private final double batteryrequired;
	
	public NotEnoughBatteryException(final double batterylevel, final double required) {
		super();
		this.batterylevel = batterylevel;
		this.batteryrequired = required;
	}

	public String toString() {
		return "Non hai batteria per muovert. Il livello della batteria è = " + batterylevel
				+ ", e la batteria richiesta è: " + batteryrequired + "]";
	}
	
	public String getMessage() {
		return this.toString();
	}
}
