package day.one;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
        long distance = 0L;
        left.sort(Long::compare);
        right.sort(Long::compare);

        Iterator<Long> leftIt = left.iterator();
        Iterator<Long> rightIt = right.iterator();

        while (leftIt.hasNext() && rightIt.hasNext()) {
            Long leftNow = leftIt.next();
            Long rightNow = rightIt.next();
            distance = distance + Math.abs(leftNow - rightNow);
        }

        return distance;
    }

    static Long getSimilarityScore(List<Long> left, List<Long> right) {
        Map<Long, Integer> counter = right.stream().collect(HashMap::new, (a, b) -> {
            if (a.containsKey(b)) {
                a.put(b, a.get(b) + 1);
            }
            a.putIfAbsent(b, 1);
        }, (a, b) -> {
        });

        return left.stream().reduce(0L, (aLong, aLong2) -> aLong + aLong2 * (counter.getOrDefault(aLong2, 0)));
    }

}
