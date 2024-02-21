package com.example.OktaSSOdemo;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "okta",
				scheme = "bearer",
				bearerFormat = "jwt",
				type = SecuritySchemeType.OAUTH2,
				in = SecuritySchemeIn.HEADER,
				openIdConnectUrl = "https://dev-60369700.okta.com/oauth2/default/v1/keys",
				flows = @OAuthFlows(
						clientCredentials = @OAuthFlow(
								authorizationUrl = "https://dev-60369700.okta.com/oauth2/default/v1/authorize",
								tokenUrl = "https://dev-60369700.okta.com/oauth2/default/v1/token",
								refreshUrl = ""
//								scopes = {@OAuthScope(name = "openid"),
//										  @OAuthScope(name = "profile"),
//										  @OAuthScope(name = "email")}
						)
				))
public class OktaSsoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OktaSsoDemoApplication.class, args);
	}

}
