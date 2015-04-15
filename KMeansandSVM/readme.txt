#################################################
#	Author - PRASHANT PRAKASH		#
# 						#	
#	Course -> Machine Learning Cs6375	#
#						#	
#	Purpose -> Assignment 4			#
# 						#
#	No of Java Files ->1			#
#						#	
#################################################


Folder Struture: 

	MLAssignment4|
		      src|
			  - Kmeans.java


1. Unzip the folder containing code
unzip MLAssignment4.zip

2. Compile all java Files
javac MLAssignment4/src/*.java

3.3. Change Directory to the src folder 
cd MLAssignment4/src/

// running Kmeans
4. Run the class having main function (PerceptronImpl)
Usage of this class:

java Kmeans p1 p2 p3

p1: Input path for input image
p2: K for kmeans Clustering
p3: output path for the output image

Sample Commands: 

java KMeans /home/004/p/px/pxp141730/Koala.jpg 2 Koalaout2.jpg

java KMeans /home/004/p/px/pxp141730/Koala.jpg 5 Koalaout5.jpg

java KMeans /home/004/p/px/pxp141730/Penguins.jpg 2 Penguinsout2.jpg

java KMeans /home/004/p/px/pxp141730/Penguins.jpg 10 Penguinsout10.jpg

