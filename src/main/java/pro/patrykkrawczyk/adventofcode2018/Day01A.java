package pro.patrykkrawczyk.adventofcode2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day01A {

    private static final String INPUT = "input/day01.txt";

    public static void main(String[] args) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(INPUT))) {
            int sum = stream.mapToInt(Integer::parseInt).sum();
            System.out.println(sum);
        }
    }
}
