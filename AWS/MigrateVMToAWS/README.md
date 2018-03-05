The first thing you want to do is upload the OVA archive or VMDK file into an S3 bucket of your choice and make sure that the bucket is in the region that you want to create the initial AMI in. If your .vmdk file size is > 5GB use the multi part upload to transfer the file to S3

https://aws.amazon.com/premiumsupport/knowledge-center/s3-multipart-upload-cli/

## Step 1: Obtain the base64 MD5 checksum of the file to be uploaded

openssl md5 -binary PATH/TO/FILE |base64
Output:
user@example:/home$ openssl md5 -binary /bin/bash |base64
+e9lnJtCrdoKwYqg9wlFwA==

## Step 2: Split the file to be uploaded into multiple parts
Let’s suppose we want to split a file into 2 chunk output files. Use ‘-n’ option with split command limit the number of split output files.

split -n2 filename

## Step 3: Initiate the multipart message upload to Amazon S3
Using the aws s3api create-multipart-upload to retrieve the unique UploadId value that associates the original file with the file parts: 

aws s3api create-multipart-upload --bucket <bucket_name> --key <bucket_key_name> --metadata md5=<md5_of_this_part>


aws s3api create-multipart-upload --bucket mirthpayaraappliance --key MR2-Payara-OVF.ova --metadata md5=gKGETBgvT5/DFHPSJLtveA==
Output:

```json
{
"Bucket": "mirthpayaraappliance",
"UploadId": "O4B8jS6KxR5HE2voW4k6oSMli5N3h_PRoijDRar_yxzXMUF6ZHMNpvsUJ_a7dEfgk2Nkvon8.FlnvYCK10iYrJIzaxcCTFyKvuUnii3kZXVKO8OiiL7VdhZZFqtNWoTA",
"Key": "MR2-Payara-OVF.ova"
}
```

## Step 4: Upload the file parts to Amazon S3
In this example, the command to upload the first message part specifies the target bucket, the original file name, the first file part, the UploadId value, and the base64 MD5 checksum for the first file part: 

aws s3api upload-part --bucket <bucket_name> --key <bucket_key_name> --part-number 1 --body MR2-Payara-part1 --upload-id <upload_id_of_main_file> --content-md5 <md5_of_part1>


aws s3api upload-part --bucket mirthpayaraappliance --key MR2-Payara-OVF.ova --part-number 1 --body MR2-Payara-part1 --upload-id O4B8jS6KxR5HE2voW4k6oSMli5N3h_PRoijDRar_yxzXMUF6ZHMNpvsUJ_a7dEfgk2Nkvon8.FlnvYCK10iYrJIzaxcCTFyKvuUnii3kZXVKO8OiiL7VdhZZFqtNWoTA --content-md5 gKGETBgvT5/DFHPSJLtveA==


The command to upload the second message part is similar, with the differences being the number of the part (--part-number 2), the name of the second part (--body testfile.002), and a different MD5 checksum value: 

Run this command to list the parts that are successfully uploaded:

aws s3api list-parts --bucket <bucket_name> --key <bucket_key_name> --upload-id <upload_id_of_main_file>


aws s3api list-parts --bucket mirthpayaraappliance --key MR2-Payara-OVF.ova --upload-id O4B8jS6KxR5HE2voW4k6oSMli5N3h_PRoijDRar_yxzXMUF6ZHMNpvsUJ_a7dEfgk2Nkvon8.FlnvYCK10iYrJIzaxcCTFyKvuUnii3kZXVKO8OiiL7VdhZZFqtNWoTA


## Step 5: Complete the multi-part upload process
Create a file with the following contents:
```json
{
    "Parts": [
    {
        "ETag": "b6b0c89d8adb3cd5281f4036278a4a39",
        "PartNumber":1
    },
    {
        "ETag": "80a1844c182f4f9fc31473d224bb6f78",
        "PartNumber":2
    }
    ]
}
```

aws s3api complete-multipart-upload --multipart-upload fileparts --bucket mirthpayaraappliance --key MR2-Payara-OVF.ova --upload-id O4B8jS6KxR5HE2voW4k6oSMli5N3h_PRoijDRar_yxzXMUF6ZHMNpvsUJ_a7dEfgk2Nkvon8.FlnvYCK10iYrJIzaxcCTFyKvuUnii3kZXVKO8OiiL7VdhZZFqtNWoTA

If this final step is successful, then output similar to the following appears:
```json
{
    "ETag": "\\"13115fdae01633ff0af167d925cad279-2\\"",
    "Bucket": "multirecv",
    "Location": "https://multirecv.s3.amazonaws.com/testfile",
    "Key": "testfile"
}
```

Once the .vmdk file has been uploaded to S3, spin up a small (t2.micro is fine) EC2 instance that uses the Amazon Linux AMI, you’re only going to be using this temporarily to convert the OVA or .vmdk file to an AMI, and you don’t need anything resource intensive. You want the Amazon flavour because it comes pre-baked with all of the cli tools, and the right versions. 

First thing to do when your VM is spun up, ssh to it and run aws configure and follow the prompts that ask for your credentials. Be sure to pick the region you actually want to deploy the new image in.

$ aws configure 
AWS Access Key ID [None]: 1234 
AWS Secret Access Key [None]: 5678 
Default region name [None]: ap-southeast-2 
Default output format [None]:
You can now now test your credentials by listing the ec2 regions:

$ aws ec2 describe-regions
{
"Regions": [
{
"Endpoint": "ec2.eu-central-1.amazonaws.com",
"RegionName": "eu-central-1"
},
{
"Endpoint": "ec2.sa-east-1.amazonaws.com",
"RegionName": "sa-east-1"
},
}
Have the s3 bucket name handy that you uploaded the OVA or .vmdk file.

A vmimport role has already been created for you to use. Edit the policy to add your bucket details. By default this role expects your ova or .vmdk file to be present in ngshare S3 bucket.

aws ec2 import-image --cli-input-json "{  \"Description\": \"Mirth Results Payara OVA\", \"DiskContainers\": [ { \"Description\": \"Disk Description\", \"UserBucket\": { \"S3Bucket\": \"mirthpayaraappliance\", \"S3Key\" : \"MR2-Payara-OVF.ova\" } } ]}"

If you didn’t get any errors, you should now be able to watch your import progress by running aws ec2 describe-import-image-tasks:

$ aws ec2 describe-import-image-tasks
```json
{
 "ImportImageTasks": [
 {
 "Status": "completed",
 "LicenseType": "BYOL",
 "Description": "Description of my OVA",
 "ImageId": "ami-d5abc1234",
 "Platform": "Linux",
 "Architecture": "x86_64",
 "SnapshotDetails": [
 {
 "UserBucket": {
 "S3Bucket": "bucketname",
 "S3Key": "OVAFILENAME.ova"
 },
 "SnapshotId": "snap-abc1234",
 "DiskImageSize": 535459840.0,
 "DeviceName": "/dev/sda1",
 "Format": "VMDK"
 }
 ],
 "ImportTaskId": "import-ami-fg4d51t0"
 }
 ]
}
```

Once that completes (it can take a while) you should be able to launch an EC2 instance, from your AMI. Login to AWS, Goto EC2 -> AMIs -> select your AMI then Launch!

 
