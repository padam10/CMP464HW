package com.example.checkingmap;

import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class YelpMain {

	OAuthService service;
	Token accessToken;

	public YelpMain(String consumerKey, String consumerSecret, String token,
			String tokenSecret) {
		this.service = new ServiceBuilder().provider(Yelp2.class)
				.apiKey(consumerKey).apiSecret(consumerSecret).build();
		this.accessToken = new Token(token, tokenSecret);
	}

	public String search(double sw_latitude, double sw_longitude,
			double ne_latitude, double ne_longitude)

	{
		OAuthRequest request = new OAuthRequest(Verb.GET,
				"http://api.yelp.com/v2/search");
		request.addQuerystringParameter("bounds", sw_latitude + ","
				+ sw_longitude + "|" + ne_latitude + "," + ne_longitude);
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
		return response.getBody();
	}

	// CLI
	public static void main(String[] args) {
		// Update tokens here from Yelp developers site, Manage API access.
		String consumerKey = "88RpY2uUIrItjeQ6j-3IAQ";
		String consumerSecret = "StyLyM0zNaP1A57zJ0ar8TYXE_8";
		String token = "6slT1j1Jku_RU2vx9eEOWyBsKNrPNpj1";
		String tokenSecret = "Mpejp-3X3LbmxuGeeQxKPPWxxRE";

		YelpMain yelp = new YelpMain(consumerKey, consumerSecret, token,
				tokenSecret);
		String response = yelp.search(37.91, -122.5, 37.788022, -122.399797);
		try {
			JSONObject mainJson = new JSONObject(response);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
