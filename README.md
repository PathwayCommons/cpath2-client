# cpath2-client

[![Build with Maven](https://github.com/PathwayCommons/cpath2-client/actions/workflows/build.yml/badge.svg)](https://github.com/PathwayCommons/cpath2-client/actions/workflows/build.yml)

A fluent API Java client/library for Pathway Commons' RESTful [web services](https://www.pathwaycommons.org/pc2/), 
our large integrated BioPAX L3 database, from other Java projects, such as Cytoscape desktop (apps). 

## Use

```
//using Pathway Commons web service URL (default):
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
        <version>1.0.0-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>org.biopax.paxtools</groupId>
        <artifactId>paxtools-core</artifactId>
        <version>6.0.0-SNAPSHOT</version>
    </dependency>
  </dependencies>
```

## See also:
* http://www.biopax.org/paxtools
* [tests](https://github.com/PathwayCommons/cpath2-client/blob/master/src/test/java/cpath/client/CPathClientIT.java) as an example;
  
If you want to use an all-in-one (with dependencies) JAR in your Java/JPype project, get the latest 
cpath2-client-*-fat-jar.jar ([from here](https://oss.sonatype.org/content/groups/public/pathwaycommons/cpath/cpath2-client/)).

## ToDo: 
* Implement cPath2 v14 API v2 (coming soon) methods (only HTTP POST and JSON request body are used in the v2 API)
