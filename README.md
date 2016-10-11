# TwitterSampleApi

${local_host}/webapi/feed : Gets 100 recent tweets for the logged in user.

${local_host} represents your local server.

Header Parameters for /feed:
Please specify userId as Head parameter and it's value as the userid for which feeds have to be extracted.


${local_host}/webapi/addFollower : Adds follower to given user. You can specify followerId and UserId in the body of the post request

${local_host}/webapi/addTweet : Adds tweet to the specified user. You can specify UserId and Tweet in the body of the post request

You need to install

1. Redis server
2. Apache server( You can use eclipse to deploy to apache server).






