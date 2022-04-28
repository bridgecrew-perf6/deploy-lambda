# How to deploy a lambda (NodeJS)

## Pre-requisites

## Node and npm
Follow instructions [here](https://nodejs.org/en/download/)

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

## 1. Install dependencies
`npm install`

## 2. Zip your code
`zip -r lambda.zip lambda.js node_modules`

## 3. Deploy the code

### 3.a Create the lambda
`aws lambda create-function --function-name hello-world-function \
--zip-file fileb://lambda.zip --handler index.handler --runtime nodejs14.x \
--role arn:aws:iam::144692380803:role/lambda-role`

### 3.b Update the lambda
`aws lambda update-function-code --function-name hello-world-function \
--zip-file fileb://lambda.zip`
