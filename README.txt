This Lab was realised By Clément ROGER

Ec2CreateInstance.java creates an EC2 instance
Ec2StartStopInstance.java toggles the instance on and off. 
	The instance id has to be entered manually at the beginning of the main function
S3BucketAndQueueCreate.java creates a bucket, adds a file in it 
	(specify the path of the file with the variable filePathString on l30)
	It then creates a queue and sends a message to it with the bucket name and the file name
S3BucketAndQueueRetrieve.java reads the last message on the queue, extracts the bucket name
	and the file name. It then downloads the file and displays the min, the max and the
	sum of the values in it. finally, it deletes the file from the bucket, and then it deletes
	the bucket itself.
S3ListBuckets.java is just the example class that was provided in the Lab instructions.