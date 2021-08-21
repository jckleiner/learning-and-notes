### 														JAX-RS and JERSEY	

* Downloaded Eclipse Java EE		( json Remote procedure call, soap .....)
* Created a new Maven project (messenger)         (deploy jersey on tomcat)
* Add arch types (like a starting template for a project)
* downloaded Tomcat 8.0 and started it
* ran our project on the server , and it worked !

-------------

http://localhost:8080/messenger/webapi/myresource 

Our application is already configured to accept REST API request at this `/webapi/` URL. We copied this project from a template so it was already configured like this. Clients request has to be something like /webapi/xxx. 

myresource is actually a resource. When we make a GET request to this resource , we get some text back. 

#### Servlets  

All the web applications are build by using the standard Java servlet technology (?) , **servlets** or **filters**. These are the only 2 ways you can handle requests in your web application. So our project must also have some kind of a servlet thats handling these requests. To find out what servlets are configured we need to open **web.xml**.

We can read in our web.xml file what servlet is used :

      <servlet> 
      	<servlet-name>Jersey Web Application</servlet-name>
        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
        <init-param>
            <param-name>jersey.config.server.provider.packages</param-name>
            <param-value>com.greydev.messenger</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
            <servlet-name>Jersey Web Application</servlet-name>
            <url-pattern>/webapi/*</url-pattern>
        </servlet-mapping>
<u>Servlet class is</u> : *`org.glassfish.jersey.servlet.ServletContainer`*. This is a class which comes bundled with the Jersey Jar. 

<u>This servlet is mapped to this url pattern</u> : `/webapi/*` . So ANY request that comes to this application that has /webapi in the front, our servlet will handle

* The concepts are standard in any JAX-RS application, more or less the same till http://localhost:8080/messenger/webapi/ this part. 

* What the Jersey Servlet does is basically look at our code/classes to find out what from this point on /webapi/{since here}

* We will write the rest of that URL, creating our own resources

  ​

------------

<br>

<br>

<br>

<br>

<br>

#### Creating a resource 

​	The question is "what happens when the request comes in ?". Jersey has identified the URI <u>up to the resource name</u>. What does it do after that, how does he know what to do with that request ? Jersey/the servlet doesn't really know what to do actually. Jersey expects there to be some code that we have written, so it hands over the responsibility of handling that request to us/our code.

To handle a resource , what we write are classes called "Resources". When we expand our src file, we see a class named `MyResource.java`. The standard class came bundled with the project. When we called the request "myresource" we got text back. So that came also with the project.

<u>Now we will create our own resource</u>: "messages" was one of the first resources that we planned.

Lets create a simple resource handler :

1. **Create** **a new class** : 

   ​	We will call that class `MessageResource`. This class will handle webapi/messages resource URL. Lets say we want to return a static text when this resource is requested.

2. **Add a method that returns the response** : 

   ```java
   package com.greydev.messenger.resources;

   public class MessageResource {
   	
   	public String getMessages() {
   		return "Hello World!";
   	}
   }
   ```

   We want to tell Jersey to execute our "getMessages()" method of our class "MessageResource" when the URL:.../webapi/messages is called and take the return type/value of that method and send it back as a response. 

   ​

3. **Make sure your class is in the package configured in Jersey servlet's `init-param`**: 

   ​	The first part is already done , pre configured : `<param-value>com.greydev.messenger</param-value>` with this line in our web.xml file, we tell Jersey : "When somebody makes a request and when you are not sure which class needs to handle it, just look up *this* package and see if there is a class which can potentially handle that request." We will talk later about how Jersey narrow downs the packages because there can be hundreds of packages.

4. **Annotate class with** `@Path` **annotation** :  

   ```java
   import javax.ws.rs.Path;
   						//every time we get a request to /messages this code will run
   @Path("/messages")
   public class MessageResource {
   	
   	public String getMessages() {
   		return "Hello World!";
   	}
   }
   ```

   Whenever there is a /messages call , Jersey looks up all the classes in this package `<param-value>com.greydev.messenger</param-value>` which has the `@Path("/messages")` annotation. When it founds it , it knows what class to look for. Now we also let Jersey know what method it needs to execute. There can be multiple HTTP methods sent to the same URL.

   <br><br><br>

5. **Annotate method with the right HTTP method annotation** : 

   ​	Jersey provides a way to map an HTTP method (GET,POST,DELETE...) with to a Java method in a class.

   ```java
   	@GET
   	public String getMessages() {
   			return "Hello World!";
   	}
   	@POST
   	public ...
   ```

   When we make a GET request to the URI /messages , now Jersey knows "/messages ? Here is the class, and here is the method that should be executed if a GET method comes for this resource". It executes the method and gets the return value, but we have to tell Jersey in what format the response should be returned.

6. **Annotate method with the** `@Produces` **specifying response format** : 

   ​	This annotation tells Jersey what is the return format of this request. **`MediaType`** is an enumeration that contains a lot of the standard content types an HTTP response would return.

   ```java
   import javax.ws.rs.GET;
   import javax.ws.rs.Path;
   import javax.ws.rs.Produces;
   import javax.ws.rs.core.MediaType;
   						//every time we get a request to /messages this code will run
   @Path("/messages")					// when this resource is called, look into this class
   public class MessageResource {
   	
   	@GET								// which HTTP request will invoke this method
   	@Produces(MediaType.TEXT_PLAIN)		// tells what the return format will be
   	public String getMessages() {
   			return "Hello World!";
   	}
   }
   ```

   Now http://localhost:8080/messenger/webapi/messages gives us `Hello World!` back as a response.




#### Returning XML Response

If this were a real application , we would have a database running on our back-end and we would have some kind of a data layer (JDBC, Hibernate) that connects to the database and pulls data that we need. For this course we wont establish a database connection and retrieve our data from there. We will create a mock service that returns a list of messages for us , focusing only on building REST API's.

1. **Create the necessary model and service classes** : 

   ```java
   // a simple model , simulating our data in a database
   // with the help of our service class "MessageService" we will be able to 
   // get data back from our model
   @XmlRootElement		// The annotation is needed for JAXP
   public class Message {
   	
   	private long id;
   	private String message;
   	private Date created;
   	private String author; 
   	
   	// Whenever when we mess around with XML or JSON conversion
   	// we always need a default constructor so those frameworks can create new Instances
   	public Message () {
   		
   	}
   	// creates new Message instances
   	public Message(long id, String message, String author) {

   		this.id = id;
   		this.message = message;
   		this.created = new Date();
   		this.author = author;
   	}
     // getters and setters ...
   ```

   ```java
   // this service class will give us back some messages
   // simulating a database connection and query result set
   public class MessageService {
   	// JAXP 
   	public List<Message> getAllMessages() {
   		Message m1 = new Message(1L,"First Message!","can");
   		Message m2 = new Message(2L,"Second Message!","ahmet");
         // cannot instantiate a List , List is an interface
   		List<Message> list = new ArrayList<>();
   		list.add(m1);
   		list.add(m2);
   		return list;
   	}
   }
   ```

   This time we want to return an XML file, so we change our `@Produces` annotation : 

   ```java
   @Path("/messages")			//every time we get a request to /messages this code will run
   public class MessageResource {
   	// CAREFULL : with every request we will create a new MessageService object
   	MessageService messageService = new MessageService();
   	
   	@GET
   	@Produces(MediaType.APPLICATION_XML)
   	public List<Message> getMessages() {
   			return messageService.getAllMessages();
   	}
   }
   ```

   What we tell Jersey with `@Produces(MediaType.APPLICATION_XML)` is to take our List that we return and convert it into XML. <u>The conversion is actually done by **JAXP** (Java API for XML Processing) , **JAXP** comes embedded in java</u>. So we are basically asking Jersey to use **JAXP** to convert whatever the return type is to XML. The problem is **JAXP** needs some information to do this conversion. It needs to know the class, the root element, we have to add this annotation :  `@XmlRootElement` 


   So now everything is ok . We get an XML file of 2 messages as a response when we call /webapi/messages.



 #### Building Service Stubs

We will implement some of the MessageService methods like getMessage(long) or addMessage(Message), with the in memory technique. We will create a map in memory , which is a map of id to message instances. We are going to add messages to that map , remove from that map.

We will create another model called Profile. The implementation will be the exact same as our Message model. Instead of storing these data to a database what we will do is create a `DatabaseClass`.

```java
public class DatabaseClass {
  	// preventing giving the same id to different messages. 
  	// This static counter will increase every time a message is created
	// Message constructor :  { this.id = DatabaseClass.getIdCounter(); } 
  	private static long messageIdCount = 1;
	// messages Map, maps an ID to the Message. Same for profiles
  	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<Long, Profile> profiles = new HashMap<>();

	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static Map<Long, Profile> getProfiles() {
		return profiles;
	}
  	public static long getIdCounter() {
		return messageIdCount++;
	}
}
```

Any class in our application can access the entire map of messages and profiles by calling these  static methods : `getMessages()` and `getProfiles()`. This is our stub for our database. This would usually be a JDBC or Hibernate class which connects to the database. (Not thread-safe, just for learning , don't do this in production)

```java
      public class Bla {
          public static int Increment() {
              int abc = 10;
              return abc++;	// Returns 10 !!!
              return ++abc;	// Returns 11 !!!
          }
          public static void main(String[] args) {
              System.out.println(Bla.Increment());
          }
      }
```



```java

// this service will give us back some messages
// simulating a database query result
public class MessageService {
	
	// every time there is a new instance of this service 
	// it will still point to the same instance of messages. This is why we can : 
		// add a message in one request, update it in another request.
		// as long as we don't restart the server.
  
	// PROBLEM HERE : The static field is copied , and this LOCAL copy is modified ???
	// not the actual static Map field.
	// ****** * * * * * * * *  NOOOOOOO * * * * * * * * *********
	// Everything except primitive types are POINTERS / REFERENCES !!!
	// We copy the pointer to DatabaseClass.messages TO messages in this class !!!
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(15L, new Message("Hello","Can"));
		messages.put(16L, new Message("Antalya","Ahmet"));
	}
	
	// messages.values() -> Returns a Collection view of the values contained in this map.
	// Passing a collection to the ArrayList constructor initialized the List with those 			elements!
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	// messages.get(id) -> Returns the value to which the specified key is mapped, 
	// or null if this map contains no mapping for the key.
	public Message getMessage(long id) {
		return messages.get(id);
	}
	
	// messages.put(KEY, VALUE)
	public Message addMessage(Message message) {
		// message.setId(message.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	public Message removeMessage(long id) {
		//returns the value that was removed
		return messages.remove(id); 
	}
}

```



#### Mapping @Path to functions and @PathParam

We will now implement a GET call `/messages/1` 

* Jersey allows the @Path annotation to be mapped to methods as well ! So we will have one top level @Path annotation and we will have method level annotations for subsequent portion of the path 

```java
@Path("/messages")
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages() {
			return messageService.getAllMessages();
	}
	@GET
	@Path("/test")		// meaning : "/messages/test"
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "test";
	}
}
```

This is how we extend the annotations. When we call http://localhost:8080/messenger/webapi/messages/test we get "test" as the result now.

* **All the methods in the class are mapped to** `/messages`  **by DEFAULT (because we wrote it at the top of the class)**. <u>getMessages are mapped to "/messages" because there is no other extra @Path annotation. However if a method has another @Path annotation, it appends it to the /messages URL, so the method test is actually mapped to</u> `/messages/test` 
* But our URL will be a variable , we cannot map 1,2,3,4... all numbers to a separate function. The way we tell Jersey that we have a variable path is by  **`{variableName}` **  
* We are not limited to just one parameter , we can have multiple parameters in our path : `/something/{id1}/name/{id2}`   and each one of these can be its own data type.
* We can also have <u>regular expressions</u> to catch different kinds of URL's


* just having the same name inside the function body wont work , we also need to give an annotation `@PathParam` 
* `@PathParam("messageId")`  "We are telling Jersey, There is a parameter in the path called messageId that we mapped to this function. Inject the value as a parameter to this method so I can access it in my method"

```java
@GET
	@Path("/{messageId}")				//this variable name can be anything !
	@Produces(MediaType.APPLICATION_XML)
	public Message getMessage(@PathParam("messageId") long messageId) {
		// Jersey converts the String to a long , because we expect a long as a parameter
		return messageService.getMessage(messageId);
	}
```

Smart thing is our function will only work with "long". If a string is passed , Jersey sends a 404 error

http://localhost:8080/messenger/webapi/messages/15 -> Gives us back the Message from Can

http://localhost:8080/messenger/webapi/messages/16 -> Gives us back the Message from Ahmet



#### Returning JSON response

Here only changing the `@Produces(MediaType.APPLICATION_JSON)` wont work. The conversion from the message instance to XML is being done by JAXP which comes with Java (so nothing extra needed). Jersey will tell JAXP to convert some things for me.

<u>For JSON there is no such thing as JAXP, no converter that comes out of the box. So we need to add the JAR that does this conversion into you application classpath</u>.

* **Open the file "pom.xml" in the root project** : this contains all the dependencies, all the JAR's that are included in our libraries. So whats the dependency we need to add ?
* **Uncomment this to activate the dependency  jersey-media-moxy** : this is the JAR which does the conversion. `Moxy` is a library for handling JSON conversions. This project comes with dependency but its commented out , so when people need it they can uncomment it

```java
<!-- uncomment this to get JSON support
        <dependency>
            <groupId>org.glassfish.jersey.media</groupId>
            <artifactId>jersey-media-moxy</artifactId>
        </dependency>
        -->
```

​	So when we cann `/messages` again , it works ! We get a JSON format back as a response. 

* The important thing to keep in mind is there are these libraries that we will have to include for different types of conversions. And some of the classes that are included in those libraries implement a particular interface. So there is an interface called `Message Body Reader` and also `Message Body Writer`. So what we are looking at is a **Message Body Writer**. 
  * Its a class that converts from a Java type to something like XML or JSON types. This was what was missing in our application. Adding this add a class message body writer that does the conversion. 

  ​



#### Implementing POST Method

We want to be able to create and add new messages. And we decided the REST api we were gonna use is a POST request to /messages collection URI, with the request body containing the new message in JSON format.

1. Annotate the handler method with `@POST` and `@Produces` : 

   ```java
   	@POST
   	@Produces(MediaType.APPLICATION_JSON)
   	public String addMessage() {
   		return "post works";
   	}	
   ```

2. We are going to use the "raw" JSON request body in Postman. <u>Important to know , when we are doing a POST request and sending something in the body we also have to give this additional clue about what the format of that request body is</u>. <u>Whenever we are sending request content or response content, we always need to make sure that we set the header value to the right content-type</u>. So when we are sending for example a JSON file we also write it in the header .

   We can do that in Postman, click "Headers" , set Key and value. 

   **Key** = Content-type 	**Value** = application/json

   ​

3. So how can we get the JSON and compute it in our method ? Just accept the Model type as argument to bind to the request body. As long as the method argument has (Message message), Jersey knows that this is what we are expecting and it will converts the request body (JSON) to a message instance.

   Since this method will consume JSON , we also should add the `@Consumes(MediaType.APPLICATION_JSON)` .  So now we have a method that accepts JSON (Consumes) and returns JSON (Produces). Thats all jersey needs to know. 

   ```java
   	@POST
   	@Produces(MediaType.APPLICATION_JSON)
   	@Consumes(MediaType.APPLICATION_JSON)
   	public Message addMessage(Message message) {
   		return messageService.addMessage(message);
   	}	
   ```



#### Implementing PUT and DELETE 

<u>Updating is a PUT request to an instance URL. When we want to update the message with the ID 2 , we do a PUT request to</u> `/messages/2` <u>with the request body containing the message in JSON format</u>. Its similar to a POST, 

- but **POST** does it to a **collection URL and creates a new resource** .

* **PUT** sends it to an **existing resource URL and it updates that existing instance** .

<u>When we want to send a message with a PUT request , usually the "id" will not be included in the JSON file , or atleas we should build our application in a way that doesn't require and "id" field in our JSON message body. We will take the number from the end of requested URL and set it in our method before updating the message</u>.

PUT http://localhost:8080/messenger/webapi/messages/1 : meaning we want to update message ID 1 with this new message :

	{	// REQUEST BODY
		"author":"Musti",
		"created":"2017-12-20T23:15:33.995",
			// "id":1 *** this shouldnt be included
		"message":"Arabası var "
	}
```java
	@PUT
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
```

Now whether the user sends an incorrect id or any id or not, the api will set it for us, depending on which resource ID is called.

##### DELETE:

```java
	@DELETE					// same idea. @Produces might be unnecessary
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
```



* **If almost all of your methods in a class @Produces or @Consumes something , you can delete the annotations from the methods and add it to the top of your class !**




#### Implementing ProfileResource

We will create a new resource for profiles, as we talked before the resource URI for a profile will be `/profiles/{profileId}` and for the collection of profiles will be `/profiles` . The resource ProfileResource that we will create has alot of similarities to our MessageResource.

ProfileService vs MessageService : 

```java
// We mapped the message id with the message . The key is Long
	private static Map<Long, Message> messages = new HashMap<>();
// here we will map a String , the name of the profile , with the profile. The key is a String
	private static Map<String, Profile> profiles = new HashMap<>();

```

```java
public class ProfileService {
		private Map<String, Profile> profiles = DatabaseClass.getProfiles();
		public ProfileService() {
			profiles.put("Can", new Profile(1L, "Can", "Can", "Kleiner"));
		}	
		public List<Profile> getAllProfiles() {
			return new ArrayList<Profile>(profiles.values());
		}
		public Profile getProfile(String profileName) {
			return profiles.get(profileName);
		}		
		// profiles.put(KEY, VALUE)
		public Profile addProfile(Profile profile) {
			// problematic
			profile.setId(profiles.size() + 1);
			profiles.put(profile.getProfileName(), profile);
			return profile;		
        }	
		public Profile updateProfile(Profile profile) {
			if (profile.getProfileName().isEmpty()) {
				return null;
			}
			// profiles.put(KEY, VALUE) Our KEY is a String
			profiles.put(profile.getProfileName(), profile);
			return profile;
		}
		public Profile removeProfile(String profileName) {
			//returns the item that was removed
			return profiles.remove(profileName); 
```

```java
@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
	private ProfileService profileService = new ProfileService();
	@GET
	public List<Profile> getProfiles() {
		return profileService.getAllProfiles();
	}
	@POST
	public Profile addProfile(Profile profile) {
		profile.setCreated(new Date());
		return profileService.addProfile(profile);
	}
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
	}
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName, 
                                 Profile profile) {
		profile.setProfileName(profileName);
		return profileService.addProfile(profile);
	}
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
```

**Does JSON converter packs the fields in ascending order ???**

### Pagination and Filtering

The message API needs some kind of a filtering mechanism. We don't want to do a GET request to `/messages` and get all messages every time. Somehow we need to filter the messages.We talked about different ways that we can filter them. 

**Filtering** : For example filtering them with the year : `/messages?year=2015`  This request would return all the messages created in 2015. This is one way of <u>filtering</u>.

**Pagination** : We talked about **/messages?start=10&size=20** the starting point would control where the page would start and the size would control what the size of the page is. This lets users <u>paginate</u> a group of messages.

