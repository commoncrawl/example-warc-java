# Java examples for processing Common Crawl WARC files

Mark Watson 2013/1/16

NOTE: this code is a work in progress and is untested! I suggest waiting a week before trying it (please).

There are two examples for now (more to come):

- ReadWARC - reads a local WARC file that was manually copied from S3 storage to your laptop
- ReadS3Bucket - this should be run on an EC2 instance for fast access to S3

For now, I provide IntelliJ 13 project files to run these examples and al required 3rd party JAR files.
It is TBD to fetch dependencies using maven and get rid of the IntelliJ dependency.

Special thanks to the developers of the edu.cmu.lemurproject package from Carnegie Mellon University. This code
reads WARC files and the source code is included in the src subdirectory.

I have just started experimenting with Common Crawl data. I plan on adding a Hadoop/Elastic MapReduce example
and also examples using other JVM languages like Clojure and JRuby.

## ReadWARC

Assuming that you have the aws command line tools installed, you can list the contents of a crawl using:

````````
aws ls -1 aws-publicdatasets/common-crawl/crawl-data/CC-MAIN-2013-48/  | head -6
````````

You can copy one segment to your laptop (segment files are less than 1 gigabutes) using:

````````
aws get aws-publicdatasets/common-crawl/crawl-data/CC-MAIN-2013-48/segments/1386163035819/warc/CC-MAIN-20131204131715-00002-ip-10-33-133-15.ec2.internal.warc.gz CC-MAIN-20131204131715-00002-ip-10-33-133-15.ec2.internal.warc.gz
````````

## ReadS3Bucket

You can set the maximum number of segment files to process using the **max** argument:

````````
public class ReadS3Bucket {
  static public void process(AmazonS3 s3, String bucketName, String prefix, int max) {
````````

As you can see in the exmple code, I pass the buck and prefix as:

````````
    process(s3, "aws-publicdatasets", "common-crawl/crawl-data/CC-MAIN-2013-20", 2);
````````

After doing a git pull to get these examples on an EC2 instance, build and run using:

````````
ant
java -cp "lib/*:out/production/java_warc" org.commoncrawl.examples.java_warc.ReadS3Bucket
````````

Note, on my test EC2, I changed the java_warc.properties file using:

````````
jdk.home.1.7=/usr/lib/jvm/java-1.6.0-openjdk-1.6.0.0.x86_64
````````

