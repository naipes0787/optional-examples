import model.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class Application {

    public static void main(String[] args) {
        Student studentWithRequiredFields = new Student(Boolean.FALSE, "33230142");
        Student studentWithName = new Student(Boolean.FALSE, "33230142");
        studentWithName.setName("Carlitos");
        Student studentWithBirthDate = new Student(Boolean.FALSE, "33230142");
        studentWithBirthDate.setBirthDate(LocalDate.of(1990, 02, 20));

        System.out.println("\nUsing Java < 8: ");
        System.out.println(getStudentNameToPrint(studentWithRequiredFields));
        System.out.println(getStudentNameToPrint(studentWithName));

        System.out.println("\n\nUsing Java >= 8: ");
        System.out.println(getStudentNameToPrintUsingOptional(studentWithRequiredFields));
        System.out.println(getStudentNameToPrintUsingOptional(studentWithName));

        calculateStudentAge(studentWithBirthDate);
        System.out.println("Age: " + studentWithBirthDate.getAge());
    }

    public static String getStudentNameToPrint(Student student){
        if(student != null) {
            if (student.getName() != null){
                return student.getName();
            }
        }
        return "There is no name to print";
    }

    public static String getStudentNameToPrintUsingOptional(Student student){
        /* An Optional describes an object that can or can't contains something inside.
         * So, with ofNullable I'm saying that student could be null (In that case optionalName
         * will have Optional.EMPTY as its value)
         * Then, using map I'm saying that if that value is not empty, then apply the getName method from Student */
        Optional<String> optionalName = Optional
                .ofNullable(student)
                .map(Student::getName);
        return optionalName.orElse("There is no name to print");
    }

    public static void calculateStudentAge(Student student){
        /* The idea here is to show that you can use ifPresent to do some stuff only if some value really exists */
        Optional<LocalDate> optionalBirthYear = Optional
                .ofNullable(student)
                .map(Student::getBirthDate);
        optionalBirthYear.ifPresent(birthDate -> calculateAgeFromDate(birthDate, student));
    }

    /**
     * Given a date and a student, calculates how old is the student and set it to its
     * attribute
     * @param date
     * @param student
     */
    private static void calculateAgeFromDate(LocalDate date, Student student){
        Period diff = Period.between(date, LocalDate.now());
        student.setAge(diff.getYears());
    }

}