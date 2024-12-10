package day.one;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Trying to solve the AoC day one problem
 */
public class AoCDayOne {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<List<Long>> inputs = inputReader(scanner);
        List<Long> left = inputs.get(0);
        List<Long> right = inputs.get(1);

        Long totalDistance = getTotalDistance(left, right);

        Long similarityScore = getSimilarityScore(left, right);

        System.out.println("Total Distance : " + totalDistance + " Similarity Score " + similarityScore);

    }

    static List<List<Long>> inputReader(Scanner scanner) {
        System.out.println("Please enter the numbers");
        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();

        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            String[] numbers = line.split(" {3}");
            left.add(Long.parseLong(numbers[0]));
            right.add(Long.parseLong(numbers[1]));
        }
        return List.of(left, right);
    }

    static Long getTotalDistance(List<Long> left, List<Long> right) {
        left.sort(Long::compare);
        right.sort(Long::compare);

        return IntStream.range(0, Math.min(left.size(), right.size()))
                .mapToLong(i -> Math.abs(left.get(i)) - right.get(i)).sum();
    }

    static Long getSimilarityScore(List<Long> left, List<Long> right) {
        Map<Long, Long> counter = right.stream().collect(Collectors.groupingBy(num -> num, Collectors.counting()));

        return left.stream().mapToLong(aLong2 -> aLong2 * (counter.getOrDefault(aLong2, 0L))).sum();
    }

}
