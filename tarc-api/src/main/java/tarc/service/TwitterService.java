package tarc.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tarc.App;
import tarc.model.Tweet;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
public class TwitterService {

    @Scheduled(fixedRate = 60000)
    public void cacheTweets() {

        List<Tweet> tweetList = new ArrayList<>();

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("bvihC4DXxlnGZMzIjtr23p4Z3")
                .setOAuthConsumerSecret("kUlhW39jBWmaccg2Z53cjLr7Chxtuyj1wRkV345HqXN4dPGuJK")
                .setOAuthAccessToken("258204458-B2FQTx8D9oq1gb8qnXy78CKg57nUsgTFsuq73Ifq")
                .setOAuthAccessTokenSecret("zatSivNA5TiaPrWTUejFmorPstGrVL5Wpc0E0eAg5tENn");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        String twitterToSearch = "#enchente #iot";

        try {
            Query query = new Query(twitterToSearch);
            query.setCount(15);
            QueryResult result;

            result = twitter.search(query);
            List<Status> tweets = result.getTweets();

            for (Status tweet : tweets) {
                Tweet currentTweet = new Tweet();
                currentTweet.setUser(tweet.getUser().getName());
                currentTweet.setText(tweet.getText());
                currentTweet.setDate(tweet.getCreatedAt());
                currentTweet.setLocation(tweet.getGeoLocation());

                tweetList.add(currentTweet);
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }

        App.tweetList.clear();
        App.tweetList.addAll(tweetList);
    }

    public List<Tweet> getTweets() {
        return App.tweetList;
    }
}
