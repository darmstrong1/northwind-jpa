package co.da.nw.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.da.nw.domain.Category;
import co.da.nw.dto.CategoryDTO;
import co.da.nw.dto.reply.JqgridReply;
import co.da.nw.dto.reply.StatusReply;
import co.da.nw.service.CategoryService;
import co.da.nw.util.JqgridFilter;
import co.da.nw.util.Mapper;

import com.google.common.collect.ImmutableList;

@Controller
@RequestMapping("/home/category")
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(CategoryController.class);

    @Resource
    private CategoryService service;

    // @Resource
    // private Validator validator;

    @RequestMapping
    public String getCategoryPage() {
        return "home/category";
    }

    @RequestMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public JqgridReply<CategoryDTO> list(
            @RequestParam("_search") Boolean search,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows,
            @RequestParam(value = "sidx", required = false) String sidx,
            @RequestParam(value = "sord", required = false) String sord) {

        Pageable pageRequest = new PageRequest(page - 1, rows);

        if (search == true) {
            return getFilteredRecords(filters, pageRequest);
        }

        Page<Category> categories = service.findAll(pageRequest);
        List<CategoryDTO> catDTOs = Mapper.mapToCategoryDTOs(categories);

        return new JqgridReply<>(
                Integer.valueOf(categories.getNumber() + 1).toString(),
                Integer.valueOf(categories.getTotalPages()).toString(),
                Long.valueOf(categories.getTotalElements()).toString(),
                catDTOs);
    }

    public JqgridReply<CategoryDTO> getFilteredRecords(String filters, Pageable pageRequest) {
        String qCategoryName = null;
        String qDescription = null;

        JqgridFilter filter = Mapper.map(filters);
        for (JqgridFilter.Rule rule : filter.getRules()) {
            if (rule.getField().equals("name")) {
                qCategoryName = rule.getData();
            } else if (rule.getField().equals("description")) {
                qDescription = rule.getData();
            }
        }

        Page<Category> categories = null;

        if (qCategoryName != null && qDescription != null) {
            categories = service.findByNameLikeAndDescriptionLike(qCategoryName, qDescription, pageRequest);
        } else if (qCategoryName != null) {
            categories = service.findByNameLike(qCategoryName, pageRequest);
        } else if (qDescription != null) {
            categories = service.findByDescriptionLike(qDescription, pageRequest);
        }

        List<CategoryDTO> catDTOs = Mapper.mapToCategoryDTOs(categories);

        return new JqgridReply<>(
                Integer.valueOf(categories.getNumber() + 1).toString(),
                Integer.valueOf(categories.getTotalPages()).toString(),
                Long.valueOf(categories.getTotalElements()).toString(),
                catDTOs);
    }

    @RequestMapping(value = "/get", produces = "application/json")
    @ResponseBody
    public CategoryDTO get(@RequestBody CategoryDTO cat) {
        List<Category> categories = service.findByName(cat.getName());
        return categories == null ? null : Mapper.map(categories.get(0));
    }

    // @RequestMapping(value = "/create", produces = "application/json", method
    // = RequestMethod.POST)
    // public @ResponseBody
    // StatusReply create(@RequestParam String name, @RequestParam String
    // description) {
    //
    // Category cat = new Category.Builder()
    // .setDescription(description)
    // .build(name);
    //
    // return new StatusReply(service.create(cat) != null);
    // }

    // adding consumes in RequestMapping didn't work... 2013/12/19
    @RequestMapping(value = "/create", produces = "application/json", consumes = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    StatusReply create(@Valid @RequestBody CategoryDTO dto, BindingResult result) {

        StatusReply reply;

        if (result.hasErrors()) {

            LOGGER.error("Add category form contains errors:");
            ImmutableList.Builder<String> builder = new ImmutableList.Builder<>();
            for (ObjectError error : result.getAllErrors()) {
                builder.add(error.getDefaultMessage());
                LOGGER.error(error.toString());
            }
            ImmutableList<String> errors = builder.build();

            reply = new StatusReply(false, errors);
        } else {

            reply = new StatusReply(service.create(dto) != null);
        }

        return reply;
    }

    @RequestMapping(value = "/update", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    StatusReply update(@Valid @RequestBody CategoryDTO dto, BindingResult result) {

        StatusReply reply;

        if (result.hasErrors()) {

            LOGGER.error("Add category form contains errors:");
            ImmutableList.Builder<String> builder = new ImmutableList.Builder<>();
            for (ObjectError error : result.getAllErrors()) {
                builder.add(error.getDefaultMessage());
                LOGGER.error(error.toString());
            }
            ImmutableList<String> errors = builder.build();

            reply = new StatusReply(false, errors);
        } else {

            reply = new StatusReply(service.update(dto) != null);
        }

        return reply;
    }

    @RequestMapping(value = "/delete", produces = "application/json", method = RequestMethod.POST)
    public @ResponseBody
    StatusReply delete(@RequestParam Long id) {

        return new StatusReply(service.delete(id) != null);
    }
}