Before we implement the API , we need to add the backing service methods to our service classes.

```java
public List<Message> getAllMessagesForYear(int year){
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	public List<Message> getAllMessagesPaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<>(messages.values());
		if (size + start > list.size()) {
			return new ArrayList <>();
		}
      	// subList(int fromIndex,int toIndex)
		return list.subList(start, start + size);
	}
```

So now we can modify our resource class:

**We CANNOT have multiple @GET methods** those will be executed when different parameters are given. We need to have one method and compute the all the different parameters inside those methods. We need to get hold of the query parameter value.

The Annotation we need here is `@QueryParam`

```java
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year,
									 @QueryParam("start") Integer start,
									 @QueryParam("size") Integer size) {
		/* if no parameters are given :
        	our int arguments will be equal to 0
        	our Integer arguments will be "null"
		  	thats why we need to control them and be carefull 
		 */
		if(year > 0) {
			return messageService.getAllMessagesForYear(year);
		}
		if (!(start == null && size == null)) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}
```

* **messages?year=2017**  now a GET request like this gives us only the messages posted in 2017.

* **/messages?start=0&size=1** : start from the first element , give me the next 1                                                                          **/messages?start=1&size=2**  : start from the 2. element , give me the next 2 elements

  ​

###Param Annotations

Lets you cherry pick the data that you want from the request that comes to us. We look at annotations that let us access parameters,  @PathParam and @QueryParam.

