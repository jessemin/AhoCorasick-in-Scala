# Revisiting Aho-Corasick in Scala
*an aho-corasick string pattern matching algorithm implemented in Scala*

## Introduction 
* Aho-Corasick Algorithm implemented in Scala
* Easy to use
* Strictly based on the original paper in which the algorithm was first introduced

*[The Original Paper <Efficient String Matching: An Aid to Bibliographic Search>](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.96.4671&rep=rep1&type=pdf)*

## How to Use

### Easy One-block Usage
1. Generate basic configuration for the algorithm
2. Extract each value from the attained tuple from the step1
3. Get the list of all keywords found in input string you entered 

```Scala
val ahoCorasickConfigTuple = ahoCorasickConfigHelper("keywords.txt")
val goToTable = ahoCorasickConfigTuple._1
val outputTable = ahoCorasickConfigTuple._2
val failTable = ahoCorasickConfigTuple._3
val matchList = ahoCorasickInstance.processAC(goToTable, outputTable, failTable, inputString2PatternMatchComesHere)
```
