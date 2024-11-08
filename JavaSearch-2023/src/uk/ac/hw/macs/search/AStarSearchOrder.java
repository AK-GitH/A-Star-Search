package uk.ac.hw.macs.search;

import java.util.List;
import java.util.Set;

public class AStarSearchOrder implements SearchOrder {

    @Override
    public void addToFringe(List<FringeNode> frontier, FringeNode parent, Set<ChildWithCost> children) {
        
    	/*"for" loop over here loops through each child node.
    	 * A new fringeNode is created for each child which will 
    	 * be added to the frontier list */
        for (ChildWithCost child : children) {
        	frontier.add(new FringeNode(child.node, parent, child.cost));
        }
        
        /*Once the nodes are added, they are sorted in the frontier list
         * in increasing f-value by implementing bubble sort */
        int n = frontier.size(); 	//List size
        boolean swap;				//variable to determine whether a swap occurred
       
        //Loop on the outer-side performs bubble sort which performs n-1 passes          
        for (int pass = 0; pass < n - 1; pass++) {
            swap = false;  //swap is set to false for current pass
            
            //Loop on the inner-side which compares the adjacent nodes in frontier
            for (int i = 0; i < n - 1 - pass; i++) {
                
            	//Taking the first and second node
            	FringeNode firstNode = frontier.get(i);
                FringeNode secondNode = frontier.get(i + 1);
                
                /* The retrieved nodes are compared for f-values and are swapped if the
                 * first node has higher f-value. */
                if (Integer.compare(firstNode.getFValue(), secondNode.getFValue()) > 0) {                               
                	frontier.set(i, secondNode);
                	frontier.set(i + 1, firstNode);
                    
                	swap = true;  //swap is then set to true on occurrence 
                }
            }
            //If no swaps are done in the pass, then list is sorted and broken out the loop
            if (!swap) {
                break;
            }
        }
    }
}