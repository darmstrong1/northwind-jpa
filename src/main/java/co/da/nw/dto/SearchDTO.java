package co.da.nw.dto;

public class SearchDTO {
    
    private String searchTerm;

    public SearchDTO() {

    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    
    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("searchTerm", searchTerm)
                .toString();
    }
}
