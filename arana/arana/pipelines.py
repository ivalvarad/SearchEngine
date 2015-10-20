# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html

import json

class AranaPipeline(object):
    
	nombre = 1
	
	def process_item(self, item, spider):
		self.file = open('../paginas/' + str(self.nombre) + '.txt', 'wb')
		self.nombre += 1;
		line = item['texto'] + "\n"
		self.file.write(line)
		return item
