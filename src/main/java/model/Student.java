package model;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class Student {

    private String name;
    private String lastName;
    private LocalDate birthDate;
    private Integer age;
    @NonNull
    private Boolean hasADegree;
    @NonNull
    private String dni;

}