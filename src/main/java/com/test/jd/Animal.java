package com.test.jd;

// 定义一个抽象类
abstract class Animal {
    // 用 private 修饰符定义一个私有字段
    private String name;

    // 用 protected 修饰符定义一个受保护的字段
    protected int age;

    // 构造方法
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 公共的 getter 方法，用于获取私有字段 name 的值
    public String getName() {
        return name;
    }
}

// 定义一个 Animal 的子类 Dog
class Dog extends Animal {
    // 构造方法
    public Dog(String name, int age) {
        super(name, age);
    }

    // 在子类中访问父类的受保护字段 age
    public void printAge() {
        System.out.println("Dog's age is:" + super.age);
    }

    public void printName() {
        System.out.println("Dog's name is:" + super.getName());
    }
}