**Matrix Params**  **[NOT OFTEN USED]** : Very similar to Query Params. 

* In <u>Query params</u> we usually do something like : **/messages?param=value**
* In <u>Matrix Params</u> this would be : **/messages;param=value**

```java
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam) {
		return "Matrix param : " + matrixParam;
	}
	// injectdemo/annotations;param=Blabla  ---> Matrix param : Blabla
	// injectdemo/annotations?param=Blabla  ---> Matrix param : null
```

**Header Parameters** : We can actually send custom header values . This is really useful when we want to handle authentication / security and things like that. You can have the client send custom values in the header. This is again done by an annotation in the method arguments : 

```java
	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("customHeaderValue") String header) {
		return "Header param : " + header;
	}
```

Now when we set our headers for our GET request : KEY = customHeaderValue , VALUE = Hey there ! and send a GET request , the response we get : **Header param : Hey there !** 

Header params are usually used to send meta-data about the request, like the authentication token etc. We can create our own authSessionID as a parameter and sending some token value and the API will be able to access that by accessing  authSessionID this parameter.

**Cookie Param** : We can also access the cookie value. Same : **`@CookieParam("name") String cookie` **

**Form Param** : `@FormParam` , this when you want to access form submissions. We could have an HTML form and when you submit it the values goes as key-value pairs. The key would be the name of the control (text box) and the value is the value that someone inputs into that textbox. Its not widely used. You normally wouldn't usually submit data to a rest api using an HTML form.

