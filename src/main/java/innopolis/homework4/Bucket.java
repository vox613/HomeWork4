package innopolis.homework4;

import java.util.ArrayList;
import java.util.Map;


public class Bucket<K, V> {
    private Node currentNode;
    private int numOfNodes;

    Bucket() {
        this.currentNode = null;
        this.numOfNodes = 0;
    }


    V add(Node node) {
        V temp;
        Node currentNodeCopy = currentNode;

        if (currentNodeCopy != null) {
            Node saveLink = currentNodeCopy;
            while (currentNodeCopy != null) {
                if (node.getKey() == null) {
                    if (currentNodeCopy.getKey() == null) {
                        temp = (V) currentNodeCopy.getValue();
                        currentNodeCopy.setValue(node.getValue());
                        return temp;
                    }
                } else {
                    if (node.getKey().equals(currentNodeCopy.getKey())) {
                        temp = (V) currentNodeCopy.getValue();
                        currentNodeCopy.setValue(node.getValue());
                        return temp;
                    }
                }
                currentNodeCopy = currentNodeCopy.getLinkToNextNode();
                if (currentNodeCopy != null) {
                    saveLink = currentNodeCopy;
                }
            }
            numOfNodes++;
            saveLink.setLinkToNextNode(node);
            return null;
        } else {
            numOfNodes++;
            currentNode = node;
            return null;
        }
    }


    public ArrayList<Map.Entry<K, V>> getPairs() {
        ArrayList<Map.Entry<K, V>> listNodes = new ArrayList<>();
        Node currentNodeCopy = currentNode;
        if (currentNodeCopy != null) {
            while (currentNodeCopy != null) {
                listNodes.add(currentNodeCopy);
                currentNodeCopy = currentNodeCopy.getLinkToNextNode();
            }
            return listNodes;
        } else {
            return null;
        }
    }


    Node takeNode(K key) {
        Node currentNodeCopy = currentNode;
        if (currentNodeCopy != null) {
            while (currentNodeCopy != null) {
                if (key == null) {
                    if (currentNodeCopy.getKey() == null) {
                        return currentNodeCopy;
                    }
                } else {
                    if (key.equals(currentNodeCopy.getKey())) {
                        return currentNodeCopy;
                    }
                }
                currentNodeCopy = currentNodeCopy.getLinkToNextNode();
            }
            return null;
        } else {
            return null;
        }
    }


    public String toString() {
        StringBuilder str = new StringBuilder("");
        Node currentNodeCopy = currentNode;
        if (currentNodeCopy != null) {
            while (currentNodeCopy != null) {
                str.append(currentNodeCopy.toString());
                str.append(" ");
                currentNodeCopy = currentNodeCopy.getLinkToNextNode();
            }
        } else {
            return str.toString();
        }
        return str.toString();
    }


    V removeNode(K key) {
        V temp;
        Node currentNodeCopy = currentNode;
        Node prevLink = null;
        if (currentNodeCopy != null) {
            while (currentNodeCopy != null) {
                if (key == null) {
                    if (currentNodeCopy.getKey() == null) {
                        temp = (V) currentNodeCopy.getValue();
                        if (prevLink == null) {
                            currentNode = currentNodeCopy.getLinkToNextNode();
                        } else {
                            prevLink.setLinkToNextNode(currentNodeCopy.getLinkToNextNode());
                        }
                        numOfNodes--;
                        return temp;
                    }
                } else {
                    if (key.equals(currentNodeCopy.getKey())) {
                        temp = (V) currentNodeCopy.getValue();
                        if (prevLink == null) {
                            currentNode = currentNodeCopy.getLinkToNextNode();
                        } else {
                            prevLink.setLinkToNextNode(currentNodeCopy.getLinkToNextNode());
                        }
                        numOfNodes--;
                        return temp;
                    }
                }
                prevLink = currentNodeCopy;
                currentNodeCopy = currentNodeCopy.getLinkToNextNode();
            }
            return null;
        } else {
            return null;
        }
    }

    int getNumOfEntry() {
        return numOfNodes;
    }

}
