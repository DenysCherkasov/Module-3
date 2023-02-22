package com.cherkasov.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    private String id;
    private String firstName;
    private String surname;
    private int age;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return age == teacher.age && Objects.equals(id, teacher.id)
                && Objects.equals(firstName, teacher.firstName)
                && Objects.equals(surname, teacher.surname)
                && Objects.equals(subject, teacher.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName,
                surname, age, subject);
    }

    @Override
    public String toString() {
        return String.format("Teacher (ID: %s, First name: %s, " +
                        "Surname: %s, Age: %s, Subject: %s)%n",
                id, firstName, surname, age, subject);
    }
}
