package streams.task1;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
		list = new LinkedList<>(list);
		list.removeIf(n -> n <= 0);
		list.removeIf(n -> n % 2 == 1);
		Collections.sort(list);
		System.out.println(list);
	}
}
