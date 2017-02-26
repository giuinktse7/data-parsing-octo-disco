package core;

import java.util.function.Consumer;

public abstract class LineConsumer {
    protected Consumer<String[]> consumer;

    LineConsumer() {}
    public LineConsumer(Consumer<String[]> consumer) {
        this.consumer = consumer;
    }

    public static SingleLineConsumer create(Consumer<String[]> consumer) {
        return new SingleLineConsumer(consumer);
    }

    void accept(String[] data) {
        consumer.accept(data);
    }

    public abstract boolean isFinished();
}
