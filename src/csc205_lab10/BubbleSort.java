package csc205_lab10;

import java.util.*;

public class BubbleSort {

	/** These are the four instance fields that define one BubbleSort experiment */
	private DataStructureChoice dsChoice;
	private boolean useSetGet;	
	private boolean useSmallTestData;
	private int numberDataItems;

	/** Constructor method initialize the parameters of the experiment */
	public BubbleSort(DataStructureChoice dsChoice, boolean useSetGet,
			boolean useSmallTestData, int numberDataItems) {
	
		this.dsChoice = dsChoice;
		this.useSetGet = useSetGet;
		this.useSmallTestData = useSmallTestData;
		this.numberDataItems = numberDataItems;
	}

	public String runExperiment() 
	{
		System.out.println ( "Hello world from CSC 205 Lab 10\n");

		/** create the List that will be sorted 
		 *  Note the use of the List interface here */
		List<Student> theListOfData = createEmptyList(dsChoice);
		
		if ( useSmallTestData ) {
			theListOfData.add (  new Student ( "edith", "EDC" ) );
			theListOfData.add (  new Student ( "dan", "DNC" ) );
			theListOfData.add (  new Student ( "chad", "CHM" ) );
			theListOfData.add (  new Student ( "bob", "BIO" ) );
			theListOfData.add (  new Student ( "alice", "ART" ) );
		} else 
			initializeLargeRandomList ( theListOfData );	

		long startTime = System.currentTimeMillis();

		/** Perform a Bubble Sort.
		 *  we repeatedly sweep through the list, until no items have been
		 *  swapped, at which time the list is sorted 	                   	 */
		boolean restart = true;
		while ( restart ) 
		{
			restart = false;

			/** sweep through the List from left-to-right, comparing adjacent items */
			for ( int j=0; j < theListOfData.size() - 1; j++ ) {

				/** swap items in positions (j) and (j+1) if out-of-order     */
				if (  theListOfData.get(j).compareTo( theListOfData.get(j+1) ) > 0  ) {

					if ( useSetGet ) {
						/** use Set and Get methods */
						Student temp = theListOfData.get(j);
						theListOfData.set ( j, theListOfData.get(j+1) );
						theListOfData.set ( j+1, temp ); 
					} else {  
						/** use Add and Remove methods, much more time-consuming */
						Student temp = theListOfData.remove(j);   
						theListOfData.add( j+1, temp );	
					}

					restart = true;  // if swap occurred, must repeat the loop
				}
			}
		}

		long endTime = System.currentTimeMillis();
		
		String resultOfExperimnet = "\n\nThe total time to sort n = " + theListOfData.size() 
				+ " items, using the " + dsChoice + " data structure with the " +
				( useSetGet ? " set/get methods " : " add/remove methods ")
				+ " was " + (endTime - startTime) + " milliseconds"; 
   
        if ( ! isSorted(theListOfData) )
        	resultOfExperimnet += "\n The sorting algorithm FAILED to correctly sort the data";

		System.out.println ("\nGood-bye world");
		
		return ( resultOfExperimnet );
	}


	/** create the List to contain the data that will be sorted */
	private static List<Student> createEmptyList(DataStructureChoice dsChoice) {

		List<Student> foo = null;          /** declared using an Interface declaration */

		switch ( dsChoice ) {
		case JAVA_ARRAY_LIST:
			foo = new ArrayList<Student>();
			break;
		case JAVA_LINKED_LIST:
			foo = new LinkedList<Student>();
			break;
		case CSC205_LINKED_LIST:
			foo = new CSC205_Project_1_Linked_List<Student>();
			break;
		default:
			System.out.println ( "Illegal choice of data structure");
			System.exit(1);   // abort the program
		}
		return foo;
	}


	/** Initialize the List to contain data in reverse sorted order */
	private void initializeLargeRandomList(List<Student> foo) 
	{
		int n = this.numberDataItems;

		/** create a list of Strings that is very un-sorted */
		for ( int i=n-1; i >= 0; i-- ) 
			foo.add( new Student("alice " + (n+i), "MTH") );     
	}


	/**
	 * @param theListofData
	 * @return true if-and-only-if the given List is in sorted order
	 */
	private static boolean isSorted ( List<Student>   theListofData ) {

		/** check every pair of adjacent items to see that they are in sorted order */
		for ( int i = 0; i < theListofData.size() - 1; i++ ) {
			Student leftItem = theListofData.get(i);
			Student rightItem = theListofData.get(i+1);

			if ( leftItem.compareTo( rightItem ) > 0 )
				return false;   // these two items are out-of-order
		}
		return true;
	}
}





