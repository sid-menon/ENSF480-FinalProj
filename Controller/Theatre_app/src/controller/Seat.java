package controller;

public class Seat 
{
	private int row;
	private int col;

	private int roomID;

	private int roomNum;

	private boolean isEarlyRegistration;
	private boolean isTaken;
	

	public Seat(int row, int col) //constructor
    {
		this.row = row;
		this.col = col;
		isEarlyRegistration = false;
		isTaken = false;
	}
	
	
	boolean occupySeat(boolean isEarly) //when the seat is occupied
    {
		if (isTaken == false) {
			if (isEarly == true) 
            {
				isEarlyRegistration = true;
			}
			isTaken = true;
			return true;
		} else {
			return false;
		}
	}
	
	boolean unoccupySeat() //when the seat is unoccupied
    {
		if (isTaken == true) 
        {
			isEarlyRegistration = false;
			isTaken = false;
			return true;
		} else 
        {
			return false;
		}
	}
	
	public int getRow() //getter for row
    {
		return row;
	}

	public int getCol() //getter for collumns
    {
		return col;
	}

	public int getRoomID() //getter for roomID
	{ 
		return roomID;
	}

	public void setRoomID(int roomID) //setter
	{
		this.roomID = roomID;
	}

	public int getRoomNum() //getter
	{
		return roomNum;
	}

	public void setRoomNum(int roomNum) //setter
	{
		this.roomNum = roomNum;
	}

	public boolean isEarlyRegistration()
    {
		return isEarlyRegistration;
	}

	public void setTaken(boolean taken) //setter
	{
		isTaken = taken;
	}

	public boolean isTaken()
    {
		return isTaken;
	}


	public String toString(){
		return (isTaken?"X":"O");
	}

}
