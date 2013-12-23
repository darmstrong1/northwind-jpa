package co.da.nw.util;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import co.da.nw.domain.Category;
import co.da.nw.dto.CategoryDTO;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/**
 * This class's purpose is to provide static classes that map one object to
 * another.
 * 
 * @author david
 * 
 */
public class Mapper {

    public static JqgridFilter map(String jsonString) {
        Preconditions.checkNotNull(jsonString, "jsonString must not be null");

        JqgridFilter filter = null;

        ObjectMapper mapper = new ObjectMapper();

        try {
            filter = mapper.readValue(jsonString, JqgridFilter.class);
        } catch (Exception e) {
            // TODO: Create an Exception that extends RuntimeException
            throw new RuntimeException(e);
        }

        return filter;
    }

    public static Category map(CategoryDTO dto) {
        Preconditions.checkNotNull(dto, "dto must not be null");

        return new Category.Builder(dto.getName())
                .setDescription(dto.getDescription())
                .build();
    }

    public static CategoryDTO map(Category domain) {
        Preconditions.checkNotNull(domain, "domain must not be null");

        return new CategoryDTO(domain.getCategoryId(),
                domain.getCategoryName(),
                domain.getDescription());
    }

    public static List<Category> mapToCategories(Iterable<CategoryDTO> dtos) {
        Preconditions.checkNotNull(dtos, "dtos must not be null");

        ImmutableList.Builder<Category> bldr = new ImmutableList.Builder<>();

        for (CategoryDTO dto : dtos) {
            bldr.add(map(dto));
        }

        return bldr.build();
    }

    public static List<CategoryDTO> mapToCategoryDTOs(Iterable<Category> domains) {
        Preconditions.checkNotNull(domains, "domains must not be null");

        ImmutableList.Builder<CategoryDTO> bldr = new ImmutableList.Builder<>();

        for (Category domain : domains) {
            bldr.add(map(domain));
        }

        return bldr.build();
    }
}
