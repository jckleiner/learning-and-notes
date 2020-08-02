package com.greydev.design_patterns.observer.weatherstation;

/* Responsibilities of an Observer:
 *  1. Register/un-regsiter yourself on a Subject
 *  2. React accordingly when your update() method is called
 */
public interface IObserver {

	public void update();

}
