package com.example.demo;

import java.util.*;

/**
 * Created by parita on 27/5/21
 */
public class Twitter {
    private HashMap<Integer, HashSet<Integer>> follows;
    private HashMap<Integer, LinkedList<Tweet>> tweets;
    private HashSet<Integer> tweetIds = new HashSet<>();
    private int timestamp;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        follows = new HashMap<>();
        tweets = new HashMap<>();
        timestamp = 0;
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        if (!tweets.containsKey(userId)) {
            tweets.put(userId, new LinkedList<>());
        }
        /**
         * Check if HashSet contain provided tweetId then just return and do nothing and If HashSet not contain given tweetId then add tweet into "Tweets" hashSet
         * Reason Behind using hashSet is not allowed duplicate. so, We can restrict duplicate value for tweetId.
         */
        if (tweetIds.contains(tweetId) || (userId > 500 || userId < 1)  || tweetId> 100000 || tweetId <0) { //Constraints [1 <= userId, followerId, followeeId <= 500, All the tweets have unique IDs, 0 <= tweetId <= 104]
            return;
        } else {
            tweets.get(userId).add(0, new Tweet(tweetId, userId, timestamp++));
            tweetIds.add(tweetId);
        }
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new LinkedList<>();
        /**
         * Use of priortityQueue is, it will manage ordering and also put small value at top.
         */
        PriorityQueue<Tweet> q = new PriorityQueue<>();

        /**
         * For listing newsFeed first we need to followers of given userId if is not present then return empty list of integer.
         */
        if (!follows.containsKey(userId)) {
            return feed;
        }
        HashSet<Integer> followed = follows.get(userId);
        HashMap<Integer, Integer> count = new HashMap<>();
        /**
         * After this forEach loop Queue contain first tweets of every user, and count map contain user and its tweet count in map.
         * This will be used to travses in next tweet of that user.
         */
        followed.forEach(follow -> {
            if (tweets.containsKey(follow)) {
                if (!tweets.get(follow).isEmpty()) {
                    Tweet t = tweets.get(follow).get(0);
                    q.add(t);
                    count.put(t.userId, 1);
                }
            }
        });

        /**
         * This loop is used to traverse nested tweets in Map by userId.
         * Because priority queue it will always maintain insertion order.
         */

        while (q.size() > 0 && feed.size() < 10) {
            Tweet t = q.poll();
            feed.add(t.tweetId);
            int next = count.get(t.userId);
            count.put(t.userId, next + 1);
            if (next < tweets.get(t.userId).size()) {
                q.add(tweets.get(t.userId).get(next));
            }
        }
        return feed;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        /**
         * If given followerId don't follow followeeId then they will follow each other  by putting there value in map.
         */
        if ((followeeId > 500 || followeeId < 1) || (followerId > 500 || followerId < 1)) {
            return;
        }
        if (!follows.containsKey(followerId)) {
            follows.put(followerId, new HashSet());
            follows.get(followerId).add(followerId);
        }
        follows.get(followerId).add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        /**
         * If given followerId follow followeeId then they will unfolllow each other  by removing there value in map.
         */

        if ((followeeId > 500 || followeeId < 1) || (followerId > 500 || followerId < 1)) {
            return;
        }
        if (follows.containsKey(followerId)) {
            follows.get(followerId).remove(followeeId);
        }
    }

    public HashMap<Integer, HashSet<Integer>> getFollows() {
        return follows;
    }

    public void setFollows(HashMap<Integer, HashSet<Integer>> follows) {
        this.follows = follows;
    }

    public HashMap<Integer, LinkedList<Tweet>> getTweets() {
        return tweets;
    }

    public void setTweets(HashMap<Integer, LinkedList<Tweet>> tweets) {
        this.tweets = tweets;
    }

    public HashSet<Integer> getTweetIds() {
        return tweetIds;
    }

    public void setTweetIds(HashSet<Integer> tweetIds) {
        this.tweetIds = tweetIds;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Use of Comparable is that Priority queue only work on Class which extends Comparable interface
     */
    private class Tweet implements Comparable<Tweet> {
        private int tweetId;
        private int counter;
        private int userId;

        public Tweet(int tweetId, int userId, int counter) {
            this.tweetId = tweetId;
            this.counter = counter;
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "Tweet{" +
                    "tweetId=" + tweetId +
                    ", counter=" + counter +
                    ", userId=" + userId +
                    '}';
        }

        @Override
        public int compareTo(Tweet t2) {
            return t2.counter - this.counter;
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
