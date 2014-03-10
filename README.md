# Java and Clojure examples for processing Common Crawl WARC files

Mark Watson 2013/1/26

There are two Java examples and one Clojure example for now (more to come):

- ReadWARC - reads a local WARC file that was manually copied from S3 storage to your laptop
- ReadS3Bucket - this should be run on an EC2 instance for fast access to S3
- clojure-examples/src/clojure-examples/core.clj - reads a local WARC file that was manually copied from S3 storage to your laptop

A JDK 1.7 or later is required (JDK 1.6 will not work).

Special thanks to the developers of the edu.cmu.lemurproject package from Carnegie Mellon University. This code
reads WARC files and the source code is included in the src subdirectory.

I have just started experimenting with Common Crawl data. I plan on adding a Hadoop/Elastic MapReduce example
and also more examples using other JVM languages like Clojure and JRuby.

## ReadWARC

Assuming that you have the aws command line tools installed, you can list the contents of a crawl using:

````````
aws ls -1 aws-publicdatasets/common-crawl/crawl-data/CC-MAIN-2013-48/  | head -6
````````

You can copy one segment to your laptop (segment files are less than 1 gigabytes) using:

````````
aws get aws-publicdatasets/common-crawl/crawl-data/CC-MAIN-2013-48/segments/1386163035819/warc/CC-MAIN-20131204131715-00002-ip-10-33-133-15.ec2.internal.warc.gz CC-MAIN-20131204131715-00002-ip-10-33-133-15.ec2.internal.warc.gz
````````

Then run this example using:

````````
mvn install
mvn exec:java -Dexec.mainClass=org.commoncrawl.examples.java_warc.ReadWARC
````````


## ReadS3Bucket

You can set the maximum number of segment files to process using the **max** argument:

````````
public class ReadS3Bucket {
  static public void process(AmazonS3 s3, String bucketName, String prefix, int max) {
````````

As you can see in the example code, I pass the bucket and prefix as:

````````
    process(s3, "aws-publicdatasets", "common-crawl/crawl-data/CC-MAIN-2013-20", 2);
````````

Note, using the Common Crawl AMI (I run it on a Medium EC2 instance), I installed JDK 1.7 (required for
the edu.cmu.lemurproject package):

````````
sudo yum install java-1.7.0-openjdk-devel.x86_64
````````

TODO: In addition to installing Java 7, you also need to configure it 
using 

`sudo alternatives --config javac`
`sudo alternatives --config java`

TODO: Maven needs to be installed and it's not available through yum 
without some gymnastics.

After cloning the Github repository to get these examples on an EC2 instance:


```
git clone https://github.com/commoncrawl/example-warc-java.git
cd example-warc-java
```

build and run using:

````````
mvn install
mvn exec:java -Dexec.mainClass=org.commoncrawl.examples.java_warc.ReadS3Bucket
````````

Note: I also tested this using a micro EC2 instance. The time to process two gzipped segment files
(of size a little less than 1 gigabyte each) is about 45 seconds on a micro EC2 instance.

## Clojure Examples

You need to install the commoncrawl JAR file in your local maven repository:

````````
mvn install:install-file -Durl=file:repo -DpomFile=pom.xml -DgroupId=local -DartifactId=commoncrawl -Dversion=0.0.1 -Dpackaging=jar -Dfile=target/commoncrawl-0.0.1.jar
````````

Then  you can:

````````
cd clojure-examples
lein deps
lein test
````````

## License 

This code is licensed under the Apache 2 license.  Please give back to
Common Crawl if you found it useful.


