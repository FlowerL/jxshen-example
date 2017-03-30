package com.jxshen.example.designmodel.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Observer model<br>
 * Observer model define a one-to-many dependency relationship<br>
 * among Objects. It let one or more observers to observe a subject<br>
 * When the state of the subject changes, system could notify all observers<br>
 * relied on this subject to update themselves automatically.<br>
 * <p>
 * reference from : http://blog.csdn.net/xsl1990/article/details/16359289<br>
 * <p>
 * Observable or subject is a interface defining add/delete observer and <br>
 * the way to notify observers to update themselves
 */
public abstract class AbstractObservable {

    List<ObserverInterf> observers = new ArrayList<ObserverInterf>();
    
    public boolean registObserver(ObserverInterf o) {
        return observers.add(o);
    }
    
    public boolean removeObserver(ObserverInterf o) {
        return observers.remove(observers);
    }
    
    public void notifyAllObservers(Object value) {
        for (Iterator<ObserverInterf> it = observers.iterator();it.hasNext();) {
            ObserverInterf o = it.next();
            o.update(this, value);
        }
    }
}
