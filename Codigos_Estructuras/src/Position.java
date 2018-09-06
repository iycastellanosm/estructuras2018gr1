class Position{
	int row, col;

	Position(){ this(0, 0); }
	
	Position (int row, int col){
		this.row = row;
		this.col = col;
	}
	
	Position add(Position aux){
		return new Position(this.row + aux.row, this.col + aux.col);
	}
	
	boolean equals(Position aux){
		return this.row == aux.row && this.col == aux.col;
	}
	
	public String toString(){
		return new String (row + " " + col);
	}
}