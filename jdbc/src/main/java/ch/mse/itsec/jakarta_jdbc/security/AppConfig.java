package ch.mse.itsec.jakarta_jdbc.security;

import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import jakarta.security.enterprise.identitystore.LdapIdentityStoreDefinition;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@BasicAuthenticationMechanismDefinition(
        realmName = "userRealm")
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:/MySqliJpaUsers",
        callerQuery = "select password from users where username = ?",
        groupsQuery = "select GROUPNAME from groups where username = ?",
        priority=30)
@LdapIdentityStoreDefinition(
        url = "ldap://ldapservice:10389",
        callerBaseDn = "ou=itsec,dc=zhaw,dc=com",
        groupSearchBase = "ou=itsec,dc=zhaw,dc=com",
        groupSearchFilter = "(&(member=%s)(objectClass=groupOfNames))",
        priority=10
)
@ApplicationScoped
public class AppConfig {

}
