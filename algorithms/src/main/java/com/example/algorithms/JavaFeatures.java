package com.example.algorithms;

import java.util.Optional;


public class JavaFeatures {

	/*
		Switch Pattern Matching:
			1. Dominance: Does the first one win or do the other matching expressions also get executed?
				-
				- 'switch' has both a total pattern and a default label, when all cases are covered and you still have a default case
				- 'switch' statement does not cover all possible input values

			2. Do we always have to give the type (case: Test t & ...), can't we directly write the boolean expression?
			3. Optionals?


		Records:
			1. When to use and when not to use records?


	 */


	public static void main(String[] args) {
		switchWithRecord(5);
		switchWithRecord(10);
		switchWithRecord(15);

		switchWithOptional(Optional.of(4));
		switchWithOptional(Optional.empty());
		switchWithOptional(null);
	}


	public static void switchWithRecord(Integer num) {
		Test test = new Test(num);

		switch (test) {
		case null -> System.out.println("null");
		case Test t && t.a < 10 -> System.out.println("< 10");
		case Test t && t.a() > 10 -> System.out.println("> 10");
		default -> System.out.println("== 10");
		}

		var a = switch (test) {
			case null -> 2;
			case Test t && t.a < 10 -> t.a * 2;
			case Test t && t.a() > 10 -> t.a * 2;
			default -> 0;
		};

		// var b = switch (test) {
		// 	case null -> 2;
		// 	case test.a < 10 -> test.a * 2;
		// 	case test.a() > 10 -> test.a * 2;
		// 	default -> 0;
		// };
	}


	public static void switchWithOptional(Optional<Integer> optionalInteger) {
		switch (optionalInteger) {
		case null -> System.out.println("null optional");
		case Optional<Integer> i && i.isEmpty() -> System.out.println("empty");
		case Optional<Integer> i -> System.out.println("present: " + i);
		}

	}


	// records will throw NPE if null is passed? Event (Integer i)?
	record Test(int a) {
	}
}
