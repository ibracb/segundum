package segundum.application.queries;

/**
 * Represents a query to retrieve the monthly history of products.
 */
public class GetMonthlyHistoryQuery {

	private final int month;
	private final int year;
	private final int pageNumber;
	private final int pageSize;

	/**
	 * Constructs a new GetMonthlyHistoryQuery with the given parameters.
	 *
	 * @param month      the month (1-12)
	 * @param year       the year
	 * @param pageNumber the page number (0-indexed)
	 * @param pageSize   the page size
	 */
	public GetMonthlyHistoryQuery(int month, int year, int pageNumber, int pageSize) {
		this.month = month;
		this.year = year;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public int getMonth() { return month; }
	public int getYear() { return year; }
	public int getPageNumber() { return pageNumber; }
	public int getPageSize() { return pageSize; }

}
