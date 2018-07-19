# cpath2-client

A convenient Java library to work with Pathway Commons web services.

We've separated cpath-client module from the cpath2 multi-module Maven project into here (at 8.0.0-SNAPSHOT version) 
and slightly refactored to make possible maintaining it separately from the server code.


# Introduction #

We develop a convenient (fluent query API, reasonable defaults) Java client for the [PC2 web services](http://www.pathwaycommons.org/pc2/) to use in other projects, such as in desktop Cytoscape apps, to quickly start exploring and querying the integrated PC BioPAX L3 database. Besides, we are not forcing anyone to use this client only; feel free to develop a simpler or more specific one.

# Use #

```
//using the Pathway Commons cPath2 web service URL (default):
CPathClient client = CPathClient.newInstance();
// then, result=client.create*Query()...result() or ...stringResult(format), etc.
```

in a Maven project, add to the pom.xml the following:

```
  <repositories>
	  <repository>
		  <id>ossrh</id>
		  <name>OSSRH Repository</name>
		  <url>https://oss.sonatype.org/content/groups/public/</url>
	  </repository>
  </repositories>
  <dependencies>
    <dependency>
      <groupId>pathwaycommons.cpath</groupId>
      <artifactId>cpath-client</artifactId>
      <version>10.0.0-SNAPSHOT</version>
    </dependency>
    <dependency>
      <groupId>org.biopax.paxtools</groupId>
      <artifactId>paxtools-core</artifactId>
      <version>5.1.0-SNAPSHOT</version>
    </dependency>
  </dependencies>
```

See also:
* the project's [tests](https://github.com/PathwayCommons/cpath2-client/blob/master/src/test/java/cpath/client/CPathClientTest.java) can be used as example;
  
Alternatively, one can add the cpath-client _fat_ jar with dependencies to a Java (or JPype) project: grab the latest fat-jar ([from here](https://oss.sonatype.org/content/groups/public/pathwaycommons/cpath/cpath-client/)).


# See also #
  * http://www.biopax.org/paxtools
  
