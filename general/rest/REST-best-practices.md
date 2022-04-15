

### Good Resources

* What are best practices for REST nested resources?: https://stackoverflow.com/questions/20951419/what-are-best-practices-for-rest-nested-resources
* RESTful web API design: https://docs.microsoft.com/en-us/azure/architecture/best-practices/api-design


## Questions

### Authentication for REST endpoints?

### In nested resources, when a sub-resource creation requests comes, should the parent be created automatically? Or better to send 2 requests?
It is fine to create multiple resources, event parent resources with one request.

### REST API - DTOs (Data Transfer Objects) or not?
* https://stackoverflow.com/questions/36174516/rest-api-dtos-or-not

Java object mapping tools: https://stackoverflow.com/questions/1432764/any-tool-for-java-object-to-object-mapping/1432956#1432956
MapStruct: https://mapstruct.org/

### Infinite recursion problem with Jackson and JPA
When trying to convert a JPA object that has a bi-directional association into JSON, you will get:

    com.fasterxml.jackson.databind.JsonMappingException: Infinite recursion (StackOverflowError)

There are several ways to solve this:
 1. `@JsonManagedReference` and `@JsonBackReference`: You put `@JsonManagedReference` on the "one-side" and the other on the "many-side". This way in the many side, the one sides reference won't be serialized
 2. `@JsonIgnoreProperties` on class or `@JsonIgnore` on field: You can tell Jackson to ignore the "back reference" field by `@JsonIgnoreProperties(value = "user")`.
 3. More options: https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion

Sources:
 * https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue