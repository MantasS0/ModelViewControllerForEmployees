package lt.bit.java2.spring.mvc.services;

import java.util.List;

public class PageResult<E> {
    private int pageSize;
    private int pageNumber;
    private int pageCount;
    private int rowCount;
    private boolean firstPage;
    private boolean lastPage;
    private List<E> data;
    private int rangeFrom;
    private int rangeTo;

    /**
     * PageResult class constructor also generates variables:
     *  int pageCount,
     *  boolean firstPage,
     *  boolean lastPage,
     *  int rangeFrom,
     *  int rangeTo from given parameters:
     * @param pageSize set page size (number of rows)
     * @param pageNumber set page number (1..)
     * @param rowCount set total number of rows in the given DB request.
     * @param data set a List<E> of returned results from DB request.
     */
    public PageResult(int pageSize, int pageNumber, int rowCount, List<E> data) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.rowCount = rowCount;
        this.data = data;

        this.pageCount = this.rowCount / this.pageSize + (this.rowCount % this.pageSize == 0 ? 0 : 1);
        this.firstPage = this.pageNumber == 1;
        this.lastPage = this.pageNumber == this.pageCount;

        if (this.pageNumber == 1) {
            this.rangeFrom = 1;
            this.rangeTo = 5;
        } else if (this.pageNumber == 2) {
            this.rangeFrom = 1;
            this.rangeTo = 5;
        } else if (this.pageNumber == 3) {
            this.rangeFrom = 1;
            this.rangeTo = 5;
        } else if (this.pageNumber == this.pageCount - 2) {
            this.rangeFrom = this.pageCount - 4;
            this.rangeTo = this.pageCount;
        } else if (this.pageNumber == this.pageCount - 1) {
            this.rangeFrom = this.pageCount - 4;
            this.rangeTo = this.pageCount;
        } else if (this.pageNumber == this.pageCount) {
            this.rangeFrom = this.pageCount - 4;
            this.rangeTo = this.pageCount;
        } else {
            this.rangeFrom = this.pageNumber - 2;
            this.rangeTo = this.pageNumber + 2;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(int rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public int getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(int rangeTo) {
        this.rangeTo = rangeTo;
    }
}
