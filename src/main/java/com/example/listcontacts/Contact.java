package com.example.listcontacts;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.UUID;

@Data
@FieldNameConstants
public class Contact {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
}
