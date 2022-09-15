### Source Code Migration Tool: User Guide 🛠

This README will give a user guide into how to run the source code migration tool! The way this tool works is by taking in the URL of your current repository and moving it straight to CodeCommit without you having to 


### Prerequisites 

- Please ensure you have full access to your current repository that you are moving to CodeCommit
- AWS console accounts --> You must have an AWS account with full access to CodeCommit (if you do not have an AWS management console account please register here: https://aws.amazon.com/console/)
- Amazon CLI --> You must have the AWS CLI installed and configured to your AWS account (if you do not have the AWS CLI installed please download it on your system from here https://aws.amazon.com/cli/)
- Java version 18 --> You can install java version 18 from here if you do not currently have it on your device --> https://www.oracle.com/java/technologies/downloads/

### Setting up

- Once you have an AWS account, open IAM and create a user that is granted full access to codecommit and download the login credentials
- Setup your AWS CLI by entering the command line terminal "aws configure" and entering the downloaded credentials 
- Once you have the CLI setup, navigate to the Migration tool file by entering the terminal command "cd ~replacewithfilepathtomigrationtoolfile~"
- Once you are in the Migration tool file, enter the command to run the jar file "java -jar SampleMigrationTool-COPY.jar"
- Follow the instructions from there! 
