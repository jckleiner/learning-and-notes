
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
 * Great article explaining api versioning: https://apisyouwonthate.com/blog/api-versioning-has-no-right-way
 * See http://www.lexicalscope.com/blog/2012/03/12/how-are-rest-apis-versioned/ for how companies version their APIs.

There are several different versioning schemas:
 1. **Using major version numbers**: (`/api/v1/orders` and `/api/v2/orders`). Most widely used. One problem for all of these schemas is, if you make a breaking change for 1 of your 20 actions/resources, you need to introduce a new version for all of them. So `v1` will have 20 operations and lets say 20 models, `v2` will also have 20 but 19 of them would be the same for v1 and v2. Versioning each operation/resource is too complex to do, that's why that is also not a good option.
 2. **Semantic versioning**: `v1_0_0`, `v1_2_0` etc. (`MAJOR.MINOR.PATCH`). Since the clients only care about the major (breaking) changes, the other version numbers are effectively useless (for the client). 
  Using the complete version number in your URLs (or whatever method you use for API versioning) would actually mean that your consumers have to update the URL of your API everytime you make an update / bugfix to your API, and they would keep using an unpatched version until they do so. This doesn't mean that you can't use semantic versioning internally, of course! Just use the first part (major version) as the version number for your API. It just doesn't make sense to include the full version number in your URL / header / other versioning system.
 3. **Sending the date**: On the application side, there will be several different versions and the client will only send a date, any date they want. It will be mapped to a specific version on the backend. If the client wants to use the latest version (without even knowing the version number or url), it can send the current date and it will be mapped to the latest version.

Different ways to send the version:
 1. **Path** (`/api/v1/orders`): Most popular becasue it is easy to implement. 
 2. **Domain/Subdomain** (`apiv1.domain.com/api/orders`, `apiv2.domain.com/api/orders`)
 3. **Query Parameter** (`/api?v=2`, or date: `/api?v=20200630`): It is easy to default to the latest version but more difficult to use for routing requests to the proper API version
 4. **Header** (`Accepts-version: 1.0`)
 5. **Content negotiation** (`curl -H "Accept: application/vnd.xm.device+json; version=1" http://www.example.com/api/products`): This is a more granular approach because it versions resource representations instead of versioning the entire API, but it also comes with a high implementation cost for both clients and developers. More often than not, content negotiation needs to be implemented from scratch as there are few libraries that offer that out of the box. Semmantically using version number in header or content negotiation seems better. But its far much more practical using the URL: less error prone, best debuged, readily seen by developers, easily modifiable in rest testing clients.

### Downsides of REST:
 * Cannot have push notifications because it is based on HTTP which is stateless
 * Uses CRUD which is an antipattern(?) - https://www.youtube.com/watch?v=frUNFrP7C9w . Not the fault of REST but since it is used with REST so commonly
 * Resource (Noun) oriented and not Action (verb) oriented (can be argued). Too much focus on the resources which are managed but software is there to do something, which means being action (verb) oriented is better.