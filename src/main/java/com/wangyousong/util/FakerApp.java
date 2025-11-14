package com.wangyousong.util;

import net.datafaker.Faker;

public class FakerApp {

    public static void main(String[] args) {
        Faker faker = new Faker();
        String name = faker.name().fullName(); // Miss Samanta Schmidt
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton
        System.out.println("Name: " + name + " FirstName: " + firstName + " LastName: " + lastName);

        String streetAddress = faker.address().streetAddress();
        System.out.println("Street Address: " + streetAddress);

        System.out.println(faker.app().name());
        System.out.println(faker.app().version());
        System.out.println(faker.app().author());
    }
}
