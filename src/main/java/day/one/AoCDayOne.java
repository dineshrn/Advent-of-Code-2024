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
        Iterator<Long> leftIt = left.iterator();
        Iterator<Long> rightIt = right.iterator();
        Long distance = 0L;

        while (leftIt.hasNext() && rightIt.hasNext()) {
            Long leftNow = leftIt.next();
            Long rightNow = rightIt.next();
            System.out.println(distance + " " + leftNow + " " + rightNow);
            distance = distance + Math.abs(leftNow - rightNow);
        }

        Long similarityScore = 0L;

        Map<Long, Integer> counter = right.stream().collect(HashMap::new, (a, b) -> {
            if (a.containsKey(b)) {
                a.put(b, (Integer) a.get(b) + 1);
            }
            a.putIfAbsent(b, 1);
        }, (a, b) -> {
        });

        System.out.println(counter);
        similarityScore = left.stream()
                .reduce(0L, (aLong, aLong2) -> aLong + aLong2 * (counter.getOrDefault(aLong2, 0)));

        System.out.println(distance);

        System.out.println(similarityScore);

    }

}
