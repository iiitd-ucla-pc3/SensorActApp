import os
import re

abs_url_name=os.path.dirname(os.path.dirname(os.getcwd()))
############# -------------- Input ---------------############

#abs_url_name = raw_input('Please enter the absolute path of the directory where you wish to save the SensorActApp project:(Don\'t put a slash at the end eg:/home/directoryName/iiitd) : ');

url_var = raw_input('Please enter the url where you wish to host this web application (Don\'t put a slash at the end eg:http://192.168.1.3) : ');

port_var = raw_input('Please enter the port number for Application eg. 9003 : ');

repository_var = raw_input('Please enter the repository url (Don\'t put a slash at the end eg:http://192.168.1.2) : ');

repository_port_var = raw_input('Please enter the repository port number eg. 9001: ');






########## ------------- Const.java -------------##############

afile = "app/edu/iiitd/muc/sensoract/constants/Const.java";
#afile = "home/Const.java";
os.rename( afile, afile+"~" )


destination= open( afile, "w+" )
source= open( afile+"~", "ra" )
for line in source:
	if re.search('BASE_WAVEFILE_URL',line):
		sub = re.sub('public static final String BASE_WAVEFILE_URL = \"[a-zA-Z/0-9]*/SensorActApp/repositoryUserInterface/app/edu/cmu/sphinx/demo/transcriber/\";','public static final String BASE_WAVEFILE_URL = "'+abs_url_name+'/SensorActApp/repositoryUserInterface/app/edu/cmu/sphinx/demo/transcriber/";',line,count=1);
		print "BASE_WAVEFILE_URL match occured";
		if(sub):    		
			print sub,"replacement BASE_WAVEFILE_URL successful"		
			destination.write( sub )	
			
	elif re.search('BASE_IMAGE_URL',line):
		sub = re.sub('public static final String BASE_IMAGE_URL = \"[a-zA-Z/0-9]*/SensorActApp/repositoryUserInterface/public/\";','public static final String BASE_IMAGE_URL = "'+abs_url_name+'/SensorActApp/repositoryUserInterface/public/";',line,count=1);
		print "BASE_IMAGE_URL match occured";
		if(sub):    		
			print sub,"replacement BASE_IMAGE_URL successful"		
			destination.write( sub )
########################## --------------------- url_formation ==> URL_UI_SERVER ------------------------ ##########
	elif re.search('String URL_UI_SERVER',line):
		if(port_var!="1"):		
			sub = re.sub('public static final String URL_UI_SERVER = \"[a-zA-Z/.\:0-9]*\";','public static final String URL_UI_SERVER = "'+url_var+':'+port_var+'/";',line,count=1);
			print "URL_UI_SERVER match occured";
		if(sub):    		
			print sub,"replacement successful"		
			destination.write( sub )
	elif re.search('String URL_REPOSITORY_SERVER',line):
		if(port_var!="1"):		
			sub = re.sub('public static final String URL_REPOSITORY_SERVER = \"[a-zA-Z/.\:0-9]*\";','public static final String URL_REPOSITORY_SERVER = "'+repository_var+':'+repository_port_var+'/";',line,count=1);
			print "URL_UI_SERVER match occured";
		if(sub):    		
			print sub,"replacement successful"		
			destination.write( sub )
	else:
		#print line,"normal line"			
		destination.write(line)
	
source.close()
destination.close()






########## ***************************************** ##############
########## ------------- for config.xml -------------##############
########## ***************************************** ##############


afile1 = "app/edu/cmu/sphinx/demo/transcriber/config.xml";

#afile1 = "home/config.xml";
os.rename( afile1, afile1+"~" )



destination= open( afile1, "w+" )
source= open( afile1+"~", "ra" )
for line in source:
	if re.search('6428.lm.DMP',line):
		sub = re.sub('<property name=\"location\" value=\"file:[a-zA-Z/0-9]*/SensorActApp/repositoryUserInterface/public/soundModels/6428.lm.DMP\"/>','<property name="location" value="file:'+ abs_url_name +'/SensorActApp/repositoryUserInterface/public/soundModels/6428.lm.DMP"/>',line,count=1);
		print " lang_model match occured";
		if(sub):    		
			print sub,"lang_model replacement successful"		
			destination.write( sub )
	elif re.search('6428.dic',line):
		sub = re.sub('value=\"file:[a-zA-Z/0-9]*/SensorActApp/repositoryUserInterface/public/soundModels/6428.dic\"/>','value="file:'+ abs_url_name + '/SensorActApp/repositoryUserInterface/public/soundModels/6428.dic"/>',line,count=1);
		print "dictionary match occured";
		if(sub):    		
			print sub,"dict replacement successful"		
			destination.write( sub )
	elif re.search('model_params/ind_eng.ci_cont/noisedict',line):
		sub = re.sub('value=\"file:[a-zA-Z/0-9]*/SensorActApp/repositoryUserInterface/public/soundModels/model_params/ind_eng.ci_cont/noisedict\"/>','value="file:'+abs_url_name+'/SensorActApp/repositoryUserInterface/public/soundModels/model_params/ind_eng.ci_cont/noisedict"/>',line,count=1);
		print "filler match occured";
		if(sub):    		
			print sub,"filler dict replacement successful"		
			destination.write( sub )
	elif re.search('model_params/ind_eng.ci_cont',line):
		#sub = "";
		sub = re.sub('<property name=\"location\" value=\"file:[a-zA-Z/0-9]*/SensorActApp/repositoryUserInterface/public/soundModels/model_params/ind_eng.ci_cont\"/>','<property name="location" value="file:'+abs_url_name+'/SensorActApp/repositoryUserInterface/public/soundModels/model_params/ind_eng.ci_cont"/>',line,count=1);
		print "acoustic match occured";
		#sub=1;
		if(sub):    		
			print sub,"acoustic model replacement successful"		
			destination.write( sub )
	else:
		#print line,"normal line"			
		destination.write(line)
	
source.close()
destination.close()


############################## application.conf ############################

afile2 = "conf/application.conf";

#afile2 = "home/application.conf";
os.rename( afile2, afile2+"~" )



destination= open( afile2, "w+" )
source= open( afile2+"~", "ra" )
#sub = 0;
for line in source:
	if re.search('http.port',line):
		#sub = "";
		sub = re.sub('http.port=[0-9]*','http.port='+port_var,line,count=1);
		print "application.conf port match occured";
		#sub=1;
		if(sub):    		
			print sub,"applicaiton.conf replacement successful"		
			destination.write( sub )
	else:
		#print line,"normal line"			
		destination.write(line)
	
source.close()
destination.close()

	
################################ constants.js ####################################

afile3 = "public/javascripts/constants.js";

#afile2 = "home/constants.js";
os.rename( afile3, afile3+"~" )



destination= open( afile3, "w+" )
source= open( afile3+"~", "ra" )
#sub = 0;
for line in source:
	if re.search('var URL_UI_SERVER',line):
		#sub = "";
		sub = re.sub('var URL_UI_SERVER=\"[A-Za-z/.:_0-9]*\";','var URL_UI_SERVER="'+url_var+':'+port_var+'/";',line,count=1);
		print "constants.js match occured";
		#sub=1;
		if(sub):    		
			print sub,"constants.js replacement successful"		
			destination.write( sub )
	else:
		#print line,"normal line"			
		destination.write(line)
	
source.close()
destination.close()
