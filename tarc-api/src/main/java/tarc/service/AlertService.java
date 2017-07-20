package tarc.service;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertService {

    @Autowired
    StatusService statusService;

    @Scheduled(fixedRate = 1000)
    public void awsPostAlert() {

        String status = statusService.getStatus().getStatus();

        if (status != null && !status.equalsIgnoreCase("normal")) {
            BasicAWSCredentials credentials = new BasicAWSCredentials("AKIAIBTLSZF6HSOHOHGQ",
                    "L3Fb9umi/HsPI6FZeCY5H8CCZbMWrJpvUAZKrMzW");
            AmazonSNSClient snsClient = new AmazonSNSClient(credentials);
            AmazonSQSClient sqsClient = new AmazonSQSClient(credentials);

            sqsClient.setRegion(Region.getRegion(Regions.US_EAST_2));
            snsClient.setRegion(Region.getRegion(Regions.US_EAST_2));

            PublishRequest publishRequest = new PublishRequest("arn:aws:sns:us-east-2:905519708970:alert", status);
            PublishResult publishResult = snsClient.publish(publishRequest);
            SendMessageResult publishResult2 = sqsClient.sendMessage("https://sqs.us-east-2.amazonaws.com/905519708970/sqs-alert", status);

            System.out.println("Message [" + status + "] published to the topic <alert>");
        }
    }
}
