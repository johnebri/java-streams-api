import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = getPeople();

        // Filter every female
        // 1. imperative approach
//        List<Person> females = new ArrayList<>();
//        for(Person person : people) {
//            if(person.getGender().equals(Gender.FEMALE)) {
//                females.add(person);
//            }
//        }
//        females.forEach(System.out::println);

        // 2. Declarative approach : YES
        // Filter
//        List<Person> females = people.stream()
//                .filter(person -> person.getGender().equals(Gender.FEMALE))
//                .collect(Collectors.toList());
//        females.forEach(System.out::println);

        // Sort
        List<Person> sorted = people.stream()
//                .sorted(Comparator.comparing(Person::getAge)) // sort by age
//                .sorted(Comparator.comparing(Person::getAge).reversed()) // sort by age in descending order
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed()) // sort by age, then gender in descending order
                .collect(Collectors.toList());
//        sorted.forEach(System.out::println);

        // All match
        // check if every element meets a criteria
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 8);
//        System.out.println(allMatch);

        // Any match // check if any element meets a criteria
        boolean anyMath = people.stream()
                .anyMatch(person -> person.getAge() > 8);
//        System.out.println(anyMath);

        // None match
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("James Bond"));
        System.out.println(noneMatch);

        // Max
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(person -> {
                    System.out.println(person);
                });

        // Min
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(person -> {
                    System.out.println(person);
                });

        // Group
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        });

        // get all females and return just the first name
        Optional<String> oldestFemaleAge = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestFemaleAge.ifPresent(System.out::println);
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("James Bond", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}
