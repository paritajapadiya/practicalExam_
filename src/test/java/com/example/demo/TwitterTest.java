package com.example.demo;

import org.junit.Test;

/**
 * Created by parita on 27/5/21
 */
public class TwitterTest {
    @Test
    public void testTweet() {
        Twitter twitter = new Twitter();

        for(int i=0; i<=1000;i++)
        {
            for(int j=0;j<=i;j++)
            {
                twitter.follow(i, j);
            }
        }

        for(int i=0; i<=1000;i++)
        {
            for(int j=0;j<=i;j++)
            {
                twitter.postTweet(i, j);

            }
        }

        for(int i=0; i<=1000;i++)
        {
            twitter.getNewsFeed(i);
        }


        for(int i=0; i<=1000;i++)
        {
            System.out.println("follow:"+ i+"data:" + twitter.getFollows().get(i));
            System.out.println("tweet:"+ i+"- tweetData:" + twitter.getTweets().get(i));
            System.out.println("Result:" + twitter.getNewsFeed(i).toString());
        }
    }
}
