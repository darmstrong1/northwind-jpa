package co.da.nw.dto.reply;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

public class StatusReply {

    private final Boolean success;
    private final List<String> messages;

    public StatusReply() {
        this(true, ImmutableList.<String> of());
    }

    public StatusReply(Boolean success) {
        this(success, ImmutableList.<String> of());
    }

    public StatusReply(Boolean success, String message) {
        this(success, ImmutableList.of(message));
    }

    public StatusReply(Boolean success, List<String> messages) {
        this.success = success;
        this.messages = ImmutableList.copyOf(messages);
    }

    @JsonProperty("Success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("Messages")
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("success", success)
                .add("messages", messages)
                .toString();
    }

}
