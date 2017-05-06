package com.jxshen.example.concurrent.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 *  reference from: java concurrency in practice<br>
 *  use lock.tryLock to avoid deadLock
 */
public class TryLockTransferMoney {
    
    public static class InsufficientFundsException extends Exception {
        private static final long serialVersionUID = 1L;
    }

    private static final Random rnd = new Random();

    private static class Account {
        public Lock lock;
        public DollarAmount getBalance() {return null;}
        public void debit() {}
        public void crebit() {}
    }
    
    private static class DollarAmount {
        public int compareTo(DollarAmount amount) {return 0;}
    }

    public static boolean transferMoney(
            Account fromAcct, 
            Account toAcct, 
            DollarAmount amount,
            long timeout,
            TimeUnit unit) throws InterruptedException, InsufficientFundsException {
        
        long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
        long randMod = getRandomDelayModulusNanos(timeout, unit);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);
        
        while (true) {
            if (fromAcct.lock.tryLock()) {
                try {
                    if (toAcct.lock.tryLock()) {
                        try {
                            if (fromAcct.getBalance().compareTo(amount) < 0) {
                                throw new InsufficientFundsException();
                            } else {
                                fromAcct.debit();
                                toAcct.crebit();
                                return true;
                            }
                        } finally {
                            toAcct.lock.unlock();
                        }
                    }
                } finally {
                    fromAcct.lock.unlock();
                }
            }
            if (System.nanoTime() > stopTime) {
                return false;
            }
            TimeUnit.NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
        }
    }

    private static long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
        return 0;
    }

    private static long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
        return 0;
    }
}
