package com.greydev.design_patterns.observer.weatherstation;

import java.util.ArrayList;
import java.util.List;

/* Responsibilities of a Subject:
 *  1. Keep a list of all the Objservers/Subscribers
 *  2. Notify them when stuff happens
 */
public class WeatherStation implements ISubject {

	private List<IObserver> observerList = new ArrayList<>();
	private Integer currentTemp = 22;

	@Override
	public void addObserver(IObserver observer) {
		observerList.add(observer); // add if its not already added
	}

	@Override
	public void removeObserver(IObserver observer) {
		observerList.remove(observer); // remove if found
	}

	@Override
	public void notifyObservers() {
		observerList.forEach(observer -> observer.update());

	}

	public Integer getCurrentTemp() {
		return this.currentTemp;
	}

	public void setCurrentTemp(Integer temp) {
		this.currentTemp = temp;
		notifyObservers();
	}

}
