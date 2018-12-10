package pro.patrykkrawczyk.adventofcode2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day01B {

    private static final String INPUT = "input/day01.txt";

    public static void main(String[] args) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(INPUT))) {
            List<Integer> numbers = stream.mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

            Set<Integer> past = new HashSet<>();
            Integer frequency = 0;

            infinite:
            while (true) {
                for (Integer n : numbers) {
                    frequency += n;

                    if (past.contains(frequency)) {
                        break infinite;
                    }

                    past.add(frequency);
                }
            }

            System.out.println(frequency);
        }
    }
}
