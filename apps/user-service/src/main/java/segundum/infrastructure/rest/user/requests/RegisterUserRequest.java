package segundum.infrastructure.rest.user.requests;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to register a new user")
public class RegisterUserRequest {

	@NotNull
	@NotBlank
	@Schema(description = "User's first name", example = "John")
	private String name;

	@NotNull
	@NotBlank
	@Schema(description = "User's last name", example = "Doe")
	private String surname;

	@NotNull
	@NotBlank
	@Schema(description = "User's email address", example = "john.doe@example.com")
	private String email;

	@NotNull
	@NotBlank
	@Schema(description = "User's password", example = "securePass123")
	private String password;

	@NotNull
	@NotBlank
	@Schema(description = "User's birthdate", example = "1990-01-15")
	private LocalDate birthdate;

	@NotNull
	@NotBlank
	@Schema(description = "User's phone number", example = "+34612345678")
	private String phone;

	public RegisterUserRequest() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
