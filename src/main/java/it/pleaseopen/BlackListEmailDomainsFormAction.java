package it.pleaseopen;

import jakarta.ws.rs.core.MultivaluedMap;
import org.jboss.logging.Logger;
import org.keycloak.authentication.*;
import org.keycloak.authentication.forms.RegistrationPage;
import org.keycloak.forms.login.LoginFormsProvider;
import org.keycloak.models.AuthenticatorConfigModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.FormMessage;

import java.util.Arrays;
import java.util.List;

public class BlackListEmailDomainsFormAction implements FormAction {

    protected static final Logger logger = Logger.getLogger(BlackListEmailDomainsFormAction.class);

    private KeycloakSession session;

    public BlackListEmailDomainsFormAction(KeycloakSession session) {
        this.session = session;
    }


    @Override
    public void buildPage(FormContext formContext, LoginFormsProvider loginFormsProvider) {

    }

    @Override
    public void validate(ValidationContext validationContext) {
        MultivaluedMap<String, String> formData = validationContext.getHttpRequest().getDecodedFormParameters();
        AuthenticatorConfigModel authenticatorConfig = validationContext.getAuthenticatorConfig();
        List<String> blacklist = Arrays.asList(authenticatorConfig.getConfig().get("blacklist").split("##"));

        if(blacklist.contains(formData.getFirst(RegistrationPage.FIELD_EMAIL).split("@")[1])){
            validationContext.validationError(formData, List.of(new FormMessage(RegistrationPage.FIELD_EMAIL, "Invalid email address")));
            logger.info("Invalid email address: " + formData.getFirst(RegistrationPage.FIELD_EMAIL));
        }
        validationContext.success();
    }

    @Override
    public void success(FormContext formContext) {

    }

    @Override
    public boolean requiresUser() {
        return false;
    }

    @Override
    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        return false;
    }

    @Override
    public void setRequiredActions(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {

    }

    @Override
    public void close() {

    }
}
