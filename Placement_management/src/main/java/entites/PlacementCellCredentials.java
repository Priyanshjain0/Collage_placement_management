package entites;

import javax.persistence.*;

@Entity
@Table(name = "placementcell_credentials")
public class PlacementCellCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    // Constructors, getters, and setters

    public PlacementCellCredentials() {
        // Default constructor
    }

    public PlacementCellCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
