package co.da.nw.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.da.nw.domain.Category;
import co.da.nw.dto.CategoryDTO;
import co.da.nw.dto.SearchDTO;
import co.da.nw.service.CategoryService;

@Controller
@SessionAttributes("category")
public class CategoryController extends AbstractController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    
    protected static final String ERROR_MESSAGE_KEY_DELETED_CATEGORY_WAS_NOT_FOUND = "error.message.deleted.not.found";
    protected static final String ERROR_MESSAGE_KEY_EDITED_CATEGORY_WAS_NOT_FOUND = "error.message.edited.not.found";
    
    protected static final String FEEDBACK_MESSAGE_KEY_CATEGORY_CREATED = "feedback.message.category.created";
    protected static final String FEEDBACK_MESSAGE_KEY_CATEGORY_DELETED = "feedback.message.category.deleted";
    protected static final String FEEDBACK_MESSAGE_KEY_CATEGORY_EDITED = "feedback.message.category.edited";
    
    protected static final String MODEL_ATTIRUTE_CATEGORY = "category";
    protected static final String MODEL_ATTRIBUTE_CATEGORIES = "categories";
    protected static final String MODEL_ATTRIBUTE_SEARCH_CRITERIA = "searchCriteria";
    
    protected static final String CATEGORY_ADD_FORM_VIEW = "category/create";
    protected static final String CATEGORY_EDIT_FORM_VIEW = "category/edit";
    protected static final String CATEGORY_LIST_VIEW = "category/list";
    protected static final String CATEGORY_SEARCH_RESULT_VIEW = "category/searchResults";
    
    protected static final String REQUEST_MAPPING_LIST = "/";
    
    @Resource
    private CategoryService service;

    /**
     * Processes delete category requests.
     * @param id    The id of the deleted category.
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/category/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) throws EntityNotFoundException {
        LOGGER.debug("Deleting category with id: " + id);

        try {
            Category deleted = service.delete(id);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CATEGORY_DELETED, deleted.getCategoryName());
        } catch(EntityNotFoundException e) {
            LOGGER.error("EntityNotFoundException: ", e);
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_DELETED_CATEGORY_WAS_NOT_FOUND);
        }

        return createRedirectViewPath(REQUEST_MAPPING_LIST);
    }

    /**
     * Processes search category requests.
     * @param searchCriteria    The search criteria.
     * @param model
     * @return
     */
    @RequestMapping(value = "/category/search", method = RequestMethod.POST)
    public String search(@ModelAttribute(MODEL_ATTRIBUTE_SEARCH_CRITERIA)SearchDTO searchCriteria, Model model) {
        LOGGER.debug("Searching categorys with search criteria: " + searchCriteria);
        
        String searchTerm = searchCriteria.getSearchTerm();
        
        return CATEGORY_SEARCH_RESULT_VIEW;
    }    
    
    /**
     * Processes create category requests.
     * @param model
     * @return  The name of the create category form view.
     */
    @RequestMapping(value = "/category/create", method = RequestMethod.GET) 
    public String showCreateCategoryForm(Model model) {
        LOGGER.debug("Rendering create category form");
        
        model.addAttribute(MODEL_ATTIRUTE_CATEGORY, new CategoryDTO());

        return CATEGORY_ADD_FORM_VIEW;
    }    

    /**
     * Processes the submissions of create category form.
     * @param created   The information of the created categories.
     * @param bindingResult
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/category/create", method = RequestMethod.POST)
    public String submitCreateCategoryForm(@Valid @ModelAttribute(MODEL_ATTIRUTE_CATEGORY) CategoryDTO created, BindingResult bindingResult, RedirectAttributes attributes) {
        LOGGER.debug("Create category form was submitted with information: " + created);

        if (bindingResult.hasErrors()) {
            return CATEGORY_ADD_FORM_VIEW;
        }
                
        Category cat = service.create(created);

        addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CATEGORY_CREATED, cat.getCategoryName());

        return createRedirectViewPath(REQUEST_MAPPING_LIST);
    }

    /**
     * Processes edit category requests.
     * @param id    The id of the edited category.
     * @param model
     * @param attributes
     * @return  The name of the edit category form view.
     */
    @RequestMapping(value = "/category/edit/{id}", method = RequestMethod.GET)
    public String showEditCategoryForm(@PathVariable("id") Long id, Model model, RedirectAttributes attributes) {
        LOGGER.debug("Rendering edit category form for category with id: " + id);
        
        Category cat = service.findById(id);
        if (cat == null) {
            LOGGER.debug("No category found with id: " + id);
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_CATEGORY_WAS_NOT_FOUND);
            return createRedirectViewPath(REQUEST_MAPPING_LIST);            
        }

        model.addAttribute(MODEL_ATTIRUTE_CATEGORY, constructFormObject(cat));
        
        return CATEGORY_EDIT_FORM_VIEW;
    }

    /**
     * Processes the submissions of edit category form.
     * @param updated   The information of the edited category.
     * @param bindingResult
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/category/edit", method = RequestMethod.POST)
    public String submitEditCategoryForm(@Valid @ModelAttribute(MODEL_ATTIRUTE_CATEGORY) CategoryDTO updated, BindingResult bindingResult, RedirectAttributes attributes) {
        LOGGER.debug("Edit category form was submitted with information: " + updated);
        
        if (bindingResult.hasErrors()) {
            LOGGER.debug("Edit category form contains validation errors. Rendering form view.");
            return CATEGORY_EDIT_FORM_VIEW;
        }
        
        try {
            Category cat = service.update(updated);
            addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_CATEGORY_EDITED, cat.getCategoryName());
        } catch (EntityNotFoundException e) {
            LOGGER.error("EntityNotFoundException: ", e);
            addErrorMessage(attributes, ERROR_MESSAGE_KEY_EDITED_CATEGORY_WAS_NOT_FOUND);
        }
        
        return createRedirectViewPath(REQUEST_MAPPING_LIST);
    }
    
    private CategoryDTO constructFormObject(Category cat) {
        CategoryDTO formObject = new CategoryDTO();
        
        formObject.setCategoryId(cat.getCategoryId());
        formObject.setCategoryName(cat.getCategoryName());
        formObject.setDescription(cat.getDescription());
        formObject.setPicture(cat.getPicture());
        
        return formObject;
    }

    /**
     * Processes requests to home page which lists all available categories.
     * @param model
     * @return  The name of the category list view.
     */
    @RequestMapping(value = REQUEST_MAPPING_LIST, method = RequestMethod.GET)
    public String showList(Model model) {
        LOGGER.debug("Rendering category list page");

        List<Category> cats = service.findAll();
        model.addAttribute(MODEL_ATTRIBUTE_CATEGORIES, cats);
        model.addAttribute(MODEL_ATTRIBUTE_SEARCH_CRITERIA, new CategoryDTO());

        return CATEGORY_LIST_VIEW;
    }

    /**
     * This setter method should only be used by unit tests
     * @param categoryService
     */
    protected void setCategoryService(CategoryService service) {
        this.service = service;
    }
}
