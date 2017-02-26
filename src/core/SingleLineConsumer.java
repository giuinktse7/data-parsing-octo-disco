package core;

import java.util.function.Consumer;

public class SingleLineConsumer extends LineConsumer {
    private boolean finished = false;

    public SingleLineConsumer(Consumer<String[]> consumer) {
        super(consumer);
    }

    public static SingleLineConsumer create(Consumer<String[]> consumer) {
        return new SingleLineConsumer(consumer);
    }

    @Override
    void accept(String[] data) {
        super.accept(data);

        finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}