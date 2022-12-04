package controller;

public class seatMap 
{
	private Seat[][] seatmap;
	private int seatUsed;
	private int seatMax;
	private int earlySeatMax;
	private int earlySeatUsed;
	
	
	public seatMap(int row, int col, boolean earlyBooking) 
    {
		seatmap = new Seat[row][col];
		for (int i = 0; i < row; i++)  
        {
			for (int j = 0; j < col; j++) 
            {
				seatmap[i][j] = new Seat(i, j);
			}
		}
		
		seatMax = row*col;
		seatUsed = 0;
		earlySeatUsed = 0;
		
		if (earlyBooking == true) 
        {
			earlySeatMax = seatMax / 10;
		} else 
        {
			earlySeatMax = 0;
		}
	}
	
	
	boolean AllowedOccupySeat(int row, int col, boolean isEarly) 
    {
		if (seatUsed >= seatMax) 
        {
			return false;
		}
		
		if (isEarly == true) 
        {
			if (earlySeatUsed >= earlySeatMax) 
            {
				return false;
			}
		}
		
		if (seatmap[row][col].isTaken() == false) 
        {
			return true;
		} 
        else 
        {
			return false;
		}		
	}
	
	

	boolean occupySeat(int row, int col, boolean isEarly) 
    {
		if (seatUsed >= seatMax) 
        {
			return false;
		}
		
		if (isEarly == true) 
        {
			if (earlySeatUsed >= earlySeatMax) 
            {
				return false;
			}
		}
		
		if (seatmap[row][col].occupySeat(isEarly) == true) 
        {
			if (isEarly == true) 
            {
				earlySeatUsed++;
			}
			seatUsed++;
			return true;
		} 
        else 
        {
			return false;
		}
	}
	

	boolean freeSeat(int row, int col) 
    {
		if (seatUsed == 0) 
        {
			return false;
		}
		
		boolean isEarly = seatmap[row][col].isEarlyRegistration();
		
		if (seatmap[row][col].unoccupySeat()) 
        {
			if (isEarly == true) 
            {
				earlySeatUsed--;
			}
			seatUsed--;
			
			return true;
		} 
        else 
        {
			return false;
		}
	}
	

	public char[][] getseatmapInChar() 
    {
		char[][] seats = new char[seatmap.length][seatmap[0].length];
		
		for (int i = 0; i < seatmap.length; i++)  
        {
			for (int j = 0; j < seatmap[i].length; j++) 
            {
				if (seatmap[i][j].isTaken() == false) 
                {
					seats[i][j] = 'f';
				} 
                else if (seatmap[i][j].isEarlyRegistration() == true) 
                {
					seats[i][j] = 'e'; 
				} 
                else 
                {
					seats[i][j] = 'r';
				}
			}
		}		
		
		return seats;
	}
	
	public Seat[][] getseatmap() 
    {
		return seatmap;
	}

	public void setseatmap(Seat[][] seatmap) 
    {
		this.seatmap = seatmap;
	}

	public int getSeatUsed() 
    {
		return seatUsed;
	}

	public void setSeatUsed(int seatUsed) 
    {
		this.seatUsed = seatUsed;
	}

	public int getSeatMax() 
    {
		return seatMax;
	}

	public void setSeatMax(int seatMax) 
    {
		this.seatMax = seatMax;
	}
	public int getEarlySeatMax() 
    {
		return earlySeatMax;
	}

	public void setEarlySeatMax(int earlySeatMax) 
    {
		this.earlySeatMax = earlySeatMax;
	}

	public int getEarlySeatUsed() 
    {
		return earlySeatUsed;
	}

	public void setEarlySeatUsed(int earlySeatUsed) 
    {
		this.earlySeatUsed = earlySeatUsed;
	}
}