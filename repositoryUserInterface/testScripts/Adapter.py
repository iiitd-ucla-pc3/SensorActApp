import urllib,urllib2
try:
	import json
except ImportError:
	import simplejson as json

class SensorActAdapter:
	
	def __init__(self,deviceName,sensorName):
		self.obj={}
		self.obj["dname"]=deviceName
		self.obj["sname"]=sensorName
		self.obj["data"]={}
		self.obj["data"]["channels"]=[]
		
	def put(self,key,value):
		self.obj[key]=value
		
	def printWavesegment(self):
		print json.dumps(self.obj)
		
	def addSecretKey(self,secretKey):
		self.put("secretkey",secretKey)
		
	def addChannel(self,channelName,channelUnit):
		size=len(self.obj["data"]["channels"])
		self.obj["data"]["channels"].append({})
		self.obj["data"]["channels"][size]["cname"]=channelName
		self.obj["data"]["channels"][size]["unit"]=channelUnit
		self.obj["data"]["channels"][size]["readings"]=[]
		
	def addReadings(self,channelName,startTime,readingsArray):
		for channel in self.obj["data"]["channels"]:
			if channel["cname"]==channelName:
				 channel["readings"]=readingsArray
				 break
		self.obj["timestamp"]=startTime
		
	def sendWavesegment(self,url):
		post_data = json.dumps(self.obj).encode('utf-8')
		try:
			req = urllib2.Request(url, post_data, {'Content-Type': 'application/json'})
			f = urllib2.urlopen(req)
			response = f.read()
			f.close()
		
		except:		
			print "Request could not be made"
	

#Example Usage

a=SensorActAdapter("device","multisensor")
a.addSecretKey("123")
a.put("deviceid",12)
a.addChannel("temp","Cel")
a.addChannel("pir","none")
a.addReadings("temp",1234,[1,2,3,4,5])
a.addReadings("pir",1234,[0,1,1,1,0])

a.printWavesegment()
a.sendWavesegment("http://localhost:9000/data/upload/wavesegment")
