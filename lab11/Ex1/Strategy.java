public interface Strategy {
    Phone[] sort(Phone[] phones);
}

class BubbleSort implements Strategy{
    @Override
    public Phone[] sort(Phone[] phones) {
        return phones;
    }
}

class InsertionSort implements Strategy{
    @Override
    public Phone[] sort(Phone[] phones) {
        return phones;
    }
}

class QuickSort implements Strategy{
    @Override
    public Phone[] sort(Phone[] phones) {
        return phones;
    }
}