package segundum.application.readmodels.user;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class UserInfoReadModel {
	
	/**
     * The identifier of the user.
     */
    private final String id;

    /**
     * The name of the user.
     */
    private final String name;

    /**
     * The surname of the user.
     */
    private final String surname;

    /**
     * The email of the user.
     */
    private final String email;
    
    /**
	 * The birthdate of the user.
	 */
    private final LocalDate birthdate;

    /**
     * The phone number of the user.
     */
    private final String phone;
    
    /**
	 * The status of the user.
	 */
    private final String status;
    
    /**
     * The registration date of the user.
     */
    private final Instant registrationDate;

    /**
     * The roles of the user.
     */
    private final List<String> roles;

    /**
     * Constructs a new UserInfoReadModel with the given data.
     *
     * @param id      the user identifier
     * @param name    the user name
     * @param surname the user surname
     * @param email   the user email
     * @param birthdate the user birthdate
     * @param phone   the user phone number
     * @param status  the user status
     * @param registrationDate the user registration date
     * @param roles   the user roles
     */
    public UserInfoReadModel(String id, String name, String surname, String email, LocalDate birthdate, String phone, String status, Instant registrationDate, List<String> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthdate = birthdate;
        this.phone = phone;
        this.status = status;
        this.registrationDate = registrationDate;
        this.roles = roles;
    }

    /**
     * Returns the identifier of the user.
     *
     * @return the user identifier
     */
    public String getId() { return id; }

    /**
     * Returns the name of the user.
     *
     * @return the user name
     */
    public String getName() { return name; }

    /**
     * Returns the surname of the user.
     *
     * @return the user surname
     */
    public String getSurname() { return surname; }

    /**
     * Returns the email of the user.
     *
     * @return the user email
     */
    public String getEmail() { return email; }
    
    /**
     * Returns the birthdate of the user.
     * @return the user birthdate
     */
    public LocalDate getBirthdate() { return birthdate; }

    /**
     * Returns the phone number of the user.
     *
     * @return the user phone number
     */
    public String getPhone() { return phone; }
    
    /**
	 * Returns the status of the user.
	 *
	 * @return the user status
	 */
    public String getStatus() { return status; }
    
    /**
     * Returns the registration date of the user.
     *
     * @return the user registration date
     */
    public Instant getRegistrationDate() { return registrationDate; }

    /**
     * Returns the roles of the user.
     * 
     * @return the user roles
     */
    public List<String> getRoles() { return roles; }

}
