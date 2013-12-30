package co.da.nw.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import co.da.nw.config.TestContext;
import co.da.nw.config.WebAppContext;
import co.da.nw.domain.Category;
import co.da.nw.dto.CategoryDTO;
import co.da.nw.dto.reply.JqgridReply;
import co.da.nw.service.CategoryService;
import co.da.nw.util.Mapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class, WebAppContext.class })
@WebAppConfiguration
public class WebAppContextCategoryControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private CategoryService serviceMock;

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private CategoryController controller;

    @Before
    public void setUp() {
        // We have to reset our mock between tests because the mock objects
        // are managed by the Spring container. If we would not reset them,
        // stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(serviceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void testList() {
        JqgridReply<CategoryDTO> reply = controller.list(false, null, 1, 10, "id", "asc");
        List<CategoryDTO> rows = reply.getRows();
        List<Category> categories = serviceMock.findAll();
        List<CategoryDTO> catDTOs = Mapper.mapDomainsToDTOs(categories);
        assertThat(rows, is(catDTOs));
    }

    @Test
    public void testDelete() {
        fail("Not yet implemented");
    }

    // @Test
    // public void testShowCreateCategoryForm() throws Exception {
    // mockMvc.perform(get("/category/create"))
    // .andExpect(status().isOk())
    // .andExpect(view().name(CategoryController.CATEGORY_ADD_FORM_VIEW))
    // .andExpect(forwardedUrl("/WEB-INF/jsp/category/create.jsp"))
    // .andExpect(model().attribute(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // hasProperty("categoryId", nullValue())))
    // .andExpect(model().attribute(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // hasProperty("categoryName", isEmptyOrNullString())))
    // .andExpect(model().attribute(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // hasProperty("description", isEmptyOrNullString())));
    // //
    // .andExpect(model().attribute(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // hasProperty("picture", nullValue())));
    //
    // verifyZeroInteractions(serviceMock);
    // }

    // @Test
    // public void
    // add_EmptyCategory_ShouldRenderFormViewAndReturnValidationErrorForName()
    // throws Exception {
    // CategoryDTO formObject = new CategoryDTO();
    // mockMvc.perform(post("/category/create")
    // .contentType(MediaType.APPLICATION_FORM_URLENCODED)
    // .content(TestUtil.convertObjectToFormUrlEncodedBytes(formObject))
    // .sessionAttr(CategoryController.MODEL_ATTIRUTE_CATEGORY, formObject)
    // )
    // .andExpect(status().isOk())
    // .andExpect(view().name(CategoryController.CATEGORY_ADD_FORM_VIEW))
    // .andExpect(forwardedUrl("/WEB-INF/jsp/category/create.jsp"))
    // .andExpect(model().attributeHasFieldErrors(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // "categoryName"))
    // .andExpect(model().attribute(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // hasProperty("categoryId", nullValue())))
    // .andExpect(model().attribute(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // hasProperty("categoryName", isEmptyOrNullString())))
    // .andExpect(model().attribute(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // hasProperty("description", isEmptyOrNullString())));
    //
    // verifyZeroInteractions(serviceMock);
    //
    // }

    // @Test
    // public void
    // add_DescriptionAndNameAreTooLong_ShouldRenderFormViewAndReturnValidationErrorsForNameAndDescription()
    // throws Exception {
    // String name =
    // TestUtil.createStringWithLength(Category.MAX_LENGTH_CATEGORY_NAME + 1);
    // String description =
    // TestUtil.createStringWithLength(Category.MAX_LENGTH_DESCRIPTION + 1);
    //
    // CategoryDTO formObject = new CategoryDTO();
    // formObject.setCategoryName(name);
    // formObject.setDescription(description);
    //
    // mockMvc.perform(post("/category/create")
    // .contentType(MediaType.APPLICATION_FORM_URLENCODED)
    // .content(TestUtil.convertObjectToFormUrlEncodedBytes(formObject))
    // .sessionAttr(CategoryController.MODEL_ATTIRUTE_CATEGORY, formObject)
    // )
    // .andExpect(status().isOk())
    // .andExpect(view().name(CategoryController.CATEGORY_ADD_FORM_VIEW))
    // .andExpect(forwardedUrl("/WEB-INF/jsp/category/create.jsp"))
    // .andExpect(model().attributeHasFieldErrors(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // "categoryName"))
    // .andExpect(model().attributeHasFieldErrors(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // "description"))
    // .andExpect(model().attribute(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // hasProperty("categoryId", nullValue())))
    // .andExpect(model().attribute(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // hasProperty("categoryName", is(name))))
    // .andExpect(model().attribute(CategoryController.MODEL_ATTIRUTE_CATEGORY,
    // hasProperty("description", is(description))));
    //
    // verifyZeroInteractions(serviceMock);
    // }

    // @Test
    // public void add_NewCategory_ShouldAddCategory() throws Exception {
    // String name = "Fruits";
    // String desc = "Various fruits";
    //
    // CategoryDTO formObject = new CategoryDTO();
    // formObject.setCategoryName(name);
    // formObject.setDescription(desc);
    //
    // Category added = new Category.Builder().setDescription(desc).build(name);
    //
    // when(serviceMock.create(formObject)).thenReturn(added);
    //
    // String expectedRedirectViewPath =
    // TestUtil.createRedirectViewPath(CategoryController.REQUEST_MAPPING_LIST);
    //
    // mockMvc.perform(post("/category/create")
    // .contentType(MediaType.APPLICATION_FORM_URLENCODED)
    // .content(TestUtil.convertObjectToFormUrlEncodedBytes(formObject))
    // .sessionAttr(CategoryController.MODEL_ATTIRUTE_CATEGORY, formObject)
    // )
    // .andExpect(status().isMovedTemporarily())
    // .andExpect(view().name(expectedRedirectViewPath))
    // .andExpect(redirectedUrl("/"));
    // //.andExpect(model().attribute(TodoController.PARAMETER_TODO_ID,
    // is("1")))
    // //.andExpect(flash().attribute(TodoController.FLASH_MESSAGE_KEY_FEEDBACK,
    // is("Todo entry: title was added.")));
    //
    // verify(serviceMock, times(1)).create(formObject);
    // verifyNoMoreInteractions(serviceMock);
    // }

    // @Test
    // public void deleteById_CategoryFound_ShouldDeleteCategory() throws
    // Exception {
    // String name = "Fruits";
    // String desc = "Various fruits";
    // long id = 1L;
    //
    // Category deleted = new
    // Category.Builder().setDescription(desc).build(name);
    // ReflectionTestUtils.setField(deleted, "categoryId", id);
    //
    // when(serviceMock.delete(id)).thenReturn(deleted);
    //
    // String expectedRedirectViewPath =
    // TestUtil.createRedirectViewPath(CategoryController.CATEGORY_LIST_VIEW);
    //
    // //mockMvc.perform(get("/"))
    // //.andExpect(status().isMovedTemporarily())
    // //.andExpect(view().name(expectedRedirectViewPath));
    // //.andExpect(flash().attribute(TodoController.FLASH_MESSAGE_KEY_FEEDBACK,
    // is("Todo entry: Foo was deleted.")));
    //
    // //verify(serviceMock, times(1)).delete(1L);
    // //verifyNoMoreInteractions(serviceMock);
    //
    // }

    @Test
    public void testSubmitCreateCategoryForm() {
        fail("Not yet implemented");
    }

    @Test
    public void testShowEditCategoryForm() {
        fail("Not yet implemented");
    }

    @Test
    public void testSubmitEditCategoryForm() {
        fail("Not yet implemented");
    }

    @Test
    public void testShowList() {
        fail("Not yet implemented");
    }

}
