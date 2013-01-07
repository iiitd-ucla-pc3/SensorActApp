import urllib,urllib2,subprocess,time,csv,sys,random

try:
	import json
except ImportError:
	import simplejson as json

#Upload URL
url="http://localhost:9000/data/upload/wavesegment"

                             

file_object=open(sys.argv[1]+".csv", 'w')
reg = csv.writer(file_object, delimiter=',')
obj={}
obj["secretkey"]=sys.argv[1]
obj["data"]={}
obj["data"]["location"]="IIITD"
obj["data"]["dname"]="FlyPort"
obj["data"]["sname"]="PIRSensor"
obj["data"]["sid"]=1
obj["data"]["channels"]=[]
obj["data"]["channels"].append({})
#obj["data"]["channels"].append({})
obj["data"]["channels"][0]["cname"]="Channel_1"
obj["data"]["channels"][0]["unit"]="none"

packet_5=[[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[1,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,1,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,1,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[0,0,0,0,0,0,0,0,0,0],[1,0,0,0,0,0,0,0,0,0]]
packet_6=[[0,0,1,0,1,0,0,0,1,0],[0,1,0,0,1,0,0,1,0,0],[0,1,0,0,1,0,0,1,0,0],[0,0,0,0,1,0,0,1,0,0],[0,0,0,1,1,0,0,1,1,1],[1,1,0,0,0,0,1,0,1,0],[0,0,1,1,1,1,0,0,0,0],[0,1,1,1,1,1,1,0,0,0],[0,0,0,1,0,1,0,0,0,0],[0,0,1,1,1,1,1,0,0,0],[0,0,0,0,0,1,1,1,1,1],[1,1,1,1,1,0,0,0,0,0]]

while 1:
	readings_core0=[]
	readings_core1=[]
	
	obj["data"]["timestamp"]=time.time()
	#for x in range(5,7):
	readings_core0 = packet_5[random.randint(0,11)]
	readings_core1 = packet_6[random.randint(0,11)]
		#readings_core1.append(random.randint(0,100))
	time.sleep(10)
	if(random.randint(0,2) == 1):		
		obj["data"]["channels"][0]["readings"]=readings_core1
	else:
		obj["data"]["channels"][0]["readings"]=readings_core1
	print obj["data"]["channels"][0]["readings"]
	#obj["data"]["channels"][1]["readings"]=readings_core1
	
	json_data=json.dumps(obj)
	post_data = json_data.encode('utf-8')
	
	start_time=time.time()
	

	try:
		req = urllib2.Request(url, post_data, {'Content-Type': 'application/json'})
		f = urllib2.urlopen(req)
		response = f.read()
		f.close()
		written=1
	except:
		
		written=0
	
	

		
	
		
	

