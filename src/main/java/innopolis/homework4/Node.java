package innopolis.homework4;

import java.util.Map;

public class Node<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;
    private int keyHashCode;
    private Node linkToNextNode;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.linkToNextNode = null;
        if (key == null) {
            this.keyHashCode = 0;
        } else {
            this.keyHashCode = key.hashCode();
        }
    }

    public String toString() {
        return "[" + key + ":" + value + "]";
    }


    public V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    Node getLinkToNextNode() {
        return linkToNextNode;
    }

    void setLinkToNextNode(Node linkToNextNode) {
        this.linkToNextNode = linkToNextNode;
    }
}
