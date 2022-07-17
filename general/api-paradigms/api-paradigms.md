
# API Paradigms

Resources:
 * The nativeWeb - API-Routen strukturieren: https://www.youtube.com/watch?v=tT-Bed5OFF0
 * The nativeWeb - APIs versionieren: https://www.youtube.com/watch?v=CHP0kPAISNQ
 * https://www.xmatters.com/blog/blog-four-rest-api-versioning-strategies/
 * https://stackoverflow.com/questions/27901549/semantic-versioning-of-rest-apis

There are 2 types of API paradigms:
 1. **Request-Response API's**
    - REST (Representational State Transfer)
    - RPC (Remote Procedure Call)
    - GraphQL
    - SOAP
 2. **Event-Driven API's**
    - WebHooks
    - WebSockets
    - HTTP Streaming

HTTP is based on TCP/IP, except HTTP 3 is based on the quick protocol which is based on UDP

## REST

 * Based on HTTP, maps HTTP verbs to CRUD operations
 * REST is stateless, because HTTP is stateless
 * Resource based URLs

### CRUD and HTTP Verbs


### Resource Based
In REST, the main focus is on the resources which is managed.
The resources are managed with the HTTP verbs, which means the actions/verbs are limited to those. 
A `DELETE /orders/<id>` is too technical and the reason/intention why this order is deleted is lost. Some of the reasons why an order is deleted might be:
 1. The customer canceled the oder
 2. The seller canceled/rejected the order.

But often times it is required to undo some actions. Since in databases there are no "UNDELETE" operation, sometimes a `isDeleted` flag is used which is changed with a delete or undo operation. But now instead of a `DELETE` request, you are sending an `UPDATE` request to delete an order (change the flag), which can get confusing.
-> Routes should be stuctured "fachlich" and not "technisch" to preserve the intention.
-> Should not restrict yourself to CRUD verbs, instead use a rich set of verbs (see Domain-Driven-Design, CQRS)

If we compare it with `POST /cancelOrder`, we see that the intention is clearer, which also separates the intention from the implemention becuase it also uses a deletion under the hood but that will happen at the end.

### Versioning
APIs are public interfaces and should generally not do any breaking changes without changing the version.
 * Great articles:
   * https://apisyouwonthate.com/blog/api-versioning-has-no-right-way
   * https://productionreadygraphql.com/blog/2019-11-06-how-should-we-version-graphql-apis
   * https://blog.restcase.com/restful-api-versioning-insights/
   * How to version with openapi spec? https://www.bennorthrop.com/Essays/2019/api-versioning-with-openapi-and-api-first.php
 * See http://www.lexicalscope.com/blog/2012/03/12/how-are-rest-apis-versioned/ for how companies version their APIs.

What constitutes a *breaking change*? In general, the term means a change to the API that can cause existing, previously valid API calls to fail

**Resource-level** versioning vs **API-level** versioning:
 1. Upgrading only the version for the resource which is changed. `/v1/ResourceA`, `lv2/ResourceB` This feels reaaaally wrong though.
 2. Upgrading the whole API when a breaking change must be done. Means lots of duplication.

How should one version their API if they use an openapi spec and an API first approach?
There are 2 options but in both cases it means loots of duplication. If only one of the 20 Resources have a breaking change, you still need to compy the 19 unchanged resources/actions.
 1. Putting all versions of an API into a single api spec. One giant `api.yaml` file.
 2. Create a new API spec for each version, `api-v1.yaml` `api-v2.yaml`. 

There are several different versioning schemas:
 1. **Using major version numbers**: (`/api/v1/orders` and `/api/v2/orders`). Most widely used. One problem for all of these schemas is, if you make a breaking change for 1 of your 20 actions/resources, you need to introduce a new version for all of them. So `v1` will have 20 operations and lets say 20 models, `v2` will also have 20 actions but 19 of them would be the same for v1 and v2. Versioning each operation/resource is too complex to do, that's why that is also not a good option.
 2. **Semantic versioning**: `v1_0_0`, `v1_2_0` etc. (`MAJOR.MINOR.PATCH`). Since the clients only care about the major (breaking) changes, the other version numbers are effectively useless (for the client). 
  Using the complete version number in your URLs (or whatever method you use for API versioning) would actually mean that your consumers have to update the URL of your API everytime you make an update / bugfix to your API, and they would keep using an unpatched version until they do so. This doesn't mean that you can't use semantic versioning internally, of course! Just use the first part (major version) as the version number for your API. It just doesn't make sense to include the full version number in your URL / header / other versioning system.
 3. **Sending the date**: On the application side, there will be several different versions and the client will only send a date, any date they want. It will be mapped to a specific version on the backend. If the client wants to use the latest version (without even knowing the version number or url), it can send the current date and it will be mapped to the latest version.

Different ways to send the version:
 1. **Path** (`/api/v1/orders`): Most popular becasue it is easy to implement. 
 2. **Domain/Subdomain** (`apiv1.domain.com/api/orders`, `apiv2.domain.com/api/orders`)
 3. **Query Parameter** (`/api?v=2`, or date: `/api?v=20200630`): It is easy to default to the latest version but more difficult to use for routing requests to the proper API version
 4. **Header** (`Accepts-version: 1.0`)
 5. **Content negotiation** (`curl -H "Accept: application/vnd.xm.device+json; version=1" http://www.example.com/api/products`): This is a more granular approach because it versions resource representations instead of versioning the entire API, but it also comes with a high implementation cost for both clients and developers. More often than not, content negotiation needs to be implemented from scratch as there are few libraries that offer that out of the box. Semmantically using version number in header or content negotiation seems better. But its far much more practical using the URL: less error prone, best debuged, readily seen by developers, easily modifiable in rest testing clients.

It is said that "with GraphQL you don't have to version your APIs" or "GraphQL handles API versioning" or similar statements. This is not true at all. 
GraphQL APIs can also be versioned and will also have unavoidable breaking changes.

GraphQL helps with continuous evolution in a few ways that make it quite a bit easier:
 1. It has first-class deprecation support on fields, and most tooling already knows how to use it.
 2. Additive changes come with no overhead on existing and new clients.
 3. Usage tracking can be done down to single fields.

Another thing to keep in mind is that if you opt for a continuous evolution approach first and then decide you absolutely need versioning, that’s possible. The opposite is much harder.

In summary, currently **there is no best solution** for *restful* or *graphQL* API versioning. Another idea is that you should not version your API at all
and try your best to obey the contract. This heavily depends on the situation, how big the project is, how many clients your API has, if you own/control the clients, even if not, could you communicate the changes and expect that they fix their client side etc.

### Downsides of REST:
 * Cannot have push notifications because it is based on HTTP which is stateless
 * Uses CRUD which is an antipattern(?) - https://www.youtube.com/watch?v=frUNFrP7C9w . Not the fault of REST but since it is used with REST so commonly
 * Resource (Noun) oriented and not Action (verb) oriented (can be argued). Too much focus on the resources which are managed but software is there to do something, which means being action (verb) oriented is better.

### TODO
 * auth for api's: https://www.youtube.com/watch?v=501dpx2IjGY
 * event driven: https://www.youtube.com/watch?v=6RvlKYgRFYQ
 * Rest of GraphQL (cache? can be solved with redis?) https://www.youtube.com/watch?v=o5orx68OJMg




