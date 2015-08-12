package ahoCorasick

import scala.collection.mutable

object ahoCorasickCongifgHelper {
  def getBasicConfig(keywordsFileName: String): (mutable.Map[(Int, Char), Int], mutable.Map[Int, mutable.Set[String]], mutable.Map[Int, Int], List[String]) = {
    val keywords = ahoCorasickInstance.generateKeyWords("testdata/" + keywordsFileName)

    val ah = new ahoCorasickInstance
    val tuple1 = ah.generateGoToAndOutput(keywords)
    val tuple2 = ah.generateFailAndOutput(tuple1._1, tuple1._2)
    val goToTable = tuple1._1
    val output = tuple2._2
    val fail = tuple2._1

    return (goToTable, output, fail, keywords)
  }
}
