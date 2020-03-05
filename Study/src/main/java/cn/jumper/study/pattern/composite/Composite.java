package cn.jumper.study.pattern.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jumper
 * 2020/3/1
 */
public class Composite {
}

interface ValueContainer extends Iterable<Integer> {
    default Integer sum() {
        Integer sum = 0;
        Iterator<Integer> iterator = iterator();
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        return sum;
    }
}

class SingleValue implements ValueContainer {
    public int value;

    // please leave this constructor as-is
    public SingleValue(int value) {

        this.value = value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Collections.singleton(this.value).iterator();
    }
}

class ManyValues extends ArrayList<Integer> implements ValueContainer {

}

class MyList {
    private List<ValueContainer> valueContainers;

    MyList(List<ValueContainer> valueContainers) {
        this.valueContainers = valueContainers;
    }

    long sum() {
        long sumOfContainers = 0L;
        Iterator<ValueContainer> iterator = valueContainers.iterator();
        while (iterator.hasNext()) {
            ValueContainer next = iterator.next();
            sumOfContainers += next.sum();
        }
        return sumOfContainers;
    }

}
