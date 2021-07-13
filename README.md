# OAuth 2.0 Client for Keycloak

Access to Resource Server with OAuth 2.0 with Keycloak Server

![keycloak-auth-flow](https://user-images.githubusercontent.com/3072734/125256072-09ad7080-e337-11eb-93d1-d192484b4120.png)

## Description
### Dependencies
- org.springframework.boot
  - `spring-boot-starter-oauth2-client`

### Authorization Callback URL to redirect
The following configuration means `http://localhost:8080/login/oauth2/code/keycloak`

```yaml
server:
  port: 8080
spring:
  security:
    oauth2:
      client:
        registration:
          keycloak:
            redirect-uri: '{baseUrl}/login/oauth2/code/{registrationId}'
```

### Registration for Client
- `spring.security.oauth2.client.registration.keycloak.client-id`
  - Client ID from Keycloak

![keycloak-client](https://user-images.githubusercontent.com/3072734/125216118-1c548500-e2f8-11eb-9000-f6340c07dcd6.png)

- `spring.security.oauth2.client.registration.keycloak.client-secret`
  - Credentials Secret from Keycloak

![access-type-for-credential](https://user-images.githubusercontent.com/3072734/125216688-e87a5f00-e2f9-11eb-8626-d0f4ef6bf2c4.png)

![secret](https://user-images.githubusercontent.com/3072734/125216753-1bbcee00-e2fa-11eb-9373-381bf8787502.png)

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          keycloak:
            client-id: shinyay-api
            client-secret: 0c5d257f-47bf-47e7-9400-08759fade9d9
```


- `spring.security.oauth2.client.registration.keycloak.scope`
  - Client Access Scope with ID Token

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          keycloak:
            scope: openid
```

### Provider for Client
Keycloak endpoints:
![keycloak-endpoint](https://user-images.githubusercontent.com/3072734/125226297-c4277e00-e30b-11eb-871c-3637a531589c.png)

```shell
$ curl -X GET http://localhost:8083/auth/realms/shinyay/.well-known/openid-configuration| jq .
```
```json
{
  "issuer": "http://localhost:8083/auth/realms/shinyay",
  "authorization_endpoint": "http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/auth",
  "token_endpoint": "http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/token",
  "introspection_endpoint": "http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/token/introspect",
  "userinfo_endpoint": "http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/userinfo",
  "end_session_endpoint": "http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/logout",
  "jwks_uri": "http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/certs",
  "check_session_iframe": "http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/login-status-iframe.html",
  "grant_types_supported": [
    "authorization_code",
    "implicit",
    :
    :
}
```
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          keycloak:
            provider: keycloak
        provider:
          keycloak:
            authorization-uri: http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/auth
            token-uri: http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/token
            jwk-set-uri: http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/certs
            user-info-uri: http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/userinfo
            user-name-attribute: preferred_username
```

### Keycloak userinfo
```shell
$ set -x TOKEN (curl -X POST "http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/token" --data "grant_type=client_credentials&client_secret=$CLIENT_SECRET&client_id=shinyay-api"|jq -r .access_token)
```
```shell
$ curl -X GET -H "Content-Type: application/x-www-form-urlencoded" -H "Authorization: Bearer $TOKEN" http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/userinfo|jq .
```
```json
{
  "sub": "fe20bbc1-f71e-4ee6-a620-5000ec28542d",
  "email_verified": false,
  "preferred_username": "service-account-shinyay-api"
}
```
## Demo
### Prepare Environment
- Authorization Server
  - [shinyay/spring-keycloak-authz-server](https://github.com/shinyay/spring-keycloak-authz-server)
- Resource Server
  - [shinyay/spring-security-oauth2-resource-server-gs](https://github.com/shinyay/spring-security-oauth2-resource-server-gs)
- OAuth2.0 Client
  - [shinyay/spring-security-oauth2-client-for-keycloak](https://github.com/shinyay/spring-security-oauth2-client-for-keycloak)


### Access Token
```shell
$ set -x TOKEN (curl -X POST "http://localhost:8083/auth/realms/shinyay/protocol/openid-connect/token" --data "grant_type=client_credentials&client_secret=0c5d257f-47bf-47e7-9400-08759fade9d9&client_id=shinyay-api"|jq -r .access_token)
```

#### Verify JWT
- https://jwt.io/

![decode-jwt](https://user-images.githubusercontent.com/3072734/125258600-79bcf600-e339-11eb-8f70-438481dce103.png)


### Register data with POST Method
```shell
$ curl -v -X POST -H "Authorization: Bearer $TOKEN" -H "Content-Type: application/json" -d '{"name":"oauth2"}' localhost:8081/resource-server/api/v1/employees
```

### Retrieve data with GET Method
```shell
$ curl -v -X GET -H "Authorization: Bearer $TOKEN" localhost:8081/resource-server/api/v1/employees
```

## Features

- feature:1
- feature:2

## Requirement

## Usage

## Installation

## References
- [Thymeleaf - Spring Security integration modules](https://github.com/thymeleaf/thymeleaf-extras-springsecurity)

## Licence

Released under the [MIT license](https://gist.githubusercontent.com/shinyay/56e54ee4c0e22db8211e05e70a63247e/raw/34c6fdd50d54aa8e23560c296424aeb61599aa71/LICENSE)

## Author

[shinyay](https://github.com/shinyay)
