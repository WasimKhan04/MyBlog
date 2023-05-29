package com.Crud_Example.payload;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
public class ClientDto {

    private String id;
    @NotEmpty
    @Size(min = 2, message = "Client name should have at least 2 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "Client last name should have at least 2 characters")
    private String lastName;
    private String city;
    @Email
    private String email;
    @NotEmpty
    @Size(min = 10, message = "Mobile number should have at least 10 digits")
    private String mobile;
}
