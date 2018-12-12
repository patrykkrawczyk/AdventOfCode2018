package pro.patrykkrawczyk.adventofcode2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day03A {

    private static final String INPUT = "input/day03.txt";

    public static void main(String[] args) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(INPUT))) {
            AtomicInteger doubled = new AtomicInteger();
            int[][] fabric = new int[1000][1000];
            Map<Point, Point> map = inputToMap(stream);

            for (Entry<Point, Point> entry : map.entrySet()) {
                Point area = entry.getValue();
                int posX = entry.getKey().x;
                int posY = entry.getKey().y;

                IntStream.range(0, area.x).forEach(x -> {
                    IntStream.range(0, area.y).forEach(y -> {
                        if (fabric[posX + x][posY + y] == 1) {
                            doubled.incrementAndGet();
                        }

                        ++fabric[posX + x][posY + y];
                    });
                });
            }

            System.out.println(doubled.get());
        }
    }

    private static Map<Point, Point> inputToMap(Stream<String> stream) {
        final Function<String[], Point> positionMapper = a -> {
            String[] pos = a[2].split(",");
            return new Point(pos[0], pos[1].replace(":", ""));
        };

        final Function<String[], Point> areaMapper = a -> {
            String[] area = a[3].split("x");
            return new Point(area[0], area[1]);
        };

        return stream
                .collect(Collectors.toList())
                .stream()
                .map(l -> l.split(" "))
                .collect(Collectors.toMap(positionMapper, areaMapper));
    }

    private static final class Point {
        private int x;
        private int y;

        private Point(String x, String y) {
            this.x = Integer.parseInt(x);
            this.y = Integer.parseInt(y);
        }
    }
}
