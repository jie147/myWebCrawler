#coding:utf8
from bs4 import BeautifulSoup
import re
import urlparse

class HtmlParser(object):

	def _get_new_urls(self, page_url, soup):
		new_urls = set()
		#<a href="313441.html">第一章 斗罗大陆，异界唐三(二)</a>
		links = soup.find_all('a', href=re.compile(r"\d+\.html"))
		for link in links:
			new_url = link['href']
			new_full_url = urlparse.urljoin(page_url, new_url)
			new_urls.add(new_full_url)
		return new_urls

	def _get_new_data(self, page_url, soup):
		res_data = {}
		#url
#		res_data['url'] = page_url
		#<span id="htmltimu">第一集 斗罗世界 第一章 斗罗大...</span>
		title_node = soup.find('span', id="htmltimu")
		res_data['title'] = title_node.get_text()

		#<div id="htmlContent" class="contentbox">
		content_node = soup.find('div', id="htmlContent")
		res_data['content'] = content_node.get_text()
		
		return res_data

	def parseUrl(self, page_url, html_cont):
		if page_url is None or html_cont is None:
			return 
		soup = BeautifulSoup(html_cont, 'html.parser', from_encoding = 'utf-8')
		new_urls = self._get_new_urls(page_url, soup)
		return new_urls

	def parseCont(self, page_url, html_cont):
		if page_url is None or html_cont is None:
			return 
		soup = BeautifulSoup(html_cont, 'html.parser', from_encoding = 'utf-8')
		new_data = self._get_new_data(page_url, soup)
		return new_data
		
	def parse(self, page_url, html_cont):
		if page_url is None or html_cont is None:
			return 
		soup = BeautifulSoup(html_cont, 'html.parser', from_encoding = 'utf-8')
		new_urls = self._get_new_urls(page_url, soup)
		new_data = self._get_new_data(page_url, soup)
		return new_urls, new_data
