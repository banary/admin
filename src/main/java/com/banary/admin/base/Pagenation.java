package com.banary.admin.base;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页对象
 *
 * @author xiyongchun
 * @date 2017/7/8 上午1:25
 */
public class Pagenation<T> {

    public static final int DEFAULT_PAGE_SIZE = 15;

    private Integer page;
    private Integer pageSize;
    private Integer nextPage;
    private Integer prePage;
    private Integer totalPage;
    private Integer totalCount;
    private Map<String, Object> params;
    private List<T> list;

    public static <T> List<T> getCurrentPage(Integer currentPage,Integer pagesize,List<T> allData) {
        return new Pagenation<>(currentPage,pagesize,allData).getList();
    }

    public static <T> Pagenation<T> newPage(Integer currentPage,Integer pageSize,Integer totalCount,List<T> data) {
        return new Pagenation<>(currentPage,pageSize,totalCount,data);
    }

    public static <T> Pagenation<T> newPage(Integer currentPage,Integer pageSize,List<T> data) {
        return new Pagenation<>(currentPage,pageSize,data);
    }

    public static Pagenation emptyPage(Integer currentPage, Integer pageSize){
        return new Pagenation<>(currentPage, pageSize, 0, new ArrayList<>());
    }

    public static Pagenation emptyPage() {
        return new Pagenation();
    }

    private Pagenation(Integer currentPage, Integer pageSize, List<T> allData) {
        if(null == currentPage || currentPage <= 0) {
            this.page = 1;
        } else {
            this.page = currentPage;
        }

        if(null == pageSize || pageSize<=0) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }

        if(CollectionUtils.isEmpty(allData)) {
            this.nextPage = page;
            this.prePage = page;
            this.list = new ArrayList<>();
            this.totalPage = 0;
            this.totalCount = 0;
            return;
        }

        this.totalCount = allData.size();
        this.totalPage = this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;

        if(this.page>this.totalPage) {
            this.nextPage = this.totalPage;
            this.prePage = this.totalPage;
            this.list = new ArrayList<>();
            return;
        }

        Integer startIndex = (this.page-1)*this.pageSize;

        this.list = new ArrayList(allData.subList(startIndex,startIndex+this.pageSize>totalCount?totalCount:startIndex+this.pageSize));

        this.prePage = Integer.max(1,this.page-1);
        this.nextPage = Integer.min(this.totalPage,this.page+1);
    }

    private Pagenation(Integer currentPage, Integer pageSize, Integer totalCount, List<T> currentPageData) {
        if(null == currentPage || currentPage<=0) {
            this.page = 1;
        } else {
            this.page = currentPage;
        }

        if(null == pageSize || pageSize<=0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        this.pageSize = pageSize;
        this.totalCount = totalCount;

        this.totalPage = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        this.nextPage = Integer.min(this.totalPage,this.page+1);
        this.nextPage = Integer.max(this.nextPage,1);
        this.prePage = Integer.max(1,this.page-1);
        this.list = currentPageData;
    }

    private Pagenation(List<T> list) {
        this.list = list;
    }

    public Pagenation() {
        this.page = 0;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.nextPage = 0;
        this.prePage = 0;
        this.totalPage = 0;
        this.totalCount = 0;
        this.list = new ArrayList<>();
    }

    public Pagenation<T> withParam(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
