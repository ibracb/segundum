package segundum.infrastructure.persistence.fakes.gateways;

import segundum.application.dtos.auth.AuthenticatedUserData;
import segundum.application.gateways.AuthenticateUser;

public class FakeAuthenticateUser implements AuthenticateUser {

    private AuthenticatedUserData result;
    private String fetchedEmail;
    private String fetchedPassword;

    @Override
    public AuthenticatedUserData fetch(String email, String password) {
        this.fetchedEmail = email;
        this.fetchedPassword = password;
        return result;
    }

    public void setResult(AuthenticatedUserData result) {
        this.result = result;
    }

    public String getFetchedEmail() {
        return fetchedEmail;
    }

    public String getFetchedPassword() {
        return fetchedPassword;
    }

}
