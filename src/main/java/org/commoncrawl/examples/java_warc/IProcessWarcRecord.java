package org.commoncrawl.examples.java_warc;

/**
 * author: Mark Watson
 */

/**
 * callback interface for handling WARC record data
 */
public interface IProcessWarcRecord {
  public void process(String url, String content);
  public void done();  // called once when there is no more data to be processed
}
