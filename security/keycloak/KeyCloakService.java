package com.rabilmiraliyev.bookstore.security.keycloak;

import com.rabilmiraliyev.bookstore.model.Users;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class KeyCloakService {
    @Value("${keycloak.auth-server-url}")
    String serverUrl;

    @Value("${keycloak.realm}")
    String realm;

    @Value("${keycloak.resource}")
    String clientId;

    @Value("${keycloak.credentials.secret}")
    String clientSecret ;

    @Value("${koauth.admin.username}")
    String username ;

    @Value("${koauth.admin.password}")
    String password ;

    @Value("${koauth.user.defaultrole}")
    String role ;

    public RealmResource getUsersResource()
    {
        Keycloak keycloak = KeycloakBuilder.builder() //
                .serverUrl(serverUrl) //
                .realm(realm) //
                .grantType(OAuth2Constants.PASSWORD) //
                .clientId(clientId) //
                .clientSecret(clientSecret) //
                .username(username) //
                .password(password) //
                .build();

        RealmResource realmResource = keycloak.realm(realm);

        return realmResource;
    }

    public void  createUser(Users userDto) {

        // User "idm-admin" needs at least "manage-users, view-clients, view-realm, view-users" roles for "realm-management"

        // Define user
        UserRepresentation user = new UserRepresentation();
//        user.setEnabled(true);
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getLastname());
     //   user.setLastName(userDto.getFurname());
        user.setEmail(userDto.getEmail());
        user.setAttributes(Collections.singletonMap("origin", Arrays.asList("demo")));

        // Get realm
        RealmResource realmResource = getUsersResource();
        UsersResource usersRessource = realmResource.users();

        // Create user (requires manage-users role)
        Response response = usersRessource.create(user);

        String userId = CreatedResponseUtil.getCreatedId(response);

        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDto.getPassword());

        UserResource userResource = usersRessource.get(userId);

        // Set password credential
        userResource.resetPassword(passwordCred);

//        // Get realm role "tester" (requires view-realm role)
        RoleRepresentation testerRealmRole = realmResource.roles()//
                .get(role).toRepresentation();
//
//        // Assign realm role to user
        userResource.roles().realmLevel() //
                .add(Arrays.asList(testerRealmRole));
//
//        // Get client
//        ClientRepresentation app1Client = realmResource.clients() //
//                .findByClientId(clientId).get(0);
//
//        // Get client level role (requires view-clients role)
//        RoleRepresentation userClientRole = realmResource.clients().get(app1Client.getId()) //
//                .roles().get("user").toRepresentation();
//
//        // Assign client level role to user
//        userResource.roles() //
//                .clientLevel(app1Client.getId()).add(Arrays.asList(userClientRole));

        // Send password reset E-Mail
        // VERIFY_EMAIL, UPDATE_PROFILE, CONFIGURE_TOTP, UPDATE_PASSWORD, TERMS_AND_CONDITIONS
//       usersRessource.get(userId).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));

        // Delete User
//        userResource.remove();
    }

    public UserRepresentation findUserByUsername(String username)
    {
        RealmResource realmResource =getUsersResource();
        UsersResource usersResource = realmResource.users();
        List<UserRepresentation> userRepresentations=usersResource.search(username);
        return userRepresentations.get(0);
    }

    public void deleteUser(String username)
    {
        RealmResource realmResource = getUsersResource();
        UsersResource usersRessource = realmResource.users();
        UserRepresentation userRepresentation=findUserByUsername(username);
        usersRessource.delete(userRepresentation.getId());
    }

    public void updateUser(Users userDto){
        RealmResource realmResource =getUsersResource();
        UsersResource usersResource = realmResource.users();
        UserRepresentation userRepresentation=findUserByUsername(userDto.getUsername());
        UserResource user=usersResource.get(userRepresentation.getId());
        userRepresentation.setEmail(userDto.getEmail());
        userRepresentation.setFirstName(userDto.getLastname());
      //
        //  userRepresentation.setLastName(userDto.getSurname());
        user.update(userRepresentation);
    }

}
