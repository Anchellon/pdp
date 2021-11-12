package lookandsay;

import java.util.Iterator;

/**
 * @author novo
 * @since 2021/11/11
 */
public interface RIterator<T> extends Iterator<T> {
    T prev();

    boolean hasPrevious();
}
