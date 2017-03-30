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
public class NameObserver implements ObserverInterf {

    @Override
    public void update(AbstractObservable o, Object arg) {
        if (arg instanceof String) {
            String name = (String) arg;
            System.out.println("名称观察者:" + o + "物品名称已经改变为: " + name);
        }
    }

}
