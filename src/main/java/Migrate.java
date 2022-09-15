//import CodeCommitClient.CodeCommitClient;

import com.amazonaws.services.codecommit.AWSCodeCommit;
import com.amazonaws.services.codecommit.AWSCodeCommitClientBuilder;
//access token to uni github account: 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

import java.util.Scanner;

public class Migrate {
    public static void main(String[] args){
        System.out.println("Constructor called");
        Terminal terminalwindow = new Terminal();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the data migration tool! Please paste in the link to your current repo");
        String repolink = sc.nextLine();
        System.out.println("Your repo link is:" + repolink);
        System.out.println("Please paste in the file path you want the repo to be cloned to (please ensure to include a backslash before your path!)");
        String filepath = sc.nextLine();
        System.out.println("Thanks! Your file path is:" + filepath);
        System.out.println("Please now enter your username");
        String username = sc.nextLine();
        System.out.println("Last thing before cloning, please enter your password/access token");
        String password = sc.nextLine();
        System.out.println("Perfect! Now cloning your repo...");
        terminalwindow.cloneRepo(repolink, filepath, username, password);
        System.out.println("Next step: Creating a codecommit repository. Please enter the name you'd like to call your repo");
        String repoName = sc.nextLine();
        System.out.println("Please enter a brief description of your repo");
        String repoDescription = sc.nextLine();
        System.out.println("Great! Now creating your remote repo... ");
        terminalwindow.createRepository(repoName, repoDescription, filepath);

    }



}