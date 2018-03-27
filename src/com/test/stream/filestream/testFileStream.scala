//scalastyle:off
package com.test.stream.filestream

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

object testFileStream {
  def main(argv : Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("testFileStream")

//    val ss = SparkSession.builder().master("local[2]").appName("testFileStream").getOrCreate()
    val ssc = new StreamingContext( conf, Seconds(5))

    val fileStream = ssc.textFileStream("F:\\SPARK\\tmp\\filestream")
    fileStream.map( s=> s.split(" ")).foreachRDD(  s => println( s.count()))

    ssc.start
    ssc.awaitTermination()

  }
}
