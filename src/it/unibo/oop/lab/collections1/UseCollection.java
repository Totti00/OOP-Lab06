package it.unibo.oop.lab.collections1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Example class using {@link java.util.List} and {@link java.util.Map}.
 * 
 */
public final class UseCollection {
	
	
	
	private static final int START = 1000;
	private static final int STOP = 2000;
	
	private static final int TO_MS = 1_000_000;
	private static final int TEMPO = 100_000;
	private static final int LEGGI = 1_000;
	
	
	private static final long AFRICA_POPULATION = 1_110_635_000;
	private static final long AMERICAS_POPULATION = 972_005_000;
	private static final long ANTARCTICA_POPULATION = 0;
	private static final long ASIA_POPULATION = 4_298_723_000l;
	private static final long EUROPE_POPULATION = 742_452_000;
	private static final long OCEANIA_POPULATION = 38_304_000;
	
    private UseCollection() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
    	
    	final ArrayList<Integer> l1 = new ArrayList<Integer>();
    	for (int i = START; i < STOP; i++) {
    		l1.add(i);
    	}
    	
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
    	
    	final LinkedList<Integer> ll1 = new LinkedList<Integer>(l1);
    	
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
    	
    	final var c = l1.get(l1.size()-l1.size());
    	l1.set(l1.size()-l1.size(), l1.get(l1.size()-1));
    	l1.set(l1.size()-1, c);
    	
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
    	
    	for(final var h: l1) {
    		System.out.println(h + ", ");
    	}
    	
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
    	
    	long time = System.nanoTime();
    	
    	for(int i = 0; i < TEMPO; i++) {
    		l1.add(0, i);
    	}
    	
    	time = System.nanoTime() - time;
    	
    	System.out.println("Ho inserito " + TEMPO + " elementi in " + time + "ns in un ArrayList ( " + time / TO_MS + " ms )");
    	
    	time = System.nanoTime();
    	
    	for(int i = 0; i < TEMPO; i++) {
    		ll1.add(0, i);
    	}
    	
    	time = System.nanoTime() - time;
    	
    	System.out.println("Ho inserito " + TEMPO + " elementi in " + time + "ns in un LinkedList ( " + time / TO_MS + " ms )");
    	
        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
    	
    	time = System.nanoTime();
    	
    	for(int i = 0; i < LEGGI; i++) {
    		l1.get(( l1.size() / 2 ));
    	}
    	time = System.nanoTime() - time;
    	
    	System.out.println("Ho letto " + LEGGI + " elementi in " + time + "ns in un ArrayList ( " + time / TO_MS + " ms )");
    	
    	
    	time = System.nanoTime();
    	
    	for(int i = 0; i < LEGGI; i++) {
    		ll1.get(( ll1.size() / 2 ));
    	}
    	
    	time = System.nanoTime() - time;
    	
    	System.out.println("Ho letto " + LEGGI + " elementi in " + time + "ns in un LinkedList ( " + time / TO_MS + " ms )");
    	
        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         * 
         * Africa -> 1,110,635,000
         * 
         * Americas -> 972,005,000
         * 
         * Antarctica -> 0
         * 
         * Asia -> 4,298,723,000
         * 
         * Europe -> 742,452,000
         * 
         * Oceania -> 38,304,000
         */
    	
    	final Map<String, Long> map = new HashMap<>();
    	map.put("Africa", AFRICA_POPULATION);
    	map.put("Americas", AMERICAS_POPULATION);
    	map.put("Antarctica", ANTARCTICA_POPULATION);
    	map.put("Asia", ASIA_POPULATION);
    	map.put("Europe", EUROPE_POPULATION);
    	map.put("Oceania", OCEANIA_POPULATION);
    	
        /*
         * 8) Compute the population of the world
         */

    	long total_abitant = 0;
    	for (final Long v: map.values()) {
    		total_abitant += v;
    	}
    	System.out.println("Gli abitanti totali sono: " + total_abitant);
    }
}
