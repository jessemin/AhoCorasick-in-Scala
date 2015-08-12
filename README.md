# Revisiting Aho-Corasick in Scala
*an aho-corasick string pattern matching algorithm implemented in Scala*

## Introduction 
* Aho-Corasick Algorithm implemented in Scala
* Easy to use
* Strictly based on the original paper in which the algorithm was first introduced
[The Original Paper: ](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.96.4671&rep=rep1&type=pdf)

## How to Use
```Scala
val ahoCorasickConfigTuple = ahoCorasickConfigHelper("keywords.txt")
val goToTable = ahoCorasickConfigTuple._1
val outputTable = ahoCorasickConfigTuple._2
val failTable = ahoCorasickConfigTuple._3
val matchList = ahoCorasickInstance.processAC(goToTable, outputTable, failTable, inputString2PatternMatchComesHere)
```
