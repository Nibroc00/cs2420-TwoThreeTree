//Corbin Park
//04/16/20

import java.util.*;


public class TwoThreeTree {
    Node root = new Node();

    TwoThreeTree() {

    }

    void reorderNode(Node a) {
        int dummy;
        if (a.valuesNum == 0) {

        }
        else if (a.valuesNum > 0) {
            boolean moved = true;
            while(moved) {
                moved = false;
                for (int n = 0; n < 2; n++) {
                    if (a.values[n] == null && a.values[n + 1] != null) {
                        dummy = a.values[n + 1];
                        a.values[n + 1] = a.values[n];
                        a.values[n] = dummy;
                        moved = true;
                    }
                    else if (a.values[n] != null && a.values[n + 1] != null) {
                        if (a.values[n + 1] < a.values[n] ) {
                            dummy = a.values[n + 1];
                            a.values[n + 1] = a.values[n];
                            a.values[n] = dummy;
                            moved = true;
                        }
                    }
                }
            }
        }
        /*if (a.childrenNum > 0) {
            for (int n = 0; n < 2; n++) {
                if (a.children.value[1] < a.children[0] || a.chrildren[0] == null) {
                    dummy = a.values[1];
                    a.values[1] = a.value[0];
                    a.value[0] = dummy;
                }
                if (a.values[2] < a.values[1] || a.values[1] == null) {
                    dummy = a.values[2];
                    a.values[2] = a.value[1];
                    a.value[1] = dummy;
                }
            }
        }*/
    }


    private boolean insert(int val, Node base) throws Exception {
        if (base.childrenNum == 0) { //if node is leaf node
            if (base.valuesNum == 0) {
                base.values[2] = val;
                base.valuesNum++;
                reorderNode(base);
                return false;
            }
            if (base.valuesNum == 1) {
                base.values[1] = val;
                base.valuesNum++;
                reorderNode(base);
                return false;
            }
            if (base.valuesNum == 2) {
                base.values[2] = val;
                base.valuesNum++;
                reorderNode(base);
                return true;
            }
        }
        else if (base.childrenNum == 2) { //if node is not a leaf node and only 2 children
            if (val < base.values[0]) {
                if (insert(val, base.children[0])) { //if i need to pull
                    base.values[2] = base.children[0].values[1]; //pull value from left node
                    base.valuesNum++; //increment current node's values count
                    base.children[0].values[1] = null; //remove pulled value from left node
                    base.children[0].valuesNum--; //decrement child node's values count
                    reorderNode(base); //reordered node
                    base.children[2] = base.children[1]; //moves right child node over one
                    base.children[1] = new Node(base.children[0].values[2]); //creates a new node to put the left node's right value into (in right child node's old spot)
                    base.childrenNum++; //increment current node's children count
                    base.children[0].values[2] = null; //removed value from left node
                    base.children[0].valuesNum--;
                    if (base.children[0].childrenNum == 4) { //if node I am pulling from has too many children: Reassign the children
                        base.children[1].children[0] = base.children[0].children[3];
                        base.children[1].children[1] = base.children[0].children[4];
                        base.children[1].childrenNum++;
                        base.children[1].childrenNum++;
                        base.children[0].children[3] = null;
                        base.children[0].children[4] = null;
                        base.children[0].childrenNum--;
                        base.children[0].childrenNum--;
                    }
                }
            }
            if (val > base.values[0]) {
                if (insert(val, base.children[1])) { //if I need to pull
                    base.values[2] = base.children[1].values[1]; //pull value from right node
                    base.valuesNum++; //increment current node's values count
                    base.children[1].values[1] = null; //remove pulled value from right node
                    base.children[1].valuesNum--; //decrement child node's value count
                    reorderNode(base); //reordered node
                    base.children[2] = new Node(base.children[1].values[2]); //creates a new node to put the right node's right value into (in right child node's old spot)
                    base.childrenNum++; //increment current node's children count
                    base.children[1].values[2] = null; //removed value from right node
                    base.children[1].valuesNum--; //decrement child node's value count
                    if (base.children[1].childrenNum == 4) { //if node I am pulling from has too many children: Reassign the children
                        base.children[2].children[0] = base.children[1].children[3];
                        base.children[2].children[1] = base.children[1].children[4];
                        base.children[2].childrenNum++;
                        base.children[2].childrenNum++;
                        base.children[1].children[3] = null;
                        base.children[1].children[4] = null;
                        base.children[1].childrenNum--;
                        base.children[1].childrenNum--;
                    }
                }
            }
            return false;
        }
        else if (base.childrenNum == 3) { //if node is not a leaf node and has 3 children
            if (val > base.values[0] && val < base.values[1]) {
                if(insert(val, base.children[1])) { //if i need to pull
                    base.values[2] = base.children[1].values[1]; //pull value from middle node
                    base.valuesNum++; //increment current node's values count
                    base.children[1].values[1] = null; //remove pulled value from middle node
                    base.children[1].valuesNum--;//decrement child node's value count
                    reorderNode(base); //reordered node
                    base.children[3] = base.children[2]; //moves right child node over one
                    base.children[2] = new Node(base.children[1].values[2]); //creates a new node to put the middle node's right value into (in right child node's old spot)
                    base.childrenNum++; //increment current node's children count
                    base.children[1].values[2] = null; //removed value from middle node
                    base.children[1].valuesNum--; //decrement child node's value count
                    if (base.children[1].childrenNum == 4) { //if node I am pulling from has too many children: Reassign the children
                        base.children[2].children[0] = base.children[1].children[3];
                        base.children[2].children[1] = base.children[1].children[4];
                        base.children[2].childrenNum++;
                        base.children[2].childrenNum++;
                        base.children[1].children[3] = null;
                        base.children[1].children[4] = null;
                        base.children[1].childrenNum--;
                        base.children[1].childrenNum--;
                    }
                }
            }
            if (val < base.values[0]) {
                if(insert(val, base.children[0])) {
                    base.values[2] = base.children[0].values[1]; //pull value from left node
                    base.valuesNum++; //increment current node's values count
                    base.children[0].values[1] = null; //remove pulled value from left node
                    base.children[0].valuesNum--; //decrement child node's value count
                    reorderNode(base); //reordered node
                    base.children[3] = base.children[2]; //moves right node over one
                    base.children[2] = base.children[1]; //moves middle node over one
                    base.children[1] = new Node(base.children[0].values[2]); //creates a new node to put the left node's right value into (in middle child node's old spot)
                    base.childrenNum++; //increment current node's children count
                    base.children[0].values[2] = null; //removed value from middle node
                    base.children[0].valuesNum--; //decrement child node's value count
                    if (base.children[0].childrenNum == 4) { //if node I am pulling from has too many children: Reassign the children
                        base.children[1].children[0] = base.children[0].children[3];
                        base.children[1].children[1] = base.children[0].children[4];
                        base.children[1].childrenNum++;
                        base.children[1].childrenNum++;
                        base.children[0].children[3] = null;
                        base.children[0].children[4] = null;
                        base.children[0].childrenNum--;
                        base.children[0].childrenNum--;
                    }
                }
            }
            if (val > base.values[1]) { //-------------------------
                if(insert(val, base.children[2])) {
                    base.values[2] = base.children[2].values[1]; //pull value from right node
                    base.valuesNum++; //increment current node's values count
                    base.children[2].values[1] = null; //remove pulled value from right node
                    base.children[2].valuesNum--;//decrement child node's value count
                    reorderNode(base); //reordered node
                    base.children[3] = new Node(base.children[2].values[2]); //creates a new node to put the right node's left value into (in right child node's old spot)
                    base.childrenNum++; //increment current node's children count
                    base.children[2].values[2] = null; //removed value from right node
                    base.children[2].valuesNum--; //decrement child node's value count
                    if (base.children[2].childrenNum == 4) { //if node I am pulling from has too many children: Reassign the children
                        base.children[3].children[0] = base.children[0].children[2];
                        base.children[3].children[1] = base.children[0].children[3];
                        base.children[3].childrenNum++;
                        base.children[3].childrenNum++;
                        base.children[2].children[3] = null;
                        base.children[2].children[4] = null;
                        base.children[2].childrenNum--;
                        base.children[2].childrenNum--;
                    }
                }
            }
            if (base.valuesNum == 3) {
                return true;
            }
            else {
                return false;
            }
        }
        if (base.childrenNum < 0) {
            throw new Exception("childrenNum < 0");
        }
        if (base.childrenNum == 1) {
            throw new Exception("childrenNum = 1");
        }
        if (base.childrenNum > 4) {
            throw new Exception("childrenNum > 4");
        }
        throw new Exception("Unknown insertion exception");
    }
    
