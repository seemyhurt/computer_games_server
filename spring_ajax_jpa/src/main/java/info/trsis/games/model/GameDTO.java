package info.trsis.games.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class GameDTO 
{
    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("price")
    private Double price = null;

    @JsonProperty("releaseDate")
    private LocalDate releaseDate = null;

    @JsonProperty("developer")
    private Integer developer = null;

    @JsonProperty("publisher")
    private Integer publisher = null;

    @NotNull
    @Schema(example = "1", required = true, description = "Game identifier")
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @NotNull
    @Schema(example = "The Witcher 3: Wild Hunt", required = true, description = "Game title")
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    @NotNull
    @Schema(example = "39.99", required = true, description = "Game price")
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    @NotNull
    @Schema(required = true, description = "Game release data")
    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    @NotNull
    @Schema(example = "1", required = true, description = "Game developer identifier")
    public Integer getDeveloper() { return developer; }
    public void setDeveloper(Integer developer) { this.developer = developer; }

    @NotNull
    @Schema(example = "1", required = true, description = "Game publisher identifier")
    public Integer getPublisher() { return publisher; }
    public void setPublisher(Integer publisher) { this.publisher = publisher; }
}
