# How to deploy a lambda (Java)

## Pre-requisites

## Java and Maven
Java : Follow instructions [here](https://www.oracle.com/java/technologies/downloads/)
Maven : Follow instructions [here](https://maven.apache.org/install.html) or use your system package manager `sudo apt install maven` (ubuntu).

### AWS CLI
- Follow the instructions to install it [here](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html#cliv2-linux-install)
- Setup AWS CLI by following the instructions [here](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-quickstart.html)

### Create an execution role for your lambda
`aws iam create-role --role-name lambda-role --assume-role-policy-document '{"Version": "2012-10-17","Statement": [{ "Effect": "Allow", "Principal": {"Service": "lambda.amazonaws.com"}, "Action": "sts:AssumeRole"}]}'`

This command creates a new role named **lambda-role**. It allows the attached lambda to access AWS resources.
Note the role ARN from the command output that should look like : `arn:aws:iam::144692380803:role/lambda-role`

### Add permission to the role
`aws iam attach-role-policy --role-name lambda-role --policy-arn arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole`

The **AWSLambdaBasicExecutionRole** policy has the permissions that the function needs to write logs to CloudWatch Logs.

To add full access to S3 (do not do this in production) : `aws iam attach-role-policy --role-name lambda-role --policy-arn arn:aws:iam::aws:policy/AmazonS3FullAccess`

The **AmazonS3FullAccess** policy gives full access to S3.

## 1. Configure your java project
Follow instructions [here](https://docs.aws.amazon.com/lambda/latest/dg/java-package.html#java-package-prereqs) depending if you're using maven or gradle. (I'm using maven here)

## 2. Build your jar file
`mvn package`

## 3. Deploy the code

### 3.a Create the lambda
`aws lambda create-function --function-name hello-world-java \     
--zip-file fileb://target/original-hello-java-1.0-SNAPSHOT.jar --handler com.test.project.LambdaHandler --runtime java11 \
--role arn:aws:iam::3827592:role/lambda-role --timeout 60 --memory-size 256`

### 3.b Update the lambda
`aws lambda update-function-code --function-name hello-world-java \
--zip-file fileb://target/hello-java-1.0-SNAPSHOT.jar`
