package core;

import java.util.function.Consumer;

public class LineConsumer {

    private boolean finished = false;

    protected Consumer<String[]> consumer;

    protected LineConsumer() {
    }

    public LineConsumer(Consumer<String[]> consumer) {
        this.consumer = consumer;
    }

    public static LineConsumer create(Consumer<String[]> consumer) {
        return new LineConsumer(consumer);
    }

    void accept(String[] data) {
        consumer.accept(data);
        finished = true;
    }

    public boolean isFinished() {
        return finished;
    }
}