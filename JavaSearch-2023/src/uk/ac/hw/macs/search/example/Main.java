package uk.ac.hw.macs.search.example;

import uk.ac.hw.macs.search.AStarSearchOrder;
import uk.ac.hw.macs.search.GridState;
import uk.ac.hw.macs.search.Node;
import uk.ac.hw.macs.search.SearchOrder;
import uk.ac.hw.macs.search.SearchProblem;

public class Main {

/* Checks whether the position (x, y) is a wall in Grid 1
    which restrict movement */
private static boolean wallGrid1(int x, int y) {
    return (x == 2 && (y == 0 || y == 2 || y == 3)) || (x == 1 && y == 3);
}

/* Checks whether the position (x, y) is a wall in Grid 2
    which restrict movement [wall placements are different from Grid 1] */
private static boolean wallGrid2(int x, int y) {
    return (x == 2 && (y == 0 || y == 2 || y == 4)) || (x == 1 && y == 4);
}

/* Method over here checks if square is grey as it costs 
 * more than white grid for grid 1*/
private static int sq_costG1(int x, int y) {
	
	//Grey squares are defined
	if ((x == 0 && y == 1) || (x == 1 && y == 1) || 
		(x == 3 && y == 0) || (x == 3 && y == 1) || 
		(x == 3 && y == 2) || (x == 4 && y == 2) || 
		(x == 0 && y == 3) || (x == 4 && y == 3) || (x == 5 && y == 3))
		
		return 2; //Cost is returned as 2 for the grey squares
	else
		return 1; //If not grey, cost is 1
	}

/* Method over here checks if square is grey as it costs 
 * more than white grid for grid 2*/
private static int sq_costG2(int x, int y) {
	
	//Grey squares are defined
	if ((x == 3 && y == 0) || (x == 0 && y == 1) || 
		(x == 3 && y == 1) || (x == 0 && y == 3) || 
		(x == 0 && y == 4) || (x == 2 && y == 3) || 
		(x == 4 && y == 4) || (x == 4 && y == 3))
		
		return 2; //Cost is returned as 2 for the grey squares
	else
		return 1; //If not grey, cost is 1
	}
    
    public static void main(String[] args) throws Exception {

        //Grid 1 dimensions defined (6 columns by 4 rows)
        int grid1_Columns = 6;
        int grid1_Rows = 4;

        //Goal node created at position (5, 2) in Grid 1
        Node G_Node = new Node(new GridState(5, 2, true));
        Node[][] grid1 = new Node[grid1_Columns][grid1_Rows]; //Initializing the 2D grid of nodes

        //Nodes created for each position in Grid 1
        for (int x = 0; x < grid1_Columns; x++) {
            for (int y = 0; y < grid1_Rows; y++) {
                grid1[x][y] = new Node(new GridState(x, y, G_Node));
            }
        }

        //Assigning goal node to correct position in grid
        grid1[5][2] = G_Node;

        //Iterates through every node in grid to determine the connection and to avoid walls
        for (int x = 0; x < grid1_Columns; x++) {
            for (int y = 0; y < grid1_Rows; y++) {
            	if (wallGrid1(x, y)) continue; //If node is wall it is skipped

                //boolean flags which determine if the connections in each direction are possible or no
                boolean addLeft = x > 0, addRight = x < 5, addTop = y > 0, addBottom = y < 2;

                //Adding child nodes (adjacent connections) if walls aren't blocking the way
                if (addLeft && !wallGrid1(x - 1, y))
                    grid1[x][y].addChild(grid1[x - 1][y], sq_costG1(x - 1, y));

                if (addRight && !wallGrid1(x + 1, y))
                    grid1[x][y].addChild(grid1[x + 1][y], sq_costG1(x + 1, y));

                if (addTop && !wallGrid1(x, y - 1))
                    grid1[x][y].addChild(grid1[x][y - 1], sq_costG1(x, y - 1));

                if (addBottom && !wallGrid1(x, y + 1))
                    grid1[x][y].addChild(grid1[x][y + 1], sq_costG1(x, y + 1));
            }
        }
      
        //Grid 2 dimensions defined (5 columns by 5 rows)
        int grid2_Columns = 5;
        int grid2_Rows = 5;

        //Goal node created at position (3, 4) in Grid 2
        Node G_Node2 = new Node(new GridState(3, 4, true));
        Node[][] grid2 = new Node[grid2_Columns][grid2_Rows]; //Initializing the 2D grid of nodes


        //Nodes created for each position in Grid 2
        for (int x = 0; x < grid2_Columns; x++) {
            for (int y = 0; y < grid2_Rows; y++) {
                grid2[x][y] = new Node(new GridState(x, y, G_Node2));
            }
        }

        //Assigning goal node to correct position in grid
        grid2[3][4] = G_Node2;

        //Iterates through every node in grid to determine the connection and to avoid walls
        for (int x = 0; x < grid2_Columns; x++) {
            for (int y = 0; y < grid2_Rows; y++) {
                if (wallGrid2(x, y)) continue; //If node is wall it is skipped

                //boolean flags which determine if the connections in each direction are possible or no
                boolean addLeft = x > 0, addRight = x < 3, addTop = y > 0, addBottom = y < 4;

                //Adding child nodes (adjacent connections) if walls aren't blocking the way
                if (addLeft && !wallGrid2(x - 1, y))
                    grid2[x][y].addChild(grid2[x - 1][y], sq_costG2(x - 1, y));

                if (addRight && !wallGrid2(x + 1, y))
                    grid2[x][y].addChild(grid2[x + 1][y], sq_costG2(x + 1, y));

                if (addTop && !wallGrid2(x, y - 1))
                    grid2[x][y].addChild(grid2[x][y - 1], sq_costG2(x, y - 1));

                if (addBottom && !wallGrid2(x, y + 1))
                    grid2[x][y].addChild(grid2[x][y + 1], sq_costG2(x, y + 1));
            }
        }

        SearchOrder searchOrder = new AStarSearchOrder();
        SearchProblem searchProblem = new SearchProblem(searchOrder);

        /* A*search can be performed on on of the grids by commenting 
        and  the specific searches */
        
        searchProblem.doSearch(grid1[0][0]);
        //searchProblem.doSearch(grid2[0][0]);
    }
}
