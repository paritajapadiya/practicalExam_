package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PracticalApplication.class, args);
        Twitter twitter = new Twitter();

        twitter.follow(1,2);
        twitter.follow(1,3);

        twitter.postTweet(1,1);
        twitter.postTweet(1,2);
        twitter.postTweet(2,3);
        twitter.postTweet(2,4);

        twitter.postTweet(3,5);
        twitter.postTweet(4,6);
        twitter.getNewsFeed(1);
        System.out.println("Result:"+ twitter.getNewsFeed(1).toString());
    }

}
