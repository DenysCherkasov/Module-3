package com.cherkasov.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentsMarksDTO {
    private String id;
    private String firstName;
    private String surname;
    private double value;
}
