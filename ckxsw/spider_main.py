#encode:utf8
import url_manager, html_downloader, html_parser
class SpiderMain(object):
	def __init__(self):
		self.urls = url_manager.UrlManager()
		self.path = "/opt/webParserData/"
		self.downloader = html_downloader.HtmlDownloader()
		self.parser = html_parser.HtmlParser()

	def craw(self, root_url):
		self.urls.add_url(root_url)
		count = 1
		while self.urls.has_url():
			try:
				new_url= self.urls.get_url()
				print 'craw %d : %s' % (count, new_url)
				html_cont = self.downloader.download(new_url)
				new_urls, new_data = self.parser.parse(new_url, html_cont)
				self.urls.add_urls(new_urls)
				full_path = self.path + new_data['title'].encode('utf-8')
				fileoutput = open(full_path,'w')
				fileoutput.write("%s" % new_data['title'].encode('utf-8'))
				fileoutput.write("%s" % new_data['content'].encode('utf-8'))
				fileoutput.close()
				if count == 250:
					break
				count += 1
			except:
				print 'craw faile.'

if __name__ == "__main__":
	root_url = "http://www.ckxsw.com/chkbook/0/694/313439.html"
	obj_spider = SpiderMain()
	obj_spider.craw(root_url)	
