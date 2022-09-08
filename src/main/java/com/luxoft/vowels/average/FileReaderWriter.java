package com.luxoft.vowels.average;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderWriter {

    private static final DecimalFormatSymbols DECIMAL_SYMBOLS = DecimalFormatSymbols.getInstance();

    static {
        DECIMAL_SYMBOLS.setDecimalSeparator('.');
    }

    private Stream<String> words;

    public Stream<String> getInputFile() {
        try (Stream<String> lines = Files.lines(new File("input.txt").toPath())) {
            this.words = (Stream<String>) lines.flatMap(Pattern.compile("\\W+")::splitAsStream).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read the file input.txt" , e);
        }
        return words;
    }

    public void report(Stream<AverageVowels> averageVowelsStream) throws IOException {
        Stream<String> lines = averageVowelsStream.map(aw ->
                String.format(Locale.US, "({%s}, %d) -> %s",
                        new TreeSet(aw.getWordVowels().getChars()),
                        aw.getWordVowels().getLength(),
                        new DecimalFormat("#.##", DECIMAL_SYMBOLS).format(aw.getAverageNumber()))
        );
        Files.write(new File("output.txt").toPath(), (Iterable<String>) lines::iterator);
    }
}
