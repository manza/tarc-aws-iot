package tarc.messaging;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tarc.App;
import tarc.model.Light;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class LightSubsService {


    @Scheduled(fixedRate = 2000)
    public static void handleTempMessages() throws IOException {

        BasicAWSCredentials credentials = null;
        try {
            credentials = new BasicAWSCredentials("AKIAIBTLSZF6HSOHOHGQ", "L3Fb9umi/HsPI6FZeCY5H8CCZbMWrJpvUAZKrMzW");
        } catch (Exception e) {
            e.printStackTrace();
        }

        AmazonSQS sqs = new AmazonSQSClient(credentials);
        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
        sqs.setRegion(usWest2);

        try {
            // Receive messages
            String queueUrl = "https://sqs.us-east-2.amazonaws.com/905519708970/sqs-light";
            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
            List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
            for (Message message : messages) {
                System.out.println("Received Light Message " + message.getMessageId());

                Light newLight = new ObjectMapper().readValue(message.getBody(), Light.class);
                newLight.setReceivedAt(new Date());

                App.lightList.add(newLight);

                String messageReceiptHandle = message.getReceiptHandle();
                sqs.deleteMessage(new DeleteMessageRequest(queueUrl, messageReceiptHandle));

                System.out.println("Received Light Message " + message.getMessageId());
            }
        } catch (AmazonServiceException ase) {
            ase.printStackTrace();
        } catch (AmazonClientException ace) {
            ace.printStackTrace();
        }
    }
}