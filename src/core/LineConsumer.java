package core;

import java.util.function.Consumer;

public abstract class LineConsumer {
    protected Consumer<String[]> consumer;

    LineConsumer() {}
    public LineConsumer(Consumer<String[]> consumer) {
        this.consumer = consumer;
    }

    void accept(String[] data) {
        consumer.accept(data);
    }

    public abstract boolean isFinished();
}
