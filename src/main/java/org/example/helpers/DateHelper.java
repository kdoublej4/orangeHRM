package org.example.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class DateHelper {
    public static String randomDateGenerate(String pattern) {
        LocalDate randomDate = generateRandomDate(1980, 2000);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);

        return randomDate.format(formatter);
    }

    private static LocalDate generateRandomDate(int startYear, int endYear) {
        long startEpochDay = LocalDate.of(startYear, 1, 1).toEpochDay();
        long endEpochDay = LocalDate.of(endYear, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}
