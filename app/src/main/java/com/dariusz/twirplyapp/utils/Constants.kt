package com.dariusz.twirplyapp.utils

import com.dariusz.twirplyapp.AUTH_CONSUMER_KEY
import com.dariusz.twirplyapp.AUTH_CONSUMER_SECRET
import com.dariusz.twirplyapp.AUTH_TOKEN

object Constants {

    //API SETUP
    const val API_URL = "https://api.twitter.com/"
    const val API_AUTH_CONSUMER_KEY = AUTH_CONSUMER_KEY
    const val API_AUTH_CONSUMER_SECRET = AUTH_CONSUMER_SECRET
    const val API_AUTH_ACCESS_TOKEN = AUTH_TOKEN

    //TWEET SETUP
    const val API_TWEET_FIELDS =
        "attachments,author_id,created_at,entities,id,in_reply_to_user_id,lang," +
                "possibly_sensitive,referenced_tweets,source,text,withheld,public_metrics"
    const val API_EXPANSIONS =
        "attachments.poll_ids,attachments.media_keys,author_id,in_reply_to_user_id," +
                "referenced_tweets.id,entities.mentions.username,referenced_tweets.id.author_id"
    const val API_MEDIA_FIELDS =
        "duration_ms,height,media_key,non_public_metrics,organic_metrics," +
                "preview_image_url,promoted_metrics,public_metrics,type,url,width"
    const val API_POLL_FIELDS =
        "duration_minutes,end_datetime,id,options,voting_status"

    //USER SETUP
    const val API_USER_FIELDS_FULL =
        "created_at,description,entities,id,location,name,pinned_tweet_id," +
                "profile_image_url,protected,public_metrics,url,username,verified,withheld"

    const val API_USER_FIELDS_COMPACT =
        "name,profile_image_url,username,verified"


}