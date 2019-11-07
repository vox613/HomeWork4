package innopolis.homework4;

import java.util.*;


public class NewMap<K, V> implements Map<K, V> {
    private final int mapCapacity;
    private Bucket[] bucketMass;
    private Set<Entry<K, V>> newEntrySet = new HashSet<>();

    NewMap(int mapCapacity) {
        this.mapCapacity = mapCapacity;
        bucketMass = new Bucket[mapCapacity];
        for (int i = 0; i < mapCapacity; i++) {
            bucketMass[i] = new Bucket();
        }
    }

    @Override
    public V put(K key, V value) {
        V pastValue;
        if (key == null) {
            pastValue = (V) bucketMass[0].add(new Node<>(key, value));
            entrySet();
            return pastValue;
        } else {
            pastValue = (V) bucketMass[getIndex(key)].add(new Node<>(key, value));
            entrySet();
            return pastValue;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        int index = ((key == null) ? 0 : getIndex(key));
        return (bucketMass[index].takeNode(key) != null);
    }

    @Override
    public V get(Object key) {
        int index = ((key == null) ? 0 : getIndex(key));
        Node tempNode = bucketMass[index].takeNode(key);
        return (tempNode == null) ? null : (V) tempNode.getValue();
    }

    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < mapCapacity; i++) {
            size += bucketMass[i].getNumOfEntry();
        }
        return size;
    }

    @Override
    public V remove(Object key) {
        int index = (key == null) ? 0 : getIndex(key);
        V removedValue;
        if (containsKey(key)) {
            removedValue = (V) bucketMass[index].removeNode(key);
            entrySet();
            return removedValue;
        } else {
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public void clear() {
        for (int i = 0; i < mapCapacity; i++) {
            bucketMass[i] = new Bucket();
        }
        entrySet();
    }

    @Override
    public boolean containsValue(Object value) {
        Collection<V> val = this.values();
        return val.contains(value);
    }

    @Override
    public void putAll(Map m) {
        Set<Entry<K, V>> es = m.entrySet();
        for (Entry<K, V> pair : es) {
            this.put(pair.getKey(), pair.getValue());
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        Set<Entry<K, V>> setEntry = entrySet();
        for (Entry<K, V> aSetEntry : setEntry) {
            set.add(aSetEntry.getKey());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> val = new ArrayList<>();
        Set<Entry<K, V>> setEntry = entrySet();
        for (Entry<K, V> itr : setEntry) {
            val.add(itr.getValue());
        }
        return val;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> es;
        if (newEntrySet != null) {
            newEntrySet.clear();
        }
        for (int i = 0; i < mapCapacity; i++) {
            if (bucketMass[i] != null) {
                es = bucketMass[i].getPairs();
                if (es != null) {
                    for (Entry<K, V> pair : es) {
                        if (pair != null) {
                            this.newEntrySet.add(pair);
                            /*if(newEntrySet == null){
                                this.newEntrySet.add(pair);

                            }else{
                                this.newEntrySet.add(pair);
                            }*/
                        }
                    }
                }
            }
        }
        return newEntrySet;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < mapCapacity; i++) {
            str.append("Bucket ").append(i).append(" : ");
            str.append(bucketMass[i].toString());
            str.append("\n");
        }
        return str.toString();
    }


    private int getIndex(Object key) {
        return newHash(key.hashCode());
    }

    private int newHash(int keyHashCode) {
        return (keyHashCode & (mapCapacity - 1));
    }

}
