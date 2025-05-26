package info.trsis.games.storage;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "game")
public class Game implements Serializable
{
    public Game() {}

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
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private double price;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "developer_id")
    private Integer developer;

    @Column(name = "publisher_id")
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
