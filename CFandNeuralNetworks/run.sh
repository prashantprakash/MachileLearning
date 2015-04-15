a=0

declare -a arr=("-L 0.03  -M 0.1 -N 2 -E 20 -H 2 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.03  -M 0.1 -N 10 -E 20 -H 4,4 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.03  -M 0.2 -N 20 -E 20 -H 4,4,4 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.1  -M 0.4 -N 2 -E 20 -H 5 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.1  -M 0.1 -N 20 -E 20 -H 10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.1  -M 0.2 -N 10 -E 20 -H 10,5 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.1  -M 0.1 -N 50 -E 20 -H 10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.1  -M 0.2 -N 50 -E 20 -H 10,10,10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.1  -M 0.2 -N 100 -E 20 -H 10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.1  -M 0.2 -N 100 -E 20 -H 10,10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.1  -M 0.4 -N 100 -E 20 -H 10,5,2 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.3  -M 0.1 -N 2 -E 20 -H 4 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.3  -M 0.1 -N 20 -E 20 -H 10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.3  -M 0.1 -N 50 -E 20 -H 10,10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.3  -M 0.2 -N 50 -E 20 -H 10,10,10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.3  -M 0.4 -N 50 -E 20 -H 10,5,2 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.3  -M 0.4 -N 100 -E 20 -H 10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.3  -M 0.4 -N 100 -E 20 -H 10,10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.9  -M 0.1 -N 2 -E 20 -H 4 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.9  -M 0.2 -N 10 -E 20 -H 10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.9  -M 0.2 -N 20 -E 20 -H 10,10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.9  -M 0.4 -N 50 -E 20 -H 10,10,10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.9  -M 0.6 -N 50 -E 20 -H 10,5,2 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.9  -M 0.6 -N 100 -E 20 -H 10,10 -B -C -I -t train.arff -T test.arff -o" 
"-L 0.9  -M 0.6 -N 100 -E 20 -H 4,4,4 -B -C -I -t train.arff -T test.arff -o" 
)







for i in "${arr[@]}"
do
java -Xmx2g -cp weka.jar weka.classifiers.functions.MultilayerPerceptron $i > $a
a=`expr $a + 1`
done
