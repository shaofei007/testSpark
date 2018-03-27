//scalastyle:off

package com.test.sql

import java.sql.Timestamp
import java.util.Properties

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

case class ActorA(val actor_id: Int, val first_name: String,
                  val last_name: String, val last_update: Timestamp)
object testSQL {
  def main(argv : Array[String]): Unit = {
    val ss = SparkSession.builder().appName("sqltest").master("local[4]").getOrCreate()

    import ss.implicits._

    val properties = new Properties()
    properties.put("user","root")
    properties.put("password","root")
    val url = "jdbc:mysql://127.0.0.1:3306/sakila?useUnicode=true&characterEncoding=gbk&serverTimezone=UTC"; //  +
     //  "&zeroDateTimeBehavior=convertToNull"
     val stud_scoreDF = ss.sqlContext.read.jdbc(url,"actor",properties).toDF()
    stud_scoreDF.show()
//    val stud_scoreDF = stud_scoreDF.as[ActorC]
    println (stud_scoreDF.count())
//    stud_scoreDF.describe("actor_id", "first_name").show
//    stud_scoreDF.foreachPartition( x => {
//      println("--------------------" )
//      x.foreach( l =>println(l))
//    })
//    val result = stud_scoreDF.reduce{ (x, y) => {
//      x + y
//    }}
//    println(result)
    stud_scoreDF.agg(countDistinct($"last_name", $"first_name").alias("xx")).show() //  $"countDistinct",
    stud_scoreDF.printSchema()

    val someSchema = List(
      StructField("name", StringType, true),
      StructField("word", ArrayType(StringType, true), true)
    )

    val list = Seq(
      Row("bob", Array("red", "blue")),
      Row("maria", Array("green", "red","red")),
      Row("sue", Array("black"))
    )
    val peopleDF = ss createDataFrame(
      ss.sparkContext.parallelize(list),
      StructType(someSchema)
    )

    peopleDF.select(
      col("name"),
      explode(col("word")).as("color")
    ).show()



//    stud_scoreDF.show()
  }
}
