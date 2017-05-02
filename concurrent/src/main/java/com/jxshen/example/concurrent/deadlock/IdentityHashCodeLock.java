package com.jxshen.example.concurrent.deadlock;

/**
 * reference from: java concurrency in practice<br>
 * user System.identityHashcode to reduce the probility of deadlock
 */
public class IdentityHashCodeLock {

    private static final Object tieLock = new Object();
    
    private class Account {
        public void debit(DollarAmount amount) {}
        public void credit(DollarAmount amount) {}
        public DollarAmount getBalance() {return null;}
    }
    
    private class DollarAmount {
        int compareTo(DollarAmount amount) {return 0;}
    }
    
    private class InsufficientFundsException extends Throwable {
        private static final long serialVersionUID = 1L;}
    
    public void transferMoney(final Account fromAcct, final Account toAcct, final DollarAmount amount)
            throws InsufficientFundsException{
        class Helper {
            public void transfer() throws InsufficientFundsException {
                if (fromAcct.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                }
                else {
                    fromAcct.debit(amount);
                    toAcct.credit(amount);
                }
            }
        }
        
        int fromHash = System.identityHashCode(fromAcct);
        int toHash = System.identityHashCode(toAcct);
        
        if (fromHash < toHash) {
            synchronized (fromAcct) {
                synchronized (toAcct) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (toAcct) {
                synchronized (fromAcct) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (fromAcct) {
                    synchronized (toAcct) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }
}
