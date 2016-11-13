#encode:utf8
import html_downloader,url_manager, html_parser

root_url = "http://www.ckxsw.com/chkbook/0/694/index.html"
downloader = html_downloader.HtmlDownloader()
html_cont = downloader.download(root_url)
parser = html_parser.HtmlParser()
new_urls, new_data = parser.parse(root_url, html_cont)
for url in new_urls:
	print 'url is : ' + url
#print 'the content is:'
#print new_data['title'].encode('utf-8')
#print new_data['content'].encode('utf-8')

#fileout = open("/opt/webParserData/test")
