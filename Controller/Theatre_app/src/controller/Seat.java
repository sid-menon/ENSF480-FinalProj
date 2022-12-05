package controller;

public class Seat 
{
	private int row;
	private int col;

	private int roomID;

	private int roomNum;

	private boolean isEarlyRegistration;
	private boolean isTaken;
	

	public Seat(int row, int col)
    {
		this.row = row;
		this.col = col;
		isEarlyRegistration = false;
		isTaken = false;
	}
	
	
	boolean occupySeat(boolean isEarly) 
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
	
	boolean unoccupySeat() 
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
	
	public int getRow()
    {
		return row;
	}

	public int getCol()
    {
		return col;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public boolean isEarlyRegistration()
    {
		return isEarlyRegistration;
	}

	public void setTaken(boolean taken) {
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
