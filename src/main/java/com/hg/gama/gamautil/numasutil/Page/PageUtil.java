package com.hg.gama.gamautil.numasutil.Page;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PageUtil {

    //page信息只含简单参数
       public static <T> PageGama<T> createPageInfo(List<T> all, Page pageable) {

        List<T> list = all == null ? new ArrayList<>(0) : all;
        if (pageable == null) {
            return PageGama.create(list);
        } else {
            pageable.setPageNum(pageable.getPageNum()+1);//导航从0页
            List<T> filtered = list.stream().skip((pageable.getPageNum()-1 ) * pageable.getPageSize()).limit(pageable.getPageSize()).collect(Collectors.toList());
            PageGama<T> newGamaPage = new PageGama<>();
            newGamaPage.setItems(filtered);
            newGamaPage.setTotal(list.size());
            newGamaPage.setPageIndex(pageable.getPageNum());
            newGamaPage.setPageSize(pageable.getPageSize());
            newGamaPage.setLastPage(list.size() <= (pageable.getPageNum() * pageable.getPageSize()));
            return newGamaPage;
        }
    }

    public static <T> PageInfo<T> createPageInfo1(List<T> input, Page pageable) {
        if (input instanceof PageGama) {
            return new PageInfo<>(input);
        }
        PageInfo<T> pageInfo = new PageInfo<>();
        if (pageable == null) {
            pageInfo.setList(input);
            return pageInfo;
        }
        pageable.setPageNum(pageable.getPageNum()+1);//导航从0页
        pageInfo.setPageNum(pageable.getPageNum());
        pageInfo.setPageSize(pageable.getPageSize());
        if (input == null) {
            input = new ArrayList<>();
        }
        pageInfo.setTotal(input.size());
        int pages = input.size();
        if (pageable.getPageSize() != 0) {
            pages /= pageable.getPageSize();
            if (input.size() % pageable.getPageSize() != 0) {
                pages += 1;
            }
            pageInfo.setPages(pages);
            pageInfo.setNavigateFirstPage(1);
            pageInfo.setPrePage(pageable.getPageNum() - 1);
            pageInfo.setNextPage(pageable.getPageNum() + 1);
            pageInfo.setNavigateLastPage(pages);
            if (pageable.getPageNum() == pageInfo.getNavigateFirstPage()) {
                pageInfo.setIsFirstPage(true);
                pageInfo.setHasPreviousPage(false);
            } else {
                pageInfo.setIsFirstPage(false);
                pageInfo.setHasPreviousPage(true);
            }
            if (pageable.getPageNum() == pageInfo.getNavigateLastPage()) {
                pageInfo.setIsLastPage(true);
                pageInfo.setHasNextPage(false);
            } else {
                pageInfo.setIsLastPage(false);
                pageInfo.setHasNextPage(true);
            }
            pageInfo.setNavigatePages(8);
            Integer pageLimit = pageable.getPageSize();
            if (pages != 0 && pageable.getPageNum() != pages) {
                pageInfo.setStartRow((pageable.getPageNum() - 1) * pageLimit + 1);
                pageInfo.setEndRow((pageable.getPageNum() - 1) * pageLimit + pageLimit);
                pageInfo.setList(input.subList(
                        Math.min(input.size(), Math.max(0, (pageable.getPageNum() - 1) * pageLimit)),
                        Math.min(input.size(), (pageable.getPageNum() - 1) * pageLimit + pageLimit)));
            } else {
                pageInfo.setStartRow((pageable.getPageNum() - 1) * pageLimit + 1);
                pageInfo.setEndRow(input.size());
                pageInfo.setList(input.subList(
                        Math.min(input.size(), Math.max(0, (pageable.getPageNum() - 1) * pageLimit)),
                        input.size()));
            }
            int[] navigatePageNums = new int[pages];
            for (int i = 0; i < pages; i++) {
                navigatePageNums[i] = i + 1;
            }
            pageInfo.setNavigatepageNums(navigatePageNums);
        } else {
            pageInfo.setList(input);
        }
        return pageInfo;
    }
}
