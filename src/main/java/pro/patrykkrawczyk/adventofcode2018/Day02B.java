package pro.patrykkrawczyk.adventofcode2018;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day02B {

    private static final String INPUT = "input/day02.txt";

    public static void main(String[] args) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(INPUT))) {
            List<String> lines = stream.collect(Collectors.toList());

            for (int i = 0; i < lines.size() - 1; ++i) {
                String base = lines.get(i);

                for (int j = i + 1; j < lines.size(); ++j) {
                    int position = differ(base, lines.get(j));

                    if (position >= 0) {
                        String result = removeCharAt(base, position);
                        System.out.println(result);
                        return;
                    }
                }
            }
        }
    }

    private static String removeCharAt(String base, int index) {
        return new StringBuilder(base).deleteCharAt(index).toString();
    }

    private static int differ(String a, String b) {
        int pos = -1;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                if (pos == -1) {
                    pos = i;
                } else {
                    return -1;
                }
            }
        }

        return pos;
    }
}
