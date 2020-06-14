import java.io.*;
/**
*<h1>NODE </h1>
*<p> 
*Node class represents the individual element of the grid.
* @author Aman Roy (2016011), Suyash Singh(2016105)
* @version 1.0
* @since 2017-10-20
*/

public class node implements Serializable
{
    int currentsize;
    int maxsize;
    String type;
    String color;
    int player;
    /**
   * This is the constructor of the node type which initializes the node/cell of the grid 
   *@param maxsize This is the First parameter to the constructor of node 
   *representing the maximum size of each cell in the grid pane
   *@param type This is the Second parameter to the constructor of node
   *representing type of cell/node that is initialised(corner/edge/middle)
   *@param color This is the third parameter to the constructor of node
   *representing the color of the orb present in the cell/node
   *@param player This is the fourth parameter to the constructor of node
   *representing the player number 
   */
    public node(int maxsize , String type , String color , int player )
    {
        this.currentsize = 0;
        this.maxsize = maxsize;
        this.type = type;
        this.color = color;
        this.player = player;
    }
}