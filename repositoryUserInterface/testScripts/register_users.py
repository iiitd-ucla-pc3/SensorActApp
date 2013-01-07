import urllib,urllib2,subprocess,time,csv,sys

try:
	import js
except ImportError:
	import simplejson as json

url="http://localhost:9000/user/register"
url2="http://localhost:9000/user/login"
url3="http://localhost:9000/device/add"

registered_users = csv.writer(open('registered_users.csv', 'wb'), delimiter=',')

obj={}
obj["password"]="password"


obj2={}
obj2["password"]="password"

#Creating device
obj3={}

room_number=0
NUMBER_ROOMS=20

while room_number<NUMBER_ROOMS :
	
	try:
		obj["username"]="username"+str(room_number)
		obj["email"]="email" +str(room_number) + "@gmail.com"
		json_data=json.dumps(obj)
		post_data = json_data.encode('utf-8')
		req = urllib2.Request(url, post_data, {'Content-Type': 'application/json'})
		f = urllib2.urlopen(req)
		response = f.read()
		print response
		a=json.loads(response)
		f.close()
	except:
		print "Error occured.Connection refused"
	room_number=room_number+1

room_number=0
while room_number<NUMBER_ROOMS :
	
	try:
		obj2["username"]="username"+str(room_number)
		json_data=json.dumps(obj2)
		post_data = json_data.encode('utf-8')
		req = urllib2.Request(url2, post_data, {'Content-Type': 'application/json'})
		f = urllib2.urlopen(req)
		response = f.read()
		print response
		a=json.loads(response)["message"]
		registered_users.writerow([str(obj2["username"])]+[a])
		f.close()
		
		#Creating device now
		obj3["secretkey"]=a
		obj3["deviceprofile"]={}
		obj3["deviceprofile"]["devicename"]="VirtualDevice"
		obj3["deviceprofile"]["ip"]="10.0.0.1"
		obj3["deviceprofile"]["location"]="virtual"
		obj3["deviceprofile"]["tags"]="Virtual"
		obj3["deviceprofile"]["latitude"]=0
		obj3["deviceprofile"]["longitude"]=0
		obj3["deviceprofile"]["sensors"]=[]
		obj3["deviceprofile"]["sensors"].append({})
		obj3["deviceprofile"]["sensors"][0]["name"]="Sensor"
		obj3["deviceprofile"]["sensors"][0]["sid"]=1
		

		obj3["deviceprofile"]["sensors"][0]["channels"]=[]
		obj3["deviceprofile"]["sensors"][0]["channels"].append({})
		obj3["deviceprofile"]["sensors"][0]["channels"].append({})
		obj3["deviceprofile"]["sensors"][0]["channels"][0]["name"]="channel0"
		obj3["deviceprofile"]["sensors"][0]["channels"][0]["type"]="none"
		obj3["deviceprofile"]["sensors"][0]["channels"][0]["unit"]="none"
		obj3["deviceprofile"]["sensors"][0]["channels"][0]["samplingperiod"]=1

		obj3["deviceprofile"]["sensors"][0]["channels"][1]["name"]="channel1"
		obj3["deviceprofile"]["sensors"][0]["channels"][1]["unit"]="none"
		obj3["deviceprofile"]["sensors"][0]["channels"][1]["type"]="none"
		obj3["deviceprofile"]["sensors"][0]["channels"][1]["samplingperiod"]=1

		
		
		json_data=json.dumps(obj3)
		print json_data
		
		post_data = json_data.encode('utf-8')
		print post_data
		req = urllib2.Request(url3, post_data, {'Content-Type': 'application/json'})
		f = urllib2.urlopen(req)
		response = f.read()
		print response

		
		
		
		
		
		
		
	except:
		print "Error occured.Connection refused"
	room_number=room_number+1
