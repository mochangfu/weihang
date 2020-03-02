package com.hg.gama.gamautil.numasutil.Page;


import java.util.List;

public class PageGama<T> {
    private int pageIndex;
    private long total;
    private int pageSize;
    private boolean isLastPage;
    private List<T> items;

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }


    public static <T> PageGama<T> create(List<T> page) {
        if (page instanceof com.github.pagehelper.Page) {
            com.github.pagehelper.Page<T> originalPage = (com.github.pagehelper.Page<T>) page;
            PageGama<T> newGamaPage = new PageGama<>();
            newGamaPage.setItems(originalPage.getResult());
            newGamaPage.setTotal(originalPage.getTotal());
            newGamaPage.setPageIndex(originalPage.getPageNum());
            newGamaPage.setPageSize(originalPage.getPageSize());
            newGamaPage.setLastPage(newGamaPage.getTotal() <=
                    (newGamaPage.getPageIndex() - 1) * newGamaPage.getPageSize() + newGamaPage.getItems().size());
            return newGamaPage;
        } else {
            PageGama<T> newGamaPage = new PageGama<>();
            newGamaPage.setItems(page);
            newGamaPage.setTotal(page.size());
            newGamaPage.setPageIndex(1);
            newGamaPage.setPageSize(page.size());
            newGamaPage.setLastPage(true);
            return newGamaPage;
        }
    }
}
