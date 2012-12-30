package benblack86.collection;

public class SetBasics {

	public static void main(String[] args) {
		// java.util.HashSet
		// - implemented using a hash table (should be bigger than N)
		// - not thread safe, iterators are fail-fast
		//        add : O(1)
		//     remove : O(1)
		//   contains : O(1)
		//  iteration : O(size of hash table)
		
		// java.util.LinkedHashSet
		// - inherits from HashSet but iteration is ordered by the how they were added
		// - maintains a link list on the elements in addition to the hash table
		// - not thread safe, iterators are fail-fast
		//        add : O(1)
		//     remove : O(1)
		//   contains : O(1)
		//  iteration : O(N)
		
		// java.util.concurrent.CopyOnWriteArraySet
		// - implemented using an array, which is copied on each write
		// - thread safe
		//        add : O(N)
		//     remove : not supported
		//   contains : O(N)

		// java.util.EnumSet
		// - implemented as a bit vector, can only hold enums, very fast
		// - sets are created using factory methods
		//        add : O(1)
		//     remove : O(1)
		//   contains : O(1)
		//  iteration : O(N)
	}

}
