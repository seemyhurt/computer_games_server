package info.trsis.games.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DeveloperDTO 
{
    @JsonProperty("id")
    private Integer id = null;
    
    @JsonProperty("name")
    private String name = null;
    
    @JsonProperty("country")
    private String country = null;
    
    @JsonProperty("foundedDate")
    private LocalDate foundedDate = null;


    @NotNull
    @Schema(example = "1", required = true, description = "Game developer identifier")
    public Integer getId() {  return id; }
    public void setId(Integer id) { this.id = id; }

    @NotNull
    @Schema(example = "CD Projekt Red", required = true, description = "Game developer name")
    public String getName() {  return name; }
    public void setName(String name) { this.name = name; }


    @NotNull
    @Schema(example = "Poland", required = true, description = "Game developer country")
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    @NotNull
    @Schema(required = true, description = "Game developer founded data")
    public LocalDate getFoundedDate() { return foundedDate; }
    public void setFoundedDate(LocalDate foundedDate) { this.foundedDate = foundedDate; }
}
