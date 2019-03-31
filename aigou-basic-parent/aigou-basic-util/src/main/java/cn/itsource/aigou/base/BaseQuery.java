package cn.itsource.aigou.base;

public class BaseQuery {
    private Integer page=1;
    private Integer rows=10;
    private String keyword;

    //手写分页sql用到的每页开始索引
    public Integer getStart() {
        return (page - 1) * rows;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
