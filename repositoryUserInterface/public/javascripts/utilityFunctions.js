/*
 * This JS file consists of all the utlity functions which
 * are required
 */

/*
 * Description:Converts datetime from the UI format to epoch
 * Input:Date Time from the UI in the following format 2012-04-18-14:33
 * Output:Date Time in epoch format,such as 1334740020
 */
function datetimeToEpoch(datetime) {
	var datetime = datetime.split('-');
	var year = datetime[0];
	var month = datetime[1] - 1;
	//Month index starts from 1
	var day = datetime[2];
	var hour_mins = datetime[3].split(':');
	var mins = hour_mins[1];
	var hours = hour_mins[0];
	var datetime_epoch = new Date(year, month, day, hours, mins).getTime() / 1000;
	return datetime_epoch;
}

 

/*
 *  To generate a 10 character long unique string for filenames
 */
function uniqueRandomString()
{
    var text = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < 10; i++ )
        text += possible.charAt(Math.floor(Math.random() * possible.length));

    return text;
}


/*
*	To add navigation links to elements like devices,visualisation,logout,etc
*	Eg : setNavURL("#devices",'href',URL_DEVICES)
*/
function setNavURL(selector ,attr , attrValue)
{
			$(selector).attr(attr , attrValue);
}

function errorInitialSettings(objectReference,value,errorState)
{
						$(objectReference).addClass("error");
						errorState = true;
						value++;
						$(objectReference).attr('count', value);
						errorInformation = "";

}



/*
 * Function to show noty
 * parameters:
 * 1.Noty Text
 * 2.Noty Position
 * 3.Noty Has Close button or not
 *
 * returns id of noty
 */

function showNoty(text, position, closeButton) {
	var noty_id = noty({
		"text" : text,
		"theme" : "noty_theme_twitter",
		"layout" : position,
		"type" : "error",
		"animateOpen" : {
			"height" : "toggle"
		},
		"animateClose" : {
			"height" : "toggle"
		},
		"speed" : 0,
		"timeout" : 0,
		"closeButton" : closeButton,
		"closeOnSelfClick" : true,
		"closeOnSelfOver" : false,
		"modal" : true
	});

	return noty_id;
}

function removeSpace(text){
	text=text.replace(/\s/g, "");
return text;
}

function createDateTimePicker(selector){

	$(selector).datetimepicker({
					changeMonth : true,
					changeYear : true,
					dateFormat : "yy-mm-dd",
					separator : "-"
				});
					
}