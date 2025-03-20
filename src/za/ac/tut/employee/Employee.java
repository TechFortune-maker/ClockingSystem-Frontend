
package za.ac.tut.employee;

public class Employee {
    private String username;
    private String gender;
    private String role;
    private String password;

    public Employee(String username, String gender, String role, String password) {
        this.username = username;
        this.gender = gender;
        this.role = role;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee Detail(s) " + "\nusername: " + username + "\n gender: " + gender + "\n role: " + role + " \n password: " + password ;
    }

    
}
