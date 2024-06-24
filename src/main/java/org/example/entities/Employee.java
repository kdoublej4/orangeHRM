package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private long salary;
    private String department;
}
