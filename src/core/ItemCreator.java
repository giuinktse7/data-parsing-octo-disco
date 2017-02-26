package core;

public abstract class ItemCreator<E> extends LineConsumer {
    private static final int AMOUNT_NOT_SPECIFIED = -1;

    private E currentItem;
    protected int remainingAmount = AMOUNT_NOT_SPECIFIED;

    public ItemCreator() {
        consumer = this::parseData;
    }

    protected abstract int getTotalItemAmount();

    @Override
    public boolean isFinished() {
        return remainingAmount == 0 && currentItem == null;
    }

    @Override
    void accept(String[] data) {
        if (remainingAmount == AMOUNT_NOT_SPECIFIED)
            remainingAmount = getTotalItemAmount();

        consumer.accept(data);
    }

    public abstract void parseData(String[] data);

    protected boolean hasItem() {
        return currentItem != null;
    }

    protected E getCurrentItem() {
        return currentItem;
    }

    protected void setCurrentItem(E newItem) {
        if (newItem != null) --remainingAmount;

        this.currentItem = newItem;
    }
}