
# Prep Summary


Printing numbers with precision with `System.out.printf` or `String.format`

 - `%.5f`  Print the decimal value with 5 places after the decimal, like 0.333333
 - `%6s`  Add 6 spaces before the string value
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

`str.chars()` returns an `IntStream` and the numbers can be mapped to characters with `.mapToObject(c -> (char) c)`

If you have a `Int/Long/DoubleStream`, you can do the following:
 - `.boxed()` -> get `Stream<Integer/Double>`
 - `.sum()`
 - `.count()`
 - `.average()`
 - `.min()`
 - `.max()`
 - `toArray()` -> to get `int[]` or `double[]`

Useful `Stream<T>` methods (also contained in `Int/Long/DoubleStream`):
 - `.distinct()`
 - `.toArray(Integer[]::new)`


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
 * join multiple lists
 * iterate map
 * BFS
 * str.matches, some common regex
 * Difference between `array`s and `ArrayList`s?
 * Stack vs Heap (There was a nice explanation video for Java)
 * TDD (https://martinfowler.com/articles/workflowsOfRefactoring/#tdd)
 * 12 PM vs 12 AM -> converting am to normal time and vice versa
 * Trie (auto complete)
 * Traverse a tree
 * Convert `int[]` to `List<Integer>` and vice versa
 * How does Java save Objects as Hash keys? - https://stackoverflow.com/questions/45831828/can-we-use-object-as-a-key-in-hashmap-in-java

### General
 1. Unicode vs Utf8 vs utf16



### Learned
 * Java natural order
 * Java Set has O(1) insert and lookup -> https://stackoverflow.com/questions/559839/big-o-summary-for-java-collections-framework-implementations 
 * Comparator vs Comparable
 * extract a comparator as a method: `private Comparator<Integer> myComparator() { return (a, b) -> {...}}`
 * `Arrays.asList()` returns a `fixed-size` list. `add()` or `remove()` will throw an exception.
   Wrap it like this to get a mutalbe list: `new ArrayList<>(Arrays.asList(...))`
 * You want to have a default fallback for an expression which can be `null`, use `Optional.of(...).orElse(0)`

 * `Arrays.stream(int[])`       gives you   `IntStream`
 * `Arrays.stream(Integer[])`   gives you   `Stream<Integer>`
 * `int[]`          -> `Integer[]`:     `Arrays.stream(int[]).boxed().toArray(Integer[]::new);`
 * `Integer[]`      -> `int[]`:         `Arrays.stream(Integer[]).mapToInt(i -> i).toArray()`
 * `int[]`          -> `List<Integer>`: `Arrays.stream(int[]).boxed().collect(Collectors.toList())`
 * `List<Integer>`  -> `int[]`:         `arr.stream().mapToInt(i -> i).toArray()`
 * `List<Integer>`  -> `Integer[]`:     `list.stream().toArray(String[]::new)`

 * String           -> `char[]`:            `str.toCharArray()`
 * `char[]`         -> `List<Characters>`:  `str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());`
 * `char[]`         -> `String`:            `new String(char[])`
 * `Character[]`    -> `String`:            `Arrays.stream(Character[]).map(String::valueOf).collect(Collectors.joining());`

 * Array -> String (a primite or non-primite array does NOT have a `toString()` method, so it prints the memory address): 
   `Arrays.toString(int[])` and `Arrays.deepToString(int[])`, for arrays, inside arrays
   To print an `Integer[]`, you first need to convert it to an `int[]` like this: `Arrays.stream(Integer[]).mapToInt(i -> i).toArray();`
   and then you can print it `Arrays.toString(int[])`.
 * Subarray: `Arrays.copyOfRange(arr, from, toExclusive)`
 * Multi-dimentional array inline: `String[][] input = new String[][] {{"I", "J"}, {"K", "I"}};`

 * `HashMap<Long, Long> memo`, imagine you put only `Long/long` keys. `memo.get(int)` will be `null` event if the given integer exists in the map


### Todo - move to docs
 * The `for` loops middle condition is evaluated before each iteration.
    
        for(int index = 0; index < list.size(); index++) {
            /* ...body... */
        }
    is equivalent to:

        int index = 0;
        while (index < list.size()) {
            /* ...body... */
            index++;
        }
    Modifying a list (structually) while iterating over it is allowed, it will compile and run 
    but you will probably break the correct sequence and miss some of the elements.

    > "A structural modification is any operation that adds or deletes one or more elements, 
    > or explicitly resizes the backing array; merely setting the value of an element is not a structural modification." 
    
    ```java
    List<Integer> list = new ArrayList<>(Arrays.asList(2, 8, 5, 4, 1));

    for (Integer i : list) {
        if (i > someValue) {
            list.remove(i); // throws concurrentModificationException
            list.add(99);   // throws concurrentModificationException
        }
    }

    // runs without an error but might skip some elements because the iteration order might change
    for (int i = 0; i < list.size(); i++) {
        if (i > someValue) {
            list.remove(i);     // works
            list.add(99);       // works
        }
    }

    Iterator<Integer> iter = list.iterator();
    while(iter.hasNext()) {
        Integer i = iter.next();
        if (i > 0) {
            System.out.println("i: " + i);
            iter.remove();      // works correctly
            list.add(12);       // throws concurrentModificationException
        }
    }
    assertThat(list).isEmpty();
    ```

    **Alternatives - Removing elements while iterating**
    1. If we want to keep our foreach loop and not use an iterator:

    ```java
    List<Integer> integers = newArrayList(1, 2, 3);
    List<Integer> toRemove = newArrayList();

    for (Integer integer : integers) {
        if(integer == 2) {
            toRemove.add(integer);
        }
    }
    integers.removeAll(toRemove);
    assertThat(integers).containsExactly(1, 3);
    ```
    2. `collection.removeIf(predicate)` - Java 8

    ```java
    List<Integer> integers = newArrayList(1, 2, 3);
    integers.removeIf(i -> i == 2);
    assertThat(integers).containsExactly(1, 3);
    ```

    3. Stream

    ```java
    Collection<Integer> integers = newArrayList(1, 2, 3);

    List<String> collected = integers
        .stream()
        .filter(i -> i != 2)
        .collect(Collectors.toList());

    assertThat(collected).containsExactly(1, 3);
    ```