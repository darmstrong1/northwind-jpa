package co.da.nw.util;

import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

public class JqgridFilter {

    private String source;
    private String groupOp;
    private List<Rule> rules;

    public JqgridFilter(String source, String groupOp, List<Rule> rules) {
        this.source = source;
        this.groupOp = groupOp;
        this.rules = ImmutableList.copyOf(rules);
    }

    public JqgridFilter(String source) {
        this.source = source;
    }

    public JqgridFilter() {
    }

    public String getSource() {
        return source;
    }

    public String getGroupOp() {
        return groupOp;
    }

    public List<Rule> getRules() {
        // Okay to return rules since we know that Rule is immutable.
        return rules;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setGroupOp(String groupOp) {
        this.groupOp = groupOp;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("source", source)
                .add("groupOp", groupOp)
                .add("rules", rules)
                .toString();
    }

    public static class Rule {
        private String junction;
        private String field;
        private String op;
        private String data;

        public Rule(String junction, String field, String op, String data) {
            this.junction = junction;
            this.field = field;
            this.op = op;
            this.data = data;
        }

        public Rule() {

        }

        public String getJunction() {
            return junction;
        }

        public String getField() {
            return field;
        }

        public String getOp() {
            return op;
        }

        public String getData() {
            return data;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("junction", junction)
                    .add("field", field)
                    .add("op", op)
                    .add("data", data)
                    .toString();
        }
    }

}
