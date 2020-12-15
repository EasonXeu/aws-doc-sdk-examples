//snippet-sourcedescription:[ListUsers.java demonstrates how to list users in the specified user pool.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Cognito]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/04/2020]
//snippet-sourceauthor:[scmacdon AWS]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package com.example.cognito;
//snippet-start:[cognito.java2.ListUsers.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserType;
//snippet-end:[cognito.java2.ListUsers.import]

public class ListUsers {

    public static void main(String[] args) {
        final String USAGE = "\n" +
                "Usage:\n" +
                "    ListUsers <userPoolId> \n\n" +
                "Where:\n" +
                "    userPoolId - the ID given to your user pool when it's created.\n\n" ;

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String userPoolId = args[0];
        CognitoIdentityProviderClient cognitoclient = CognitoIdentityProviderClient.builder()
                .region(Region.US_EAST_1)
                .build();

        listAllUsers(cognitoclient, userPoolId );
        cognitoclient.close();
    }

    //snippet-start:[cognito.java2.ListUsers.main]
    public static void listAllUsers(CognitoIdentityProviderClient cognitoclient, String userPoolId ) {

        try {
            ListUsersResponse response = cognitoclient.listUsers(ListUsersRequest.builder()
                    .userPoolId(userPoolId)
                    .build());

            for(UserType user : response.users()) {
                System.out.println("User " + user.username() + " Status " + user.userStatus() + " Created " + user.userCreateDate() );
            }

        } catch (CognitoIdentityProviderException e){
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        //snippet-end:[cognito.java2.ListUsers.main]
    }
}