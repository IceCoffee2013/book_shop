package net.shop.entity;

/**
 * Query Record.
 * Created by Langley on 8/30/16.
 */
public class Query {

    private String query = "";
    private QueryType queryType = QueryType.NONE;
    private QueryType searchIn = QueryType.NONE;

    public Query(String query, QueryType queryType, QueryType searchIn) {
        this.query = query;
        this.queryType = queryType;
        this.searchIn = searchIn;
    }

    public Query(String query, String queryType, String searchIn) {
        this.query = query;
        if (queryType == null) {
          this.queryType = QueryType.NONE;
        } else if (queryType.equals("AND")) {
            this.queryType = QueryType.AND;
        } else if (queryType.equals("OR")) {
            this.queryType = QueryType.OR;
        } else if (queryType.equals("NOT")) {
            this.queryType = QueryType.NOT;
        } else {
            this.queryType = QueryType.NONE;
        }

        if (searchIn.equals("author")) {
            this.searchIn = QueryType.AUTHOR;
        } else if (searchIn.equals("title")) {
            this.searchIn = QueryType.TITLE;
        } else if (searchIn.equals("year")) {
            this.searchIn = QueryType.YEAR;
        } else if (searchIn.equals("journal")) {
            this.searchIn = QueryType.JOURNAL;
        } else if (searchIn.equals("volume")) {
            this.searchIn = QueryType.VOLUME;
        }else {
            this.searchIn = QueryType.NONE;
        }

    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public QueryType getSearchIn() {
        return searchIn;
    }

    public void setSearchIn(QueryType searchIn) {
        this.searchIn = searchIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Query query1 = (Query) o;

        if (!query.equals(query1.query)) return false;
        if (queryType != query1.queryType) return false;
        return searchIn == query1.searchIn;

    }

    @Override
    public int hashCode() {
        int result = query.hashCode();
        result = 31 * result + queryType.hashCode();
        result = 31 * result + searchIn.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Query{" +
                "query='" + query + '\'' +
                ", queryType=" + queryType +
                ", searchIn=" + searchIn +
                '}';
    }
}
