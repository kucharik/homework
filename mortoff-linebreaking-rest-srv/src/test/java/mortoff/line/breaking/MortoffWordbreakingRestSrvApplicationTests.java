package mortoff.line.breaking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class MortoffWordbreakingRestSrvApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void aaa() {
		int maxN = 10;
		List<String> elements = new ArrayList<String>();

		List<String> list = Stream.of(a.replaceAll("\\s+", " ").split(" ")).map(elem -> new String(elem + " "))
				.collect(Collectors.toList());
		
		boolean hasTrailingSpace = a.endsWith(" ");
		
		StringBuffer line = new StringBuffer();
		for (String e : list) {
			if ( (line.length() + e.length() - 1) <= maxN) {
				if ( (line.length() + e.length()) > maxN) {
					line.append(e.trim());
				} else {
					line.append(e);
				}
			} else {
				elements.add(line.toString());
				line = new StringBuffer();
				if (e.length() >= maxN) {
					line.append(e.trim());
				} else {
					line.append(e);
				}
			}
			System.out.println("LINE: " + line);
			System.out.println("E: " + e);
		}
		if (line.length() > 0) {
			if (!hasTrailingSpace) {
				elements.add(line.toString().trim());
			} else {
				elements.add(line.toString());
			}	
		}

		list.forEach(e -> System.out.println("-" + e + "-"));
		System.out.println("***************************************************");
		elements.forEach(f -> System.out.println("-" + f + "-"));
		System.out.println("***************************************************");
	}


	static String a10 = "This iss a test sentence to smoke-testt line breaking.";
	static String b5 = "This is a test where n is very tiny.";
	
	static String a = a10;

}
