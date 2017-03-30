package com.jxshen.example.designmodel.observer;

/**
 * Observer model<br>
 * Observer model define a one-to-many dependency relationship<br>
 * among Objects. It let one or more observers to observe a subject<br>
 * When the state of the subject changes, system could notify all observers<br>
 * relied on this subject to update themselves automatically.<br>
 * <p>
 * reference from : http://blog.csdn.net/xsl1990/article/details/16359289<br>
 * <p>
 */
public class PriceObserver implements ObserverInterf {

    @Override
    public void update(AbstractObservable o, Object arg) {

        if (arg instanceof Double) {
            System.out.println("价格观察者:" + o + "物品价格已经改变为: " + arg);
        }
    }

}
