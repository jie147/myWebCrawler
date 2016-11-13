package com.jie.parser;

import com.jie.bean.ThingMes;
import com.jie.util.MatchUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by jie on 16-7-24.
 */
public class ParserProductMess {
	static String ruleMatchSumStars = ".*(\\d{1}\\.\\d{1}).*";

	public ThingMes parseThingMes(String url, String content) {
		return parseThingMes(url, Jsoup.parse(content));
	}

	public ThingMes parseThingMes(String url, Document document) {
		if(document.select("div.a-container[role=main]") == null){
			System.err.println("not product page!!");
			return null;
		}
		ThingMes thing = new ThingMes();

		thing.setContent(document.toString());

		// parse thing Id
		String ruleThingId = ".*/dp/(\\w{10}).*";
		String thingId = MatchUtil.MatchGroup_1(ruleThingId, url);
		thing.setThingId(thingId);

		// thing name
		thing.setThingName(document.title());

		// thing keywords
		try{
			thing.setKeywords(document.select("meta[name=keywords]").get(0).attr("content"));
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		// thing type
		try {
			thing.setClassType(document.getElementById("showing-breadcrumbs_div").text());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			thing.setMoney(document.getElementById("priceblock_ourprice").text());
		} catch (Exception e) {
		}
		try {
			String sumStars = document.select("span#acrPopover").attr("title");
			thing.setSumStars(Double.parseDouble(MatchUtil.MatchGroup_1(ruleMatchSumStars, sumStars)));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// thing.setProdDetails(document.body().toString());
		return thing;
	}

}
