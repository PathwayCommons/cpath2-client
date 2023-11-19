# cpath2-client

[![Build with Maven](https://github.com/PathwayCommons/cpath2-client/actions/workflows/build.yml/badge.svg)](https://github.com/PathwayCommons/cpath2-client/actions/workflows/build.yml)

A convenient Java library to work with Pathway Commons web services.

We've just cut the cpath-client module from the cpath2 multimodule Maven project, refatored and moved here to make possible maintaining it separately from the server code (cpath2).

This ia a fluent API Java client library for using Pathway Commons' RESTful [web services](https://www.pathwaycommons.org/pc2/), our large integrated BioPAX L3 database, from other Java projects, such as Cytoscape desctop (apps). But feel free to use another tool or develop own web client for Pathway Commons if you wish.

## Use

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
        <version>10.1.0-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>org.biopax.paxtools</groupId>
        <artifactId>paxtools-core</artifactId>
        <version>5.3.0</version>
    </dependency>
</dependencies>
```

## See also:
* http://www.biopax.org/paxtools
* [tests](https://github.com/PathwayCommons/cpath2-client/blob/master/src/test/java/cpath/client/CPathClientIT.java) as an example;
  
One can add the cpath-client _fat_ jar with dependencies to a Java (or JPype) project: grab the latest fat-jar ([from here](https://oss.sonatype.org/content/groups/public/pathwaycommons/cpath/cpath-client/)).

## ToDo: 
* Implement cPath2 v14 APIv2 (coming soon) client methods (only HTTP POST and JSON request body are used in the v2 API)
