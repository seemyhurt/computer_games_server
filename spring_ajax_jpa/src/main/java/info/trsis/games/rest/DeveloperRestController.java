package info.trsis.games.rest;

import java.util.List;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import info.trsis.games.model.DeveloperDTO;
import info.trsis.games.service.DeveloperService;
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
public class DeveloperRestController 
{
    private final DeveloperService developerService;

    @Operation(summary = "Get all developers", description = "Get all developers", tags={ "Developers" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "List of all developers", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DeveloperDTO.class)))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) 
    })
    @RequestMapping(value = "/developers", produces = { "application/json" }, method = RequestMethod.GET)

    ResponseEntity<?> developersGet()
    {
        try {
            List<DeveloperDTO> developers = developerService.listAll();
            return ResponseEntity.ok(developers);
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
                .body(new OperationStatusDTO("Failed to get developers, error: " + e.getMessage()));
        }
    }


    @Operation(summary = "Delete a developer", description = "Delete a developer by id", tags={ "Developers" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Developer deleted successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "404", description = "Developer not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) 
    })
    @RequestMapping(value = "/developers/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
    
    ResponseEntity<OperationStatusDTO> developersIdDelete(@Parameter(in = ParameterIn.PATH, description = "Developer id", required=true, schema=@Schema()) @PathVariable("id") Integer id)
    {
        try {
            if (id == null)
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OperationStatusDTO("Invalid developer id"));

            developerService.delete(id);
            return ResponseEntity.ok(new OperationStatusDTO("Developer deleted successfully"));
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
                .body(new OperationStatusDTO("Developer not found. error: " + e.getMessage()));
        }
    }


    @Operation(summary = "Get a developer by ID", description = "Get a developer by ID", tags={ "Developers" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Developer finded successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeveloperDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "404", description = "Developer not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) 
    })
    @RequestMapping(value = "/developers/{id}", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<?> developerIdGet(@Parameter(in = ParameterIn.PATH, description = "Developer id", required=true, schema=@Schema()) @PathVariable("id") Integer id)
    {
        try {
            if (id == null)
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OperationStatusDTO("Invalid developer id"));

            DeveloperDTO developer = developerService.findById(id);
            if (developer == null)
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new OperationStatusDTO("Developer not found"));

            return ResponseEntity.ok(developer);
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
                .body(new OperationStatusDTO("Developer not found. error: " + e.getMessage()));
        }
    }


    @Operation(summary = "Create a new developer", description = "Create a new developer", tags={ "Developers" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Developer created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "401", description = "Authentication required", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))),
        @ApiResponse(responseCode = "403", description = "Authentication granted but user does not have access", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationStatusDTO.class))) })
    @RequestMapping(value = "/developers", produces = { "application/json" }, consumes = { "application/x-www-form-urlencoded" }, method = RequestMethod.POST)
    ResponseEntity<OperationStatusDTO> developersPost(
        @Parameter(in = ParameterIn.DEFAULT, description = "Developer name", required=true,schema=@Schema()) @RequestParam(value="name", required=true)  String name, 
        @Parameter(in = ParameterIn.DEFAULT, description = "Developer country", required=true,schema=@Schema()) @RequestParam(value="country", required=true)  String country, 
        @Parameter(in = ParameterIn.DEFAULT, description = "Developer founded date", required=true,schema=@Schema()) @RequestParam(value="foundedDate", required=true)  LocalDate foundedDate)
    {
        try {
            DeveloperDTO developer = developerService.add(name, country, foundedDate);
            if (developer == null)
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new OperationStatusDTO("Invalid input"));

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new OperationStatusDTO("Developer created successfully"));
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
                .body(new OperationStatusDTO("Failed to create developer, error: " + e.getMessage()));
        }
    }
}