The `@PathParam` and the other parameter-based annotations, `@MatrixParam`, `@HeaderParam`, `@CookieParam`, `@FormParam` obey the same rules as `@QueryParam`. `@MatrixParam` extracts information from URL path segments. `@HeaderParam` extracts information from the HTTP headers. `@CookieParam` extracts information from the cookies declared in cookie related HTTP headers.

### Context Param , Bean Param

There are a couple problems with the params that we saw till now. First problem is if you have more then 5 params it gets really messy and hard to work with. Second problem is , when we access something we need to know what the name is . Example : we want to access a cookie with the name "name" cookieParam("name"). So we need to know before hand that there is a cookie called "name" . 

**Context Param** :  If we are not sure what we are looking for .What if we want to get all the cookies or all the header values and see what we can do based on that. The `@ContextParam` is used for this. <u>This is a special Annotation . It can be annotated to special types of arguments</u>. Cookie , header , matrix can be annotated to any primitive value. There are limited types that Context can annotate.

One of them is **UriInfo**.  Its going to inject an instance of UriInfo. It contains a lot of information about the URI that was accessed. That led to this method being called. There are alot of methods that could be useful.

```java
	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo 
										@Context HttpHeaders headers) {
		uriInfo.getAbsolutePath();		// returns the whole path
		uriInfo.getQueryParameters(); 	// returns a map, what query parameters were send
		uriInfo.getPathParameters(); 	//...
		headers.getCookies();
		headers.getMediaType();
		return "";
	}
```

