package ahoCorasick
/*
 * Implementation of Aho-Corasick Pattern Matching Algorithm in Scala
 */
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object ahoCorasickInstance {
  def processAC(inputString: String, keywords: List[String]): List[String] = {
    val acInstance = new ahoCorasickInstance
    acInstance.generateGoToAndOutput(keywords)
    acInstance.generateFailAndOutput
    val matchList = acInstance.executeAutomata(inputString)
    return matchList
  }
}

class ahoCorasickInstance {
  var newState = 0
  val goToTable = mutable.Map.empty[(Int, Char), Int]
  val output = mutable.Map.empty[Int, mutable.Set[String]]
  val fail = mutable.Map.empty[Int, Int]
  val nodeSet = mutable.Set.empty[Char]
  val nonZeroNodeSet = mutable.Set.empty[Char]

  def enter(keyword: String) = {
    var state = 0
    var j = 0
    val m = keyword.length
    while (goToTable.getOrElse((state, keyword.charAt(j)), -1) != -1 ){
      state = goToTable.getOrElse((state, keyword.charAt(j)), -1)
      j = j + 1
    }
    for(p <- j until m){
      newState = newState + 1
      goToTable.update((state, keyword.charAt(p)), newState)
      state = newState
    }
    val currentMatchedOutput: mutable.Set[String] = output.getOrElse(state, mutable.Set.empty[String])
    currentMatchedOutput.add(keyword)
    output.update(state, currentMatchedOutput)
  }
  def generateGoToAndOutput(inputs: List[String]) = {
    val numInputs = inputs.length
    for(i <- 0 until numInputs) enter(inputs.apply(i))
    goToTable.keySet.foreach({ key =>
      nodeSet.add(key._2)
    })
    nodeSet.foreach({ node =>
      if(!goToTable.keySet.contains((0, node))) goToTable.update((0, node), 0)
      else nonZeroNodeSet.add(node)
    })
  }

  def generateFailAndOutput = {
    val queue = mutable.Queue.empty[Int]

    nonZeroNodeSet.foreach({ nonZero =>
      val s = goToTable.getOrElse((0, nonZero), 0)
      queue.enqueue(s)
      fail.update(s, 0)
    })
    while (!queue.isEmpty){
      val r = queue.dequeue()
      val aSet = mutable.Set.empty[Char]
      nodeSet.foreach({ node =>
        if(goToTable.getOrElse((r,node), -1) != -1) aSet.add(node)
      })
      aSet.foreach({ a =>
        val s = goToTable.getOrElse((r,a),0)
        queue.enqueue(s)
        var t_state = fail.getOrElse(r,0)
        while(goToTable.getOrElse((t_state,a),-1) == -1) t_state = fail.getOrElse(t_state, 0)
        fail.update(s,goToTable.getOrElse((t_state, a),0))
        val t_outputSet1 = output.getOrElse(s, mutable.Set.empty[String])
        val t_outputSet2 = output.getOrElse(fail.getOrElse(s, 0), mutable.Set.empty[String])
        val res = t_outputSet1 ++ t_outputSet2
        if(!res.isEmpty )output.update(s, res)
      })
    }
  }
  def executeAutomata(inputString: String): List[String] ={
    val results = new ListBuffer[String]()
    var state = 0
    val inputLength = inputString.length
    for(i <- 0 until inputLength){
      while(goToTable.getOrElse((state,inputString.charAt((i))),-1) == -1 && state!=0) state = fail.getOrElse(state, 0)

      state = goToTable.getOrElse((state, inputString.charAt(i)),0)
      if(!output.getOrElse(state, mutable.Set.empty[String]).isEmpty){
        output.getOrElse(state, mutable.Set.empty[String]).foreach({ result =>
          results += result
        })
      }
    }
    return results.toList
  }
}