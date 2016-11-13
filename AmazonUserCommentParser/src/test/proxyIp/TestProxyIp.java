package test.proxyIp;

import java.io.IOException;
import java.util.List;

import getKuaidailiProxyIp.GetProxyIp;

public class TestProxyIp {
public static void main(String[] args) throws IOException {
	List<String> list = GetProxyIp.getProxyIp(5);
	for (String string : list) {
		System.out.println(string);
	}
}
}
