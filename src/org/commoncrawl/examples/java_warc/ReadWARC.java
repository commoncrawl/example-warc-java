// based on an example from http://boston.lti.cs.cmu.edu/clueweb09/wiki/tiki-index.php?page=Working+with+WARC+Files

package org.commoncrawl.examples.java_warc;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import edu.cmu.lemurproject.WarcRecord;
import edu.cmu.lemurproject.WarcHTMLResponseRecord;

public class ReadWARC {

  public static void main(String[] args) throws IOException {
    String inputWarcFile="/Users/markw/ccrawl/CC-MAIN-20131204131715-00002-ip-10-33-133-15.ec2.internal.warc.gz";
    GZIPInputStream gzInputStream=new GZIPInputStream(new FileInputStream(inputWarcFile));
    DataInputStream inStream=new DataInputStream(gzInputStream);

    WarcRecord thisWarcRecord;
    while ((thisWarcRecord=WarcRecord.readNextWarcRecord(inStream))!=null) {
      if (thisWarcRecord.getHeaderRecordType().equals("response")) {
        WarcHTMLResponseRecord htmlRecord=new WarcHTMLResponseRecord(thisWarcRecord);
        String thisTargetURI=htmlRecord.getTargetURI();
        String thisContentUtf8 = htmlRecord.getRawRecord().getContentUTF8();
        System.out.println("$$$$$$$$$$ " + thisTargetURI + "\n\n" + thisContentUtf8 + "\n");
      }
    }
    inStream.close();
  }
}
