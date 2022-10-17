package ch.mse.itsec.jakarta_jdbc.security;

import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import javax.enterprise.context.ApplicationScoped;
import java.util.EnumSet;
import java.util.Set;

@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {

    @Override
    public Set<ValidationType> validationTypes() {
        return EnumSet.of(ValidationType.VALIDATE);
    }

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        return validateLogin(credential.getCaller(), credential.getPassword());
    }

    private CredentialValidationResult validateLogin(String caller, Password password) {
        return new CredentialValidationResult("test");
    }
}
