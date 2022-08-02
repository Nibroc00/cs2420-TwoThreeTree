
public class Node {
    Integer values[];
    Node children[];
    int childrenNum; //Possible values after evalution: 0, 2, 3, 4
    int valuesNum; //Possible values after evaluation: 0-3
    Node() {
        values = new Integer[3];
        children = new Node[4];
        childrenNum = 0;
        valuesNum = 0;
    }
    Node(int a) {
        values = new Integer[3];
        values[0] = a;
        children = new Node[4];
        childrenNum = 0;
        valuesNum = 1;
    }
    void printValues() {
    }
}