One more context instance we can use is **HttpHeaders**. It gives us the header information. 

**Bean Param** : We don't want too many annotations in our methods and there is another way to access them. The way Bean Param works is you create a separate bean with all the annotations and then in our resource class we just say @BeanParam and we accept that bean.



```java
public class MessageFilterBean {
	private @QueryParam("year") int year;
	private @QueryParam("start") Integer start;
	private @QueryParam("size") Integer size;
	// Getters and Setters ...
  public int getYear() {...}	public void setYear(int year) {...}	getStart...
}
```

If the list is getting too long , We can change our getMessage method like this, pack all the annotations into a class. 

```java
@GET
@Produces(MediaType.APPLICATION_JSON)
public List<Message> getMessages(@BeanParam MessageFilterBean filterBean) {
  // to access the year		 // to access start			// to access size
  filterBean.getYear();		 filterBean.getStart();		filterBean.getSize();
}
```

### Implementing Sub-resources

This is a Cool way to build nested resources (sub-resources) . Lets implement the comment resource api. A comment is not the root resource, it follows the message. Its `/messages/{messageID}/comments/{commentId}` . 

So we might be thinking that we can add new methods to our MessageResource class to implement the comment resource : 

```java
	@GET
	@Path("/{messageId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCommentResource(@PathParam("messageId") long messageId) {
		return "test";
	}
```

<u>but what if we also have likes , shares , ... and other resources as well ? This means we would pack everything into just one class and we DON'T want that !</u> We want separate classes CommentResource , LikeResource and so on.

* **The concept of a sub-resource** : We can tell Jersey , whenever the path starts with `/{messageId}/comments` , don't execute a method but instead hand over the responsibility to this other class "CommentResource". We still have the root resource but then we say "after some point in the URL send the responsibility to this other resource". Somehow we need a connection from the main resource to this other resource : 

Instead of returning some value which is send back as a response, we will return an instance of the resource thatwe want the responsibility to pass to. 

```java
	// dont give a method annotation means : FOR ALL METHODS 
	// this will continue the search in the CommentResource class
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
```

No matter what HTTP method is accessed here ,when the path matches , Jersey will see that the return type is another resource , so its gonna look for the actual method in this resource 

```java
public class CommentResource {	
	@GET
	public String test() {
		return "test new sub-resource";
	}	
} // GET: .../webapi/messages/1/comments     returns "plain-text" "test new sub-resource"
```

* **Jersey adds the paths up , so `/messages` from the top of the MessageResource class, `/{messageId}/comments` from the method getCommentResource and then it will continue to search in CommentResource class, since this class doesn't have a path it will add nothing. The @GET method also doens't have a path so it adds nothing . Then it will look for the matching HTTP method in the CommentResource class**

```java
	@GET
	@Path("/{commentId}")
	public String getComment(@PathParam("commentId") long commentId,
							 @PathParam("messageId") long messageId ) {
		return "messageId is " + messageId + " commentId is " + commentId;
	}	// GET: /messages/123/comments/44 : messageId is 123 commentId is 44
```

Since we have 2 parameters in our path , we can both access it in our matching methods like above.

We implemented a new Comment model :

```java
public class Comment {
	private long id ;
	private String message;
	private Date created;
	private String author;
	
	public Comment() {}
	public Comment(long id, String message, String author) {
		this.id = id;
		this.message = message;
		this.created = new Date();
		this.author = author;
	}
  // Getters and Setters
```

Also added a new field (getters setters) in Message model. Every message has a bunch of comments. So every message instance has a list of comments for that instance. (Probably an ArrayList would be more suitable here ?)

```java
	private Map<Long, Comment> comments = new HashMap();

	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}
	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}
```

