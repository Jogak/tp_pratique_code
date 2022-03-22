package org.sam.mines.examples.patterns;

public class UserBuilder {
    private String firstname;
    private String lastname;
    private int age;
    private double height;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserBuilder lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserBuilder age(int age) {
        this.age = age;
        return this;
    }

    public UserBuilder height(double height) {
        this.height = height;
        return this;
    }

    public User build() {
        return new User(firstname, lastname, age, height);
    }
}
