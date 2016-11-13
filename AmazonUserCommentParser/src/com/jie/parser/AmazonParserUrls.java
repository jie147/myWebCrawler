package com.jie.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jie.util.MatchUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 16-7-19.
 */
public class AmazonParserUrls {
	// https://www.amazon.cn/product-reviews/B00OUFBM0I/ref=cm_cr_dp_see_all_summary?ie=UTF8&showViewpoints=1&sortBy=helpful
	static String selectThingIdRule1 = ".*/product-reviews/(\\w{10})/.*";
	// https://www.amazon.cn/Apple-MGEM2CH-A-Mac-mini-%E8%BF%B7%E4%BD%A0%E5%8F%B0%E5%BC%8F%E4%B8%BB%E6%9C%BA/dp/B00OUFBM0I/ref=cm_cr_arp_d_product_top?ie=UTF8
	static String selectThingIdRule2 = ".*/dp/(\\w{10})/.*";

	public List<String> GetAllThingId(Document document) {
		Elements links = document.select("a[href]");
		List<String> thingId = new ArrayList<>();
		for (Element link : links) {
			String linkStr = link.attr("href");
			if (MatchUtil.MatchGroup_1(selectThingIdRule1, linkStr) != null)
				thingId.add(MatchUtil.MatchGroup_1(selectThingIdRule1, linkStr));
			if (MatchUtil.MatchGroup_1(selectThingIdRule2, linkStr) != null)
				thingId.add(MatchUtil.MatchGroup_1(selectThingIdRule2, linkStr));
		}
		return thingId;
	}

	public List<String> GetAllUsefulPageUrl(String content) {
		return GetAllUsefulPageUrl(Jsoup.parse(content));
	}

	public List<String> GetAllUsefulPageUrl(Document document) {
		List<String> usefulUrls = new ArrayList<>();
		List<String> allThingId = GetAllThingId(document);
		for (String thingId : allThingId) {
			usefulUrls.add("https://www.amazon.cn/product-reviews/"+thingId);
			usefulUrls.add("https://www.amazon.cn/dp/"+thingId);
		}
		return usefulUrls;
	}

}
