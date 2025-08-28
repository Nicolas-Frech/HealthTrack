package br.com.nicolasfrech.HealthTrack.domain.user;

public class User {

    private Long id;
    private String username;
    private String password;
    private Role role;
    private String medicCRM;

    public User() {
    }

    public User(Long id, String username, String password, Role role, String medicCRM) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.medicCRM = medicCRM;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getMedicCRM() {
        return medicCRM;
    }

    public void setMedicCRM(String medicCRM) {
        this.medicCRM = medicCRM;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
