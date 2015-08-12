# Revisiting Aho-Corasick in Scala
*an aho-corasick string pattern matching algorithm implemented in Scala*

## Introduction 
    Aho-Corasick Algorithm has been one of the most popular, widely-used string pattern matching algorithm
    since its first publication in the paper, "Efficient String Mathcing: An Aid to Bibliographic Search." 
    C, C++, and Java implementations can be easily found on the web. 
    However, it is hard to find a Scala version that is easy to use. 
    In response to that, I implemented the Aho-Corasick algorithm strictly based on the original paper in which the algorithm was first and formally presented. 
    Hence, I hope many Scala users can take advantage of my implementation, saving huge amount of time reading papers or searching books.
[The Original Paper]: (http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.96.4671&rep=rep1&type=pdf)

## How to Use
```Scala
val ahoCorasickConfigTuple = ahoCorasickConfigHelper("keywords.txt")
val goToTable = ahoCorasickConfigTuple._1
val outputTable = ahoCorasickConfigTuple._2
val failTable = ahoCorasickConfigTuple._3
val matchList = ahoCorasickInstance.processAC(goToTable, outputTable, failTable, inputString2PatternMatchComesHere)
```