* **Also an important point** : We marked the GETTER getComments with `@XmlTransient` because we don't want the comment data showing up when you access a message. When the message instance is converted to XML , the list of all the comments will be ignored and wont be converted ! It works also for JSON (not sure)ç So JSON converters also will ignore this list when converting the instance to a JSON format.
* **REALLY IMPORTANT TO REMEMBER** : <u>With each request , a new instance of the corresponding Resource classes with @Path annotations will be created (not sure ?), so keep this in mind !</u>

<u>We implemented CommentResource and CommentService classes. See source code*****</u>



### Sending Status Codes and Location Headers

Getting more control over the response that we send back. Right now we just return the message instance and wait for Jersey to convert it to JSON. So what we are returning is just the response content. What if we want control over what the status code is or what the header values are ? There is no way to control those if we just return our instance as a response.

We had a design discussion before about : 

1. There are different responses for different situations and the response should ideally be 201 CREATED (resource created). 200 is the success status code.
2. When a new resource was created we want to send the newly created resource URL back in the header so it would be easier for the user.

In order to control the entire response of a method, we need to return a separate instance of a different class called `Response`. To create a response we need to use the "response builder" which will allow us to modify the status code and the header.

```java
	public Response addMessage(Message message) {
		Message newMessage = messageService.addMessage(message);
		// creating a new Response instance creating the response builder, (STATIC CLASS)
      	return Response.status(Status.CREATED)	// status code will be 201
          			   .entity(newMessage)		// return this message instance back
                       .build();				// return an instance of the response
```

There are many more methods that we can work with in the Response class. The idea is set all the different things and then call the `.build()` method (<u>uses builder pattern*</u>). 

So now to send the URL of the created resource back we have couple options : 

1. We could add another method before the build , adding the `header(string, obj)` method , where we can tell the name and return the URL back on the header . Example "createdResourseURI" value : ".../messages/..."
2. <u>BEST PRACTICE</u> :There is some default (shortcut) methods for standard status codes . A better way , not using the `status()` method. Instead use the `created(URI location)` method. It lets you send a location back. It will set the status code to 201 and it adds the location as the header and sends it back

```java
	public Response addMessage(Message message) throws URISyntaxException {
		Message newMessage = messageService.addMessage(message);
     	// http://localhost:8080 is root for URI, so the rest of the URI we have to give it
		return Response.created(new URI("/messenger/webapi/messages/" + newMessage.getId()))
					   .entity(newMessage)
					   .build();
	}	/* our header that we send back when a new message is created :
		 * location -> http://localhost:8080/messenger/webapi/messages/5 	*/
```

​		**this works but the code is horrible (hard coded URI), we can make it cleaner !** 

```java
	public Response addMessage(Message message, @Context UriInfo uriInfo ) {
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri)
					   .entity(newMessage)
					   .build();
	}	/* uriInfo.getAbsolutePathBuilder() returns URI of absolute path : ".../messages" 
		 * and with .path(newID) we add the string to this path and return a URI instance
		 * because .created wants a URI as an argument  */
		
```

<br>

### Handling Exceptions

What do we want to send as a response when an exception occurs ?  We don't want to show an HTML response. We want to send a type of error code in JSON and not a generated error page from tomcat server.

For example when a request to `messages/250` comes , the response will be "204 No Content". Here we want to implement an exception.

We will create a new class `DataNotFoundException` which will extend `RuntimeException`.

```java
public class DataNotFoundException extends RuntimeException {
	 //Serial Id of runtime exception ?	 
	private static final long serialVersionUID = -4238998518887124497L;
	public DataNotFoundException(String message) {
		super(message);
	}
```

```java
	// MessageService class	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Message with id: " + id + " not found");
		}
		return message;
	}
```

**Problem** : If we now try to get the message 250 , we will get an error page in HTML with status 500. This is because while we threw the exception from the service we didn't write anything to handle that exception. The MessageService throwing this exception to the MessageResource , to the getMessage() method which doesn't catch that exception so that gets thrown further , the exception keeps bubbling up and it goes all the way up to the tomcat servlet container, this container has some default behaviour what to do when exceptions get bubbled up : to show the standard tomcat error page. This is bad.

The way to **prevent this** is to intercept this bubbling up of the exception. <u>The service throws it to the resource , the resource throws it to the framework. We want the framework to catch this and return a JSON response so it does not go all the way to the tomcat servlet container</u>. Here are the steps : 

1. Prepare the JSON response
2. Map it to the exception . We tell Jersey "When this exception is thrown, return this JSON response"

Lets create the JSON payload that we want to return when there is an exception. The end client is a developer . We should give good information so the developer knows what is happening and how to troubleshoot.

```java
// annotating this class because I want this to be converted to JSON
@XmlRootElement
public class ErrorMessage {
		private String errorMessage;
		//could be unique to your API
		private int errorCode;
		private String documentation;
		
		public ErrorMessage () {}
		public ErrorMessage (String errorMessage, int errorCode, String documentation) {
			this.errorMessage = errorMessage;
			this.errorCode = errorCode;
			this.documentation = documentation;		}
    // Getters and Setters
```

So we prepared our JSON response, now we have to map it to the response. The way we do this is with a class called `ExceptionMapper` . Every exception mapper in JAX-RS needs to implement `ExceptionMapper` . Its a raw type , we need to make it generic. The generic type is gonna be the exception that we want this exception mapper want to map , so DataNotFoundException.

```java
// this annotation registers this class in JAX-RS. 
// JAX-RS knows its there and it can map this exception 
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, 		
                                                     "https://google.com");
		return Response.status(Status.NOT_FOUND)
					   .entity(errorMessage)
					   .build();
	}
```

