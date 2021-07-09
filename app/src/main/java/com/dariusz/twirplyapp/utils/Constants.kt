package com.dariusz.twirplyapp.utils

object Constants {

    //API SETUP
    const val API_URL = "https://api.twitter.com/2/"

    //TWEET SETUP
    const val API_TWEET_FIELDS_FULL =
        "attachments,author_id,created_at,entities,id,in_reply_to_user_id,lang," +
                "possibly_sensitive,referenced_tweets,source,text,withheld"
    const val API_EXPANSIONS =
        "geo.place_id, attachments.poll_ids, attachments.media_keys"
    const val API_MEDIA_FIELDS =
        "duration_ms,height,media_key,preview_image_url,public_metrics,type,url,width"
    const val API_POLL_FIELDS =
        "duration_minutes,end_datetime,id,options,voting_status"
    const val API_PLACE_FIELDS =
        "contained_within,country,country_code,full_name,geo,id,name,place_type"

    //USER SETUP
    const val API_USER_FIELDS_FULL =
        "created_at,description,entities,id,location,name," +
                "pinned_tweet_id,profile_image_url,protected,url,username,verified,withheld"

    const val API_USER_FIELDS_COMPACT =
        "name,profile_image_url,username,verified"

    //TEST SETUP
    const val PROFILE_ID_FOR_TESTS = 12345
    const val TWEET_ID_FOR_TESTS = 12345
    const val QUERY_FOR_TESTS = "android"


}