package com.twitter.controller.v1;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.model.Tweet;
import com.twitter.service.TweetService;

@RestController
@RequestMapping("/v1")
public class TweetController {

	@Autowired
	TweetService tweetService;

	Logger logger = Logger.getLogger(TweetController.class);

	/**
	 * Get the latest 100 posts from the users that the current user follows.
	 * 
	 * @action /tweets/{userId}
	 * @method GET
	 * @summary It should get the latest max 100 messages for the user.
	 * @param Integer
	 *            required UserId.
	 * @throws 500: db access problems
	 * @return status code 400 on Bad Request e.g., empty UserId
	 * 				status code 204 on No Content. e.g., User does not have tweets.
	 * 				status code 200 on success API:
	 *         URL: http://hostname:8080/twitter-api/v1/tweets/4 
	 *         
	 *         Curl command: curl
	 *         -X GET -H "Content-Type: application/json"
	 *         http://hostname:8080/twitter-api/v1/tweets/1
	 *
	 **/

	@RequestMapping(value = "/tweets/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<String> getLatestTweetsForUser(
			@PathVariable String userId) {

		if (StringUtils.isEmpty(userId)) {
			return new ResponseEntity<String>("Need UserId",
					HttpStatus.BAD_REQUEST);
		}

		List<Tweet> tweets = (ArrayList<Tweet>) tweetService
				.getLatestTweetsForUser(new Integer(userId));
		logger.debug("Tweets for the user " + userId + " no of tweets ="
				+ tweets.size());
		if (tweets.size() == 0) {
			return new ResponseEntity<String>("No Tweets Found for this User",
					HttpStatus.NO_CONTENT);
		}

		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.valueToTree(tweets);

		return ResponseEntity.ok(json.toString());
	}

}
