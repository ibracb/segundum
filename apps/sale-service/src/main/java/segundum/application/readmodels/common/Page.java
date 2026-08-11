package segundum.application.readmodels.common;

import java.util.List;

/**
 * Represents a page of results with pagination metadata.
 */
public class Page<T> {

    /**
     * The content of the current page.
     */
    private final List<T> content;

    /**
     * The total number of elements across all pages.
     */
    private final long totalElements;

    /**
     * The number of the current page.
     */
    private final int pageNumber;

    /**
     * The number of elements per page.
     */
    private final int pageSize;

    /**
     * The total number of pages.
     */
    private final int totalPages;

    /**
     * Constructs a new Page with the given values.
     *
     * @param content       the content of the current page
     * @param totalElements the total number of elements across all pages
     * @param pageNumber    the number of the current page
     * @param pageSize      the number of elements per page
     */
    public Page(List<T> content, long totalElements, int pageNumber, int pageSize) {
        this.content = content;
        this.totalElements = totalElements;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPages = pageSize > 0 ? (int) Math.ceil((double) totalElements / pageSize) : 0;
    }

    /**
     * Returns the content of the current page.
     *
     * @return the content of the current page
     */
    public List<T> getContent() {
        return content;
    }

    /**
     * Returns the total number of elements across all pages.
     *
     * @return the total number of elements across all pages
     */
    public long getTotalElements() {
        return totalElements;
    }

    /**
     * Returns the number of the current page.
     *
     * @return the number of the current page
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Returns the number of elements per page.
     *
     * @return the number of elements per page
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Returns the total number of pages.
     *
     * @return the total number of pages
     */
    public int getTotalPages() {
        return totalPages;
    }

}
