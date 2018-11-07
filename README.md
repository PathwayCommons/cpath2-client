# cpath2-client

[![Build Status](https://travis-ci.org/PathwayCommons/cpath2.svg?branch=master)](https://travis-ci.org/PathwayCommons/cpath2-client)  
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c9722bf60f714e87a7137ff2f2586926)](https://www.codacy.com/app/IgorRodchenkov/cpath2-client?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=PathwayCommons/cpath2&amp;utm_campaign=Badge_Grade)

A convenient Java library to work with Pathway Commons web services.

We've just cut the cpath-client module from the cpath2 multi-module Maven project, refatored and moved here to make possible maintaining it separately from the server code (cpath2).

This ia a fluent API Java client library for using Pathway Commons' RESTful [web services](http://www.pathwaycommons.org/pc2/), our large integrated BioPAX L3 database, from other Java projects, such as Cytoscape desctop (apps). But feel free to use another tool or develop own web client for Pathway Commons if you wish.

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
  
