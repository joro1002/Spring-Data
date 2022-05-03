package mappingobjects.automappingobjects.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

public class UserRegisterDto {
    @Pattern(regexp = "\\w+@+.+", message = "Email incorrect")
    private String email;
    @Length(min = 6, message = "Password length should be at least 6 symbols")
    @Pattern(regexp = "[A-Z]+[a-z]+\\d+", message = "Password incorrect")
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
