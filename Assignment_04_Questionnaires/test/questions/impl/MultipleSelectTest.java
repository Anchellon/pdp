package questions.impl;

import org.junit.Before;
import org.junit.Test;
import questions.MultipleSelect;

import static org.junit.Assert.*;

/**
 * @author novo
 * @since 2021/10/14
 */
public class MultipleSelectTest {

    MultipleSelect multipleSelect;

    @Before
    public void setUp() throws Exception {
        multipleSelect = new MultipleSelect("aaa", "1 3", "z", "b", "c");
    }

    @Test
    public void answer() {
        assertEquals("Correct", multipleSelect.answer("1 3"));
        assertEquals("Incorrect", multipleSelect.answer("2 3"));
        assertEquals("Incorrect", multipleSelect.answer("3"));
        assertEquals("Correct", multipleSelect.answer("3 1"));

    }
}