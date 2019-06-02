package kdf11_proj1;
import java.util.Arrays;
import java.util.Random;

public class SetDriver {
	public static void main(String[] args) throws SetFullException {
		// this code is absolutely identical except for "new ArrayBag"

		SetInterface<String> setOfStrings = new Set<String>(3);

		setOfStrings.add("hello");
		setOfStrings.add("hello");
		System.out.printf("There are %d items in the set.\n", setOfStrings.getSize());
		System.out.printf("The contents are: %s\n", Arrays.toString(setOfStrings.toArray()));

		setOfStrings.add("goodbye");
		setOfStrings.add("cat");
		setOfStrings.add("dog");



		System.out.printf("There are %d items in the set.\n", setOfStrings.getSize());
		System.out.printf("The contents are: %s\n", Arrays.toString(setOfStrings.toArray()));

		System.out.println("Now let's dump out the set...");

		setOfStrings.clear();

System.out.printf("There are %d items in the set.\n", setOfStrings.getSize());
		System.out.printf("The contents are: %s\n", Arrays.toString(setOfStrings.toArray()));

		setOfStrings.add("goodbye");
		setOfStrings.add("cat");
		setOfStrings.add("dog");
		setOfStrings.add("goodbyes");
		setOfStrings.add("cats");
		setOfStrings.add("dogs");

		System.out.printf("There are %d items in the set.\n", setOfStrings.getSize());
				System.out.printf("The contents are: %s\n", Arrays.toString(setOfStrings.toArray()));

		setOfStrings.remove();
		setOfStrings.remove();

		System.out.printf("There are %d items in the set.\n", setOfStrings.getSize());
				System.out.printf("The contents are: %s\n", Arrays.toString(setOfStrings.toArray()));


		setOfStrings.clear();
//		String[] myArray;
//		myArray = new String[] {"dog", "cat", "frog", "hi", "34", "grape"};
//		SetInterface<String> setOfStrings = new Set<String>(myArray);
//		System.out.printf("The contents are: %s\n", Arrays.toString(setOfStrings.toArray()));
//				System.out.printf("The contents are: %s\n", Arrays.toString(myArray));
		}
	}
