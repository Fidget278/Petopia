package model.statistics;

public class StatisticsVo {
	private String dailyDate;
	private int dailyBoardCount;
	private int dailyVisitorsCount;
	private int totalMemberCount;
	private int totalBoardCount;
	private int totalViewCount;
	private int totalVisitorsCount;

	public StatisticsVo() {
		super();
	}

	public StatisticsVo(String dailyDate, int dailyBoardCount, int dailyVisitorsCount) {
		super();
		this.dailyDate = dailyDate;
		this.dailyBoardCount = dailyBoardCount;
		this.dailyVisitorsCount = dailyVisitorsCount;
	}

	public StatisticsVo(int totalMemberCount, int totalBoardCount, int totalViewCount, int totalVisitorsCount) {
		super();
		this.totalMemberCount = totalMemberCount;
		this.totalBoardCount = totalBoardCount;
		this.totalViewCount = totalViewCount;
		this.totalVisitorsCount = totalVisitorsCount;
	}

	public String getDailyDate() {
		return dailyDate;
	}

	public void setDailyDate(String dailyDate) {
		this.dailyDate = dailyDate;
	}

	public int getDailyBoardCount() {
		return dailyBoardCount;
	}

	public void setDailyBoardCount(int dailyBoardCount) {
		this.dailyBoardCount = dailyBoardCount;
	}

	public int getDailyVisitorsCount() {
		return dailyVisitorsCount;
	}

	public void setDailyVisitorsCount(int dailyVisitorsCount) {
		this.dailyVisitorsCount = dailyVisitorsCount;
	}

	public int getTotalMemberCount() {
		return totalMemberCount;
	}

	public void setTotalMemberCount(int totalMemberCount) {
		this.totalMemberCount = totalMemberCount;
	}

	public int getTotalBoardCount() {
		return totalBoardCount;
	}

	public void setTotalBoardCount(int totalBoardCount) {
		this.totalBoardCount = totalBoardCount;
	}

	public int getTotalViewCount() {
		return totalViewCount;
	}

	public void setTotalViewCount(int totalViewCount) {
		this.totalViewCount = totalViewCount;
	}

	public int getTotalVisitorsCount() {
		return totalVisitorsCount;
	}

	public void setTotalVisitorsCount(int totalVisitorsCount) {
		this.totalVisitorsCount = totalVisitorsCount;
	}

	@Override
	public String toString() {
		return "StatisticsVo [dailyDate=" + dailyDate + ", dailyBoardCount=" + dailyBoardCount + ", dailyVisitorsCount="
				+ dailyVisitorsCount + ", totalMemberCount=" + totalMemberCount + ", totalBoardCount=" + totalBoardCount
				+ ", totalViewCount=" + totalViewCount + ", totalVisitorsCount=" + totalVisitorsCount + "]";
	}

	
	

}

