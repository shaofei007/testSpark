//scalastyle:off

package com.test.sql

import java.sql.Timestamp
import java.util.Properties

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object testSQLJSON {
  def main(argv : Array[String]): Unit = {
    val ss = SparkSession.builder().appName("sqltest").master("local[4]").getOrCreate()

    import ss.implicits._

    val someSchema = List(
      StructField("name", StringType, true),
      StructField("word", StringType, true)
    )

    val list = Seq(
      Row("bob", "{\"name\":\"a\", \"age\":9}"),
      Row("maria", "{\"name\":\"ab\", \"age\":19}"),
      Row("sue", "{\"name\":\"ad\", \"age\":29}")
    )
    val peopleDF = ss createDataFrame(
      ss.sparkContext.parallelize(list),
      StructType(someSchema)
    )

    val schemaExample = new StructType()
      .add("name", StringType)
      .add("age", StringType)

    peopleDF.withColumn(
      "nameXXX",
      from_json(col("word"),schemaExample)
    ).show()



//    stud_scoreDF.show()
  }
}
