package com.rabilmiraliyev.dto;

import lombok.Data;
import java.util.List;
@Data
public class PersonDto {

    private Long id;

    private String firstname;

    private String lastname;

    private List<String> adresses;
}

