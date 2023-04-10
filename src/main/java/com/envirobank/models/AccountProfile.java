package com.envirobank.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                 //objects in database
@Data                   //gettera and setters for each entry
@AllArgsConstructor     //can create a object that takes in all attributes
@NoArgsConstructor      //can create a object with empty contstructor
public class AccountProfile {

    @Id
    @GeneratedValue     //auto increments unique id
    private int id;
    private String accountHolderName;
    private String accountHolderSurname;
    private String httpImageLink;
}
