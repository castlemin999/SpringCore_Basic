package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Lombok을 통해 getter setter 생략 가능
@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("sasdsa");

        String name = helloLombok.getName();
        System.out.println("name = " + name);
    }
}
