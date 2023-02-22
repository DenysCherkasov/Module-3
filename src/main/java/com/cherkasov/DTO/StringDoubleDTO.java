package com.cherkasov.DTO;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StringDoubleDTO {
    @Id
    private String name;
    private double value;
}
