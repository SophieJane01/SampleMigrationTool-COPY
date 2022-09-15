import com.amazonaws.services.codecommit.AWSCodeCommit;
import com.amazonaws.services.codecommit.AWSCodeCommitClientBuilder;
import com.amazonaws.services.codecommit.model.CreateRepositoryRequest;
import com.amazonaws.services.codecommit.model.CreateRepositoryResult;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
//import software.amazon.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Scanner;
//codecommit+1-at-289358781292
//BSp72Q3V95StgzNOsqIan2ahNRqpvS01afjqXfPBEp0=

public class Terminal  {
    // callTerminal is a function which runs a ping test through the terminal

    public static void callTerminal() {
        System.out.println("Terminal called");
        //String[] args = new String[] {"/bin/bash", "-c", "your_command", "with", "args"};
        String[] args = new String[] {"ping", "www.google.com"};
        try {
            //Process proc = new ProcessBuilder(args).start();
            Process proc = new ProcessBuilder(args).start();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }

            proc.waitFor();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    // createRepo creates a new empty local repository
    public static void createRepo() {
        System.out.println("createRepo called");
        try {
            Git git = Git.init().setDirectory(new File("/Users/lillicos/Desktop/samplerepo1")).call();
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }

    }

    //cloneRepo clones an existing remote repository to the local machine
    // TODO: 12/08/2022 : ensure that user interface includes the file path to clone a repo to the local machine
    public static void cloneRepo(String repolink, String filepath, String username, String password) {
        try {
            // Call Git library to clone the given repository
            Git git = Git.cloneRepository()
                    .setURI(repolink)
                    .setDirectory(new File(filepath))
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
                    .call();
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Your repo has been cloned!");
    }

    // create an AWS CodeCommit client request to aid with login
    AWSCodeCommit client = AWSCodeCommitClientBuilder.defaultClient();
    //createRepository is part of the AWS CodeCommit SDK which creates an empty repo on codecommit
    public CreateRepositoryResult createRepository(String repoName, String repoDescription, String filepath) {
        // if the repository name is Null, exit with null
        if (repoName == null) {
            return null;
        }
        // Create a repository request with the given repository name parameter passed in
        try {
            CreateRepositoryRequest request = new CreateRepositoryRequest();
            request.setRepositoryName(repoName);
            // if the repository description parameter passed in is at least one character, set the repository description in the request to its' value
            if (repoDescription != null) {
                request.setRepositoryDescription(repoDescription);
            }
            // return the result of the request with the codecommit client
            CreateRepositoryResult result = this.client.createRepository(request);
            System.out.println("Remote repository created! Please check your codecommit profile");
            System.out.println(result.getRepositoryMetadata().getCloneUrlHttp());
            String repopath=result.getRepositoryMetadata().getCloneUrlHttp();
            Scanner sc = new Scanner(System.in);
            System.out.println("Please enter your CodeCommit username");
            String username = sc.nextLine();
            System.out.println("Please enter your CodeCommit password");
            String password = sc.nextLine();
            pushRepo(filepath,repopath, username, password);
            return result;
        }
        // if any exceptions occur print it out to terminal and return null
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    // pushRepo opens a given repository, adds it to git, commits its' initial message and pushes it to the empty CodeCommit repo
    public static void pushRepo(String filepath, String repopath, String username, String password){
        // open the repository by file path
        File repo = new File(filepath);
        try (Git git = Git.open(repo)) {
            // add the repo file to git
            git.add().addFilepattern(filepath).call();
            //commit the initial message
            git.commit().setMessage("init commit").call();
            //push the repo to the new remote (the codecommit repo)
            git.push().setRemote(repopath)
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password))
                    .setForce(true)
                    .setPushAll()
                    .call();
            System.out.println("Your repository is now on CodeCommit woop! Thank you for using the data migration tool :) ");

            // if any exceptions occur print to terminal
        } catch (GitAPIException | IOException e) {
            throw new RuntimeException(e);
        }


    }
}





