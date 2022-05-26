
# Prep Summary


Printing numbers with precision with `System.out.printf` or `String.format`

 - `%.5f`  Print the decimal value with 5 places after the decimal, like 0.333333
 - `%s represents an string`
 - `%d represents an integer`
 - `%f represents an double`
 - You will get a `IllegalFormatConversionException: d != java.lang.Double` exception if the types don't match

Sum an array or list of numbers with Streams

```java
    long sumOfAll = arr.stream()        // Stream<Integer>
            .mapToLong(Long::valueOf)   // LongStream
            .sum();
```

If you have a `Int/Long/DoubleStream`, you can do the following:
 - `.boxed()`
 - `.sum()`
 - `.count()`
 - `.average()`
 - `.min()`
 - `.max()`

Useful `Stream<T>` methods (also contained in `Int/Long/DoubleStream`):
 - `.distinct()`


### Hackerrank
Autocompletion does not show all the available methods. For example, what is now shown but still works:
 - `Collections.sort(arr)`

Read the `Constraints` and understand what input type you will get. If it can be negative values, empty values etc.

Important: be careful about integer overflow. If you use `int` or `Integer`, it might not be enough

While comparing the sum of lists: don't calculate the sum of the list each time a value changes. Instead, calculate the sum once and then get the value that changed and add / subtract that from the sum variable.

```java
Long sumOfArr = arr.stream()
            .reduce(0L, Long::sum, Long::sum);
```

Or similarly, if you want to iterate over a list in an asc / desc order, meaning you want to start from the biggest value and go desc for example. Don't try to find the largest item in the array, remove it and continue to do the same each time. Instead, sort the array once, asc (default): `Collections.sort(arr)` or for desc: `Collections.sort(arr, Collections.reverseOrder())` and then iterate over it with a for loop.


### Spring
 1. What happens when you define a bean in a project and then include that project in your other project? For example a RestTemplate Bean
 2. Can you declare an @Autowired property as "needs to be provided by the project which uses this project"?
 3. How do you define executors? What kind of executors are there? Which one should we use when we want blocking request and which one for parallel requests?

**With Spring Boot 2.1 bean overriding is disabled by default, which is a good thing. But I can define RestTemplate multiple times with the same method name in different projects?**


    Could not autowire. There is more than one bean of 'RestTemplate' type.
    Beans:
        crmRestTemplate   (CrmAdapterConfiguration.java) 
        restTemplateUser (UserManagementApplication.java)

**There are 2 RestTemplate beans in  project A, which will be autowired? One comes from project B, which is a dependency and the other is defined in project A. So when we do a `@Autowired` which one of these will be used???**
 * Looks like a criteria is if the field name matches the method name which returns the bean

 



### RestTemplate

 1. Timeout needs to be configured, the default is too long
 2. When does rest template throw an exception and when should we check the return codes?
 3. Is the POST body converted to JSON automatically?


### Java

**3. Convert list into array, array into list (Java 8)**

4. `c.toString() == "1"` false ???

**5. merge 2 lists, arrays, maps, sets**

**Find the min / max of list**

    Collections.min(collection, [comparator])
    integerStream.reduce((subtotal, el) -> Math.min(subtotal, el))
    intStream.min()

**Stream - Collectors**
 - `Collectors.joining([separator, [prefix, suffix]])`
 - `Collectors.summarizingDouble/Long/Int()`
 - `Collectors.groupBy`

**Concurrent Programming Fundamentals— Thread Safety**

**When to use which collection type?**

**SOLID and clean code**

**Serializable and SerializationUtils::clone**

 * Tuple and Triple in Java? What to use instead? (when no dependencies is allowed)
 * Sorting Maps
 * Sorting a map of lists
 * Sorting tuples, triples
 * List of lists, flatmap
 * BFS
 * str.matches, some common regex


### General
 1. Unicode vs Utf8 vs utf16



### Learned
 * Java natural order
 * Comparator vs Comparable
 * extract a comparator as a method: `private Comparator<Integer> myComparator() { return (a, b) -> {...}}`