We created a response by adding a status code, then created our entity and added it and build it.

So now when the MessageService throws this exception, which will go to the resource, it wont be catched there, it throws it to JAX-RS. JAX-RS will looks at all the exception mappers that have been annotated with `@Provider` and it looks if there is anything that maps to the exception that is thrown. So its gonna call the toResponse method and will pass in the exception. Returns a response to the user.

It works ! Now we get a JSON response : 

```json
{												Status: 404 Not Found
    "documentation": "https://google.com",
    "errorCode": 404,
    "errorMessage": "Message with id: 1111 not found"
}
```

* This can be implemented for any kind of exceptions , doesn't have to be custom made.

Another exception : calling a URL that does not exist : 404 not found page, because tomcat is handling this. <u>We can have a bunch of exception mappers to handle all these different exceptions. All we need to do is figure out what is the exception that is being thrown and create a new exception mapper to map that exception. Jersey is gonna call that and return the response</u>.

Let create a generic exception handler so that we wont see the tomcat response anymore when exceptions occur : 

```java
					// no matter what the exception is, catch all
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, 
                                                     "https://google.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
					   .type(MediaType.APPLICATION_JSON)
					   .entity(errorMessage)
					   .build();
```

**To return JSON we can add `.type(MediaType.APPLICATION_JSON)` !**

It is good to have multiple mappers to have custom error messages that we can send.



### Using WebApplicationException

Why did we need the exception mapper ? Because jax-rs has no information about the kind of exceptions our application could throw. Could be a custom / null pointer exception. Jax-rs doesn't know what to do with it. So its hard to map an exception to a response by default.

JAX-RS has its own set of exceptions too that it does map to a status and a response. Since jax-rs knows what does exceptions are , we don't need to write an exception mapper. Jax-rs already knows what the response for those exceptions should be.

We should disable our generic exception (catch all exceptions) handler that we wrote last time, or else it will override anything from jax-rs. This is something we DONT want to do, mapping a throwable to an exception mapper because this catches everything and returns just one kind of a response. Just delete the `@Provider` annotation so it does not get register in Jersey.

The Exceptions that JAX-RS is aware of are called `WebApplicationException`  . We will modify our `getComment` method now so that it throws an exception that jax-rs already knows.

```java
// CommentService class
public Comment getComment(long messageId, long commentId) {
		ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "https://google.com");
		Response response = Response.status(Status.NOT_FOUND)
					   .entity(errorMessage)
					   .build();
		Message message = messages.get(messageId);
		if (message == null) {
			// this has many different constructors
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new WebApplicationException(response);
		}
		return comment;
```

Since we are using an exception that comes with JAX-RS, we don't have to map it. It already knows what to send back , we passed the response into the constructor.

* Build a response 
* Pass it to the constructor of WebApplicationException
* throw it, JAX-RS knows what to do 



**Here is why this method is NOT SO CLEAN** : CommentService class is a business service. In the business service we have all this code which handles response that needs to be sent to the user. So this is not really business code , its more like presentation code, the user sees this. Its a bad place for this code to be in. 

```java
	ErrorMessage errorMessage = new ErrorMessage("Not found", 404, "https://google.com");
	Response response = Response.status(Status.NOT_FOUND)
					   				   .entity(errorMessage)
					   				   .build();
```

Its kind of ok if this were in the CommentResource class , if commentService.getComment(...) returns null then we could create that response. If the exception mapper is in a separate class , its more cleaner. Its still ok but it clutters the business code.



### HATEOAS

We already talked about what HATEOAS is. We need to be able to send links to resources in the responses. Once the user get a response, they will know what other options they can do only looking to the response.

Our message response contains all the information about the message. Now our goal is to add links that provides clients information about where to find other resources. Link to self, profile, comments ,likes ...

The first thought is to extend our Model since our Message model is converted to JSON. So that our links will be converted and presented in JSON format too.

```java
public class Link {
	private String link;
	private String rel;
  // Getters , Setters }
```

We don't have to annotate this class with `@XmlRootElement` because this is not the root element. Its a member variable of Message class. Message class is annotated. 

```java
// we added this to the Message class 	
	private List<Link> links = new ArrayList<>();

	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}
// Also we need Getters in our Message class , so that our converter can access to the List.
```

`UriInfo.getAbsolutePathBuilder()` has some methods to build our path. 

