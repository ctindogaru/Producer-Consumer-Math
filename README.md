# Producer-Consumer-Math

Implementation of the producer-consumer problem. There are 4 types of math events:
PRIME - computes the biggest prime number smaller than N  
FACT - computes the biggest number with the factorial smaller than N  
SQUARE - computes the biggest number with the square smaller than N  
FIB - computes the biggest number with Fibonacci value smaller than N  

How do I run it?  
For linux users compile it via:  
javac Main.java Event.java EventCalculator.java EventGenerator.java  
Then run it:  
java Main <queue_dim> <nr_of_rows> <in_file_1> ... <in_file_n>  
queue_dim - the size of the queue  
nr_of_rows - how many events does each file have  
in_file_i - input files  

Example: java Main 5 5 input1.in input2.in  

The input file contains more lines, on each line we have:  
time_interval - how much ms does a generator wait until produces the event  
eveniment - type of event  
N - the number for the event  