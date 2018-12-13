package pro.patrykkrawczyk.adventofcode2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Day03A {

    private static final String INPUT = "input/day03.txt";

    public static void main(String[] args) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(INPUT))) {
            AtomicInteger doubled = new AtomicInteger();
            int[][] fabric = new int[1000][1000];
            List<Claim> claims = parseInput(stream);

            for (Claim c : claims) {
                IntStream.range(0, c.areaX).forEach(x -> {
                    IntStream.range(0, c.areaY).forEach(y -> {
                        if (fabric[c.posX + x][c.posY + y] == 1) {
                            doubled.incrementAndGet();
                        }

                        ++fabric[c.posX + x][c.posY + y];
                    });
                });
            }

            System.out.println(doubled.get());
        }
    }

    private static List<Claim> parseInput(Stream<String> stream) {
        return stream.map(Claim::new).collect(toList());
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
