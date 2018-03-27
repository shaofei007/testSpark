//scalastyle:off
package com.test.sql

import java.sql.Timestamp

import org.apache.spark.sql.Encoder
import org.apache.spark.sql.types._

case class ActorC(val actor_id: Int, val first_name: String,
                  val last_name: String, val last_update: Timestamp)
{
    def + (other : ActorC): ActorC = {
      new ActorC( actor_id + other.actor_id, "", "", null)
    }
}
//  extends Encoder[actor]{
//
//  def + (other : actor): actor = {
//    new actor( actor_id + other.actor_id, "", "", null)
//  }
//
//  override def schema: StructType =  {
//
//    var fields = new Array[StructField](4)
//
//    val actor_id_type = new StructField(
//      "actor_id",
//      IntegerType,
//      true)
//
//    fields(0) = actor_id_type
//
//    val first_name_type = new StructField(
//      "first_name",
//      StringType,
//      true)
//
//    fields(1) = first_name_type
//
//    val last_name_type = new StructField(
//      "last_name",
//      StringType,
//      true)
//
//    fields(2) = last_name_type
//
//    val last_update_type = new StructField(
//      "last_update",
//      TimestampType,
//      true)
//
//    fields(3) = last_update_type
//
//    StructType.apply(fields)
//  }
//
//  override def clsTag: ClassManifest[actor] = {
//    ClassManifest[actor]
//  }
//}
