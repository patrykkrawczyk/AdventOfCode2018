package pro.patrykkrawczyk.adventofcode2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class Day03B {

    private static final String EMPTY = ".";
    private static final String INPUT = "input/day03.txt";

    public static void main(String[] args) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(INPUT))) {
            Map<String, Claim> claims = parseInput(stream);

            String[][] fabric = prepareEmptyFabric();

            for (Entry<String, Claim> e : claims.entrySet()) {
                Claim c = e.getValue();

                IntStream.range(0, c.areaX).forEach(x -> {
                    IntStream.range(0, c.areaY).forEach(y -> {
                        int fx = c.posX + x;
                        int fy = c.posY + y;

                        if (fabric[fy][fx].equals(EMPTY)) {
                            fabric[fy][fx] = c.id;
                        } else {
                            claims.get(fabric[fy][fx]).complete = false;
                            c.complete = false;
                        }
                    });
                });
            }

            claims.values().stream().filter(c -> c.complete).forEach(System.out::println);
        }
    }

    private static Map<String, Claim> parseInput(Stream<String> stream) {
        return stream.map(Claim::new).collect(toMap(c -> c.id, Function.identity()));
    }

    private static String[][] prepareEmptyFabric() {
        String[][] fabric = new String[1000][1000];

        for (String[] row : fabric) {
            Arrays.fill(row, EMPTY);
        }

        return fabric;
    }

    private static final class Claim {
        private String id;
        private int posX;
        private int posY;
        private int areaX;
        private int areaY;
        private boolean complete;

        private Claim(String line) {
            String[] bySpaces = line.split(" ");

            String[] pos = bySpaces[2].split(",");
            pos[1] = pos[1].replace(":", "");

            String[] area = bySpaces[3].split("x");

            this.id = bySpaces[0].substring(1);
            this.posX = Integer.parseInt(pos[0]);
            this.posY = Integer.parseInt(pos[1]);
            this.areaX = Integer.parseInt(area[0]);
            this.areaY = Integer.parseInt(area[1]);
            this.complete = true;
        }

        @Override
        public String toString() {
            return "Claim{id=" + id + '}';
        }
    }
}
