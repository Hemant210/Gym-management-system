/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package record;

import java.util.Set;
import jakarta.security.enterprise.CallerPrincipal;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;

/**
 *
 * @author root
 */
public class KeepRecord {

    private static CredentialValidationResult result;
    private static CallerPrincipal principal;
    private static Set<String> roles;
    private static String token;
    private static String email;
    private static String password;

    public static String getUsername() {
        return email;
    }

    public static void setEmail(String email) {
        KeepRecord.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        KeepRecord.password = password;
    }

    public static CredentialValidationResult getResult() {
        return result;
    }

    public static void setResult(CredentialValidationResult result) {
        KeepRecord.result = result;
    }

    public static CallerPrincipal getPrincipal() {
        return principal;
    }

    public static void setPrincipal(CallerPrincipal principal) {
        KeepRecord.principal = principal;
    }

    public static Set<String> getRoles() {
        return roles;
    }

    public static void setRoles(Set<String> roles) {
        KeepRecord.roles = roles;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        KeepRecord.token = token;
    }

    public static void reset() {

        principal = null;
        token = null;
    }

}
