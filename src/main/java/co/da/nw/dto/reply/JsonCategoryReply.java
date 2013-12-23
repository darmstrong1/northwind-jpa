package co.da.nw.dto.reply;

import org.codehaus.jackson.annotate.JsonProperty;

import co.da.nw.dto.CategoryDTO;

import com.google.common.base.Objects;

public class JsonCategoryReply {

    private String result;
    private String message;
    private CategoryDTO record;

    public JsonCategoryReply() {
    }

    public JsonCategoryReply(String result) {
        this.result = result;
    }

    public JsonCategoryReply(String result, CategoryDTO record) {
        this(result);
        this.record = record;
    }

    public JsonCategoryReply(String result, String message) {
        this(result);
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

    @JsonProperty("Record")
    public CategoryDTO getRecord() {
        return record;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRecord(CategoryDTO record) {
        this.record = record;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("result", result)
                .add("message", message)
                .add("record", record)
                .toString();
    }
}
