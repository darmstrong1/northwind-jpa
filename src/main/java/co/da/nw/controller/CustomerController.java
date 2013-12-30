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

import co.da.nw.dao.CustomerPredicates;
import co.da.nw.domain.Customer;
import co.da.nw.dto.CustomerDTO;
import co.da.nw.dto.reply.JqgridReply;
import co.da.nw.dto.reply.StatusReply;
import co.da.nw.service.CustomerService;
import co.da.nw.util.JqgridFilter;
import co.da.nw.util.Mapper;

import com.google.common.collect.ImmutableList;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/home/customer")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Resource
    private CustomerService service;

    @RequestMapping
    public String getCustomerPage() {
        return "home/customer";
    }

    @RequestMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public JqgridReply<CustomerDTO> list(
            @RequestParam("_search") Boolean search,
            @RequestParam(value = "filters", required = false) String filters,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows,
            @RequestParam(value = "sidx", required = false) String sidx,
            @RequestParam(value = "sord", required = false) String sord) {

        Pageable pageRequest = new PageRequest(page - 1, rows);

        // If search is true, filter out the records based on the filters String.
        Page<Customer> categories = search ? getFilteredRecords(filters, pageRequest) : service.findAll(pageRequest);
        List<CustomerDTO> customerDTOs = Mapper.mapDomainsToDTOs(categories);

        return new JqgridReply<>(
                Integer.valueOf(categories.getNumber() + 1).toString(),
                Integer.valueOf(categories.getTotalPages()).toString(),
                Long.valueOf(categories.getTotalElements()).toString(),
                customerDTOs);
    }

    /**
     * Parse the JqgridFilter to build the predicates to use in our search.
     * 
     * @param filter
     * @return
     */
    private Predicate buildPredicates(JqgridFilter filter) {
        BooleanBuilder builder = new BooleanBuilder();

        for (JqgridFilter.Rule rule : filter.getRules()) {
            String field = rule.getField();

            switch (field) {
            case "customerId":
                builder.and(CustomerPredicates.customerIdIsLike(rule.getData()));
                break;

            case "companyNm":
                builder.and(CustomerPredicates.companyNmIsLike(rule.getData()));
                break;

            case "contactNm":
                builder.and(CustomerPredicates.contactNmIsLike(rule.getData()));
                break;

            case "contactTitle":
                builder.and(CustomerPredicates.contactTitleIsLike(rule.getData()));
                break;

            case "address":
                builder.and(CustomerPredicates.addressIsLike(rule.getData()));
                break;

            case "city":
                builder.and(CustomerPredicates.cityIsLike(rule.getData()));
                break;

            case "region":
                builder.and(CustomerPredicates.regionIsLike(rule.getData()));
                break;

            case "postalCode":
                builder.and(CustomerPredicates.postalCdIsLike(rule.getData()));
                break;

            case "country":
                builder.and(CustomerPredicates.countryIsLike(rule.getData()));
                break;

            case "phone":
                builder.and(CustomerPredicates.phoneIsLike(rule.getData()));
                break;

            case "fax":
                builder.and(CustomerPredicates.faxIsLike(rule.getData()));
                break;

            default:
                LOGGER.warn("Unknown field type:" + field);
            }
        }

        return builder.getValue();
    }

    private Page<Customer> getFilteredRecords(String filters, Pageable pageRequest) {

        // Build the predicates to use for the filtered search.
        Predicate predicates = buildPredicates(Mapper.map(filters));

        return service.findAll(predicates, pageRequest);
    }

    @RequestMapping(value = "/get", produces = "application/json")
    @ResponseBody
    public CustomerDTO get(@RequestBody CustomerDTO dto) {
        Customer customer = service.findById(dto.getCustomerId());
        return customer == null ? null : Mapper.map(customer);
    }

    @RequestMapping(value = "/create", produces = "application/json", consumes = "application/json",
            method = RequestMethod.POST)
    public @ResponseBody
    StatusReply create(@Valid @RequestBody CustomerDTO dto, BindingResult result) {

        StatusReply reply;

        if (result.hasErrors()) {

            LOGGER.error("Add customer form contains errors:");
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
    StatusReply update(@Valid @RequestBody CustomerDTO dto, BindingResult result) {

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
    StatusReply delete(@RequestParam String customerId) {

        return new StatusReply(service.delete(customerId) != null);
    }

}
