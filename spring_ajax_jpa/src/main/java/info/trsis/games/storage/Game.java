package info.trsis.games.storage;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GAME")
public class Game implements Serializable
{
    public Game(
        String title, 
        double price, 
        LocalDate releaseDate, 
        Integer developer, 
        Integer publisher)
    {
        setTitle(title);
        setPrice(price);
        setReleaseDate(releaseDate);
        setDeveloper(developer);
        setPublisher(publisher);
    }

    @Id
    @Column(name = "GAME_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "RELEASE_DATE")
    private LocalDate releaseDate;

    @Column(name = "DEVELOPER_ID")
    private Integer developer;

    @Column(name = "PUBLISHER_ID")
    private Integer publisher;


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public Integer getDeveloper() { return developer; }
    public void setDeveloper(Integer developer) { this.developer = developer; }

    public Integer getPublisher() { return publisher; }
    public void setPublisher(Integer publisher) { this.publisher = publisher; }
}
