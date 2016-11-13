package com.jie.main;

import com.jie.bean.AmazonUserComment;
import com.jie.bean.ThingMes;
import com.jie.db.ContentSaver;
import com.jie.db.SaveProdMes;
import com.jie.parser.AmazonParserUrls;
import com.jie.parser.ParseUserCommentsPage;
import com.jie.parser.ParserProductMess;
import com.jie.util.MatchUtil;

import java.sql.SQLException;
import java.util.List;

import org.jsoup.Jsoup;

/**
 * Created by jie on 16-7-24.
 */
public class ParseSaveM {
	List<String> usefulUrls = null;

	public int ParseAndSave(String url, String content) {
		System.out.println("parse .....................");
		AmazonParserUrls amazonParserUrls = new AmazonParserUrls();
		usefulUrls = amazonParserUrls.GetAllUsefulPageUrl(content);

		String userCommentsPage = "https://www.amazon.cn/product-reviews/\\w{10}.*";
		if (MatchUtil.IsMatch(userCommentsPage, url)) {
			System.out.println("paser User comments Url is:" + url);
			PSUserComments(url, content);
			return 0;
		}

		String proPage = "https://www.amazon.cn/dp/\\w{10}.*";
		if (MatchUtil.IsMatch(proPage, url)) {
			System.out.println("paser product Mes Url is:" + url);
			PSProductMess(url, content);
			return 0;
		}

		return 3;
	}

	private void PSProductMess(String url, String content) {

		ParserProductMess parserProductMess = new ParserProductMess();
		ThingMes thing = parserProductMess.parseThingMes(url, content);
		if(thing == null) return ;
		SaveProdMes saveProdMes = new SaveProdMes();
		try {
			saveProdMes.insertOneComment(thing);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void PSUserComments(String url, String content) {
		ParseUserCommentsPage pucp = new ParseUserCommentsPage();
		pucp.parserUsersComment(content);
		List<AmazonUserComment> userComments = pucp.getComments();

		if(userComments.size()==0)
			return ;
		String thingId=userComments.get(0).getThingId();
		int page = pucp.getCommentsPageNum(Jsoup.parse(content));
		for(int i=2;i<=page;i++){
			String urlTmp = "https://www.amazon.cn/product-reviews/"+thingId+"/ref=cm_cr_arp_d_paging_btm_"+i+"?ie=UTF8&showViewpoints=1&sortBy=helpful&pageNumber="+i;//https://www.amazon.cn/product-reviews/B00HFJ9O42/ref=cm_cr_arp_d_paging_btm_3?ie=UTF8&showViewpoints=1&sortBy=helpful&pageNumber=3
			usefulUrls.add(urlTmp);
		}
		// save
		ContentSaver contentSaver = new ContentSaver();
		try {
			contentSaver.insertComments(userComments);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<String> getUsefulUrl() {
		return usefulUrls;
	}
}
