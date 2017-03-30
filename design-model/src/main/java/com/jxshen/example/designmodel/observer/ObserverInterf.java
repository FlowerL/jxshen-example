package com.jxshen.example.designmodel.observer;

/**
 * Observer model<br>
 * Observer model define a one-to-many dependency relationship<br>
 * among Objects. It let one or more observers to observe a subject<br>
 * When the state of the subject changes, system could notify all observers<br>
 * relied on this subject to update themselves automatically.
 * 
 * reference from : http://blog.csdn.net/xsl1990/article/details/16359289
 * 
 * Observer is a inteface defining how actual observer update itself
 */
public interface ObserverInterf {
    
    void update(AbstractObservable o, Object arg);
}
