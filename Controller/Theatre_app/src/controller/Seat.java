package controller;

public class Seat 
{
	private int row;
	private int col;
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
	
	public int getrow() 
    {
		return row;
	}

	public int getcol() 
    {
		return col;
	}

	public boolean isEarlyRegistration() 
    {
		return isEarlyRegistration;
	}

	public boolean isTaken() 
    {
		return isTaken;
	}

}
