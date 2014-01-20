package org.commoncrawl.examples.java_warc;

/**
 * author: Mark Watson
 */

/**
 * a sample callback class for handling WARC record data by implementing IProcessWarcRecord interface
 */
public class SampleProcessWarcRecord implements IProcessWarcRecord {
  @Override
  public void process(String url, String content) {
    System.out.println("url: " + url);
    System.out.println("content: " + url + "\n\n" + content + "\n");
  }
}
