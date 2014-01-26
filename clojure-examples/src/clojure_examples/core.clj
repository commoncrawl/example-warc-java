(ns clojure-examples.core
  (:import [java.io DataInputStream FileInputStream])
  (:import [java.util.zip GZIPInputStream])
  (:import [edu.cmu.lemurproject WarcRecord WarcHTMLResponseRecord])
  )


(defn single-warc-file []
  (let [input-warc-file "CC-MAIN-20131204131715-00002-ip-10-33-133-15.ec2.internal.warc.gz"
        gz-input-stream (GZIPInputStream. (FileInputStream. input-warc-file))
        in-stream (DataInputStream. gz-input-stream)]
    (defn read-warc-record []
      (let [r (WarcRecord/readNextWarcRecord in-stream)]
        (if (= (.getHeaderRecordType r) "response")
          (let [html-record (WarcHTMLResponseRecord. r)
                uri (.getTargetURI html-record)
                content (.getContentUTF8 (.getRawRecord html-record))]
            (println uri)
            ;;(println content)
            ))))
    (dotimes [n 50] (read-warc-record))))

;; (single-warc-file)