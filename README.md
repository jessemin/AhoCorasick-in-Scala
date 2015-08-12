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
val matchList = ahoCorasickInstance.processAC(goToTable, outputTable, failTable, inputStr)
```

### Detailed Usages

#### * Get Keywords List from File

```Scala
* Input: <file_name>: String
* Output: <keywords_list>: List[String]

//Code Body
val keywords = ahoCorasickInstance.generateKeyWords(keywordsFileName)
```

#### * Get goto function table
* Input: <keywords_list>: List[String]
* Output: <tuple_of_goto_and_incomplete_output>: (mutable.Map[(Int, Char), Int],mutable.Map[Int, mutable.Set[String]])

```Scala
val ahInstance = new ahoCorasickInstance
val gotoTable = ahInstance.generateGoToAndOutput(keywordsList)._1
```

- Caution: Create ahoCorasickInstance first!
- Caution: Returned output table is incomplete here. You have to process it one more time. (See the next)

#### * Get complete output table and fail table
* Input: <goto_table>: mutable.Map[(Int, Char), Int], <incomplete_output_table>: mutable.Map[Int, mutable.Set[String]]
* Output: <complete_output_table>: mutable.Map[Int, mutable.Set[String]], <fail_table>: mutable.Map[Int, Int]

```Scala
val ahInstance = new ahoCorasickInstance
val intermediateTuple = ahInstance.generateGoToAndOutput(keywordsList)
val outputAndFailTuple = ahInstance.generateFailAndOutput(intermediateTuple._1, intermediateTuple._2)
val outputTable = outputAndFailTuple._1
val failTable = outputAndFailTuple._2
```
