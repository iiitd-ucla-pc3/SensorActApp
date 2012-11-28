import json,urllib,urllib2,subprocess,time,csv,sys,random

#Upload URL
url="http://localhost:9000/data/upload/wavesegment"

                             

file_object=open(sys.argv[1]+".csv", 'w')
reg = csv.writer(file_object, delimiter=',')
obj={}
obj["secretkey"]=sys.argv[1]
obj["data"]={}
obj["data"]["location"]="IIIT"
obj["data"]["dname"]="VirtualDevice"
obj["data"]["sname"]="Sensor"
obj["data"]["sid"]=1
obj["data"]["channels"]=[]
obj["data"]["channels"].append({})
obj["data"]["channels"].append({})


for x in range(0,2):
	obj["data"]["channels"][x]["cname"]="Channel_"+str(x)
	obj["data"]["channels"][x]["unit"]="none"


while 1:
	readings_core0=[]
	readings_core1=[]
	
	obj["data"]["timestamp"]=time.time()
	for x in range(0,10):
		readings_core0.append(random.randint(0,100))
		readings_core1.append(random.randint(0,100))
		time.sleep(1)		
	obj["data"]["channels"][0]["readings"]=readings_core0
	obj["data"]["channels"][1]["readings"]=readings_core1
	
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
	
	

		
	
		
	

