/*
 * This JS file consists of all the utlity functions which 
 * are required 
 */


/*
 * Description:Converts datetime from the UI format to epoch
 * Input:Date Time from the UI in the following format 2012-04-18-14:33
 * Output:Date Time in epoch format,such as 1334740020
 */
function datetimeToEpoch(datetime)
		{
		var datetime=datetime.split('-');
		var year=datetime[0];
		var month=datetime[1]-1;//Month index starts from 1
		var day=datetime[2];
		var hour_mins=datetime[3].split(':');
		var mins=hour_mins[1];
		var hours=hour_mins[0];
		var datetime_epoch=new Date(year,month,day,hours,mins).getTime()/1000;
		return datetime_epoch;				
		}