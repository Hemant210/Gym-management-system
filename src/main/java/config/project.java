package config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/gym_pro",
        callerQuery = "select password from user where email = ?",
        groupsQuery = "select g.groupName from groupmaster g join user u on u.groupmaster_ID = g.groupMasterId where u.email = ?",
        hashAlgorithm = Pbkdf2PasswordHash.class,
        priority = 30
)
@Named
@ApplicationScoped
public class project {

    public project() {
        System.out.println("Project Config Initialized");
    }

}
