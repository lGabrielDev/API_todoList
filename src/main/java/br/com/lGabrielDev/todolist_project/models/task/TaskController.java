package br.com.lGabrielDev.todolist_project.models.task;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.lGabrielDev.todolist_project.models.task.dto.TaskCreateDto;
import br.com.lGabrielDev.todolist_project.models.task.dto.TaskFullDto;
import br.com.lGabrielDev.todolist_project.models.task.dto.TaskUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/api")
@Tag(name = "tasks")
@SecurityRequirement(name = "basica")
public class TaskController {

    //injected attributes
    @Autowired
    private TaskService taskService;
    
    // ------------------------- CREATE -------------------------
    @Operation(
        summary = "create a task",
        description = "You cannot inform category_IDs from another person",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "You don't need to pass all the attributes. Just inform the attributes you want to update."))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "CREATED - task successfully"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - username and password are wrong", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "409",description = "CONFLICT - some attribute receive a wrong value", content = {@Content(examples = {})})
    })
    @PostMapping("/tasks")
    public ResponseEntity<List<TaskFullDto>> createTask(@RequestBody TaskCreateDto taskDto){
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(this.taskService.createTask(taskDto));
    }

    // ------------------------- READ ALL -------------------------
    @Operation(summary = "read all tasks", description = "The request params are not required.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - username and password are wrong", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - username and password are wrong", content = {@Content(examples = {})}) //no content
    })
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskFullDto>> readAllTasks(
        @RequestParam(required = false, value = "priority") Integer priority,
        @RequestParam(required = false, value = "completed") Boolean completed,
        @RequestParam(required = false, value = "title_like") String titleLike
    )
    {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.taskService.readAllTasks(priority,completed,titleLike));
    }

    // ------------------------- UPDATE -------------------------
    @Operation(
        summary = "update a task",
        description = "You can only update your own tasks",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "You don't need to pass all the attributes. Just inform the attributes you want to update.")
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK - task updated successfully"),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - username and password are wrong", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "404", description = "NOT_FOUND - task_id does not exists in our database", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "406",description = "NOT_ACCEPTABLE - task from another person", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "409",description = "CONFLICT - some attribute receive a wrong value",content = {@Content(examples = {})})
    })
    @PutMapping("/tasks/{id}")
    public ResponseEntity<List<TaskFullDto>> updateTask(@PathVariable(value = "id") Long taskId, @RequestBody TaskUpdateDto taskDto){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.taskService.updateTask(taskId, taskDto));
    }

    // ------------------------- DELETE -------------------------
    @Operation(summary = "delete a particular task", description = "You can only delete your own tasks")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "task deleted successfully", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - username and password are wrong", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "404", description = "NOT_FOUND - task_id does not exists in our database", content = {@Content(examples = {})}),
        @ApiResponse(responseCode = "406",description = "NOT_ACCEPTABLE - task from another person", content = {@Content(examples = {})}),
        
    })
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<List<TaskFullDto>> deleteTask(@PathVariable(value = "id") Long taskId){
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(this.taskService.deleteTaskById(taskId));
    }
}