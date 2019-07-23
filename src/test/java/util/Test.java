package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		List<Integer> sort = new ArrayList<>();
		sort.add(10);
		sort.add(1);
		sort.add(20);
		sort.add(5);
		sort.add(8);
		sort.add(8);
		sort.add(19);
		sort.add(100);
		Collections.sort(sort, (x, y) -> x - y);
	}
}
