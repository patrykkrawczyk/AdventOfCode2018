package pro.patrykkrawczyk.adventofcode2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Day03A {

    private static final String EMPTY = ".";
    private static final String OVERTAKEN = "X";
    private static final String INPUT = "input/day03.txt";

    public static void main(String[] args) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(INPUT))) {
            List<Claim> claims = parseInput(stream);

            AtomicInteger doubled = new AtomicInteger();
            String[][] fabric = prepareEmptyFabric();

            for (Claim c : claims) {
                IntStream.range(0, c.areaX).forEach(x -> {
                    IntStream.range(0, c.areaY).forEach(y -> {
                        int fx = c.posX + x;
                        int fy = c.posY + y;

                        if (fabric[fy][fx].equals(EMPTY)) {
                            fabric[fy][fx] = c.id;
                        } else if (!fabric[fy][fx].equals(OVERTAKEN)) {
                            doubled.incrementAndGet();
                            fabric[fy][fx] = OVERTAKEN;
                        }
                    });
                });
            }

            System.out.println(doubled.get());
        }
    }

    private static List<Claim> parseInput(Stream<String> stream) {
        return stream.map(Claim::new).collect(toList());
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
        }
    }
}
