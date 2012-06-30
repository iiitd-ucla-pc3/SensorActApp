import os
import re

########## ------------- for WavfileURl in const.java -------------##############
#afile = "app/edu/iiitd/muc/sensoract/constants/Const.java";
afile = "home/Const.java";
os.rename( afile, afile+"~" )

abs_url_name = raw_input('Please enter the absolute path of the directory where you wish to save the SensorActApp project:(Don\'t put a slash at the end eg:/home/directoryName/iiitd)');

url_var = raw_input('Please enter the url where you wish to host this web application (Don\'t put a slash at the end eg:http://localhost):');

port_var = raw_input('Please enter the port number if any(Enter 1 in case of no port):');


destination= open( afile, "w+" )
source= open( afile+"~", "ra" )
#sub = 0;
for line in source:
	if re.search('BASE_WAVEFILE_URL',line):
		#sub = "";
		sub = re.sub('public static final String BASE_WAVEFILE_URL = \"[a-zA-Z/0-9]*/SensorActApp/repositoryUserInterface/app/edu/cmu/sphinx/demo/transcriber/\";','public static final String BASE_WAVEFILE_URL = "'+abs_url_name+'/SensorActApp/repositoryUserInterface/app/edu/cmu/sphinx/demo/transcriber/";',line,count=1);
		print "match occured";
		#sub=1;
		if(sub):    		
			print sub,"replacement successful"		
			destination.write( sub )
	##########################url_formation##########
	elif re.search('URL_UI_SERVER',line):
		if(port_var!="1"):		
			sub = re.sub('public static final String URL_UI_SERVER = \"[a-zA-Z/\:0-9]*\";','public static final String URL_UI_SERVER = "'+url_var+':'+port_var+'";',line,count=1);
			print "url match occured";
			#sub=1;
		if(sub):    		
			print sub,"replacement successful"		
			destination.write( sub )
	else:
		print line,"normal line"			
		destination.write(line)
	
source.close()
destination.close()

########## ------------- for config.xml -------------##############

#afile = "app/edu/cmu/sphinx/demo/transcriber/config.xml";

afile1 = "home/config.xml";
os.rename( afile1, afile1+"~" )



destination= open( afile, "w+" )
source= open( afile+"~", "ra" )
#sub = 0;
for line in source:
	if re.search('6428.lm.DMP',line):
		#sub = "";
		sub = re.sub('<property name=\"location\" value=\"file:[a-zA-Z/0-9]*/SensorActApp/repositoryUserInterface/public/soundModels/6428.lm.DMP\"/>','<property name="location" value="file:'+ abs_url_name +'/SensorActApp/repositoryUserInterface/public/soundModels/6428.lm.DMP"/>',line,count=1);
		print "match occured";
		#sub=1;
		if(sub):    		
			print sub,"lang_model replacement successful"		
			destination.write( sub )
	elif re.search('6428.dict',line):
		#sub = "";
		sub = re.sub('<property name=\"dictionaryPath\" value=\"file:[a-zA-Z/0-9]*/SensorActApp/repositoryUserInterface/public/soundModels/6428.dict\"/>','<property name="dictionaryPath" value="file:'+ abs_url_name + '/SensorActApp/repositoryUserInterface/public/soundModels/6428.dict"/>',line,count=1);
		print "dictionary match occured";
		#sub=1;
		if(sub):    		
			print sub,"dict replacement successful"		
			destination.write( sub )
	elif re.search('model_params/ind_eng.ci_cont/noisedict',line):
		#sub = "";
		sub = re.sub('<property name=\"fillerPath\" value=\"file:[a-zA-Z/0-9]*/model_params/ind_eng.ci_cont/noisedict\"/>','<property name="fillerPath" value="file:'+abs_url_name+'/model_params/ind_eng.ci_cont/noisedict"/>',line,count=1);
		print "filler match occured";
		#sub=1;
		if(sub):    		
			print sub,"dict replacement successful"		
			destination.write( sub )
	elif re.search('model_params/ind_eng.ci_cont',line):
		#sub = "";
		sub = re.sub('<property name=\"location\" value=\"file:[a-zA-Z/0-9]*/model_params/ind_eng.ci_cont\"/>','        <property name="location" value="file:'+abs_url_name+'/model_params/ind_eng.ci_cont/"/>',line,count=1);
		print "acoustic match occured";
		#sub=1;
		if(sub):    		
			print sub,"dict replacement successful"		
			destination.write( sub )
	else:
		print line,"normal line"			
		destination.write(line)
	
source.close()
destination.close()



