package csc205_lab10;

public class Student implements Comparable<Student> {

	private final String name;

	private String major;

	/** Construct a new Student with the given name and major.
	 *  @param name The name of the person
	 *  @param major The major field of study of the person
	 */
	public Student ( String name, String major ) {
		this.name = name;
		this.major = major;  
	}


	/** Retrieve the value of the Student's name
	 *  @return the value of the Student's name
	 */
	public String getName() {
		return name;
	}


	/** Retrieve the value of the Student's major field of study
	 *  @return the value of the Student's major field of study
	 */
	public String getMajor() {
		return major;	
	}


	/** Update the value of the Student's major field of study
	 *  @param major The new major
	 */
	public void setMajor(String major) {	
		this.major = major;
	}


	/** Return the  information about the Student as a formatted String formatted
	 *  @return information about the Student
	 */
	@Override
	public String toString() {
		return ( name + "(" + major + ")" );
		
		// return ( name.substring(0,2));  // only the first 2 letters
	}


	/** Compares this object with the specified object for order. 
	 *  Returns a negative integer, zero, or a positive
	 *  integer as this object is less than, equal to, or greater than the specified object.
	 *  @param o -  the Object to be compared. 
	 *  @return a negative integer, zero, or a positive integer as this
	 *  object is less than, equal to, or greater than the specified object. 
	 */ 
	public int compareTo ( Student other )
	{
		return   name.compareTo  ( other.name );
	}  

	
	
	/** Compute a hash code that is consistent with the equals method.
	 *  @return hashCode of the name field
	 */
	@Override
	public int hashCode() {
		
		int result = ((name == null) ? 0 : name.hashCode());
		
		return Math.abs(result);
	}


	
	/** Compare for equality. Equality is based only on the name field.
	 *  @param obj - The object that is being compared to "this" object
	 *  @return true If the objects are equal, otherwise return false
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if ( this.getClass() != obj.getClass() )
			return false;

		Student other = (Student) obj;

		if (name == null) {
			if (other.name != null)
				return false;

		} else if (!name.equals(other.name))
			return false;

		return true;
	}
}
