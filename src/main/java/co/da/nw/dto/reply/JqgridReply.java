package co.da.nw.dto.reply;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

public class JqgridReply<T extends Serializable> {

    /**
     * Current page
     */
    private final String page;

    /**
     * Total pages
     */
    private final String total;

    /**
     * Total number of records
     */
    private final String records;

    /**
     * Contains the actual data
     */
    private final List<T> rows;

    public JqgridReply(String page, String total, String records, List<T> rows) {
        Preconditions.checkNotNull(page, "page must not be null");
        Preconditions.checkNotNull(total, "total must not be null");
        Preconditions.checkNotNull(records, "records must not be null");
        Preconditions.checkNotNull(rows, "rows must not be null");
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = ImmutableList.copyOf(rows);
    }

    public String getPage() {
        return page;
    }

    public String getTotal() {
        return total;
    }

    public String getRecords() {
        return records;
    }

    public List<T> getRows() {
        // Return a copy of the list. Don't assume that T is immutable.
        return ImmutableList.copyOf(rows);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("page", page)
                .add("total", total)
                .add("records", records)
                .add("rows", rows)
                .toString();
    }

}
