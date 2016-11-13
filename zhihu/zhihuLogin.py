#-*- coding:utf-8 -*-
import requests
import re
from bs4 import BeautifulSoup

def login():
	url = 'http://www.zhihu.com'
	login_url = url + '/login/email'
	login_data = {
		'password':'jie646958863',
		'email':'qq646958863@163.com',
		'remember_me':'true',
	}
	headers_base = {
		'Host': 'www.zhihu.com',
		'User-Agent': 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:41.0) Gecko/20100101 Firefox/41.0',
		'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
		'Accept-Language': 'zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3',
		'Accept-Encoding': 'gzip, deflate',
		'Referer': 'http://www.zhihu.com/',
		'Connection': 'keep-alive',
	}

	s= requests.session()

	def get_xsrf(url = None):
		r = s.get(url, headers = headers_base)
		xsrf = re.search(r'(?<=name="_xsrf" value=")[^"]*(?="/>)', r.text)
		if xsrf == None:
			return ''
		else:
			return xsrf.group(0)
	
	xsrf = get_xsrf(url)
	print 'xsrf is :\n'+xsrf
	login_data['_xsrf'] = xsrf.encode('utf-8')
	"""
	captcha_url = 'http://www.zhihu.com/captcha.gif'
	captcha = s.get(captcha_url, stream=True)
	print captcha
	f = open('captcha.gif','wb')
	for line in captcha.iter_content(10):
		f.write(line)
	f.close()

	print u'输入验证码：'
	captcha_str = raw_input()
	login_data['captcha'] = captcha_str
	"""
	res = s.post(login_url, headers = headers_base, data = login_data)
	print res.status_code

	m_cookies = res.cookies
	print '\ncookies \n\n %s \n' % m_cookies
	
	test_url = 'https://www.zhihu.com/people/solax/followers'
	res = s.get(test_url, headers=headers_base, cookies=m_cookies)
	#m_cookies2 = res.cookies	
	#print 'followers cookies is:\n'+m_cookies2
	#print 'followers html content is:\n'+res.text+'\n'
	foutRes = open('/opt/resfile','w')
	foutRes.write(res.text.encode('utf-8'))
	foutRes.close()

	def get_users(content = None):
		users = re.search(r'<a title="*>', content)
		print users.group(0)

#	get_users(res.text)

	ids = '1663b8bf30fb0bcc5dd0cb15dd064a20'
	nums = 300
	indexs = [20,40,60,80]

	def get_fos(nums, ids):
		users = []
		for index in indexs:
			print 'index is :'+str(index)
			fo_url = 'http://www.zhihu.com/node/ProfileFolloweesListV2'
			m_data = {
				'method':'next',
				'params':'{"offset":'+ str(index) + ',"order_by":"created","hash_id":"'+ ids +'"}',
				'_xsrf':xsrf
			}
			res = s.post(fo_url, headers = headers_base, cookies = m_cookies, data = m_data)
			print res.status_code
			print 'res content is :\n'+res.text
			#soup = BeautifulSoup(res.text, 'html.parser')
			#for link in soup.find_all('a', 'zm-item-link-avatar'):
			#users.append(link.get('title'))
		return users
	
	users = get_fos(nums, ids)
	#print 'the follower is:\n'
	#print len(users)
	#for user in users:
	#	print user
	pass

def work():
	login()
	pass

if __name__ == '__main__':
	work()
