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

```java
Linode api = new Linode(API_KEY);
api.updateDomain(4).withDescription("someDescription").withMasterIps("82.12.53.123");
```

##Legal
MIT License
