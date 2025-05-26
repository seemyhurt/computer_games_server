package info.trsis.games.storage;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "publisher")
public class Publisher implements Serializable 
{
    public Publisher() {}

    public Publisher(
        String name, 
        String county)
    {
        setName(name);
        setCounty(county);
    }

    @Id
    @Column(name = "publisher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "publisher_name")
    private String name;

    @Column(name = "country")
    private String country;


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
        
    public String getCounty() { return country; }
    public void setCounty(String country) { this.country = country; }
}

