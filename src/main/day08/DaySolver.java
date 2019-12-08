package main.day08;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import main.AbstractSolver;
import utils.AoCFileReader;

public class DaySolver extends AbstractSolver {

    public DaySolver(String day) {
        super(day);
    }

    private final int width = 25;
    private final int height = 6;
    private final int fullSize = width * height;

    @Override
    public void solvePart1() {
        String input = AoCFileReader.readOneLine(new File(this.inputFile1));
        List<char[]> pic = new ArrayList<>();

        int pos = 0;
        while (pos < input.length()) {
            pic.add(input.substring(pos, pos + fullSize).toCharArray());
            pos += fullSize;
        }

        long min0 = Integer.MAX_VALUE;
        int pos0 = 0;
        for (int i = 0; i < pic.size(); i++) {
            Stream<Character> temp = CharBuffer.wrap(pic.get(i)).chars().mapToObj(ch -> (char)ch);
            long min = temp.filter(c -> c.equals('0')).count();
            if (min < min0) {
                min0 = min;
                pos0 = i;
            }
        }

        long res = CharBuffer.wrap(pic.get(pos0)).chars().mapToObj(ch -> (char)ch).filter(c -> c.equals('1')).count() *
        CharBuffer.wrap(pic.get(pos0)).chars().mapToObj(ch -> (char)ch).filter(c -> c.equals('2')).count();

        System.out.println(res);
    }

    @Override
    public void solvePart2() {
        String input = AoCFileReader.readOneLine(new File(this.inputFile2));
        List<char[]> pic = new ArrayList<>();

        int pos = 0;
        while (pos < input.length()) {
            pic.add(input.substring(pos, pos + fullSize).toCharArray());
            pos += fullSize;
        }

        char[] res = pic.get(0);

        for (int i = 1; i < pic.size(); i++) {
            for (int j = 0; j < pic.get(i).length; j++) {
                if (res[j] == '2') {
                    res[j] = pic.get(i)[j];
                }
            }
        }
        pos = 0;
        System.out.println();
        while (pos < res.length) {
            for (int i = pos; i < pos+width; i++) {
                System.out.print(res[i] == '1' ? '*' : ' ');
            }
            System.out.println();
            pos += width;
        }

    }

}
