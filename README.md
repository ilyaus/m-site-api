# M-Site API
The M-Site project, created with [`aws-serverless-java-container`](https://github.com/awslabs/aws-serverless-java-container).

The project folder includes a `template.yml` file. You can use this [SAM](https://github.com/awslabs/serverless-application-model) file to deploy the project to AWS Lambda and Amazon API Gateway or test in local with the [SAM CLI](https://github.com/awslabs/aws-sam-cli). 

## Pre-requisites
* [AWS CLI](https://aws.amazon.com/cli/)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)
* [Gradle](https://gradle.org/) or [Maven](https://maven.apache.org/)

## Building the project
You can use the SAM CLI to quickly build the project
```bash
$ mvn archetype:generate -DartifactId=m-site -DarchetypeGroupId=com.amazonaws.serverless.archetypes -DarchetypeArtifactId=aws-serverless-spring-archetype -DarchetypeVersion=1.6.1 -DgroupId=org.ubf -Dversion=1.0-SNAPSHOT -Dinteractive=false
$ cd m-site-api
$ sam build
Building resource 'MSiteFunction'
Running JavaGradleWorkflow:GradleBuild
Running JavaGradleWorkflow:CopyArtifacts

Build Succeeded

Built Artifacts  : .aws-sam/build
Built Template   : .aws-sam/build/template.yaml

Commands you can use next
=========================
[*] Invoke Function: sam local invoke
[*] Deploy: sam deploy --guided
```

## Testing locally with the SAM CLI

From the project root folder - where the `template.yml` file is located - start the API with the SAM CLI.

```bash
$ sam local start-api

...
Mounting com.amazonaws.serverless.archetypes.StreamLambdaHandler::handleRequest (java8) at http://127.0.0.1:3000/{proxy+} [OPTIONS GET HEAD POST PUT DELETE PATCH]
...
```

Using a new shell, you can send a test ping request to your API:

```bash
$ curl -s http://127.0.0.1:3000/members | python -m json.tool
``` 

## Deploying to AWS
To deploy the application in your AWS account, you can use the SAM CLI's guided deployment process and follow the instructions on the screen

```
$ sam deploy --guided
```

Once the deployment is completed, the SAM CLI will print out the stack's outputs, including the new application URL. You can use `curl` or a web browser to make a call to the URL

```
...
-------------------------------------------------------------------------------------------------------------
OutputKey-Description                        OutputValue
-------------------------------------------------------------------------------------------------------------
MSiteApi - URL for application            https://xxxxxxxxxx.execute-api.us-west-2.amazonaws.com/Prod/members
-------------------------------------------------------------------------------------------------------------
```

Copy the `OutputValue` into a browser or use curl to test your first request:

```bash
$ curl -s https://xxxxxxx.execute-api.us-west-2.amazonaws.com/Prod/members | python -m json.tool
```

### Deploying to AWS using AWS CLI
Create bucket for Lambda package

```bash
aws s3 mb s3://ubf-msite-deploy
```

Prepare template and package
```bash
aws cloudformation package --template-file .\template.yml --output-template-file output-template.yml --s3-bucket ubf-msite-deploy
```

Deploy application
```bash
aws cloudformation deploy --template-file .\output-template.yml --stack-name ubf-msite-api --capabilities CAPABILITY_IAM
```