package co.da.nw.dto.reply;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import co.da.nw.dto.CategoryDTO;

import com.google.common.base.Objects;

public class JsonCategoryListReply {

    private String result;
    private String message;
    private List<CategoryDTO> records;
    private int totalRecordCount;

    public JsonCategoryListReply(String result, List<CategoryDTO> records,
            int totalRecordCount) {
        this.result = result;
        this.records = records;
        this.totalRecordCount = totalRecordCount;
    }

    public JsonCategoryListReply(String result, String message) {
        this.result = result;
        this.message = message;
    }

    @JsonProperty("Result")
    public String getResult() {
        return result;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("Records")
    public List<CategoryDTO> getRecords() {
        return records;
    }

    @JsonProperty("TotalRecordCount")
    public int getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecord(List<CategoryDTO> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("result", result)
                .add("message", message)
                .add("records", records)
                .add("totalRecordCount", totalRecordCount)
                .toString();
    }

}
