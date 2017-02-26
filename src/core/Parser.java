package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Parser {

    final private String path;
    private static final String DEFAULT_DELIMITER = " ";

    private Deque<LineConsumer> consumerStack = new ArrayDeque<>();
    private String delimiter = DEFAULT_DELIMITER;

    public Parser(String path, LineConsumer... consumers) {
        this.path = path;

        consumerStack.addAll(Arrays.asList(consumers));
    }

    public void parse() {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.map(this::splitByDelimiter).forEach(this::consumeData);
        } catch (IOException e) {
            System.out.println("Could not find file: " + path);
            e.printStackTrace();
        }
    }

    private void updateConsumer() {
        if (consumerStack.getFirst().isFinished())
            consumerStack.removeFirst();
    }

    private void consumeData(String[] data) {
        updateConsumer();
        consumerStack.getFirst().accept(data);
    }

    private String[] splitByDelimiter(String line) {
        return line.split(delimiter);
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}