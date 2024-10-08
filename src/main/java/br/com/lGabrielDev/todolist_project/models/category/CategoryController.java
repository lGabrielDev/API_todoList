package br.com.lGabrielDev.todolist_project.models.category;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.lGabrielDev.todolist_project.models.category.dtos.CategoryCreateDto;
import br.com.lGabrielDev.todolist_project.models.category.dtos.CategoryReadOneDto;
import br.com.lGabrielDev.todolist_project.models.category.dtos.CategoryWithIdNameAndOwnerIdDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/api")
@Tag(name = "categories")
@SecurityRequirement(name = "basica")
public class CategoryController {
    
    //injected attributes
    @Autowired
    private CategoryService categoryService;

    // ------------------------- CREATE -------------------------
    @Operation(summary = "create a category", description = "You only have to inform the 'category name'. Remember, your categories must to be unique. You cannot have categories with the same name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "CREATED - category successfully"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - username and password are wrong", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "409", description = "CONFLICT - category name is wrong.", content = {@Content(examples = {})}),

    })
    @PostMapping("/categories")
    public ResponseEntity<List<CategoryWithIdNameAndOwnerIdDto>> createCategory(@RequestBody CategoryCreateDto categoryDto){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(this.categoryService.createCategory(categoryDto));
    }

    // ------------------------- READ All -------------------------
    @Operation(summary = "read all categories", description = "You only have to inform the 'category name'. Remember, your categories must to be unique. You cannot have categories with the same name.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - username and password are wrong", content = {@Content(examples = {})}),
    })
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryWithIdNameAndOwnerIdDto>> readAll(){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.categoryService.readAllCategories());
    }

    // ------------------------- READ by #ID -------------------------
    @Operation(summary = "read a particular category", description = "The authenticated person can only access its own categories.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - username and password are wrong", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "404", description = "NOT_FOUND - category #ID does not exists in our database", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE - category belongs to another person. The authenticated person cannot access others persons categories.", content = {@Content(examples = {})}),
    })
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryReadOneDto> readOneCategory(@PathVariable(value = "id") Long categoryId){
         return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.categoryService.readOneCategory(categoryId));
    }

    // ------------------------- UPDATE -------------------------
    @Operation(
        summary = "Update a particular category",
        description = "The authenticated person can only update their own categories",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "You only need to provide the 'category name'.")
    )
    
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK - category updated successfully."),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - username and password are wrong.", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "404", description = "NOT_FOUND - category #ID does not exists in our database.", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE - category belongs to another person. The authenticated person cannot update others persons categories.", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "409", description = "CONFLICT - category name is wrong.", content = {@Content(examples = {})}),     
    })
    @PutMapping("/categories/{id}")
    public ResponseEntity<List<CategoryWithIdNameAndOwnerIdDto>> updateCategory(@PathVariable(value = "id") Long categoryId, @RequestBody CategoryCreateDto categoryDto){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.categoryService.updateCategory(categoryId, categoryDto));
    }

    // ------------------------- DELETE -------------------------
    @Operation(summary = "delete a particular category", description = "The authenticated person can only delete its own categories.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK - category deleted successfully."),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - username and password are wrong.", content = {@Content(examples = {})}),     
        @ApiResponse(responseCode = "404", description = "NOT_FOUND - category #ID does not exists in our database.", content = {@Content(examples = {})}),     
        @ApiResponse(responseCode = "406", description = "NOT_ACCEPTABLE - category belongs to another person. The authenticated person cannot delete others persons categories.", content = {@Content(examples = {})}),     
        @ApiResponse(responseCode = "417", description = "EXPECTATION_FAILED - cannot delete a category that contains tasks on it.", content = {@Content(examples = {})}),     
    })
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<List<CategoryWithIdNameAndOwnerIdDto>> deleteCategory(@PathVariable(value = "id") Long categoryId){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.categoryService.deleteCategoryById(categoryId));
    }
}