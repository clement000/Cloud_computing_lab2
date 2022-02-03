package emse;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.InstanceType;
import software.amazon.awssdk.services.ec2.model.RunInstancesRequest;
import software.amazon.awssdk.services.ec2.model.RunInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Tag;
import software.amazon.awssdk.services.ec2.model.CreateTagsRequest;
import software.amazon.awssdk.services.ec2.model.Ec2Exception;

public class Ec2CreateInstance {
	
		public static void main(String[] args) {
			Ec2Client ec2 = Ec2Client.create();
			String name = "MyTestInstance";
			String amiId = "ami-0a8b4cd432b1c3063";
			createEC2Instance(ec2, name, amiId);
		}
	
	   	public static String createEC2Instance(Ec2Client ec2,String name, String amiId ) {

	        RunInstancesRequest runRequest = RunInstancesRequest.builder()
	                .imageId(amiId)
	                .instanceType(InstanceType.T1_MICRO)
	                .maxCount(1)
	                .minCount(1)
	                .build();

	        RunInstancesResponse response = ec2.runInstances(runRequest);
	        String instanceId = response.instances().get(0).instanceId();

	        Tag tag = Tag.builder()
	                .key("Name")
	                .value(name)
	                .build();

	        CreateTagsRequest tagRequest = CreateTagsRequest.builder()
	                .resources(instanceId)
	                .tags(tag)
	                .build();

	        try {
	            ec2.createTags(tagRequest);
	            System.out.printf(
	                    "Successfully started EC2 Instance %s based on AMI %s",
	                    instanceId, amiId);

	          return instanceId;

	        } catch (Ec2Exception e) {
	            System.err.println(e.awsErrorDetails().errorMessage());
	            System.exit(1);
	        }

	        return "";
	   	}

}