- One is `.path("blah")` , passing a string adds it at the end of the current path **/blah/**
- Other one is `.path(AnyResource.class)`  Its gonna find the @Path annotation for AnyResource.class and adds it to the current path that you are building. `.path(MessageResource.class)`will add **/messages/**

This is very good when implementing HATEOAS. It lets you find paths to resources easily without hard coding it 

```java
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		// Jersey converts the String to a long , because we expect a long as a parameter
		Message message = messageService.getMessage(messageId);
// String uri = uriInfo.getAbsolutePathBuilder().path("messageId").build().toString();
// String uri = uriInfo.getAbsolutePathBuilder().path(message.getClass()).build().toString();
		
		String uri = uriInfo.getBaseUriBuilder()  	// http://localhost:8080/messenger/webapi/
							.path(MessageResource.class)	// + messages/
							.path(Long.toString(message.getId())) // + /{messageId}
							.toString();
		
		message.addLink(uri, "self");		
		return message;
	}
```

```java
{
    "author": "Can",
    "created": "2017-12-25T13:34:53.963",
    "id": 1,
    "links": [
        {
            "link": "http://localhost:8080/messenger/webapi/messages/1",
            "rel": "self"
        }
    ],
    "message": "Hello"
}
```

We can separate this to a method so it will be cleaner.

```java
private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()  	// http://localhost:8080/messenger/webapi/
							.path(MessageResource.class)	// + messages/
							.path(Long.toString(message.getId())) // + /{messageId}
							.toString();
		return uri;
```

**Building the link for Profile** : 

```java
// create a new method for retrieving profile   Author == Profile name
private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()	 // /messenger/webapi
						.path(ProfileResource.class) // + /profiles
						.path(message.getAuthor())	 // + /{profileName}
						.build()
						.toString();
		return uri;
```

```java
public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(messageId);
		String uri = getUriForSelf(uriInfo, message);
		String profile = getUriForProfile(uriInfo, message);	
		message.addLink(uri, "self");
		message.addLink(profile, "profile");
		return message; }
```

**Building the link for Comments** : 

This is a bit tricky because comment is a **sub-resource**. We want to send to the client a URI where they can get all the comments for any given message. Comment does not have an `@Path` annotation , thats why we <u>CANNOT</u> use the `.getBaseUriBuilder()` to build our path.

The method `getCommentResource()` in the `MessageResource` class has the `@Path("/{messageId}/comments")` annotation that we need. So how do we get hold of that path that is mapped to a method ? 

There is a different (overloaded) `.path(Class resource, String method)` method which takes in 2 arguments. One is the resource class name, the other is the method name. Like this : 

`path(MessageResource.class,"getCommentResource")`. But the problem here is this getCommentResource has this annotation : `@Path("/{messageId}/comments")`  there is a variable and its mapped to what the client sends. But we to replace it with the actual id of the message. This can be done with this method :

`.resolveTemplate(name, value)` . <u>name</u> : whats the name of the variable that you want to replace ? <u>value</u> : whats the value you want to replace it with ?

The variable portion that we want to replace is {messageId} and the value is message.getId().

```java
private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()	
				.path(MessageResource.class) 		
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();

		return uri;
```

We left the CommentResource not annotated so we got this error message : "HTTP Status 500 - java.lang.IllegalArgumentException: The class, class com.greydev.messenger.resources.CommentResource is not annotated with @Path." 

When we added @Path("/") it fixed it. **So good practise to always add this** (?)

There is also another class in JAX-RS "Link" which we could also use instead of creating our own Link class. But its a bit buggy ? We are using the moxy converter ?



### Content Negotiation

A client can ask a server for a particular content type for the response. The client can also send different types of content to the server with telling it what the type of the content is. Client tells that its sending a JSON and asks to get a JSON back. If server supports JSON then its sends JSON back but if not then it might send an error message back. 

This is done by adding a value in the header of each request that it sends. **Accept Header** can be added to a request. The value of that header is the content type that the client asks for. If the client wants json the header will look like this : `Accept ->  application/json`  

So if we would add a header like this to our GET request for messenger api :  `Accept ->  text/xml`   We would get an error : "HTTP Status 406 - Not Acceptable" . Because our api doesn't have support for xml at the moment. We only produce JSON. We could enhance this and add xml support too.

This annotation can take more values , so we can just add XML support like this  : 

`@Produces( value = {MediaType.APPLICATION_JSON, MediaType.TEXT_XML} )` (added to MessageReourseClass)

Now the same request with the Accept header will return us XML.

Why not support all content types all the time , if the client gives a type that we don't know, we will return an error anyway ? 

This is because we can use this Produces annotation to (pick and choose) act / run different methods depending on what the requested type is. Depending on the value in the Accept header , JAX-RS or Jersey will call the correct method.

**Class level annotation** : Applies to all methods in the class

**Method level annotation** : Applies only for a method.

* Method annotations can <u>OVERRIDE</u> class level annotations !

```java
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {
	private MessageService messageService = new MessageService();
	@GET
	@Produces(MediaType.APPLICATION_JSON)	// OVERRIDE class level annotations !
	public List<Message> getJsonMessages(@QueryParam("year") int year,
									 @QueryParam("start") Integer start,
									 @QueryParam("size") Integer size) { 
		if(year > 0) {
			return messageService.getAllMessagesForYear(year);
		}if (!(start == null && size == null)) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}
	@GET
	@Produces(MediaType.TEXT_XML)	// OVERRIDE class level annotations !
	public List<Message> getXmlMessages(@QueryParam("year") int year,
									 @QueryParam("start") Integer start,
									 @QueryParam("size") Integer size) {
		if(year > 0) {
			return messageService.getAllMessagesForYear(year);
		}if (!(start == null && size == null)) {
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}
```

Now depending on the Accept header , we will run the right methods.

This is not just for what the client wants from the server , its also a property what the client sends to the server. Lets say the server gets a post request with a message body, this request will contain information about the format of the body , as a header value : `Content-Type` Header . Client lets the server know what to format is , with this header.

* **Accept** maps to `@Produces` annotation
* **Content-Type** maps to `@Consumes` annotation 

Because we don't support XML being send to the server , if we try to add a new message and send XML , we would get an error message. The same concept applies here as we did for @Produces , using @Consumes ! Same , different methods could be called based on the request format.

-------------------------------------

<br>

MediaType.TEXT_XML , these are actuall only sting constants . So we could write "/text/xml" instead but using MediaType is a more elegant way.

```java
public final static String APPLICATION_JSON = "application/json";
public final static String TEXT_XML = "text/xml";
public final static String TEXT_PLAIN = "text/plain";
```

