package org.commoncrawl.examples.java_warc;

/**
 * author: Mark Watson
 */

/**
 * callback interface for handling WARC record data
 */
public interface IProcessWarcRecord {
  public void process(String url, String content);
}
