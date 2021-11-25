package com.greydev.springbootconfiguration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {

	@Value("${app.fullName: default value}")
	private String fullName;

	@Value("${app.list}")
	private List<String> list;

	@Value("${app.array}")
	private String[] array;

	@Value("#{${app.mapString}}")
	private Map<String, String> mapString;

	@Value("#{${app.mapInt}}")
	private Map<String, Integer> mapInt;

	@Value("#{${app.mapDouble}}")
	private Map<String, Double> mapDouble;

	@Value("#{${app.mapMap}}")
	private Map<String, Map<String, String>> mapMap;


	@GetMapping("/greeting")
	public String greeting() {

		return "<p>Hello " + fullName
				+ "<br> list: " + list + ", size: " + list.size()
				+ "<br> array: " + array + ", size: " + array.length
				+ "<br> mapString: " + mapString + ", size: " + mapString.size()
				+ "<br> mapInt: " + mapInt + ", size: " + mapInt.size()
				+ "<br> mapDouble: " + mapDouble + ", size: " + mapDouble.size()
				+ "<br> mapMap: " + mapMap + ", size: " + mapMap.size()
				+ "</p>";
	}
}

