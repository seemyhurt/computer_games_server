package info.trsis.games.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PublisherDTO 
{
    @JsonProperty("id")
    private Integer id = null;
    
    @JsonProperty("name")
    private String name = null;
    
    @JsonProperty("country")
    private String country = null;

    @NotNull
    @Schema(example = "1", required = true, description = "Game publisher identifier")
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @NotNull
    @Schema(example = "CD Projekt", required = true, description = "Game publisher name")
    public String getName() { return name; }
    public void setName(String name) { this.name = name;}

    @NotNull
    @Schema(example = "Poland", required = true, description = "Game publisher county")
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
