package segundum.application.readmodels.common;

import java.util.List;

/**
 * A paginated result set.
 *
 * @param <T> the type of elements in the page
 */
public class Page<T> {

	private final List<T> content;
	private final long totalElements;
	private final int pageNumber;
	private final int pageSize;
	private final int totalPages;

	/**
	 * Constructs a new Page.
	 *
	 * @param content       the page content
	 * @param totalElements the total number of matching elements
	 * @param pageNumber    the current page number (0-indexed)
	 * @param pageSize      the page size
	 */
	public Page(List<T> content, long totalElements, int pageNumber, int pageSize) {
		this.content = content;
		this.totalElements = totalElements;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPages = pageSize > 0 ? (int) Math.ceil((double) totalElements / pageSize) : 0;
	}

	public List<T> getContent() {
		return content;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

}
