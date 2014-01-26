# clojure-examples for reading Common Crawl WARC files

Note: this is still a work in progress, but if you use Clojure, please try this simple example and provide feedback.

First, build the parent Java project and make sure to install the generated maven project JAR file into
your local maven repository using:

````````
mvn install:install-file -Durl=file:repo -DpomFile=pom.xml -DgroupId=local -DartifactId=commoncrawl -Dversion=0.0.1 -Dpackaging=jar -Dfile=target/commoncrawl-0.0.1.jar
````````

in the parent directory. Copy a single test WARC file from S3:

````````
aws get aws-publicdatasets/common-crawl/crawl-data/CC-MAIN-2013-48/segments/1386163035819/warc/CC-MAIN-20131204131715-00002-ip-10-33-133-15.ec2.internal.warc.gz CC-MAIN-20131204131715-00002-ip-10-33-133-15.ec2.internal.warc.gz
````````

Then you can run 

````````
lein test
````````


