package io.github.jonarzz;

import static java.util.stream.Collectors.toList;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Verifier {

    private static final int WARMUPS_COUNT = 100;
    private static final int VERIFICATIONS_COUNT = 3;

    private static final List<String> TEST_ARGS =
            IntStream.rangeClosed(1, 100)
                     .mapToObj(i -> List.of("test", "abc", "qwe123", "anagram", "stet", "cab"))
                     .flatMap(Collection::stream)
                     .collect(toList());

    public static void main(String[] args) {
        Supplier<Solution> standardSupplier = StandardSolution::new;
        Supplier<Solution> hackySupplier = HackySolution::new;
        warmUp(hackySupplier);
        System.out.println(">>> Hacky solution <<<");
        verify(hackySupplier);
        System.out.println("--- --- --- ---\n");
        warmUp(standardSupplier);
        System.out.println(">>> Standard solution <<<");
        verify(standardSupplier);
    }

    private static void warmUp(Supplier<Solution> solutionSupplier) {
        var defaultOut = System.out;
        try {
            System.setOut(new NoOpStream());
            for (int i = 0; i < WARMUPS_COUNT; i++) {
                verify(solutionSupplier, Verifier::forEach);
                verify(solutionSupplier, Verifier::indexedLoop);
            }
        } finally {
            System.setOut(defaultOut);
        }
    }

    private static void verify(Supplier<Solution> solutionSupplier) {
        System.out.println("> for each");
        verify(solutionSupplier, Verifier::forEach);
        System.out.println();

        System.out.println("> indexed loop");
        verify(solutionSupplier, Verifier::indexedLoop);
        System.out.println();
    }

    private static void verify(Supplier<Solution> solutionSupplier, Consumer<List<List<String>>> resultsIterator) {
        for (int i = 0; i < VERIFICATIONS_COUNT; i++) {
            Collections.shuffle(TEST_ARGS);
            var stopWatch = new StopWatch();
            System.out.println("[" + i + "] Creating:\t\t\t\t" + stopWatch.diff());
            var solution = solutionSupplier.get();
            System.out.println("[" + i + "] Starting:\t\t\t\t" + stopWatch.diff());
            var results = solution.groupAnagrams(TEST_ARGS.toArray(String[]::new));
            System.out.println("[" + i + "] Result calculated:\t\t" + stopWatch.diff());
            resultsIterator.accept(results);
            System.out.println("[" + i + "] > Verification done:\t" + stopWatch.diff());
        }
    }

    private static void indexedLoop(List<List<String>> results) {
        for (int i = 0; i < results.size(); i++) {
            blackHole(results.get(i));
        }
    }

    private static void forEach(List<List<String>> results) {
        for (var result : results) {
            blackHole(result);
        }
    }

    private static void blackHole(List<String> result) {
        if (result.isEmpty()) {
            System.out.println("Never happens, but we want the loop to run");
        }
    }

    private static class NoOpStream extends PrintStream {

        NoOpStream() {
            super(new OutputStream() {
                @Override
                public void write(int b) {
                    if (b < -5) {
                        System.out.println("Never happens, but we want the loop to run");
                    }
                }
            });
        }
    }

}
