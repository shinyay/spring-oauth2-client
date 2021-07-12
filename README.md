# OAuth 2.0 Client for Keycloak

Access to Resource Server with OAuth 2.0 with Keycloak Server

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

### Client ID and Secret
- `spring.security.oauth2.client.registration.keycloak.client-id`
  - Client ID from Keycloak

![keycloak-client](https://user-images.githubusercontent.com/3072734/125216118-1c548500-e2f8-11eb-9000-f6340c07dcd6.png)

## Demo

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
