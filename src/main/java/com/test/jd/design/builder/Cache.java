package com.test.jd.design.builder;

/**
 * @author :panligang
 * @description :
 * @create :2023-04-26 21:01:00
 */
public class Cache<K,V> {

    private Class<K> keyClass;

    private Class<V> valueClass;

    private int capacity = -1;

    private String name;

    public Cache() {
    }

    public Cache(Class<K> keyClass, Class<V> valueClass, int capacity, String name) {
        this.keyClass = keyClass;
        this.valueClass = valueClass;
        this.capacity = capacity;
        this.name = name;
    }

    public Class<K> getKeyClass() {
        return keyClass;
    }

    public Class<V> getValueClass() {
        return valueClass;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public static <K,V> Builder<K,V> builder() {
        return new Builder<>();
    }

    public static class Builder<K,V> {

        private Class<K> keyClass;

        private Class<V> valueClass;

        private int capacity = -1;

        private String name;

        public Builder<K,V> keyClass(Class<K> keyClass) {
            this.keyClass = keyClass;
            return this;
        }

        public Builder<K,V> valueClass(Class<V> keyClass) {
            this.valueClass = keyClass;
            return this;
        }

        public Cache builder() {
            return new Cache(keyClass,valueClass,capacity,name);
        }
    }

    public static void main(String[] args) {
        Builder<String,String> builder = Cache.builder();
        Cache<String,String> builder1 = builder.keyClass(String.class).valueClass(String.class).builder();
    }


}
