package getKuaidailiProxyIp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetProxyIp {
public static List<String> getProxyIp(int numberOfPages) throws IOException{
	List<String> proxyIps = new ArrayList<>();
	String url = "http://www.kuaidaili.com/free/inha/";
	for(int i= 1;i<=numberOfPages;i++){
		Document document = Jsoup.connect(url+i).get();
		Elements eles = document.select("tbody>tr");
		for(Element item:eles){
			proxyIps.add(item.select("td[data-title=IP]").text()+":"+item.select("td[data-title=PORT]").text());
		}
	}
	return proxyIps;
}
}
