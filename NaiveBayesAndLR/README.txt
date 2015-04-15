#################################################
#	Author - PRASHANT PRAKASH		#
# 						#	
#	Course -> Machine Learning Cs6375	#
#						#	
#	Purpose -> Assignment 2			#
# 						#
#	No of Java Files ->6 			#
#						#	
#################################################

Folder Struture: 

	MLAssignment2|
		      src|
			  - Dictionary.java
			  - FileDataSource.java
			  - Porter.java
			  - Prior.java
			  - SpamHamImpl.java
			  - StopWords.java

1. Unzip the folder containing code 
unzip MLAssignment2.zip 
2. Compile all java Files 
javac MLAssignment2/src/*.java
3. Change Directory to the src folder 
cd MLAssignment2/src/
4. Run the class having main function (SpamhamImpl)
Usage of this class:

Java SpamHamImpl p1 p2 p3 p4 p5 p6 p7 
p1: Absolute path of folder containing spam train folder 
p2: Absolute path of folder containing ham train folder
p3: Absolute path of folder containing spam test folder
p4: Aboslute path of folder containing ham test folder 
p5: Number of iterations
p5: eta value
p6: lambda value


Sample Commands: 

java SpamHamImpl /home/004/p/px/pxp141730/train/spam/ /home/004/p/px/pxp141730/train/ham/ /home/004/p/px/pxp141730/test/spam/ /home/004/p/px/pxp141730/test/ham/ 100 .01 1


java SpamHamImpl /home/004/p/px/pxp141730/train/spam/ /home/004/p/px/pxp141730/train/ham/ /home/004/p/px/pxp141730/test/spam/ /home/004/p/px/pxp141730/test/ham/ 200 .01 1

java SpamHamImpl /home/004/p/px/pxp141730/train/spam/ /home/004/p/px/pxp141730/train/ham/ /home/004/p/px/pxp141730/test/spam/ /home/004/p/px/pxp141730/test/ham/ 100 .01 5


