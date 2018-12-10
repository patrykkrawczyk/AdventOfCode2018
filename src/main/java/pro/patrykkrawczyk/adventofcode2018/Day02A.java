package pro.patrykkrawczyk.adventofcode2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day02A {

    private static final String INPUT = "input/day02.txt";

    public static void main(String[] args) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(INPUT))) {
            List<String> lines = stream.collect(Collectors.toList());
            int twos = 0;
            int threes = 0;

            for (String line : lines) {
                List<Integer> occurrences = characterOccurrences(line);
                twos += occurrences.contains(2) ? 1 : 0;
                threes += occurrences.contains(3) ? 1 : 0;
            }

            System.out.println(twos * threes);
        }
    }

    private static List<Integer> characterOccurrences(String line) {
        List<Integer> list = Arrays.asList(new Integer[26]);
        Collections.fill(list, 0);
        line.chars().forEach(c -> list.set(c - 97, list.get(c - 97) + 1));
        return list;
    }
}
