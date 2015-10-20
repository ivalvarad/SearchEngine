import scrapy

from arana.items import DmozItem

class DmozSpider(scrapy.Spider):
    name = "dmoz"
    allowed_domains = ["en.wikipedia.org"]
    start_urls = [
        "https://en.wikipedia.org/wiki/Main_Page",
		"https://en.wikipedia.org/wiki/Portal"
    ]

    def parse(self, response):
		for href in response.css("a::attr('href')"):
			url = response.urljoin(href.extract())
			yield scrapy.Request(url, callback=self.parse_dir_contents)

    def parse_dir_contents(self, response):
		item = DmozItem()
		item['texto'] = response.body
		yield item
	