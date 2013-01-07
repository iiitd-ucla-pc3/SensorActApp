import csv,subprocess
f = open('registered_users1.csv', 'rt')
reader = csv.reader(f)
count=0
for row in reader:
	if count<2:
		a="python data_upload_simulation.py "+row[1]
		subprocess.call(a, executable="bash", shell=True)
		count=count+1
	else:
		break
