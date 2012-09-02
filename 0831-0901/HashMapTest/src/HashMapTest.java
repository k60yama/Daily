import java.util.ArrayList;
import java.util.HashMap;

public class HashMapTest {
	public static void main(String[] args) {
		
		/*
		HashMap map = new HashMap();
		map.put("NAME", "“c’†");
		map.put("phone number", "08043438830");
		System.out.println(map.get("NAME"));
		System.out.println(map.get("phone number"));
		*/
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("NAME", "ƒmƒQƒCƒ‰");
		map.put("NAME", "‚Ğ‚å[ƒhƒ‹");
		map.put("AGE", "30");
		map.put("AGE", "39");
		
		/*
		System.out.println("NAME: " + map.get("NAME"));
		System.out.println("AGE: " + map.get("AGE"));
		*/
		
		System.out.println("HashMap:" + map);
		
		ArrayList<String> array = new ArrayList<String>();
		array.add("test1");
		array.add("test2");
		array.add("test3");
		array.add("test4");
		array.add("test5");
		
		for(int i=0; i<array.size(); i++){
			System.out.println(array.get(i));
		}
		
		
	}
}
