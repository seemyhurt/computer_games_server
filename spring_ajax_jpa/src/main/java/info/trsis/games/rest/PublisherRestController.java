package info.trsis.games.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import info.trsis.games.model.PublisherDTO;
import info.trsis.games.service.PublisherService;
import info.trsis.games.model.OperationStatusDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PublisherRestController 
{
    private final PublisherService publisherService;

    @Operation(summary = "Get all publishers", description = "Get all publishers", tags={ "Publishers" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "List of all publishers", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PublisherDTO.class)))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) 
    })
    @RequestMapping(value = "/publishers", produces = { "application/json" }, method = RequestMethod.GET)

    ResponseEntity<?> publishersGet()
    {
        try {
            List<PublisherDTO> publishers = publisherService.listAll();
            return ResponseEntity.ok(publishers);
        // } catch (AuthenticationException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.UNAUTHORIZED)
        //         .body(new OperationStatusDTO("Authentication required"));
        // } catch (AccessDeniedException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.FORBIDDEN)
        //         .body(new OperationStatusDTO("Access denied"));
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new OperationStatusDTO("Failed to get publishers, error: " + e.getMessage()));
        }
    }


    @Operation(summary = "Delete a publisher", description = "Delete a publisher by id", tags={ "Publishers" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Publisher deleted successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "404", description = "Publisher not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) 
    })
    @RequestMapping(value = "/publishers/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
    
    ResponseEntity<OperationStatusDTO> publishersIdDelete(@Parameter(in = ParameterIn.PATH, description = "Publisher id", required=true, schema=@Schema()) @PathVariable("id") Integer id)
    {
        try {
            if (id == null)
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OperationStatusDTO("Invalid publisher id"));

            publisherService.delete(id);
            return ResponseEntity.ok(new OperationStatusDTO("Publisher deleted successfully"));
        // } catch (AuthenticationException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.UNAUTHORIZED)
        //         .body(new OperationStatusDTO("Authentication required"));
        // } catch (AccessDeniedException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.FORBIDDEN)
        //         .body(new OperationStatusDTO("Access denied"));
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new OperationStatusDTO("Failed to delete publisher, error: " + e.getMessage()));
        }
    }


    @Operation(summary = "Get a publisher by ID", description = "Get a publisher by ID", tags={ "Publishers" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Publisher finded successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PublisherDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "404", description = "Publisher not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) 
    })
    @RequestMapping(value = "/publishers/{id}", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<?> publisherIdGet(@Parameter(in = ParameterIn.PATH, description = "Publisher id", required=true, schema=@Schema()) @PathVariable("id") Integer id)
    {
        try {
            if (id == null)
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OperationStatusDTO("Invalid publisher id"));

            PublisherDTO publisher = publisherService.findById(id);
            if (publisher == null)
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new OperationStatusDTO("Publisher not found"));

            return ResponseEntity.ok(publisher);
        // } catch (AuthenticationException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.UNAUTHORIZED)
        //         .body(new OperationStatusDTO("Authentication required"));
        // } catch (AccessDeniedException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.FORBIDDEN)
        //         .body(new OperationStatusDTO("Access denied"));
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new OperationStatusDTO("Failed to get publisher, error: " + e.getMessage()));
        }
    }


    @Operation(summary = "Create a new publisher", description = "Create a new publisher", tags={ "Publishers" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Publisher created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) })
    @RequestMapping(value = "/publishers", produces = { "application/json" }, consumes = { "application/x-www-form-urlencoded" }, method = RequestMethod.POST)
    ResponseEntity<OperationStatusDTO> publishersPost(
        @Parameter(in = ParameterIn.DEFAULT, description = "Publisher name", required=true,schema=@Schema()) @RequestParam(value="name", required=true)  String name, 
        @Parameter(in = ParameterIn.DEFAULT, description = "Publisher country", required=true,schema=@Schema()) @RequestParam(value="country", required=true)  String country)
    {
        try {
            PublisherDTO publisher = publisherService.add(name, country);
            if (publisher == null)
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OperationStatusDTO("Invalid input"));

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new OperationStatusDTO("Publisher created successfully"));
        // } catch (AuthenticationException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.UNAUTHORIZED)
        //         .body(new OperationStatusDTO("Authentication required"));
        // } catch (AccessDeniedException e) {
        //     return ResponseEntity
        //         .status(HttpStatus.FORBIDDEN)
        //         .body(new OperationStatusDTO("Access denied"));
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new OperationStatusDTO("Failed to create publisher, error: " + e.getMessage()));
        }
    }
}
