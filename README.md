# Revisiting Aho-Corasick in Scala
*aho-corasick string pattern matching algorithm in Scala*

## Introduction 
* Scala implementation of Aho-Corasick Algorithm
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
val matchList = ahoCorasickInstance.processAC(goToTable, outputTable, failTable, inputStr)
```

### Detailed Usages

#### - Get Keywords List from File

```Scala
* Inputs: 1) <file_name>: String
* Output: 2) <keywords_list>: List[String]

//Code Body
val keywords = ahoCorasickInstance.generateKeyWords(keywordsFileName)
```

#### - Get goto function table

```Scala
* Inputs: 1) <keywords_list>: List[String]
* Output: 1) <tuple_of_goto_and_incomplete_output>: (mutable.Map[(Int, Char), Int],mutable.Map[Int, mutable.Set[String]])

//Code Body
val ahInstance = new ahoCorasickInstance
val gotoTable = ahInstance.generateGoToAndOutput(keywordsList)._1

//Cautions
- Create ahoCorasickInstance first!
- Returned output table is incomplete here. You have to process it one more time. (See the next)
```

#### - Get complete output table and fail table

```Scala
* Inputs: 1) <goto_table>: mutable.Map[(Int, Char), Int], 
          2) <incomplete_output_table>: mutable.Map[Int, mutable.Set[String]]
* Output: 1) <complete_output_table>: mutable.Map[Int, mutable.Set[String]]
          2) <fail_table>: mutable.Map[Int, Int]

//Code Body
val ahInstance = new ahoCorasickInstance
val notYetTuple = ahInstance.generateGoToAndOutput(keywordsList)
val outputAndFailTuple = ahInstance.generateFailAndOutput(notYet._1, notYet._2)
val outputTable = outputAndFailTuple._1
val failTable = outputAndFailTuple._2
```
