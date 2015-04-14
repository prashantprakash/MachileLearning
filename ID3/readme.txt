This project contains the parent Folder as ID3 

Project Folder struture : 

ID3|
   | src |
	 | com |
	       | pp |
		    | ml | 
			 | assignment1 |
				        - Input.java (To load Data)
					- DTNode.java (DT Node)
					- DT.java (Implemenation)
					- DTImpl.java (entry of program)

Package Name is : com.pp.ml.assignment1


1.Command to unzip 
unzip ID3.zip 

2. For compiling All JAVA Files use the command 

javac ID3/src/com/pp/ml/assignment1/*.java 

Where ID3 folder should be in your current Working Directory

3.Now give command to reach src folder of the code if ID3 is in your 
current working directory then give command : cd ID3/src/

4. Now give command to run the application :

java com/pp/ml/assignment1/DTImpl

5. To understand the usage of program give command : 

java com/pp/ml/assignment1/DTImpl --usage

6. Now for path you have to provide the absolute path where the datafiles resides

for ex. 

/home/004/p/px/pxp141730/training_set.csv  // absolute path for training set
/home/004/p/px/pxp141730/validation_set.csv  // absolute path for validation set
/home/004/p/px/pxp141730/test_set.csv  // absolute path fr test set


sample commands: 

These all commands should be run from src folder of the project

java com/pp/ml/assignment1/DTImpl 10 20 /home/004/p/px/pxp141730/training_set.csv /home/004/p/px/pxp141730/validation_set.csv /home/004/p/px/pxp141730/test_set.csv no

java com/pp/ml/assignment1/DTImpl 100 20 /home/004/p/px/pxp141730/training_set.csv /home/004/p/px/pxp141730/validation_set.csv /home/004/p/px/pxp141730/test_set.csv yes
