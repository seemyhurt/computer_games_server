package info.trsis.games.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Data
public class OperationStatusDTO 
{
    public OperationStatusDTO(String status) { setStatus(status);}
    
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    @JsonSetter(nulls = Nulls.FAIL)
    @JsonProperty("status")
    private String status = null;

    @Schema(example = "Operation error, invalid fields", description = "Description of the operation execution status")
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
