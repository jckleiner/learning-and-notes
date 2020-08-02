package com.greydev.junitmockito;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;


public class LambdaExceptionHandling {

	public static void name() {

		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 0);
//		numbers.forEach(i -> System.out.println(50 / i)); // throws ex

//		numbers.forEach(lambdaWrapper(i -> System.out.println(50 / i)));

		numbers.forEach(i -> {
			try {
				writeToFile(i);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});

		numbers.forEach(handlingConsumerWrapper(i -> writeToFile(i), IOException.class));
	}


	static <T, E extends Exception> Consumer<T> handlingConsumerWrapper(
			ThrowingConsumer<T, E> throwingConsumer, Class<E> clazz) {

		return i -> {
			try {
				throwingConsumer.accept(i);
			} catch (Exception ex) {
				try {
					E exCast = clazz.cast(ex);
					System.err.println("Exception occured : " + exCast.getMessage());
				} catch (ClassCastException ccEx) {
					throw new RuntimeException(ex);
				}
			}
		};
	}


	static <T> Consumer<T> throwingConsumerWrapper(
			ThrowingConsumer<T, Exception> throwingConsumer) {

		return i -> {
			try {
				throwingConsumer.accept(i);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		};
	}


	static void writeToFile(Integer integer) throws IOException {
		// logic to write to file which throws IOException
	}


	// Second approach
	public static Consumer<Integer> lambdaWrapper(Consumer<Integer> lambda) {
		return i -> {
			try {
				lambda.accept(i);
			} catch (ArithmeticException e) {
				System.out.println("Wrapper: Divided by 0");
			}
		};
	}


	static <T, E extends Exception> Consumer<T> consumerWrapper(Consumer<T> consumer, Class<E> clazz) {

		return i -> {
			try {
				consumer.accept(i);
			} catch (Exception ex) {
				try {
					E exCast = clazz.cast(ex);
					System.err.println("Exception occured : " + exCast.getMessage());
				} catch (ClassCastException ccEx) {
					// ignore ccEx
					throw ex;
				}
			}
		};
	}

}
