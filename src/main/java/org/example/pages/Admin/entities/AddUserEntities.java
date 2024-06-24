package org.example.pages.Admin.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddUserEntities {
    private String employeeName;
    private String userName;
    private String password;
}
