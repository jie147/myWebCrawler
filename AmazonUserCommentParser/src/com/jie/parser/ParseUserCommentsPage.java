package com.jie.parser;

import com.jie.bean.AmazonUserComment;
import com.jie.bean.ThingMes;
import com.jie.util.MatchUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 16-7-18.
 */
public class ParseUserCommentsPage {
	List<AmazonUserComment> comments = new ArrayList<>();
	private String thingName = "";
	private String thingId = "";

	public List<AmazonUserComment> parserUsersComment(String htmlContent) {
		Document doc = Jsoup.parse(htmlContent);
		return parserUserComment(doc);
	}

	public List<AmazonUserComment> parserUserComment(Document document) {
		Element usersCommentModel = document.getElementById("cm_cr-review_list");
		if(usersCommentModel == null) return null;
		thingName = document.title().substring(10);
		Elements thingTag = document.getElementById("cm_cr-brdcmb").getElementsByTag("a");
		// thingName = thingTag.get(0).text();
		String thingUrl = thingTag.get(0).attr("href");
//		System.out.println("*** thing url is :" + thingUrl);

		String rule = ".*/dp/(\\w{10})/.*";
		thingId = MatchUtil.MatchGroup_1(rule, thingUrl);

		// 鑾峰彇鐢ㄦ埛璇勮淇℃伅
		// System.out.println(usersCommentModel.toString());
		parserUserCommentModel(usersCommentModel);
		// System.out.println("comments size is :"+comments.size());
		return comments;
	}

	public List<AmazonUserComment> parserUserCommentModel(Element usersCommentModel) {
		if (usersCommentModel == null) {
			return null;
		}
		Elements userComments = usersCommentModel.select("div[id][class=\"a-section review\"]");
		System.out.println(userComments.size());
		if (usersCommentModel.getElementById("cm_cr-pagination_bar") != null)
			userComments.remove(userComments.size() - 1);
		for (Element itemUserComment : userComments) {
			comments.add(getOnePersonComment(itemUserComment));
		}
		return comments;
	}

	private AmazonUserComment getOnePersonComment(Element element) {
		AmazonUserComment auc = new AmazonUserComment();
		auc.setUserId(element.attr("id"));

		// 鑾峰緱stars
		String starsStr = element.select("i>span.a-icon-alt").first().text();
		System.out.println(starsStr);
		if (!starsStr.equals("")){
			auc.setStars(Float.parseFloat(element.select("i>span.a-icon-alt").text().substring(0, 3)));
		}
		auc.setContentSummary(
				element.select("a[class=\"a-size-base a-link-normal review-title a-color-base a-text-bold\"]").text());

		// 鑾峰彇鐢ㄦ埛濮撳悕锛屽拰璇勮鏃堕棿
		auc.setUserName(element.select("a[class=\"a-size-base a-link-normal author\"]").text());// 鐢ㄦ埛濮撳悕
		// childElement.get(1).getElementsByTag("a").get(0).attr("href");//鐢ㄦ埛閾炬帴--涓嶉渶瑕�
		auc.setTime(element.select("span[class=\"a-size-base a-color-secondary review-date\"]").text());

		// 鑾峰彇鍟嗗搧绫诲瀷
		auc.setThingStype(element.select("a[class=\"a-size-mini a-link-normal a-color-secondary\"]").text());

		// 鑾峰彇鐢ㄦ埛璇勮
		auc.setContent(element.select("span[class=\"a-size-base review-text\"]").text());

		// 鑾峰彇璇勮鏄惁鏈夌敤淇℃伅
		String str = element.select("span.cr-vote-buttons>span.review-votes").text();
		// System.out.println(str);
		try{
		if (!str.equals(""))
			auc.setHelpPersons(Integer.parseInt(str.split(" ")[0]));
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		// 璁剧疆thingId鍜宯ame
		auc.setThingId(thingId);
		auc.setThingName(thingName);

		// auc.printMess();
		return auc;
	}

	/*
	 * private AmazonUserComment getOnePersonComment(Element element) {
	 * AmazonUserComment auc = new AmazonUserComment();
	 * auc.setUserId(element.attr("id"));
	 * 
	 * Elements childElement = element.getElementsByTag("div"); //鑾峰緱stars
	 * Elements aRow =
	 * childElement.get(1).getElementsByClass("a-row").get(0).getElementsByTag(
	 * "a");
	 * auc.setStars(Float.parseFloat(aRow.get(0).getElementsByTag("i").text().
	 * substring(0, 3))); auc.setContentSummary(aRow.get(1).text());
	 * 
	 * //鑾峰彇鐢ㄦ埛濮撳悕锛屽拰璇勮鏃堕棿
	 * auc.setUserName(childElement.get(2).getElementsByTag("a").get(0).text());
	 * //鐢ㄦ埛濮撳悕 //
	 * childElement.get(1).getElementsByTag("a").get(0).attr("href");//鐢ㄦ埛閾炬帴--
	 * 涓嶉渶瑕�
	 * auc.setTime(childElement.get(2).children().last().text().substring(2));
	 * // System.out.println(childElement.get(2).toString());
	 * 
	 * //鑾峰彇鍟嗗搧绫诲瀷 try {
	 * auc.setThingStype(childElement.get(3).getElementsByTag("a").get(0).text()
	 * );//have some bg } catch (Exception e) {
	 * System.err.println("not thing type!!"); } //鑾峰彇鐢ㄦ埛璇勮
	 * auc.setContent(childElement.get(4).text());
	 * 
	 * //鑾峰彇璇勮鏄惁鏈夌敤淇℃伅 //
	 * childElement.last().getElementsByClass("a-expander-prompt").get(0).
	 * getElementsByTag("span").get(0).text();//鍥炲簲浜烘暟 String helpPerson =
	 * element.getElementsByClass("cr-vote-buttons").get(0).text().split(" ")[0]
	 * ; // System.out.println(helpPerson); //涓汉鍙戠幇姝よ瘎璁烘湁鐢�. if
	 * (helpPerson.equals("杩欐潯璇勮瀵规偍鏈夌敤鍚�?鏄惁")) { helpPerson = "0"; }
	 * 
	 * auc.setHelpPersons(Integer.parseInt(helpPerson));
	 * 
	 * //璁剧疆thingId鍜宯ame auc.setThingId(thingId); auc.setThingName(thingName);
	 * 
	 * //Test auc.printMess(); return auc; }
	 */

	public int getCommentsPageNum(Document document) {
		if (document.getElementById("cm_cr-review_list") == null)
			return 0;
		if (document.getElementById("cm_cr-pagination_bar") == null)
			return 1;
		Elements eles = document.select("div#cm_cr-pagination_bar").select("ul>li");
		String pagenum = eles.get(eles.size() - 2).text().trim().replace(",", "");
		System.out.println("page number is :" + pagenum);
		return Integer.parseInt(pagenum);
	}

	public List<AmazonUserComment> getComments() {
		return comments;
	}

}
