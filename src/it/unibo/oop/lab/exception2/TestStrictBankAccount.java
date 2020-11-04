package it.unibo.oop.lab.exception2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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
    	
    	final AccountHolder h1 = new AccountHolder("Mario", "Rossi", 01);
    	final AccountHolder h2 = new AccountHolder("Giacomo", "Totaro", 02);
    	final StrictBankAccount account1 = new StrictBankAccount(h1.getUserID(), AMOUNT_INITIAL, TRANSACTION_INITIAL);
    	final StrictBankAccount account2 = new StrictBankAccount(h2.getUserID(), AMOUNT_INITIAL, TRANSACTION_INITIAL);
    	
    	try {
    		account1.deposit(4, 100);
    		fail();
    	}catch (WrongAccountHolderException e) {
    		assertNotNull(e);
    	}
    	
    	for (int i = 0; i < 10; i++) {
    		try {
    			account2.depositFromATM(h2.getUserID(), 1);
    		}catch (TransactionsOverQuotaException | WrongAccountHolderException e) {
    			fail("Non superato ancora numero max di transazioni");
    		}
    	}
    	
    	try {
    		account2.depositFromATM(h2.getUserID(), 1);
    		fail("Dovrebbe sollevare eccezione");
    	} catch (TransactionsOverQuotaException | WrongAccountHolderException e) {
    		assertNotNull(e);
    	}
    	 
    	try {
    		account1.withdraw(h1.getUserID(), MOLTI_SOLDI);
    	} catch (WrongAccountHolderException e) {
    		fail();
    	} catch (NotEnoughFoundsException e) {
    		assertNotNull(e);
    	}
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
