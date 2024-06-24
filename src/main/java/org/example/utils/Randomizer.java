package org.example.utils;

import com.github.javafaker.Faker;
import org.example.pages.Admin.entities.AddUserEntities;
import org.example.pages.Admin.userManagement.addUser.AddUserPage;

public class Randomizer {
    public static Faker faker = new Faker();

    public static AddUserEntities addUserData() {
        AddUserEntities addUserEntities = new AddUserEntities();
        addUserEntities.setEmployeeName(String.valueOf(faker.letterify("?").charAt(0)));
        addUserEntities.setUserName(faker.name().username());
        addUserEntities.setPassword(faker.internet().password());
        return addUserEntities;
    }
}
