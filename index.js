// @ts-check

const { S3Client, PutObjectCommand } = require("@aws-sdk/client-s3");

const REGION = "eu-west-3";
const BUCKET = "deploy-lambda-codingmentor";
const FILE_NAME = "file.txt";

exports.handler = async function (event, context) {
  const client = new S3Client({ region: REGION });

  const fileStream = "test";

  const params = {
    Bucket: BUCKET,
    Key: FILE_NAME,
    Body: fileStream,
  };
  const command = new PutObjectCommand(params);

  const response = await client.send(command);

  return response;
};
