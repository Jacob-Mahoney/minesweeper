package main;

public class Dog {

    private String name;
    private int age;

    Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getInfo() {
        return this.name + " is " + age + " year(s) old!";
    }

}
