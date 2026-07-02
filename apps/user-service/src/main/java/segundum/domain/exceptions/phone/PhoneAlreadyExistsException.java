package segundum.domain.exceptions.phone;

import segundum.domain.exceptions.DomainException;

/**
 * Exception thrown when a phone number is already in use.
 */
@SuppressWarnings("serial")
public class PhoneAlreadyExistsException extends DomainException {

    /**
     * Constructs a new PhoneAlreadyExistsException with a message indicating that the specified phone number is already
     * in use.
     * 
     * @param phone the phone number that is already in use
     */
    public PhoneAlreadyExistsException(String phone) {
        super("The phone " + phone + " is already in use.");
    }

}
