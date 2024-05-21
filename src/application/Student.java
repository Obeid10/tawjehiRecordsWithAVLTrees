package application;

public class Student implements Comparable<Student> {
	private int seatNumber;
	private String Branch;
	private double Average;

	public Student(int seatNumber, String Branch, double Average) {
		super();
		this.seatNumber = seatNumber;
		this.Branch = Branch;
		this.Average = Average;
	}

	@Override
	public String toString() {
		return "SeatNumber=" + seatNumber + ", Branch=" + Branch + ", Average=" + Average + "\n";
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getBranch() {
		return Branch;
	}

	public void setBranch(String branch) {
		Branch = branch;
	}

	public double getAverage() {
		return Average;
	}

	public void setAverage(double average) {
		Average = average;
	}

	@Override
	public int compareTo(Student other) {
		if (this.Average < other.Average) {
			return 1;
		} else if (this.Average > other.Average) {
			return -1;
		} else {
			return 0;
		}

	}

}
