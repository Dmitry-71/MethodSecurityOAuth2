package netology.ru.methodsecurityoauth2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
@Entity
@IdClass(PersonId.class)
public class Person implements Serializable {
    @Id
    @Column(length = 20, nullable = false)
    private String name;
    @Id
    @Column(length = 30, nullable = false)
    private String surname;
    @Id
    @Column(nullable = false)
    private int age;
    @Column(name = "phone_number", length = 12)
    private String phone;
    @Column(name = "city_of_living", length = 30)
    private String city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() &&
                Objects.equals(getName(), person.getName()) &&
                Objects.equals(getSurname(), person.getSurname()) &&
                Objects.equals(getPhone(), person.getPhone()) &&
                Objects.equals(getCity(), person.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getAge(), getPhone(), getCity());
    }
}
