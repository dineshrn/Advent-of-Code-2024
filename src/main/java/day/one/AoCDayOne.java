package day.one;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
Trying to solve the AoC day one problem
 */
public class AoCDayOne {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the numbers");
        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();

        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            String[] numbers = line.split("   ");
            left.add(Long.parseLong(numbers[0]));
            right.add(Long.parseLong(numbers[1]));
        }

        left.sort(Long::compare);
        right.sort(Long::compare);

        System.out.println("Input left: " + left.toString());
        System.out.println("Input right: " + right.toString());

        Long distances = IntStream.range(0, left.size()).mapToLong(index -> {
            Long locationLeft = left.get(index);
            Long locationRight = right.get(index);
            return Math.abs(locationLeft - locationRight);
        }).reduce(Long::sum).orElseThrow();

        System.out.println(distances);

    }

}
