package it.unibo.oop.lab.exception2;

/**
 * Class modeling a BankAccount with strict policies: getting money is allowed
 * only with enough founds, and there are also a limited number of free ATM
 * transaction (this number is provided as a input in the constructor).
 * 
 */
public class StrictBankAccount implements BankAccount {

    private final int usrID;
    private double balance;
    private int nTransactions;
    private final int nMaxATMTransactions;
    private static final double ATM_TRANSACTION_FEE = 1;
    private static final double MANAGEMENT_FEE = 5;
    private static final double TRANSACTION_FEE = 0.1;

    /**
     * 
     * @param usrID
     *            user id
     * @param balance
     *            initial balance
     * @param nMaxATMTransactions
     *            max no of ATM transactions allowed
     */
    public StrictBankAccount(final int usrID, final double balance, final int nMaxATMTransactions) {
        this.usrID = usrID;
        this.balance = balance;
        this.nMaxATMTransactions = nMaxATMTransactions;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     */
    
    public void deposit(final int usrID, final double amount) throws WrongAccountHolderException{
        if (checkUser(usrID)) {
            this.balance += amount;
            incTransactions();
        } else {
        	throw new WrongAccountHolderException();
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void withdraw(final int usrID, final double amount) throws NotEnoughFoundsException, WrongAccountHolderException{
    	if (checkUser(usrID)) {
            if(isWithdrawAllowed(amount)) {
            	this.balance -= amount;
            	incTransactions();
            } else {
            	throw new NotEnoughFoundsException();
            }
        }else {
        	throw new WrongAccountHolderException();
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    private boolean TransactionAvailable() {
		return nTransactions < nMaxATMTransactions;
    	
    }
    
    public void depositFromATM(final int usrID, final double amount) throws TransactionsOverQuotaException{
        if (TransactionAvailable()) {
            this.deposit(usrID, amount - StrictBankAccount.ATM_TRANSACTION_FEE);
            nTransactions++;
        } else {
        	throw new TransactionsOverQuotaException();
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public void withdrawFromATM(final int usrID, final double amount) throws TransactionsOverQuotaException {
        if (TransactionAvailable()) {
            this.withdraw(usrID, amount + StrictBankAccount.ATM_TRANSACTION_FEE);
        }else {
        	throw new TransactionsOverQuotaException();
        }
    }

    /**
     * 
     * {@inheritDoc}
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public int getNTransactions() {
        return nTransactions;
    }

    /**
     * 
     * @param usrID
     *            id of the user related to these fees
     */
    public void computeManagementFees(final int usrID) throws NotEnoughFoundsException, WrongAccountHolderException{
        final double feeAmount = MANAGEMENT_FEE + (nTransactions * StrictBankAccount.TRANSACTION_FEE);
        if (checkUser(usrID)) {
        	if (isWithdrawAllowed(feeAmount)) {
                balance -= MANAGEMENT_FEE + nTransactions * StrictBankAccount.TRANSACTION_FEE;
                nTransactions = 0;
        	} else {
        		throw new NotEnoughFoundsException();
        	}
        } else {
        	throw new WrongAccountHolderException();
        }
    }

    private boolean checkUser(final int id) {
        return this.usrID == id;
    }

    private boolean isWithdrawAllowed(final double amount) {
        return balance > amount;
    }

    private void incTransactions() {
        nTransactions++;
    }
}
