package com.example.algorithms.util;

import lombok.ToString;


@ToString
public class Tuple<T, A> {
	private T first;
	private A second;


	public Tuple() {
	}


	public Tuple(T first, A second) {
		this.first = first;
		this.second = second;
	}


	public T getFirst() {
		return this.first;
	}


	public A getSecond() {
		return this.second;
	}


	public void setFirst(T first) {
		this.first = first;
	}


	public void setSecond(A second) {
		this.second = second;
	}
}
