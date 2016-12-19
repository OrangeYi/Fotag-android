package com.example.mvc;

import android.util.Log;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
	private int counter;

	Model() {
		counter = 0;
	}

	// Data methods
	public int getCounterValue() {
		return counter;
	}

	public void setCounterValue(int i) {
		counter = i;
		Log.d("MVC", "Model: set counter to " + counter);
		setChanged();
		notifyObservers();
	}

	public void incrementCounter() {
		counter++;
		Log.d("MVC", "Model: increment counter to " + counter);
		setChanged();
		notifyObservers();
	}

	// Observer methods
	@Override
	public void addObserver(Observer observer) {
		Log.d("MVC", "Model: Observer added");
		super.addObserver(observer);
	}

	@Override
	public synchronized void deleteObservers() {
		super.deleteObservers();
	}

	@Override
	public void notifyObservers() {
		Log.d("MVC", "Model: Observers notified");
		super.notifyObservers();
	}

	@Override
	protected void setChanged() {
		super.setChanged();
	}

	@Override
	protected void clearChanged() {
		super.clearChanged();
	}
}