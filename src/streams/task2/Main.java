package streams.task2;

import java.util.*;

import static java.util.stream.Collectors.joining;

public class Main {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
		List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
		List<Person> persons = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10_000_000; i++) {
			persons.add(new Person(
					names.get(random.nextInt(names.size())),
					families.get(random.nextInt(families.size())),
					random.nextInt(100),
					Sex.values()[random.nextInt(Sex.values().length)],
					Education.values()[random.nextInt(Education.values().length)])
			);
		}

		System.out.println("Кол-во несовершеннолетних: " + persons.stream()
				.filter(p -> p.getAge() < 18)
				.count());
		System.out.println("Фамилии призывников: " + persons.stream()
				.filter(p -> p.getSex().equals(Sex.MAN))
				.filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
				.map(Person::getFamily)
				.distinct()
				.collect(joining(", ")));
		System.out.println("Работоспособные люди: " + persons.stream()
				.filter(p -> p.getEducation().equals(Education.HIGHER))
				.filter(p -> {
					int age = p.getAge();
					boolean isMan = p.getSex().equals(Sex.MAN);
					return age >= 18 && (isMan && age < 65 || !isMan && age < 60);
				})
				.sorted(Comparator.comparing(Person::getFamily))
				.map(Person::toString)
				.collect(joining("\n")));
	}
}
