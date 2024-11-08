package uk.ac.hw.macs.search;

//GridState represents the state of a position in a grid-based search algorithm.
public class GridState implements State {
	private int pos_X, pos_Y; 		//position X and Y are the coordinates of the state
    private int heuristic_val; 		// Heuristic value for the state
    private boolean goalState; 		// True/False for indication if we have reached or not reached the goal  
    
    //Constructor generates a GridState with a goal state status and an assigned position
    public GridState(int posX, int posY, boolean goalS_Status) {
        
    	//X and Y position initialized
    	this.pos_X = posX; 
        this.pos_Y = posY; 

        this.heuristic_val = 0; 		//Heuristic value set to zero
        this.goalState = goalS_Status;	//Set goal state status
    }

    //Constructor creates GridState based on a node with heuristic calculation
    public GridState(int posX, int posY, Node t_node) throws Exception {
    	
    	//X and Y position being initialized
        this.pos_X = posX;
        this.pos_Y = posY;
        
        this.goalState = false; 		//Initially, goal state is set to false 
        
        //Heuristic value calculates based on given node
        this.heuristic_val = heuristic_calc(t_node); 
    }

    /* Method returns the position of the state in form of an array. 
     * 
     * Array of two integers containing the the X and Y coordinates. */
    public int[] getPosition() {
        return new int[]{this.pos_X, this.pos_Y};
    }

    /* Method returns heuristic value for the state */
    @Override
    public int getHeuristic() {
        return this.heuristic_val;
    }

    /* Method returns by checking if the state is a goal state
     * True if goal state or else false */
    @Override
    public boolean isGoal() {
        return this.goalState;
    }

    /* Method that calculates heuristic value using a given node.
     * t_node (target) is the goal and exception is thrown if the t_node isn't a goal node. */
    private int heuristic_calc(Node t_node) throws Exception {
        
    	
    	/* "if" statement which checks if the node is a goal node
    	 * if not, then an exception is thrown in response */
    	if (!t_node.isGoal()) {
            throw new Exception("Node isn't a goal node, thus heuristic value cannott be calculated");
        }
    	
    	//Gets the position (X and Y coordinates) of the goal node and casts it to GridState
        int[] goalPosition = ((GridState) t_node.getValue()).getPosition();

        //Arithmetic Manhattan distance calculation is calculated and Returned
        return Math.abs(goalPosition[0] - this.pos_X) + Math.abs(goalPosition[1] - this.pos_Y);
    }

    /* Method returns a string representation of GridState. */
    @Override
    public String toString() {
    	
    	//Format of the output-string
        return "GridState [(X,Y)=(" + pos_X + "," + pos_Y + "), Heuristic=" + heuristic_val + ", Goal=" + goalState + "]"; 
    }
}


