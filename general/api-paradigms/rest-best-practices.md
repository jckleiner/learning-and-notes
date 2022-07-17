
Sources:
 * https://levelup.gitconnected.com/best-practices-for-building-developer-friendly-rest-apis-e6a419fcbd38
 * https://docs.microsoft.com/en-us/azure/architecture/best-practices/api-design



#### Avoid creating APIs that simply mirror the internal structure of a database
A resource doesn't have to be based on a single physical data item. For example, an order resource might be implemented internally as *several tables in a relational database*, but presented to the client as a single entity. Avoid creating APIs that simply mirror the internal structure of a database. The purpose of REST is to model entities and the operations that an application can perform on those entities. A client should not be exposed to the internal implementation.

Avoid introducing dependencies between the web API and the underlying data sources. For example, if your data is stored in a relational database, the web API doesn't need to expose each table as a collection of resources. In fact, that's probably a poor design. Instead, think of the web API as an abstraction of the database. If necessary, introduce a **mapping layer** between the database and the web API. That way, client applications are isolated from changes to the underlying database scheme.

#### Consider the relationships between different types of resources and how you might expose these associations
For example, the `/customers/5/orders` might represent all of the orders for customer 5. You could also go in the other direction, and represent the association from an order back to a customer with a URI such as `/orders/99/customer`. However, extending this model too far **can become cumbersome to implement**. A better solution is to provide **navigable links** to associated resources in the body of the HTTP response message. This mechanism is described in more detail in the section Use HATEOAS to enable navigation to related resources.

TODO include the HATEOAS part
 * The Microsoft guide says there is no HATEOAS standard. Well, there are a couple. I saw a talk which introduces such a standard but I need to find it.
 * When is HATEOAS needed? Only for really large APIs or also mid/small API's?
 * Why is nobody using HATEOAS? Is it to complex to implement and maintain?
 * Once you have HATEOAS, you also need to care about not changing those fields, right?
 * What about openapi generated models with HATEOAS?

#### Avoid requiring resource URIs more complex than `collection/item/collection`
In more complex systems, it can be tempting to provide URIs that enable a client to navigate through several levels of relationships, such as `/customers/1/orders/99/products`. However, this level of complexity can be difficult to maintain and is inflexible if the relationships between resources change in the future. Instead, try to keep URIs relatively simple. Once an application has a reference to a resource, it should be possible to use this reference to find items related to that resource. The preceding query can be replaced with the URI `/customers/1/orders` to find all the orders for customer 1, and then `/orders/99/products` to find the products in this order.

#### Try to find the right balance between "chatty" APIs and APIs with a huge response body
Another factor is that all web requests impose a load on the web server. The more requests, the bigger the load. Therefore, try to avoid "chatty" web APIs that expose a large number of small resources. Such an API may require a client application to send multiple requests to find all of the data that it requires. Instead, you might want to denormalize the data and combine related information into bigger resources that can be retrieved with a single request. However, you need to balance this approach against the overhead of fetching data that the client doesn't need. Retrieving large objects can increase the latency of a request and incur additional bandwidth costs. For more information about these performance antipatterns, see **Chatty I/O** and **Extraneous Fetching**.

TODO: https://docs.microsoft.com/en-us/azure/architecture/antipatterns/chatty-io/
TODO: https://docs.microsoft.com/en-us/azure/architecture/antipatterns/extraneous-fetching/

#### Non-resource scenarios 
Finally, it might not be possible to map every operation implemented by a web API to a specific resource. You can handle such non-resource scenarios through HTTP requests that invoke a function and return the results as an HTTP response message. For example, a web API that implements simple calculator operations such as add and subtract could provide URIs that expose these operations as pseudo resources and use the query string to specify the parameters required. For example, a `GET` request to the URI `/add?operand1=99&operand2=1` would return a response message with the body containing the value `100`. However, only use these forms of URIs sparingly.


### Define API operations in terms of HTTP methods

The HTTP protocol defines a number of methods that assign semantic meaning to a request. The common HTTP methods used by most RESTful web APIs are:

 * `GET` retrieves a representation of the resource at the specified URI. The body of the response message contains the details of the requested resource.
 * `POST` **creates a new resource** at the specified URI. The body of the request message provides the details of the new resource. Note that POST can also be used to trigger operations that don't actually create resources.
 * `PUT` **either creates or replaces the resource** at the specified URI. The body of the request message specifies the resource to be created or updated.
 * `PATCH` performs a **partial update of a resource**. The request body specifies the set of changes to apply to the resource.
 * `DELETE` removes the resource at the specified URI.

The effect of a specific request should depend on whether the resource is a collection or an individual item. The following table summarizes the common conventions adopted by most RESTful implementations using the e-commerce example. Not all of these requests might be implemented—it depends on the specific scenario.

| Resource | POST | GET  | PUT | DELETE|
|----------|-------|-----|-----|-------|
|/customers| Create a new customer| Retrieve all customers | Bulk update of customers | Remove all customers |
|/customers/1 | Error | Retrieve the details for customer 1 | Update the details of customer 1 if it exists | Remove customer 1   |
| /customers/1/orders | Create a new order for customer 1 | Retrieve all orders for customer 1  | Bulk update of orders for customer 1 | Remove all orders for customer 1 |

The differences between POST, PUT, and PATCH can be confusing.

 * A `POST` request **creates a resource**. The server assigns a URI for the new resource, and returns that URI to the client. In the REST model, you frequently apply POST requests to collections. The new resource is added to the collection. A POST request can also be used to submit data for processing to an existing resource, without any new resource being created.

 * A `PUT` request **creates a resource or updates an existing resource**. The client specifies the URI for the resource. The request body contains a **complete representation of the resource**. <u>If a resource with this URI already exists, it is replaced. Otherwise a new resource is created, if the server supports doing so</u>. PUT requests are most frequently applied to resources that are individual items, such as a specific customer, rather than collections. A server might support updates but not creation via PUT. Whether to support creation via PUT depends on whether the client can meaningfully assign a URI to a resource before it exists. If not, then use POST to create resources and PUT or PATCH to update.

 * A `PATCH` request performs a **partial update** to an existing resource. The client specifies the URI for the resource. The request body specifies a set of changes to apply to the resource. This can be more efficient than using PUT, because the client only sends the changes, not the entire representation of the resource. Technically PATCH can also create a new resource (by specifying a set of updates to a "null" resource), if the server supports this.

PUT (and DELETE) requests must be **idempotent**. If a client submits the same PUT request multiple times, the results should always be the same (the same resource will be modified with the same values). POST and PATCH requests are not guaranteed to be idempotent.










### Versioning

### Versioning Strategies of Companies:
 * Stripe: https://stripe.com/blog/api-versioning


### Security
 * TODO Good talk about Endpoint/REST security: https://www.youtube.com/watch?v=IL2PlsbvUDs