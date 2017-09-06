package com.jxshen.example.lambda;

import java.util.function.Supplier;

public class SupplierTest {
    
    static class Person {
        private String name;
        private Integer age;
        public Person() {}
        public Person(String name, Integer age) {
            super();
            this.name = name;
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getAge() {
            return age;
        }
        public void setAge(Integer age) {
            this.age = age;
        }
        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + "]";
        }
        
    }

    public static void main(String[] args) {
        Supplier<Person> pSupplier = Person::new;
        Person person = pSupplier.get();
        System.out.println(person.toString());
    }
}

