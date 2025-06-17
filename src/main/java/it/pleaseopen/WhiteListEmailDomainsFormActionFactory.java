package it.pleaseopen;

import org.keycloak.Config;
import org.keycloak.authentication.FormAction;
import org.keycloak.authentication.FormActionFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.List;

public class WhiteListEmailDomainsFormActionFactory implements FormActionFactory {
    @Override
    public String getDisplayType() {
        return "Whitelist Email Domains";
    }

    @Override
    public String getReferenceCategory() {
        return "";
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    private static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
            AuthenticationExecutionModel.Requirement.REQUIRED,
            AuthenticationExecutionModel.Requirement.DISABLED
    };

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "Whitelist email domains";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        ProviderConfigProperty property = new ProviderConfigProperty();
        property.setType(ProviderConfigProperty.MULTIVALUED_STRING_TYPE);
        property.setLabel("Whitelist email domains");
        property.setRequired(true);
        property.setHelpText("List of domains to whitelist");
        property.setName("whitelist");
        return List.of(property);
    }

    @Override
    public FormAction create(KeycloakSession keycloakSession) {
        return new WhiteListEmailDomainsFormAction(keycloakSession);
    }

    @Override
    public void init(Config.Scope scope) {

    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "whitelist-email-domains";
    }
}
