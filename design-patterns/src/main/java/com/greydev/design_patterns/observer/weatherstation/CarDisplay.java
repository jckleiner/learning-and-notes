package com.greydev.design_patterns.observer.weatherstation;

import java.util.Objects;

/* Responsibilities of an Observer:
 *  1. Register/un-regsiter yourself on a Subject
 *  2. React accordingly when your update() method is called
 */
public class CarDisplay implements IObserver {

	/* Note that this is a concrete type and not an ISubject.
	 * If you will have different subject types for a particular purpose 
	 * and make them interchangable, you can for example create an IWeatherStationSubject.
	 */
	private WeatherStation weatherStation;
	private Integer temp;

	public CarDisplay(WeatherStation weatherStation) {
		Objects.requireNonNull(weatherStation, "weatherStation can't be null");
		this.weatherStation = weatherStation;
		weatherStation.addObserver(this);
		this.temp = weatherStation.getCurrentTemp();
	}

	@Override
	public void update() { // do the required things when you get updated
		this.temp = weatherStation.getCurrentTemp();
	}

	public void showCurrentTemparature() {
		System.out.println("Car display (temp): " + temp);
	}

	// unregister method

}
