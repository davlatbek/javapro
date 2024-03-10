package ru.innotech.javapro.hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamHw {
    public static void main(String[] args) {

        //Реализуйте удаление из листа всех дубликатов
        List<String> dublicateList = new ArrayList<>();
        dublicateList.add("cat");
        dublicateList.add("cat");
        dublicateList.add("dog");
        dublicateList.add("crocodile");
        dublicateList = dublicateList.stream().distinct().collect(Collectors.toList());
        System.out.println(dublicateList);

        //Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        List<Integer> intList = Arrays.asList(1, -1, 10, 12, 100, 10000, -500);
        Integer integer = intList.stream().sorted(Comparator.reverseOrder()).skip(2).findFirst().get();
        System.out.println(integer);

        //Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9,
        // в отличие от прошлой задачи здесь разные 10 считает за одно число)
        List<Integer> intList2 = Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13);
        Integer unique = intList2.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2).findFirst().orElseThrow();
        System.out.println(unique);

        //Имеется список объектов типа Сотрудник (имя, возраст, должность),
        // необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
        List<Employee> employees = List.of(
                new Employee("Илья", 22, "developer"),
                new Employee("Владимир", 29, "engineer"),
                new Employee("Алина", 19, "qa"),
                new Employee("Владимир", 41, "devops"),
                new Employee("Константин", 36, "qa"),
                new Employee("Марина", 23, "engineer"),
                new Employee("Алла", 20, "engineer"),
                new Employee("Николай", 37, "engineer")
        );
        System.out.println(employees.stream()
                .filter(s -> s.getPosition().equals("engineer"))
                .sorted((e1, e2) -> Integer.compare(e2.getAge(), e1.getAge()))
                .map(Employee::getName)
                .limit(3)
                .collect(Collectors.toList()));

        //Имеется список объектов типа Сотрудник (имя, возраст, должность),
        // посчитайте средний возраст сотрудников с должностью «Инженер»
        System.out.println(
                employees.stream()
                        .filter(s -> s.getPosition().equals("engineer"))
                        .mapToDouble(s -> (double)s.getAge()).average()
                        .orElseThrow()
        );

        //Найдите в списке слов самое длинное
        System.out.println(
                Stream.of("forsake", "extracurriculum", "it", "a", "2", "indigenous", "вирус")
                        .max(Comparator.comparingInt(s -> s.length()))
                        .orElseThrow());

        //Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
        // Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
        System.out.println(
                Stream.of(("work leg day off day season off")
                                .split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        );

        //Отпечатайте в консоль строки из списка в порядке увеличения длины слова,
        // если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
        System.out.println("______");
        List<String> list = Arrays.asList("monitor", "computer", "mouse", "screen", "click", "disk", "user");
        list.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);

        //Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом,
        // найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
        String[] stringArray = new String[]{
                "vibe shadow qwer soda user",
                "pown mommy neon amber get",
                "asdf olaf ex morning slip",
                "ornament zxcv esse job qwerqwer",
                "dell zxcv uuid lepricom knot"
        };
        System.out.println("______");
        System.out.println(
            Arrays.stream(stringArray)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .max(Comparator.comparingInt(String::length))
                .orElseThrow());



    }
}