    public void insert(int val) {
        try {
            if (root.childrenNum == 0) { //if node is leaf node
                root.values[2] = val;
                root.valuesNum++;
                reorderNode(root);
            }
            else if (root.childrenNum == 2) { //if node is not a leaf node and only 2 children
                if (val < root.values[0]) {
                    if (insert(val, root.children[0])) { //if i need to pull
                        root.values[2] = root.children[0].values[1]; //pull value from left node
                        root.valuesNum++; //increment current node's values count
                        root.children[0].values[1] = null; //remove pulled value from left node
                        root.children[0].valuesNum--; //decrement child node's values count
                        reorderNode(root); //reordered node
                        root.children[2] = root.children[1]; //moves right child node over one
                        root.children[1] = new Node(root.children[0].values[2]); //creates a new node to put the left node's right value into (in right child node's old spot)
                        root.childrenNum++; //increment current node's children count
                        root.children[0].values[2] = null; //removed value from left node
                        root.children[0].valuesNum--;
                        if (root.children[0].childrenNum == 4) { //if node I am pulling from has too many children: Reassign the children
                            root.children[1].children[0] = root.children[0].children[2];
                            root.children[1].children[1] = root.children[0].children[3];
                            root.children[1].childrenNum++;
                            root.children[1].childrenNum++;
                            root.children[0].children[2] = null;
                            root.children[0].children[3] = null;
                            root.children[0].childrenNum--;
                            root.children[0].childrenNum--;
                        }
                    }
                }
                if (val > root.values[0]) {
                    if (insert(val, root.children[1])) { //if I need to pull
                        root.values[2] = root.children[1].values[1]; //pull value from right node
                        root.valuesNum++; //increment current node's values count
                        root.children[1].values[1] = null; //remove pulled value from right node
                        root.children[1].valuesNum--; //decrement child node's value count
                        reorderNode(root); //reordered node
                        root.children[2] = new Node(root.children[1].values[2]); //creates a new node to put the right node's right value into (in right child node's old spot)
                        root.childrenNum++; //increment current node's children count
                        root.children[1].values[2] = null; //removed value from right node
                        root.children[1].valuesNum--; //decrement child node's value count
                        if (root.children[1].childrenNum == 4) { //if node I am pulling from has too many children: Reassign the children
                            root.children[2].children[0] = root.children[1].children[2];
                            root.children[2].children[1] = root.children[1].children[3];
                            root.children[2].childrenNum++;
                            root.children[2].childrenNum++;
                            root.children[1].children[2] = null;
                            root.children[1].children[3] = null;
                            root.children[1].childrenNum--;
                            root.children[1].childrenNum--;
                        }
                    }
                }
            }
            else if (root.childrenNum == 3) { //if node is not a leaf node and has 3 children
                if (val > root.values[0] && val < root.values[1]) {
                    if(insert(val, root.children[1])) { //if i need to pull
                        root.values[2] = root.children[1].values[1]; //pull value from middle node
                        root.valuesNum++; //increment current node's values count
                        root.children[1].values[1] = null; //remove pulled value from middle node
                        root.children[1].valuesNum--;//decrement child node's value count
                        reorderNode(root); //reordered node
                        root.children[3] = root.children[2]; //moves right child node over one
                        root.children[2] = new Node(root.children[1].values[2]); //creates a new node to put the middle node's right value into (in right child node's old spot)
                        root.childrenNum++; //increment current node's children count
                        root.children[1].values[2] = null; //removed value from middle node
                        root.children[1].valuesNum--; //decrement child node's value count
                        if (root.children[1].childrenNum == 4) { //if node I am pulling from has too many children: Reassign the children
                            root.children[2].children[0] = root.children[1].children[2];
                            root.children[2].children[1] = root.children[1].children[3];
                            root.children[2].childrenNum++;
                            root.children[2].childrenNum++;
                            root.children[1].children[2] = null;
                            root.children[1].children[3] = null;
                            root.children[1].childrenNum--;
                            root.children[1].childrenNum--;
                        }
                    }
                }
                if (val < root.values[0]) {
                    if(insert(val, root.children[0])) {
                        root.values[2] = root.children[0].values[1]; //pull value from left node
                        root.valuesNum++; //increment current node's values count
                        root.children[0].values[1] = null; //remove pulled value from left node
                        root.children[0].valuesNum--; //decrement child node's value count
                        reorderNode(root); //reordered node
                        root.children[3] = root.children[2]; //moves right node over one
                        root.children[2] = root.children[1]; //moves middle node over one
                        root.children[1] = new Node(root.children[0].values[2]); //creates a new node to put the left node's right value into (in middle child node's old spot)
                        root.childrenNum++; //increment current node's children count
                        root.children[0].values[2] = null; //removed value from middle node
                        root.children[0].valuesNum--; //decrement child node's value count
                        if (root.children[0].childrenNum == 4) { //if node I am pulling from has too many children: Reassign the children
                            root.children[1].children[0] = root.children[0].children[2];
                            root.children[1].children[1] = root.children[0].children[3];
                            root.children[1].childrenNum++;
                            root.children[1].childrenNum++;
                            root.children[0].children[2] = null;
                            root.children[0].children[3] = null;
                            root.children[0].childrenNum--;
                            root.children[0].childrenNum--;
                        }
                    }
                }
                if (val > root.values[1]) {
                    if(insert(val, root.children[2])) {
                        root.values[2] = root.children[2].values[1]; //pull value from right node
                        root.valuesNum++; //increment current node's values count
                        root.children[2].values[1] = null; //remove pulled value from right node
                        root.children[2].valuesNum--;//decrement child node's value count
                        reorderNode(root); //reordered node
                        root.children[3] = new Node(root.children[2].values[2]); //creates a new node to put the right node's left value into (in right child node's old spot)
                        root.childrenNum++; //increment current node's children count
                        root.children[2].values[2] = null; //removed value from right node
                        root.children[2].valuesNum--; //decrement child node's value count
                        if (root.children[2].childrenNum == 4) { //if node I am pulling from has too many children: Reassign the children
                            root.children[3].children[0] = root.children[2].children[2];
                            root.children[3].children[1] = root.children[2].children[3];
                            root.children[3].childrenNum++;
                            root.children[3].childrenNum++;
                            root.children[2].children[2] = null;
                            root.children[2].children[3] = null;
                            root.children[2].childrenNum--;
                            root.children[2].childrenNum--;
                        }
                    }
                }
            }
            if (root.childrenNum < 0) {
                throw new Exception("childrenNum < 0");
            }
            if (root.childrenNum == 1) {
                throw new Exception("childrenNum = 1");
            }
            if (root.childrenNum > 4) {
                throw new Exception("childrenNum > 4");
            }
            if (root.valuesNum == 3) {
                Node dummy = new Node(root.values[1]);
                root.values[1] = null;
                root.valuesNum--;
                dummy.children[0] = root;
                dummy.childrenNum++;
                dummy.children[1] = new Node(root.values[2]);
                dummy.childrenNum++;
                root.values[2] = null;
                root.valuesNum--;
                if (root.childrenNum == 4) {
                    dummy.children[1].children[0] = root.children[2];
                    dummy.children[1].children[1] = root.children[3];
                    dummy.children[1].childrenNum++;
                    dummy.children[1].childrenNum++;
                    root.children[2] = null;
                    root.children[3] = null;
                    root.childrenNum--;
                    root.childrenNum--;
                }
                root = dummy;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean search(int val, Node base) {
        if (base.valuesNum > 0) {
            for (int n = 0; n < base.valuesNum; n++) {
                if (base.values[n] == val) {
                    return true;
                }
            }
            if (base.valuesNum == 1 && base.childrenNum == 2) {
                if (val < base.values[0]) {
                    return search(val, base.children[0]);
                }
                if (val > base.values[0]) {
                    return search(val, base.children[1]);
                }
            }
            if (base.valuesNum == 2 && base.childrenNum == 3) {
                if (val > base.values[0] && val < base.values[1]) {
                    return search(val, base.children[1]);
                }
                if (val < base.values[0]) {
                    return search(val, base.children[0]);
                }
                if (val > base.values[0]) {
                    return search(val, base.children[2]);
                }
            }
        }
        return false;
    }

    Boolean search(int val) {
        if (root.valuesNum > 0) {
            for (int n = 0; n < root.valuesNum; n++) {
                if (root.values[n] == val) {
                    return true;
                }
            }
            if (root.valuesNum == 1 && root.childrenNum == 2) {
                if (val < root.values[0]) {
                    return search(val, root.children[0]);
                }
                if (val > root.values[0]) {
                    return search(val, root.children[1]);
                }
            }
            if (root.valuesNum == 2 && root.childrenNum == 3) {
                if (val > root.values[0] && val < root.values[1]) {
                    return search(val, root.children[1]);
                }
                if (val < root.values[0]) {
                    return search(val, root.children[0]);
                }
                if (val > root.values[0]) {
                    return search(val, root.children[2]);
                }
            }
        }
        return false;
    }

    Node inorderSuccessor(Node base) {
        if (base.childrenNum > 1) {
            return inorderSuccessor(base.children[0]);
        }
        return base;
    }
    
    boolean delete(int val, Node base, boolean swapped) throws Exception{
        if (base.childrenNum == 0) { //is leaf node
            for (int n = 0; n < base.valuesNum; n++) {
                if (base.values[n] == val) { //if node has val
                    if (base.valuesNum == 2) {
                        base.values[n] = null;
                        base.valuesNum--;
                        reorderNode(base);
                        //done
                        return false;
                    }
                    else {
                        base.values[n] = null; //delete
                        base.valuesNum--;
                        return true;
                    }
                }
            }
            return false;
        }
        if (base.childrenNum == 2) { //is internal node with two children
            Node IOS = null;
            int dummy = 0;
            int a = 0;
            boolean toSwap = false;
            if (!swapped) {
                for (int n = 0; n < base.valuesNum; n++) {
                    if (base.values[n] == val) { //if node has val
                        a = n;
                        IOS = inorderSuccessor(base.children[1]); //swap with inorder successor
                        dummy = IOS.values[0];
                        toSwap = true;
                    }
                }
            }
            if (val < base.values[0]) {
                if (toSwap) {
                    IOS.values[0] = base.values[a];
                    base.values[a] = dummy;
                    swapped = true;
                }
                if (delete(val, base.children[0], swapped)) { //if node below needs help after deletion
                    if (base.children[1].valuesNum == 2) { //if right child node has two values
                        if (base.children[1].childrenNum == 3) { //if right child is an internal node
                            base.children[0].children[1] = base.children[1].children[0]; //left child node adopts one of right child node's children
                            base.children[0].childrenNum++; //increment left child's children count
                            base.children[1].children[0] = null; //remove child that was just adopted from right child
                            base.children[1].childrenNum--; //decrement right child's children count
                            base.children[1].children[0] = base.children[1].children[1]; //shift children on right child
                            base.children[1].children[1] = base.children[1].children[2]; //shift children on right child
                            base.children[1].children[2] = null; //shift children on right child
                        }
                        base.children[0].values[0] = base.values[0];
                        base.children[0].valuesNum++; //increment left child's value count
                        base.values[0] = base.children[1].values[0];
                        base.children[1].values[0] = null;
                        base.children[1].valuesNum--;
                        reorderNode(base.children[1]);
                        return false;
                    }
                    else if (base.children[1].valuesNum == 1) { //if right child node has one values
                        if (base.children[1].childrenNum == 2) { //if right child node is an internal node
                            base.children[1].children[2] = base.children[1].children[1]; //shift children on right child
                            base.children[1].children[1] = base.children[1].children[0]; //shift children on right child
                            base.children[1].children[0] = base.children[0].children[0]; //right child adopts left child's only child
                            base.children[1].childrenNum++;
                            base.children[0] = null; //removes extra node
                        }
                        base.children[0] = null; //remove the left child
                        base.childrenNum--; //decrement the child count
                        base.children[1].values[2] = base.values[0]; //merge the parent node to the right child node
                        base.children[1].valuesNum++; //increment the right child's values counter
                        reorderNode(base.children[1]);
                        base.values[0] = null; //remove the value that was merged from parent node
                        base.valuesNum--; //decrement the values count
                        base.children[0] = base.children[1]; //shift right child node over
                        base.children[1] = null; //shift right child node over
                        return true;
                    }
                }
                return false;
            }
            else if (val >= base.values[0]) {
                if (toSwap) {
                    IOS.values[0] = base.values[a];
                    base.values[a] = dummy;
                    swapped = true;
                }
                if (delete(val, base.children[1], swapped)) {
                    if (base.children[0].valuesNum == 2) { //if left child node has two values
                        if (base.children[0].childrenNum == 3) { //if left child is an internal node
                            base.children[1].children[1] = base.children[1].children[0]; //shift right child's children over one
                            base.children[1].children[0] = base.children[0].children[2]; //right child node adopts one of left child node's children
                            base.children[1].childrenNum++; //increment right child's children count
                            base.children[0].children[2] = null; //remove child that was just adopted from right child
                            base.children[0].childrenNum--; //decrement right child's children count
                        }
                        base.children[1].values[0] = base.values[0];
                        base.children[0].valuesNum++; //increment right child's value count
                        base.values[0] = base.children[1].values[0];
                        base.children[1].values[1] = null;
                        base.children[1].valuesNum--;
                        reorderNode(base.children[0]);
                        return false;
                    }
                    else if (base.children[0].valuesNum == 1) { //if left child node has one values
                        if (base.children[0].childrenNum == 2) { //if left child node is an internal node
                            base.children[0].children[2] = base.children[1].children[0]; //left child adopts right child's only child
                            base.children[0].childrenNum++;
                            base.children[1] = null; //removes extra node
                        }
                        base.children[1] = null; //remove the right child
                        base.childrenNum--; //decrement the child count
                        base.children[0].values[2] = base.values[0]; //merge the parent node to the left child node
                        reorderNode(base.children[0]);
                        base.children[0].valuesNum++; //increment the right child's values counter
                        base.values[0] = null; //remove the value that was merged from parent node
                        base.valuesNum--; //decrement the values count
                        return true;
                    }
                }
                return false;
            }
        }
        if (base.childrenNum == 3) { //is internal node with three children
            Node IOS = null;
            int dummy = 0;
            int a = 0;
            boolean toSwap = false;
            if (!swapped) {
                for (int n = 0; n < base.valuesNum; n++) {
                    if (base.values[n] == val) { //if node has val
                        a = n;
                        IOS = inorderSuccessor(base.children[1]); //swap with inorder successor
                        dummy = IOS.values[0];
                        toSwap = true;
                    }
                }
            }
            if (val >= base.values[0] && val <= base.values[1]) {
                if (toSwap) {
                    IOS.values[0] = base.values[a];
                    base.values[a] = dummy;
                    swapped = true;
                }
                if (delete(val, base.children[1], swapped)) { //if node below need help after deletion
                    if (base.children[2].valuesNum == 2) { //if right child node has two values
                        if (base.children[2].childrenNum == 3) { //if right child is an internal node
                            base.children[1].children[1] = base.children[2].children[0]; //middle child adopts right child's first child
                            base.children[1].childrenNum++; //increment middle child's children count
                            base.children[2].children[0] = base.children[2].children[1]; //shift right child's children left  
                            base.children[2].children[1] = base.children[2].children[2]; //shift right child's children left
                            base.children[2].children[2] = null; //remove right child's old right child
                        }
                        base.children[1].values[0] = base.values[1]; //middle child takes parent node's second value
                        base.children[1].valuesNum++; //increment middle child's values count
                        base.values[1] = base.children[2].values[0]; //parent node takes right child's first value
                        base.children[2].values[0] = base.children[2].values[1]; //shifts right child's second value left
                        base.children[2].values[1] = null; //remove second value from right child
                        base.children[2].valuesNum--; //decrement right child's values count
                    }
                    else if (base.children[0].valuesNum == 2) { //if left child has two values
                        if (base.children[0].childrenNum == 3) { //if left child is an internal node
                            base.children[1].children[1] = base.children[1].children[0]; //shift middle child's child to the right for adoption
                            base.children[1].children[0] = base.children[0].children[2]; //middle node adopts left node's right child
                            base.children[1].childrenNum++; //increment middle child's children count
                            base.children[0].children[2] = null; //remove left child's right child
                            base.children[0].childrenNum--; //decrement left child's children count
                        }
                        base.children[1].values[0] = base.values[0]; //middle child takes parent node's first value
                        base.children[1].valuesNum++; //increment middle child's values count
                        base.values[0] = base.children[0].values[1]; //parent node takes left child's second value
                        base.children[0].values[1] = null; //remove second value from left child
                        base.children[0].valuesNum--; //decrement left child's values count

                    }
                    else { //if neither left or right child has two values
                        if(base.children[1].childrenNum > 0) { //if node below is an internal node
                            base.children[0].children[2] = base.children[1].children[0]; //left node adopts middle node's only child
                        }
                        base.children[0].values[1] = base.values[0];
                        base.children[0].valuesNum++;
                        base.values[0] = null;
                        base.valuesNum--;
                        reorderNode(base);
                        base.children[1] = base.children[2];
                        base.children[2] = null;
                        base.childrenNum--;
                    }
                }
                return false;
            }
            if (val < base.values[0]) {
                if (toSwap) {
                    IOS.values[0] = base.values[a];
                    base.values[a] = dummy;
                    swapped = true;
                }
                if (delete(val, base.children[0], swapped)) { //if node below needs help after deletion
                    if (base.children[1].valuesNum == 2) { //if middle child node has two values
                        if (base.children[1].childrenNum == 3) { //if middle child is an internal node
                            base.children[0].children[1] = base.children[1].children[0]; //left child adopts middle child's left child
                            base.children[0].childrenNum++; //increment left child's children count
                            base.children[1].children[0] = base.children[1].children[1]; //shift middle child's children left
                            base.children[1].children[1] = base.children[1].children[2]; //shift middle child's children left
                            base.children[1].children[2] = null; //remove middle child's old right child
                            base.children[1].childrenNum--; //decrement middle child's children count
                        }
                        base.children[0].values[0] = base.values[0];
                        base.children[0].valuesNum++;
                        base.values[0] = base.children[1].values[0];
                        base.children[1].values[0] = null;
                        reorderNode(base.children[1]);
                    }
                    else if (base.children[1].valuesNum == 1) { //if middle child node has one values
                        if (base.children[1].childrenNum == 2) { //if middle child node is an internal node
                            base.children[0].children[1] = base.children[1].children[0]; //left child adopts both middle child's children
                            base.children[0].children[2] = base.children[1].children[1]; //left child adopts both middle child's children
                            base.children[0].childrenNum++; //increment left child's children count
                            base.children[0].childrenNum++; //increment left child's children count
                        }
                        base.children[0].values[0] = base.values[0]; //left child takes parent's first value
                        base.children[0].valuesNum++; //increment left child's value count
                        base.values[0] = null;  //remove parent's first value
                        base.valuesNum--; //decrement parent's value count
                        reorderNode(base);
                        base.children[0].values[1] = base.children[1].values[0]; //take middle child value into left child
                        base.children[0].valuesNum++; //increment left child's value count
                        base.children[1] = base.children[2]; //shift right child left
                        base.children[2] = null; //remove old right child
                        base.childrenNum--; //decrement parent's child count
                    }
                }
                return false;
            }
            if (val >= base.values[1]) {
                if (toSwap) {
                    IOS.values[0] = base.values[a];
                    base.values[a] = dummy;
                    swapped = true;
                }
                if (delete(val, base.children[2], swapped)) {
                    if (base.children[1].valuesNum == 2) { //if middle child node has two values
                        if (base.children[1].childrenNum == 3) { //if left child node is an internal node
                            base.children[2].children[1] = base.children[2].children[0]; //move right child's child to the right
                            base.children[2].children[0] = base.children[1].children[2]; //left child adopts middle child's right child
                        }
                        base.children[2].values[0] = base.values[1]; //right child takes parent's second value
                        base.children[2].valuesNum++; //increment right child's value count
                        base.values[1] = base.children[1].values[1]; //parent take middle childs second value as its own second value
                        base.children[1].values[1] = null; //remove middle child's second value
                        base.children[1].valuesNum--; //decrement middle child's value count
                    }
                    if (base.children[1].valuesNum == 1) { //if left child node has one values
                        if (base.children[1].childrenNum == 2) { //if left child node is an internal node
                            base.children[1].children[2] = base.children[2].children[0];
                            base.children[1].childrenNum++;
                        }
                        base.children[2] = null;
                        base.childrenNum--;
                        base.children[1].values[1] = base.values[1];
                        base.children[1].valuesNum++;
                        base.values[1] = null;
                        base.valuesNum--;
                    }
                }
                return false;
            }
        }        
        if (base.childrenNum < 0) {
            throw new Exception("childrenNum < 0");
        }
        if (base.childrenNum == 1) {
            throw new Exception("childrenNum = 1");
        }
        if (base.childrenNum > 4) {
            throw new Exception("childrenNum > 4");
        }
        throw new Exception("Unknown insertion exception");
    }

    void delete(int val) {
        try {
            boolean swapped = false;
            if (root.childrenNum == 0) { //is leaf node
                for (int n = 0; n < root.valuesNum; n++) {
                    if (root.values[n] == val) { //if node has val
                        if (root.valuesNum == 2) {
                            root.values[n] = null;
                            root.valuesNum--;
                            reorderNode(root);
                            //done
                        }
                        else {
                            root.values[n] = null; //delete
                            root.valuesNum--;
                        }
                    }
                }
            }
            else if (root.childrenNum == 2) { //is internal node with two children
                Node IOS = null;
                int dummy = 0;
                int a = 0;
                boolean toSwap = false;
                for (int n = 0; n < root.valuesNum; n++) {
                    if (root.values[n] == val) { //if node has val
                        a = n;
                        IOS = inorderSuccessor(root.children[1]); //swap with inorder successor
                        dummy = IOS.values[0];
                        toSwap = true;
                    }
                }
                if (val < root.values[0]) {
                    if (toSwap) {
                        IOS.values[0] = root.values[a];
                        root.values[a] = dummy;
                        swapped = true;
                    }
                    if (delete(val, root.children[0], swapped)) { //if node below needs help after deletion
                        if (root.children[1].valuesNum == 2) { //if right child node has two values
                            if (root.children[1].childrenNum == 3) { //if right child is an internal node
                                root.children[0].children[1] = root.children[1].children[0]; //left child node adopts one of right child node's children
                                root.children[0].childrenNum++; //increment left child's children count
                                root.children[1].children[0] = null; //remove child that was just adopted from right child
                                root.children[1].childrenNum--; //decrement right child's children count
                                root.children[1].children[0] = root.children[1].children[1]; //shift children on right child
                                root.children[1].children[1] = root.children[1].children[2]; //shift children on right child
                                root.children[1].children[2] = null; //shift children on right child
                            }
                            root.children[0].values[0] = root.values[0];
                            root.children[0].valuesNum++; //increment left child's value count
                            root.values[0] = root.children[1].values[0];
                            root.children[1].values[0] = null;
                            root.children[1].valuesNum--;
                            reorderNode(root.children[1]);
                        }
                        if (root.children[1].valuesNum == 1) { //if right child node has one values
                            if (root.children[1].childrenNum == 2) { //if right child node is an internal node
                                root.children[1].children[2] = root.children[1].children[1]; //shift children on right child
                                root.children[1].children[1] = root.children[1].children[0]; //shift children on right child
                                root.children[1].children[0] = root.children[0].children[0]; //right child adopts left child's only child
                                root.children[1].childrenNum++;
                            }
                            root.children[1].values[2] = root.values[0]; //merge the parent node to the right child node
                            root.children[1].valuesNum++; //increment the right child's values counter
                            reorderNode(root.children[1]);
                            root = root.children[1];
                        }
                    }
                }
                else if (val >= root.values[0]) {
                    if (toSwap) {
                        IOS.values[0] = root.values[a];
                        root.values[a] = dummy;
                        swapped = true;
                    }
                    if (delete(val, root.children[1], swapped)) {
                        if (root.children[0].valuesNum == 2) { //if left child node has two values
                            root.children[1].values[0] = root.values[0];
                            root.children[0].valuesNum++; //increment right child's value count
                            root.values[0] = root.children[1].values[0];
                            root.children[1].values[1] = null;
                            root.children[1].valuesNum--;
                            if (root.children[0].childrenNum == 3) { //if left child is an internal node
                                root.children[1].children[1] = root.children[1].children[0]; //shift right child's children over one
                                root.children[1].children[0] = root.children[0].children[2]; //right child node adopts one of left child node's children
                                root.children[1].childrenNum++; //increment right child's children count
                                root.children[0].children[2] = null; //remove child that was just adopted from right child
                                root.children[0].childrenNum--; //decrement right child's children count
                            }
                            reorderNode(root.children[0]);
                        }
                        if (root.children[0].valuesNum == 1) { //if left child node has one value
                            if (root.children[0].childrenNum == 2) { //if left child node is an internal node
                                root.children[0].children[2] = root.children[1].children[0]; //left child adopts right child's only child
                                root.children[0].childrenNum++;
                                root.children[1] = null; //removes extra node
                            }
                            root.children[1] = null; //remove the right child
                            root.childrenNum--; //decrement the child count
                            root.children[0].values[2] = root.values[0]; //merge the parent node to the left child node
                            reorderNode(root.children[0]);
                            root.children[0].valuesNum++; //increment the right child's values counter
                            root.values[0] = null; //remove the value that was merged from parent node
                            root.valuesNum--; //decrement the values count
                            root = root.children[0];
                        }
                    }
                }
            }
            else if (root.childrenNum == 3) { //is internal node with three children
                Node IOS = null;
                int dummy = 0;
                int a = 0;
                boolean toSwap = false;
                if (!swapped) {
                    for (int n = 0; n < root.valuesNum; n++) {
                        if (root.values[n] == val) { //if node has val
                            a = n;
                            IOS = inorderSuccessor(root.children[1]);
                            dummy = IOS.values[0];
                            toSwap = true;
                        }
                    }
                }
                if (val >= root.values[0] && val <= root.values[1]) {
                    if (toSwap) {
                        IOS.values[0] = root.values[a];
                        root.values[a] = dummy;
                        swapped = true;
                    }
                    if (delete(val, root.children[1], swapped)) { //if node below need help after deletion
                        if (root.children[2].valuesNum == 2) { //if right child node has two values
                            if (root.children[2].childrenNum == 3) { //if right child is an internal node
                                root.children[1].children[1] = root.children[2].children[0]; //middle child adopts right child's first child
                                root.children[1].childrenNum++; //increment middle child's children count
                                root.children[2].children[0] = root.children[2].children[1]; //shift right child's children left  
                                root.children[2].children[1] = root.children[2].children[2]; //shift right child's children left
                                root.children[2].children[2] = null; //remove right child's old right child
                            }
                            root.children[1].values[0] = root.values[1]; //middle child takes parent node's second value
                            root.children[1].valuesNum++; //increment middle child's values count
                            root.values[1] = root.children[2].values[0]; //parent node takes right child's first value
                            root.children[2].values[0] = root.children[2].values[1]; //shifts right child's second value left
                            root.children[2].values[1] = null; //remove second value from right child
                            root.children[2].valuesNum--; //decrement right child's values count
                        }
                        else if (root.children[0].valuesNum == 2) { //if left child has two values
                            if (root.children[0].childrenNum == 3) { //if left child is an internal node
                                root.children[1].children[1] = root.children[1].children[0]; //shift middle child's child to the right for adoption
                                root.children[1].children[0] = root.children[0].children[2]; //middle node adopts left node's right child
                                root.children[1].childrenNum++; //increment middle child's children count
                                root.children[0].children[2] = null; //remove left child's right child
                                root.children[0].childrenNum--; //decrement left child's children count
                            }
                            root.children[1].values[0] = root.values[0]; //middle child takes parent node's first value
                            root.children[1].valuesNum++; //increment middle child's values count
                            root.values[0] = root.children[0].values[1]; //parent node takes left child's second value
                            root.children[0].values[1] = null; //remove second value from left child
                            root.children[0].valuesNum--; //decrement left child's values count
    
                        }
                        else { //if neither left or right child has two values
                            if(root.children[1].childrenNum > 0) { //if node below is an internal node
                                root.children[0].children[2] = root.children[1].children[0]; //left node adopts middle node's only child
                            }
                            root.children[0].values[1] = root.values[0];
                            root.children[0].valuesNum++;
                            root.values[0] = null;
                            root.valuesNum--;
                            reorderNode(root);
                            root.children[1] = root.children[2];
                            root.children[2] = null;
                            root.childrenNum--;
                        }
                    }
                }
                if (val < root.values[0]) {
                    if (toSwap) {
                        IOS.values[0] = root.values[a];
                        root.values[a] = dummy;
                        swapped = true;
                    }
                    if (delete(val, root.children[0], swapped)) { //if node below needs help after deletion
                        if (root.children[1].valuesNum == 2) { //if middle child node has two values
                            if (root.children[1].childrenNum == 3) { //if middle child is an internal node
                                root.children[0].children[1] = root.children[1].children[0]; //left child adopts middle child's left child
                                root.children[0].childrenNum++; //increment left child's children count
                                root.children[1].children[0] = root.children[1].children[1]; //shift middle child's children left
                                root.children[1].children[1] = root.children[1].children[2]; //shift middle child's children left
                                root.children[1].children[2] = null; //remove middle child's old right child
                                root.children[1].childrenNum--; //decrement middle child's children count
                            }
                            root.children[0].values[0] = root.values[0];
                            root.children[0].valuesNum++;
                            root.values[0] = root.children[1].values[0];
                            root.children[1].values[0] = null;
                            reorderNode(root.children[1]);
                        }
                        else if (root.children[1].valuesNum == 1) { //if middle child node has one values
                            if (root.children[1].childrenNum == 2) { //if middle child node is an internal node
                                root.children[0].children[1] = root.children[1].children[0]; //left child adopts both middle child's children
                                root.children[0].children[2] = root.children[1].children[1]; //left child adopts both middle child's children
                                root.children[0].childrenNum++; //increment left child's children count
                                root.children[0].childrenNum++; //increment left child's children count
                            }
                            root.children[0].values[0] = root.values[0]; //left child takes parent's first value
                            root.children[0].valuesNum++; //increment left child's value count
                            root.values[0] = null;  //remove parent's first value
                            root.valuesNum--; //decrement parent's value count
                            reorderNode(root);
                            root.children[1] = root.children[2]; //shift right child left
                            root.children[2] = null; //remove old right child
                            root.childrenNum--; //decrement parent's child count
                        }
                    }
                }
                if (val >= root.values[1]) {
                    if (toSwap) {
                        IOS.values[0] = root.values[a];
                        root.values[a] = dummy;
                        swapped = true;
                    }
                    if (delete(val, root.children[2], swapped)) {
                        if (root.children[1].valuesNum == 2) { //if middle child node has two values
                            if (root.children[1].childrenNum == 3) { //if left child node is an internal node
                                root.children[2].children[1] = root.children[2].children[0]; //move right child's child to the right
                                root.children[2].children[0] = root.children[1].children[2]; //left child adopts middle child's right child
                            }
                            root.children[2].values[0] = root.values[1]; //right child takes parent's second value
                            root.children[2].valuesNum++; //increment right child's value count
                            root.values[1] = root.children[1].values[1]; //parent take middle childs second value as its own second value
                            root.children[1].values[1] = null; //remove middle child's second value
                            root.children[1].valuesNum--; //decrement middle child's value count
                        }
                        if (root.children[1].valuesNum == 1) { //if left child node has one values
                            if (root.children[1].childrenNum == 2) { //if left child node is an internal node
                                root.children[1].children[2] = root.children[2].children[0];
                                root.children[1].childrenNum++;
                            }
                            root.children[2] = null;
                            root.childrenNum--;
                            root.children[1].values[1] = root.values[1];
                            root.children[1].valuesNum++;
                            root.values[1] = null;
                            root.valuesNum--;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void printInOrder(Node base) {
        if (base.childrenNum == 0) {
            for (int n = 0; n < base.valuesNum; n++) {
                System.out.print(base.values[n] + " ");
            }
        }
        if (base.childrenNum == 2) {
            printInOrder(base.children[0]);
            System.out.print(base.values[0] + " ");
            printInOrder(base.children[1]);
        }
        if (base.childrenNum == 3) {
            printInOrder(base.children[0]);
            System.out.print(base.values[0] + " ");
            printInOrder(base.children[1]);
            System.out.print(base.values[1] + " ");
            printInOrder(base.children[2]);
        }
    }

    void printInOrder() {
        if (root.childrenNum == 0) {
            for (int n = 0; n < root.valuesNum; n++) {
                System.out.print(root.values[n] + " ");
            }
        }
        if (root.childrenNum == 2) {
            printInOrder(root.children[0]);
            System.out.print(root.values[0] + " ");
            printInOrder(root.children[1]);
        }
        if (root.childrenNum == 3) {
            printInOrder(root.children[0]);
            System.out.print(root.values[0] + " ");
            printInOrder(root.children[1]);
            System.out.print(root.values[1] + " ");
            printInOrder(root.children[2]);
        }
        System.out.print("\n");
    }

    int height(Node base, int n) {
        if (base.childrenNum > 0) {
            return height(base.children[0], n + 1);
        }
        else {
            return n;
        }
    }

    int height() {
        if (root.childrenNum > 0) {
            return height(root.children[0], 2);
        }
        else {
            return 1;
        }
    }

    void printLevels(Node base, int height, int a, String str[]) {
        if (base.childrenNum == 0) {
            if (base.valuesNum == 1) {
                str[a] += base.values[0];
                str[a] += ", ";
            }
            if (base.valuesNum == 2) {
                str[a] += base.values[0];
                str[a] += " ";
                str[a] += base.values[1];
                str[a] += ", ";
            }
        }
        if (base.childrenNum == 2) {
            printLevels(base.children[0], height, a + 1, str);
            str[a] += base.values[0];
            str[a] += ", ";
            printLevels(base.children[1], height, a + 1, str);
        }
        if (base.childrenNum == 3) {
            printLevels(base.children[0], height, a + 1, str);
            str[a] += base.values[0];
            str[a] += " ";
            printLevels(base.children[1], height, a + 1, str);
            str[a] += base.values[1];
            str[a] += ", ";
            printLevels(base.children[2], height, a + 1, str);
        }
    }
    
    void printLevels() {
        int a = 0;
        int height = height();
        String str[] = new String[height];
        for (int n = 0; n < height; n++) {
            str[n] = "";
        }
        if (root.childrenNum == 0) {
            for (int n = 0; n < root.valuesNum; n++) {
                str[a] += root.values[n];
                str[a] += " ";
            }
        }
        if (root.childrenNum == 2) {
            printLevels(root.children[0], height, a + 1, str);
            str[a] += root.values[0];
            str[a] += " ";
            printLevels(root.children[1], height, a + 1, str);
        }
        if (root.childrenNum == 3) {
            printLevels(root.children[0], height, a + 1, str);
            str[a] += root.values[0];
            str[a] += " ";
            printLevels(root.children[1], height, a + 1, str);
            str[a] += root.values[1];
            str[a] += " ";
            printLevels(root.children[2], height, a + 1, str);
        }
        for (int n = 0; n < height; n++) {
            System.out.println(str[n]);
        }
    }

    public static void main(String[] args) {
        TwoThreeTree tree = new TwoThreeTree();
        tree.insert(10);
        tree.insert(11);
        tree.insert(12);
        tree.insert(13);
        tree.insert(14);
        tree.insert(15);
        tree.insert(16);
        tree.insert(17);
        tree.insert(18);
        tree.insert(19);
        tree.insert(20);
        tree.insert(21);
        tree.insert(22);
        tree.insert(23);
        tree.insert(24);
        tree.printLevels();

        
        System.out.println(" ");
        tree.delete(2);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(10);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(11);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(12);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(24);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(23);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(19);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(23);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(17);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(18);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(20);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(15);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(22);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(16);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(13);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(21);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(14);
        tree.printLevels();
        System.out.println(" ");
        tree.delete(13);
        tree.printLevels();
        System.out.println(" ");


        tree.insert(9);
        tree.printLevels();
        System.out.println(" ");
        System.out.print("Press Enter to continue");
        try {
            System.in.read();
        }
        catch(Exception e){
    
        }
     }

}

