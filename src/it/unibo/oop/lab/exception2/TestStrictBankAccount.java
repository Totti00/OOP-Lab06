package it.unibo.oop.lab.exception2;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test to test
 * {@link StrictBankAccount}.
 * 
 */
public class TestStrictBankAccount {

	private static final int AMOUNT_INITIAL = 10_000;
	private static final int TRANSACTION_INITIAL = 10;
	
	private static final int MOLTI_SOLDI = 50_000;
    /**
     * Used to test Exceptions on {@link StrictBankAccount}.
     */
    @Test
    public void testBankOperations() {
    	
    	final AccountHolder h1 = new AccountHolder("Mario", "Rossi", 1);
    	final AccountHolder h2 = new AccountHolder("Tommaso", "Zorzi", 2);
    	final BankAccount account1 = new StrictBankAccount(h1.getUserID(), AMOUNT_INITIAL, TRANSACTION_INITIAL);
    	final BankAccount account2 = new StrictBankAccount(h2.getUserID(), AMOUNT_INITIAL, TRANSACTION_INITIAL);
    	
    	try {
    		account1.deposit(4, 100);
    	}catch (Exception e) {
    		System.out.println(e);
    	}
    	Assert.assertEquals("Incorrect deposit", AMOUNT_INITIAL, account1.getBalance(), 0.001);

    	try {
    		account2.withdraw(h2.getUserID(), MOLTI_SOLDI);
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	Assert.assertEquals("Incorrect withdraw", AMOUNT_INITIAL, account2.getBalance(), 0.001);
    
    	try {
    		account1.withdraw(h1.getUserID(), 1000);
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	Assert.assertEquals("Incorrect withdraw", 9000, account1.getBalance(), 0.001);
    	
    	System.out.println("b1 balance: " + account1.getBalance()); 
    	System.out.println("b2 balance: " + account2.getBalance()); 
        /*
         * 1) Creare due StrictBankAccountImpl assegnati a due AccountHolder a
         * scelta, con ammontare iniziale pari a 10000 e nMaxATMTransactions=10
         * 
         * 2) Effetture un numero di operazioni a piacere per verificare che le
         * eccezioni create vengano lanciate soltanto quando opportuno, cioè in
         * presenza di un id utente errato, oppure al superamento del numero di
         * operazioni ATM gratuite.
         */
    }
}
