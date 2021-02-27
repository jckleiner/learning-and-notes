package com.greydev.design_patterns.observer.weatherstation;

/* Responsibilities of a Subject:
 *  1. Keep a list of all the Objservers/Subscribers
 *  2. Notify them when stuff happens
 */
public interface ISubject {

	public void addObserver(IObserver observer);

	public void removeObserver(IObserver observer);

	public void notifyObservers();

}
