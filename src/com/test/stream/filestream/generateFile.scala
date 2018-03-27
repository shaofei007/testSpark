//scalastyle:off
package com.test.stream.filestream

import java.io.{File, PrintWriter}

object generateFile {
  def main(argv : Array[String]): Unit = {
    val path  = "F:\\SPARK\\tmp\\filestream\\"
    val delFile = new File( path)
    delFile.delete()
    delFile.mkdir()
    var index  = 0
    do {
      val writer = new PrintWriter(new File( path + "test" + index +".txt")) //当前工程根目录下
        for(i <- 1 to 10000)
          writer.println("hello " + i)
      writer.close()
      index += 1
      Thread.sleep( 10 * 1000)
    } while( true)

  }
}
