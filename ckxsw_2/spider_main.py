#encode:utf8
import url_manager, html_downloader, html_parser
class SpiderMain(object):
	def __init__(self):
		self.err = set()
		self.urls = url_manager.UrlManager()
		self.path = "/opt/webParserData/"
		self.downloader = html_downloader.HtmlDownloader()
		self.parser = html_parser.HtmlParser()

	def craw(self, root_url):
		html_urls_cont = self.downloader.download(root_url)
		html_urls = self.parser.parseUrl(root_url, html_urls_cont)
		self.urls.add_urls(html_urls)
		count = 1
		while self.urls.has_url():
			try:
				new_url= self.urls.get_url()
				print 'craw %d : %s' % (count, new_url)
				html_cont = self.downloader.download(new_url)
				new_data = self.parser.parseCont(new_url, html_cont)
				full_path = self.path + new_data['title'].encode('utf-8')
				fileoutput = open(full_path,'w')
				fileoutput.write("%s" % new_data['title'].encode('utf-8'))
				fileoutput.write("%s" % new_data['content'].encode('utf-8'))
				fileoutput.close()
				count += 1
			except:
				print 'craw faile.'
				self.err.add(new_url)

		patherr = self.path + 'errUrl'
		f = open(patherr)
		for errurl in err:
			f.write(errurl)
		f.close()

if __name__ == "__main__":
	root_url = "http://www.ckxsw.com/chkbook/0/694/index.html"
	obj_spider = SpiderMain()
	obj_spider.craw(root_url)	
