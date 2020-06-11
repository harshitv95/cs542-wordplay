# CSX42: Assignment 1
## Name: Harshit Vadodaria

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in wordPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile wordPlay/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile wordPlay/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile wordPlay/src/build.xml run -Dinput="input.txt" -Doutput="output.txt" -Dmetrics="metrics.txt"

Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description:

A Program that reads an input file, rotates each word based on its index, and writes it to an output file.
During this, various statistics are collected off the words, like word count, sentence count, character count.
These are used, at the end, to calculate certain metrics like AVG\_WORDS\_PER_SENTENCE etc.

The Driver class (Main class) controls (or rather, Drives) the whole program.
It performs validation of input file, and output and metrics filenames,
creates instances of various classes used in this context, including:
ValidationHelper (contains various methods that help with validation/assertion),
FileProcessor (used to read contents of input file, word by word),
WordRotator (contains logic to rotate words by provided index),
MetricsCalculator (calculates various metrics registered through the Driver),
Results (used to write content to a file and stdout)

The `processWord` method of the `MetricsCalculator` class is to be called for each word.
It gathers various stats (configurable in the `enum wordPlay.metrics.helper.Stat`),
which can later be used to calculate required metrics. This class lets the user 
configure the needed metrics, by calling the `registerCalculator` method on the instance
of the `MetricsCalculator` class, which accepts an instance of an implementation of the
interface `MetricCalculator`, which contains a `calculate` method to be overridden.
Notice the generic type parameter of the MetricCalculator interface to be registered,
should be the same as the generic type parameter of the MetricsCalculator class.
This is to maintain type uniformity across the registered instances.

The `Results` class is used to write something to files and stdout. The constructor of
this class accepts a `String`, filename, and the `Results` instance will write to this file
through the `printToFile` method, and to stdout through the `printToStdOut` method. Thus 2
instances of this class will be needed, one for writing to output file, and another for
metrics file.

The program simply reads words from the input file (with the help of `FileProcessor`)
in a loop, and rotates each word and prints it to stdout as well as to the output file.
The loop terminates when there are no words left. This is when the `calculate` method of the
`MetricsCalculator` class' instance is called, which gives the required String containing
the calculated metric based on the registered calculators.

Various boundary conditions are handled, like:
1. Missing input file - through `ValidationHelper`
2. Empty input file - by checking if the number of words (as in collected stats in
`MetricsCalculator`) is 0
3. Empty line - through the `ValidationHelper`, by checking if the word string is null or empty
4. Words containing characters other than alphabets and numbers - through `ValidationHelper`



-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: June 10, 2020


