package com.ds.designPattern.builder.simple;

/**
 * @author: dongsheng
 * @CreateTime: 2022/4/12
 * @Description:
 *
 * 典型的java中builder模式
 */
public class Person {
    int id;
    String name;
    int age;
    double weight;
    int score;
    Location loc;

    private Person(){}

    public static class PersonBuilder{
        Person p = new Person();
        public PersonBuilder basicInfo(int id,String name,int age){
            p.id = id;
            p.name = name;
            p.age = age;
            return this;
        }
        public PersonBuilder weight(double weight){
            p.weight = weight;
            return this;
        }
        public PersonBuilder score(int score){
            p.score = score;
            return this;
        }
        public PersonBuilder loc(String street,String roomNo){
            p.loc = new Location(street,roomNo);
            return this;
        }
        public Person build(){
            return p;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", score=" + score +
                ", loc=" + loc +
                '}';
    }
}

