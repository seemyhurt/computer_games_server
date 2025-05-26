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
@Table(name = "developer")
public class Developer implements Serializable 
{ 
    public Developer() {}

    public Developer(
        String name, 
        String county, 
        LocalDate foundedDate)
    {
        setName(name);
        setCounty(county);
        setFoundedDate(foundedDate);
    }

    @Id
    @Column(name = "developer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "developer_name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "founded_date")
    private LocalDate foundedDate;


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
        
    public String getCounty() { return country; }
    public void setCounty(String country) { this.country = country; }

    public LocalDate getFoundedDate() { return foundedDate; }
    public void setFoundedDate(LocalDate foundedDate) { this.foundedDate = foundedDate; }
}
