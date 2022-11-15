package com.revature;

import com.revature.service.AuthService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class HashUtility {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.print("username: ");
        String username = scanner.nextLine().trim().toLowerCase();
        System.out.print("password: ");
        String password = scanner.nextLine();
        String result = AuthService.quickhash(username, password);
        result = result.replace("'", "''");
        System.out.println(result);
    }
}
