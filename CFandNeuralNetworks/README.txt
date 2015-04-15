#################################################
#	Author - PRASHANT PRAKASH		#
# 						#	
#	Course -> Machine Learning Cs6375	#
#						#	
#	Purpose -> Assignment 3			#
# 						#
#	No of Java Files ->11			#
#						#	
#################################################

Folder Struture: 

	MLAssignment3|
		      src|
			  - Dictionary.java
			  - FileDataSource.java
			  - Input.java
			  - PerceptronImpl.java
			  - RunCollab.java
			  - StopWords.java
			  - RunPerceptron.java
			  - Similarity.java
			  - Texttoarff.java
			  - User.java
			  - RunCollab.java
 
1. Unzip the folder containing code 
unzip MLAssignment3.zip 
2. Compile all java Files 
javac MLAssignment3/src/*.java
3. Change Directory to the src folder 
cd MLAssignment3/src/

// running Perceptron
4. Run the class having main function (PerceptronImpl)
Usage of this class:

java PerceptronImpl p1 p2 p3 p4 p5 p6 p7
p1: Absolute path of folder containing spam train folder 
p2: Absolute path of folder containing ham train folder
p3: Absolute path of folder containing spam test folder
p4: Aboslute path of folder containing ham test folder 
p5: learning rate
p6: number of iterations
p6: {yes or no} // to include or exclude stopwrods yes-> exclude stopwords.


Sample Commands: 

java PerceptronImpl /home/004/p/px/pxp141730/train/spam/ /home/004/p/px/pxp141730/train/ham/  /home/004/p/px/pxp141730/test/spam/ /home/004/p/px/pxp141730/test/ham/ .1 10 no

java PerceptronImpl /home/004/p/px/pxp141730/train/spam/ /home/004/p/px/pxp141730/train/ham/  /home/004/p/px/pxp141730/test/spam/ /home/004/p/px/pxp141730/test/ham/ .1 10 yes



// running script to convert to arff file (Texttoarff)
Note: This program handles folder Implementation not ZIP Implemenation. THe folder should not be ZIPPED.
Also when you provide the root folder. The current implementation assumes that the folder has subfolder as ham and spam. If there is anything else as subfolder then the program won't work. 
5. java Texttoarff p1 p2 
p1: Absolute path to train folder which has spam and ham as subfolder 
p2: Absolute path to test folder which has spam and ham as subfolder 


Also the filename for train and test arff is hardcoded in the program. So if you want to retain your previous obtained arff file after running this program move obtained arff to different location and then run again for different input.
Currently realtion name is also hardcoded.

Once you run this program you can find arff files in the same working dirctory.
do ls -l to check files . You can see two files train.arff and test.arff 
Also this works with Linux System only because of path problem "/" "\" .

sample Commands:

java Texttoarff /home/004/p/px/pxp141730/train/ /home/004/p/px/pxp141730/test/
 



// running collaborative Filtering 

6. java p1 p2 p3 

P1: Absolute path for training set 
p2: Absolute path for testing set 
p3: Number of test set to consider (if 0 then it will consider whole test data set otherwise it will consider n number if testdata)

Sample Commands: 

 // this will run for only with 100 test data set 
 java CollabFiltering /home/004/p/px/pxp141730/TrainingRatings.txt /home/004/p/px/pxp141730/TestingRatings.txt 100  

// this will run for whole test data set 

java CollabFiltering /home/004/p/px/pxp141730/TrainingRatings.txt /home/004/p/px/pxp141730/TestingRatings.txt 0

if you get heap memory error increase your heap memory while running program.


 