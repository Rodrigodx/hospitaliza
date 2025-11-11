package com.rodrigo.hospitaliza.seguranca;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;

@CustomFormAuthenticationMechanismDefinition(
		loginToContinue = @LoginToContinue(
				loginPage = "/login.xhtml",
				useForwardToLogin = false,
				errorPage = ""
		)
)
/*@DatabaseIdentityStoreDefinition(
	    dataSourceLookup = "java:jboss/datasources/HospitalizaDS",
	    callerQuery = "SELECT senha FROM funcionario WHERE login = ?",
	    groupsQuery = "SELECT funcao FROM funcionario WHERE login = ?",
	    hashAlgorithm = PasswordHash.class,
	    priority = 10
	)*/
@ApplicationScoped
public class FormAuthentication {

}
