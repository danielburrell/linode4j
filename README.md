linode4j
========

Java implementation of the Linode API


##Features
 - Clean, simple data access layer and data objects
 - Access to underlying API data for ultimate control
 - Threadsafe Spring-like template-based data access objects.
 - Ultra fast serialisation via Jackson.
 - Simplified access to the most common api requests.

##Quickstart

###Maven
```xml
<dependency>
    <groupId>uk.co.solong</groupId>
    <artifactId>linode4j</artifactId>
    <version>1.0</version>
</dependency>
```

Get a string response from the server
```java
Linode api = new Linode(API_KEY);
String reply = api.updateDomain(4).withDescription("someDescription").withMasterIps("82.12.53.123").asString();
```
Or as json
```java
Linode api = new Linode(API_KEY);
JsonNode reply = api.updateDomain(4).withDescription("someDescription").withMasterIps("82.12.53.123").asJson();
```

Or (coming soon) as a nicely typed object
```java
Linode api = new Linode(API_KEY);
DomainUpdateReply reply = api.updateDomain(4).withDescription("someDescription").withMasterIps("82.12.53.123").asObject();
```

##Legal
MIT License
