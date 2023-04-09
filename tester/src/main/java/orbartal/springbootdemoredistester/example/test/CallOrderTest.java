package orbartal.springbootdemoredistester.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class CallOrderTest {

	private final static List<String> VALUES = Arrays.asList("a", "b", "c");

	private final static List<String> elements = new ArrayList<>();

	@Order(0)
	@Test
	public void test0() throws Exception {
		Assertions.assertEquals(0, elements.size());
	}

	@Order(1)
	@Test
	public void test1() throws Exception {
		elements.add(VALUES.get(0));
		Assertions.assertEquals(1, elements.size());
		Assertions.assertEquals(VALUES.get(0), elements.get(0));
	}

	@Order(2)
	@Test
	public void test2() throws Exception {
		elements.add(VALUES.get(1));
		Assertions.assertEquals(2, elements.size());
		Assertions.assertEquals(VALUES.get(0), elements.get(0));
		Assertions.assertEquals(VALUES.get(1), elements.get(1));
	}

	@Order(3)
	@Test
	public void test3() throws Exception {
		elements.add(VALUES.get(2));
		Assertions.assertEquals(3, elements.size());
		Assertions.assertEquals(VALUES.get(0), elements.get(0));
		Assertions.assertEquals(VALUES.get(1), elements.get(1));
		Assertions.assertEquals(VALUES.get(2), elements.get(2));
	}
}